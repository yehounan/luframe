package com.yezi.luframe.thirdlogin.qq;

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
 * QQ第三方登录
 *
 * @author yezi
 * @date 2019/3/6 17:45
 */
@Slf4j
@RestController
public class QQLoginController {


    @Value("${user.api.url}")
    private String userApiUrl = "";

    @Value("${home.url}")
    private String homeUrl = "";

    @Value("${qq.third.appid}")
    private String qqAppid = "";

    @Value("${qq.third.appkey}")
    private String qqAppkey = "";

    /**
     * QQ登录获取AuthorizationCode
     *
     * @param request{homeUrl}
     * @param response
     */
    @RequestMapping(value = "/qq/login", method = RequestMethod.GET)
    public JsonResult getQQLoginUrl(HttpServletRequest request, HttpServletResponse response) {
        log.info("QQ登录获取AuthorizationCode-参数！");
        Map<String, String> params = new HashMap<>(4);
        // 授权类型，此值固定为“code”。
        params.put("response_type", "code");
        // 申请QQ登录成功后，分配给应用的appid。
        params.put("client_id", qqAppid);
        String state = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "");
        jsonObject.put("state", state);
        jsonObject.put("ip", IpUtil.getIpAddr(request));
        jsonObject.put("homeUrl", ServletRequestUtils.getStringParameter(request, "homeUrl", ""));
        //todo 随机state存入redis
        // 成功授权后的回调地址，必须是注册appid时填写的主域名下的地址，建议设置为网站首页或网站的用户中心。注意需要将url进行URLEncode。
        params.put("redirect_uri", userApiUrl + "/qq/login/callback");
        // client端的状态值。用于第三方应用防止CSRF攻击，成功授权后回调时会原样带回。请务必严格按照流程检查用户与state参数状态的绑定。
        params.put("state", state);
        // 请求用户授权时向用户显示的可进行授权的列表。
        // params.put("scope", "")
        // 仅PC网站接入时使用。用于展示的样式。不传则默认展示为PC下的样式。如果传入“mobile”，则展示为mobile端下的样式。
        // params.put("display", "")
        // 仅WAP网站接入时使用。QQ登录页面版本（1：wml版本； 2：xhtml版本），默认值为1。
        // params.put("g_ut", "")
        try {
            String url = "https://graph.qq.com/oauth2.0/authorize?" + getUrlParamsByMap(params);
            log.info("QQ登录获取AuthorizationCode-返回：{url:" + url + "}");
            response.sendRedirect(url);
        } catch (Exception e) {
            log.info("QQ登录获取AuthorizationCode-失败！", e);
        }
        return new JsonResult().OK();
    }

    /**
     * QQ登录回调
     *
     * @param request{code,state}
     * @param response
     */
    @RequestMapping(value = "/qq/login/callback", method = RequestMethod.GET)
    public JsonResult loginQQCallback(HttpServletRequest request, HttpServletResponse response) {
        // 获取Authorization Code
        String authorizationCode = ServletRequestUtils.getStringParameter(request, "code", "");
        // client端的状态值。用于第三方应用防止CSRF攻击，成功授权后回调时会原样带回。请务必严格按照流程检查用户与state参数状态的绑定。
        String state = ServletRequestUtils.getStringParameter(request, "state", "");
        log.info("QQ登录回调-参数：{code:" + authorizationCode + ",state:" + state + "}");
        try {
            //todo 获取redis中state与client传送state验证比对
            String redisState = null;
            // 通过Authorization Code获取Access Token
            String url = "https://graph.qq.com/oauth2.0/token?";
            Map<String, String> params = new HashMap<>(8);
            // 授权类型，在本步骤中，此值为“authorization_code”。
            params.put("grant_type", "authorization_code");
            // 申请QQ登录成功后，分配给网站的appid。
            params.put("client_id", qqAppid);
            // 申请QQ登录成功后，分配给网站的appkey。
            params.put("client_secret", qqAppkey);
            // authorization code 注意此code会在10分钟内过期。
            params.put("code", authorizationCode);
            // 成功授权后的回调地址，必须是注册appid时填写的主域名下的地址，建议设置为网站首页或网站的用户中心。注意需要将url进行URLEncode。
            params.put("redirect_uri", userApiUrl + "/qq/login/callback");
            log.info("QQ登录回调-获取AccessToken-参数：" + JSONObject.toJSONString(params));
            String result = Jsoup.connect(url + getUrlParamsByMap(params)).ignoreContentType(true).execute().body();
            log.info("QQ登录回调-获取AccessToken-结果：" + result);
            String splitChar = "&";
            if (StringUtils.isNotEmpty(result) && result.contains(splitChar)) {
                String accessToken = result.split("&")[0].split("=")[1];
                // 使用Access Token来获取用户的OpenID
                url = "https://graph.qq.com/oauth2.0/me?";
                params = new HashMap<>(4);
                params.put("access_token", accessToken);
                log.info("QQ登录回调-使用Access Token来获取用户的OpenID-参数：" + JSONObject.toJSONString(params));
                result = Jsoup.connect(url + getUrlParamsByMap(params)).ignoreContentType(true).execute().body();
                log.info("QQ登录回调-使用Access Token来获取用户的OpenID-结果：" + result);
                String openid = JSONObject.parseObject(result).getString("openid");
                if (StringUtils.isNotEmpty(openid)) {
                    // 获取用户个人信息
                    url = "https://graph.qq.com/user/get_user_info?";
                    params = new HashMap<>(4);
                    params.put("access_token", accessToken);
                    // 申请QQ登录成功后，分配给应用的appid
                    params.put("oauth_consumer_key", qqAppid);
                    // 用户的ID，与QQ号码一一对应。
                    params.put("openid", openid);
                    log.info("QQ登录回调-获取用户个人信息-参数：" + JSONObject.toJSONString(params));
                    result = Jsoup.connect(url + getUrlParamsByMap(params)).ignoreContentType(true).execute().body();
                    JSONObject userInfoJson = JSONObject.parseObject(result);
                    log.info("QQ登录回调-获取用户个人信息-结果：" + userInfoJson);
                    //todo 绑定QQ用户操作
                    // 授权成功跳转到个人中心首页
                    log.info("QQ登录回调-返回：{url:" + url + "}");
                    response.sendRedirect(homeUrl);
                    return new JsonResult().OK();
                }
            }
        } catch (Exception e) {
            log.info("QQ登录回调-失败！", e);
        }
        try {
            // 授权失败跳转到个人中心首页
            response.sendRedirect(homeUrl);
        } catch (Exception e) {
            log.error("QQ登录回调-授权失败", e);
        }
        return new JsonResult().OK();
    }

    /**
     * QQ APP登录回调
     *
     * @param request{accessToken,openId,homeUrl}
     * @param response
     * @return
     */
    @RequestMapping(value = "/qq/app/login/callback", method = RequestMethod.GET)
    public JsonResult loginQQAppCallback(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = ServletRequestUtils.getStringParameter(request, "accessToken", "");
        String openId = ServletRequestUtils.getStringParameter(request, "openId", "");
        String homeUrl = ServletRequestUtils.getStringParameter(request, "homeUrl", "");
        log.info("QQ APP登录回调-参数：{accessToken:" + accessToken + ",openId:" + openId + "}");
        try {
            // 使用Access Token来获取用户的OpenID
            String url = "https://graph.qq.com/oauth2.0/me";
            Map<String, String> params = new HashMap<>(4);
            params.put("access_token", accessToken);
            String result = Jsoup.connect(url + "?" + getUrlParamsByMap(params)).ignoreContentType(true).execute().body();
            log.info(result);
            String openid = JSONObject.parseObject(result).getString("openid");
            if (StringUtils.isNotEmpty(openid)) {
                String ip = IpUtil.getIpAddr(request);
                String state = UUID.randomUUID().toString().replace("-", "").toUpperCase();
                JSONObject userInfo = new JSONObject();
                userInfo.put("qqOpenid", openId);
                userInfo.put("userType", "qq");
                userInfo.put("ip", ip);
                userInfo.put("state", state);
                JsonResult<JSONObject> jsonResult = new JsonResult().OK(userInfo);
                log.info("QQ APP登录回调-返回：" + jsonResult);
                return jsonResult;
            }
        } catch (Exception e) {
            log.info("QQ APP登录回调-失败！", e);
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
