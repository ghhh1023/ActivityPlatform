package com.activityplatform.util;


import com.activityplatform.config.Client;



public class MobileMessageUtil {

    /**
     * 发送验证码
     * @param mobile 要发送的手机号
     * @param code 要发送的验证码内容
     * @param status 验证码模版编号状态码
     * status       模版名称
     *    3         信息变更
     *    4         修改密码
     *    5         用户注册
     *    6         登录异常
     *    7         登录确认
     *    8         身份验证
     */
    public static Client.Request sendIdentifyingCode(String mobile, String code, int status) {
        Client client = new Client();
        client.setAppId("hw_10511");     //开发者ID，在【设置】-【开发设置】中获取
        client.setSecretKey("5354e599062278d422667efbd779655e");    //开发者密钥，在【设置】-【开发设置】中获取
        client.setVersion("1.0");

        /**
         *   json格式可在 bejson.com 进行校验
         */
        String templateId = null;
        switch (status) {
            case 4:
                templateId = "ST_2020101100000004";
                break;
            case 5:
                templateId = "ST_2020101100000005";
                break;
            case 6:
                templateId = "ST_2020101100000006";
                break;
            case 7:
                templateId = "ST_2020101100000007";
                break;
            case 8:
                templateId = "ST_2020101100000008";
                break;
            default:
                templateId = "ST_2020101100000003";
        }
        Client.Request request = new Client.Request();
        request.setBizContent("{\"mobile\":[\"" + mobile + "\"],\"type\":0,\"template_id\":\"" + templateId + "\",\"sign\":\"闪验\",\"send_time\":\"\",\"params\":{\"code\":" + code + "}}");  // 这里是json字符串，send_time 为空时可以为null, params 为空时可以为null,短信签名填写审核后的签名本身，不需要填写签名id
        request.setMethod("sms.message.send");
        System.out.println( client.execute(request) );
        return request;
    }
}
