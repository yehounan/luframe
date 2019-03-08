package com.yezi.luframe.enums;

/**
 * Desc   : 响应码枚举
 *
 * @author : yxy
 * @date : 2017-12-20
 */
public enum CodeEnum {

    SUCCESS(0, "操作成功"),
    /*
     * 1000-1999, 系统预留错误码
     */
    ERROR(1000, "系统开小差了，请稍后再试"),

    ERROR_PARAM(1001, "参数异常"),

    ERROR_TOKEN(1002, "TOKEN无效"),

    ERROR_TOKEN_EXPIRE(1003, "TOKEN已过期"),

    ERROR_REQUEST(1004, "请求异常"),

    ERROR_RATE_LIMIT(1005, "超出访问限制"),
    //=====================================================================个人中心 begin========================================================================//
    /*
     * 2000-2999, 用户模块预留错误码
     */
    USER_ERROR(2000, "user_error"),


    USER_IP_LIMINT(20010, "你没有访问权限"),

    /*
     * 注册、登录、密码：2050-2099
     */
    /**
     * 邮箱格式不正确
     */
    USER_EMAIL_ERROR(2073, "email_error"),

    /**
     * 邮箱已使用
     */
    USER_EMAIL_USED_ERROR(2050, "email_used_error"),

    /**
     * 邮箱未认证
     */
    USER_EMAIL_NOT_VALIDATE_ERROR(2070, "email_not_validate_error"),

    /**
     * 邮箱验证失败
     */
    USER_EMAIL_VALIDATE_ERROR(2051, "email_validate_error"),

    /**
     * 手机号格式不正确
     */
    USER_PHONE_ERROR(2052, "phone_error"),

    /**
     * 短信验证码发送失败
     */
    USER_PHONE_SMS_CODE_SEND_ERROR(2062, "phone_sms_code_send_error"),

    /**
     * 手机号已使用
     */
    USER_PHONE_USED(2053, "phone_used_error"),

    /**
     * 该手机号未注册
     */
    USER_PHONE_NON_EXISTENT(2063, "phone_non_existent_error"),

    /**
     * 用户名已被使用
     */
    USER_USER_NAME_USED_USED(2072, "user_user_name_used_used"),

    /**
     * 身份证已被使用
     */
    USER_ID_USED_ERROR(2071, "id_used_error"),

    /**
     * 图片验证码不正确
     */
    USER_IMAGE_CODE_ERROR(2054, "image_code_error"),

    /**
     * 请添加图片验证码
     */
    USER_NO_IMAGE_CODE_ERROR(2067, "no_image_code_error"),

    /**
     * 短信验证码不正确
     */
    USER_SMS_CODE_ERROR(2055, "sms_code_error"),

    /**
     * 邀请码不存在
     */
    USER_INVITE_CODE_ERROR(2056, "invite_code_error"),

    /**
     * 原密码不正确
     */
    USER_PASSWORD_ERROR(2057, "password_error"),

    /**
     * 账号或密码错误
     */
    USER_NAME_OR_PASSWORD_ERROR(2069, "user_name_or_password_error"),

    /**
     * 密码格式不正确
     */
    // USER_PASSWORD_FORMAT_ERROR(2058,"password_format_error"),

    /**
     * 新密码与旧密码相同
     */
    USER_PASSWORD_NOT_CHANGE_ERROR(2065, "password_not_change_error"),

    /**
     * 用户未登录
     */
    USER_NOT_LOGIN_ERROR(2059, "user_not_login"),

    /**
     * SteamId已注册
     */
    USER_STEAM_ID_USED_ERROR(2060, "steamId_used_error"),

    /**
     * Steam交易链接已被其他用户使用
     */
    USER_STEAM_TRANSACTION_URL_USED_ERROR(2066, "steam_transaction_url_used_error"),

    /**
     * 用户状态冻结异常
     */
    USER_STATUS_FROZEN_ERROR(2061, "user_status_frozen_error"),

    /**
     * 真实姓名为空
     */
    USER_TRUE_NAME_EMPTY_ERROR(2062, "user_true_name_empty_error"),

