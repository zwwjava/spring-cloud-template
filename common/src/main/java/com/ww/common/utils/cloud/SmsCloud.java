/*
package com.ww.common.utils.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;

*/
/**
 * @Description - 腾讯云短信平台
 * @Author 查旺旺
 * @Date 2019/4/29 11:15
 *//*

@Component
public class SmsCloud {

    // 需要发送短信的手机号码
    String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};

    @Autowired
    SmsSingleSender ssender;

    private void send() {
        try {
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers[0],
                    "【腾讯云】您的验证码是: 5678", "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
}
*/
