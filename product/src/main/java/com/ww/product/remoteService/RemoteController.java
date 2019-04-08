package com.ww.product.remoteService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/20 10:51
 */
@RestController
public class RemoteController {

    @Value("${server.port}")
    String port;

    @GetMapping("msg")
    public String msg() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "this is product' service; port:" + port;
    }

}
