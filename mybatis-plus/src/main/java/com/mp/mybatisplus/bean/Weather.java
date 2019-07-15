package com.mp.mybatisplus.bean;

import lombok.Data;


/**
 * @Description -
 * @Author zww
 * @Date 2019/3/13 15:04
 */
@Data
public class Weather {
    private Integer wId;
    private String wDate;
    private String wDetail;
    private String wTemperatureLow;
    private String wTemperatureHigh;
}
