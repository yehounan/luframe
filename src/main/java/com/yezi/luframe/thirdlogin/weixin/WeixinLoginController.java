package com.yezi.luframe.thirdlogin.weixin;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.yezi.luframe.util.IpUtil;
import com.yezi.luframe.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 微信第三方登录控制器
 * 订阅号。。。无法获取授权，草他吗!!!!
 * @author yezi
 * @date 2019/3/6 16:42
 */
@Slf4j
@RestController
public class WeixinLoginController {


    @Value("${user.api.url}")
    private String userApiUrl = "";

    @Value("${home.url}")
    private String homeUrl = "";

    @Value("${weixin.third.appid}")
    private String weixinAppid = "";

    @Value("${weixin.third.appkey}")
    private String weixinAppkey = "";

    /**
     * 微信登录获取AuthorizationCode
     *
     * @param request{homeUrl}
     * @param response
     */
    @RequestMapping(value = "/weixin/login", method = RequestMethod.GET)
    public JsonResult getWeixinLoginUrl(HttpServletRequest request, HttpServletResponse response) {
        try {
            log.info("微信登录获取AuthorizationCode-参数！");
            Map<String, String> params = new HashMap<>(8);
            // 应用唯一标识
            params.put("appid", weixinAppid);
            // 填code
            params.put("response_type", "code");
            String state = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", "");
            jsonObject.put("state", state);
            jsonObject.put("ip", IpUtil.getIpAddr(request));
            //todo  state随机数存入redis缓存以待验证
            // 请使用urlEncode对链接进行处理
            params.put("redirect_uri", userApiUrl + "/weixin/login/callback");
            // 应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
            params.put("scope", "snsapi_login");
            // 用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
            params.put("state", state);
            String url = "https://open.weixin.qq.com/connect/qrconnect?" + getUrlParamsByMap(params);
            log.info("微信登录获取AuthorizationCode-返回：{url:" + url + "}");
            response.sendRedirect(url);
        } catch (Exception e) {
            log.info("微信登录获取AuthorizationCode-失败！", e);
        }
        return new JsonResult().OK();
    }

    /**
     * 微信登录回调
     *
     * @param request{code,state,client}
     * @param response
     * @return
     */
    @RequestMapping(value = "/weixin/login/callback", method = RequestMethod.GET)
    public JsonResult loginWeixinCallback(HttpServletRequest request, HttpServletResponse response) {
        // 获取Authorization Code
        // 若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数
        String authorizationCode = ServletRequestUtils.getStringParameter(request, "code", "");
        String state = ServletRequestUtils.getStringParameter(request, "state", "");
        log.info("微信登录回调-参数：{code:" + authorizationCode + ",state:" + state + "}");
        try {
            //todo 验证redis中获取的随机数
            String redisState = null;
            // 通过Authorization Code获取Access Token
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
            Map<String, String> params = new HashMap<>(8);
            // 应用唯一标识，在微信开放平台提交应用审核通过后获得
            params.put("appid", weixinAppid);
            // 应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
            params.put("secret", weixinAppkey);
            // 填写第一步获取的code参数
            params.put("code", authorizationCode);
            // 填authorization_code
            params.put("grant_type", "authorization_code");
            log.info("微信登录回调-获取AccessToken-参数：" + JSONObject.toJSONString(params));
            String result = Jsoup.connect(url + getUrlParamsByMap(params)).ignoreContentType(true).execute().body();
            log.info("微信登录回调-获取AccessToken-结果：" + result);
            if (StringUtils.isNotEmpty(result)) {
                JSONObject accessTokenJson = JSONObject.parseObject(result);
                String unionid = accessTokenJson.getString("unionid");
                String openid = accessTokenJson.getString("openid");
                log.info("openid:{},unionid:{}", openid, unionid);
                if (StringUtils.isNotEmpty(unionid)) {
                    // 获取用户个人信息
                    url = "https://api.weixin.qq.com/sns/userinfo?";
                    params = new HashMap<>(4);
                    // 调用凭证
                    params.put("access_token", authorizationCode);
                    // 普通用户的标识，对当前开发者帐号唯一
                    params.put("openid", unionid);
                    // 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为zh-CN
                    params.put("lang", "zh-CN");
                    log.info("微信登录回调-获取用户个人信息-参数：" + JSONObject.toJSONString(params));
                    result = Jsoup.connect(url + getUrlParamsByMap(params)).ignoreContentType(true).execute().body();
                    log.info("微信登录回调-获取用户个人信息-结果：" + result);
                    JSONObject userInfoJson = JSONObject.parseObject(result);
                    JSONObject userInfo = new JSONObject();
                    userInfo.put("weixinUnionid", unionid);
                    userInfo.put("userType", "weixin");
                    userInfo.put("nickname", userInfoJson.getString("nickname"));
                    //todo 绑定微信用户
                    JsonResult jsonResult = new JsonResult().OK(userInfo);
                    log.info("微信登录获取AuthorizationCode-成功：" + JSONObject.toJSONString(jsonResult));
                    // 授权成功跳转到个人中心首页
                    log.info("微信登录获取AuthorizationCode-返回：{url:" + homeUrl + "}");
                    response.sendRedirect(url);
                    return jsonResult;
                }
            }

        } catch (Exception e) {
            log.info("微信登录回调-验证失败！", e);
        }
        log.info("微信登录回调-验证失败！");
        try {
            // 授权失败跳转到登录首页
            response.sendRedirect(homeUrl);
        } catch (Exception e) {
            log.error("微信登录回调-授权失败跳转到登录首页", e);
        }
        return new JsonResult().OK();

    }

    private String getUrlParamsByMap(Map<String, String> params) {
        String url = "";
        Set<Map.Entry<String, String>> set = params.entrySet();
        try {
            for (Map.Entry<String, String> entry : set) {
                url += "&" + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "utf-8");
            }
        } catch (Exception e) {
            log.warn("参数组装错误", e);
        }
        return url.length() > 0 ? url.substring(1) : url;
    }
}