    /**
     * 不能超过n个字符
     */
    USER_GREATER_THAN_N_CHAR_ERROR(2063, "user_greater_than_n_char_error"),

    /**
     * 该用户未注册
     */
    USER_NOT_EXIST_ERROR(2064, "user_not_exist_error"),

    /**
     * 您请求过于频发，请稍后再试
     */
    USER_REQUEST_FAST_ERROR(2074, "user_request_fast_error"),

    /**
     * 您在一个小时内只可获取三次短信验证码，请稍后再试！
     */
    USER_SMS_HOUR_MORE_ERROR(2068, "user_sms_hour_more_error"),

    /**
     * 您在一天内只可获取十次短信验证码，请明天再试！
     */
    USER_SMS_DAY_MORE_ERROR(2079, "user_sms_day_more_error"),

    /**
     * 用户标签已存在
     */
    USER_TAG_EXIST_ERROR(2075, "user_tag_exist_error"),

    /**
     * 用户邀请码已存在
     */
    USER_USER_INVITE_CODE_EXIST_ERROR(2076, "user_user_invite_code_exist_error"),

    /**
     * 代理人推广码已存在
     */
    USER_AGENT_INVITE_CODE_EXIST_ERROR(2077, "user_agent_invite_code_exist_error"),

    /**
     * 用户资金冻结
     */
    BET_USER_ACCOUNT_FREEZE(2078, "bet_user_account_freeze"),


    // 支付：2800-2899
    /**
     * 当前银行是唯一使用中的银行，不能冻结
     */
    USER_BANK_FROZEN_ONLY_ERROR(2831, "bank_frozen_only_error"),

    /**
     * 不能冻结当前正在使用的银行
     */
    USER_BANK_FROZEN_USED_ERROR(2834, "bank_frozen_used_error"),

    /**
     * 当前银行是唯一使用中的银行，不能删除
     */
    USER_BANK_DELETE_ONLY_ERROR(2832, "bank_delete_only_error"),

    /**
     * 不能删除当前正在使用的银行
     */
    USER_BANK_DELETE_USED_ERROR(2835, "bank_delete_used_error"),

    /**
     * 使用中银行已达100个
     */
    USER_BANK_USED_MORE_ERROR(2833, "bank_used_more_error"),

    /**
     * 银行卡号不正确
     */
    USER_BANK_CARD_NO_FORMAT_ERROR(2801, "bank_card_no_format_error"),

    /**
     * 银行卡号已被使用
     */
    USER_BANK_CARD_USED_ERROR(2802, "bank_card_used_error"),

    /**
     * 当前银行卡是唯一使用中的银行卡，不能冻结
     */
    USER_BANK_CARD_FROZEN_ONLY_ERROR(2828, "bank_card_frozen_only_error"),

    /**
     * 当前银行卡是唯一使用中的银行卡，不能删除
     */
    USER_BANK_CARD_DELETE_ONLY_ERROR(2829, "bank_card_delete_only_error"),

    /**
     * 使用中银行卡已达n个
     */
    USER_BANK_CARD_USED_MORE_ERROR(2830, "bank_card_used_more_error"),

    /**
     * 余额不足
     */
    USER_BALANCE_NOT_ENOUGH(2811, "balance_is_not_enough"),

    /**
     * 不能转账给自己
     */
    USER_PAY_CAN_NOT_YOURSELF(2821, "pay_can_not_yourself"),

    /**
     * 请输入充值金额
     */
    USER_PAY_RECHARGE_MONEY_EMPTY_ERROR(2822, "pay_recharge_money_empty_error"),

    /**
     * 提现金额不正确
     */
    USER_PAY_WITHDRAW_MONEY_ERROR(2822, "pay_withdraw_money_error"),

    /**
     * 将超过今日提款限额
     */
    USER_PAY_WITHDRAW_BALANCE_TODAY_NOT_ENOUGH(2823, "pay_withdraw_balance_today_not_enough"),

    /**
     * 不能大于可提现余额
     */
    USER_PAY_WITHDRAW_BALANCE_NOT_ENOUGH(2824, "pay_withdraw_balance_not_enough"),

    /**
     * 竞猜流水不足
     */
    USER_PAY_GUESS_BALANCE_NOT_ENOUGH(2837, "pay_guess_balance_not_enough"),

