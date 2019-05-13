/*
package com.ww.common.config;

import com.github.qcloudsms.SmsSingleSender;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

*/
/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/29 11:37
 *//*

@Component
public class SmsCloudConfig {

    // 短信应用SDK AppID
    //@Value("${cloud.sms.appid}")
    int appid = 1400009099; // 1400开头

    // 短信应用SDK AppKey
    String appkey = "9ff91d87c2cd7cd0ea762f141975d1df37481d48700d70ac37470aefc60f9bad";

    // 短信模板ID，需要在短信应用中申请
    int templateId = 7839; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

    // 签名
    String smsSign = "躲猫猫"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`

    @Bean
    public SmsSingleSender smsSingleSender() {
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        return ssender;
    }

}
*/