    /**
     * 购买饰品流水不足
     */
    USER_BUY_PAY_GUESS_BALANCE_NOT_ENOUGH(2838, "pay_buy_guess_balance_not_enough"),

    /**
     * 如需购买饰品，还需相应投注流水
     */
    USER_BUY_PAY_GUESS_BALANCE_NONE_ENOUGH(2839, "pay_buy_guess_balance_none_enough"),

    /**
     * 每日最多提现5次
     */
    USER_PAY_WITHDRAW_TIMES_MORE(2836, "pay_withdraw_times_more"),

    /**
     * 用户资金账户状态异常
     */
    USER_ACCOUNT_STATUS_ERROR(2825, "user_account_status_error"),

    /**
     * 银行信息不存在
     */
    USER_PAY_BANK_NOT_EXIST_ERROR(2826, "pay_bank_not_exist_error"),

    /**
     * 银行信息已存在
     */
    USER_PAY_BANK_IS_EXIST_ERROR(2827, "pay_bank_is_exist_error"),


    // 饰品：2700-2799,
    /**
     * 取出饰品时：账户余额异常，请联系管理员！
     */
    USER_GET_GOODS_ACCOUNT_BALANCE_ERROR(2701, "user_get_goods_account_balance_error"),

    /**
     * 取出饰品时：您还未绑定Steam交易链接，无法取出饰品。请先绑定Steam交易链接！
     */
    USER_GET_GOODS_NO_STEAM_TRANSACTION_URL_ERROR(2702, "user_get_goods_no_steam_transaction_url_error"),

    // 其他：2900-2999,
    /**
     * 订单状态异常
     */
    USER_ORDER_STATUS_ERROR(2903, "order_status_error"),

    /**
     * 资源不存在
     */
    //USER_DOES_NOT_EXIST_ERROR(2900,"does not exist"),

    /**
     * 数据超长
     */
    //USER_DATA_TOO_LONG_ERROR(2901,"data too long"),

    /**
     * 参数不能为空
     */
    USER_PARAM_IS_EMPTY_ERROR(2902, "param_is_empty"),

    /**
     * 账户资金变动异常
     */
    USER_ACCOUNT_PAY_SAVE_ERROR(2903, "user_account_pay_save_error"),

    /**
     * 用户积分增加消息发送失败
     */
    USER_SEND_POINT_MESSAGE_ERROR(2904, "发送用户积分增加消息失败"),
    //=====================================================================个人中心 end========================================================================//

    /*
     * 3000-3999, 竞猜模块预留错误码
     */
    GUESS_ERROR(3000, "竞猜服务异常"),
    GUESS_EXCEPTION(3001, "系统开小差了，请稍后再试"),
    GUESS_PARAM_ERROR(3002, "请求参数不能为空"),
    GUESS_NORMAL_REPEAT(3003, "有相同的比赛信息，请确定后再次提交。"),
    GUESS_DEPLOY_NOT_EXIST(3004, "竞猜配置信息不存在"),
    GUESS_BET_RECORD_USER_INFO_ERROR(3005, "用户竞猜记录获取用户信息异常"),
    GUESS_BET_ODD_CHANGE(3333, "赔率有变化，请确认赔率后重试！"),
    GUESS_BET_MULTI_ODD_CHANGE(3555, "赔率有变化，请确认赔率后重试！"),
    /*
     * 4000-4999, 商城模块预留错误码
     */
    MALL_ERROR(4000, "mall error"),
    MALL_CART_GOODS_EXIST(4001, "该饰品已经存在您的购物车中了"),
    MALL_GOODS_CAN_NOT_PURCHASE(4002, "饰品不能购买"),
    MALL_ORDER_BALANCE_INSUFFICIENT(4003, "余额不足"),
    MALL_GOODS_IS_NOT_BUY(4004, "该饰品属于你自己的，不能加入购物车"),
    MALL_GOODS_IS_YOURSLEF(4005, "该饰品属于你自己的，不能购买啦"),
    MALL_CREATE_SHOP_ORDER_FAILD(4006, "创建订单失败,请稍后重试"),
    MALL_CACHE_SHOP_ORDER_PAYING(4007, "你购买的商品已经被别人抢先一步，请稍后再试"),
    MALL_CACHE_SHOP_ORDER_REPEAT(4008, "正在购买中，请不要重复操作"),

    //=====================================================================支付 begin========================================================================//
    /*0
     * 5000-5999, 支付模块预留错误码
     */
    PAY_ERROR(5000, "支付失败！"),

    /**
     * 未找到合适的支付通道
     */
    PAY_NO_CHANNE_ERROR(5001, "通道信息错误！"),

    /**
     * 订单不存在
     */
    PAY_ORDER_NOT_EXIST_ERROR(5101, "订单不存在！"),

    /**
     * 订单状态未知
     */
    PAY_ORDER_STATUS_ERROR(5102, "订单状态未知！"),

    /**
     * 订单已经成功
     */
    PAY_ORDER_STATUS_1_ERROR(5103, "订单已经成功！"),

    /**
     * 订单已经失败
     */
    PAY_ORDER_STATUS_2_ERROR(5104, "系统繁忙，请稍后再试！"),
    //=====================================================================支付 end========================================================================//

    /*
     * 6000-6999, 新闻资讯预留错误码
     */

    NEWS_UPDATE_ZERO(6000, "修改或删除数据条数为0"),
    NEWS_UPDATO_DATA_NULL(6001, "未找到要删除/修改的数据"),


    /*
     * 9000-9999,后台管理权限错误码
     */
    ADMIN_PASSWORD_ERROR(9001, "账号或密码错误"),

    ADMIN_USER_NOT_EXISTS(9002, "账号或密码错误"),

    ADMIN_USER_NOT_ROLE(9003, "该管理员用户没有被分配角色"),

    ADMIN_USER_NOT_RESOURCE(9004, "该管理员用户没有被分配任何权限"),

    ADMIN_RESOURCE_NOT_EXISTS(9005, "该资源不存在"),

    ADMIN_NOT_LOGIN(9006, "用户未登录"),

    ADMIN_USER_IP_ERR(500, "请填写ip地址"),

    ADMIN_UDER_IP_ISEXIST(501, "ip地址已经存在，请重新添加！"),

    ADMIN_USER_IMAGE_CODE_EXPIRED(9007, "验证码已经失效"),

    ADMIN_USER_IMAGE_CODE_ERROR(9008, "验证码错误"),

    ADMIN_USER_LOCKED(9009, "该用户被锁定,请联系管理员"),

    ADMIN_USER_IP_NOT_EXISTS(90010, "你的ip没有获取到，不支持登录"),

    ADMIN_USER_IP_IS_NOT_WHITELIST(90011, "你的IP没有授权，请授权后访问"),

    ADMIN_USER_ALREADY_EXISTS(9010, "账号已经存在"),

    ADMIN_CONTACT_US_ERROR(9011, "联系我们处理异常"),

    BOT_STEAM_UNEXCEPTION(7001, "机器人未知异常"),

    BOT_STEAM_TIMEOUT(7002, "机器人接口超时"),

    BOT_STEAM_BUSINESS_EXCEPTION(7003, "机器人业务异常"),

    BOT_STEAM_LOGINERROR(7004, "机器人登录失败"),

    /**
     * 动态码，用以暂存动态错误信息
     */
    DYNAMIC_CODE(-1, "");

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 自定义错误信息的方法
     *
     * @param message
     * @return
     */
    @Deprecated
    public CodeEnum setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 自定义错误信息的方法,使用方法:
     * CodeEnum.DYNAMIC_CODE.setDynamic(CodeEnum codeEnum,String message)
     *
     * @param codeEnum
     * @param message
     * @return
     */
    public CodeEnum setDynamic(CodeEnum codeEnum, String message) {
        this.code = codeEnum.getCode();
        this.message = message;
        return this;
    }

    /**
     * 自定义错误信息的方法,使用方法:
     * CodeEnum.DYNAMIC_CODE.setDynamicCode(Integer code,String message)
     *
     * @param code
     * @param message
     * @return
     */
    public CodeEnum setDynamicCode(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

}
