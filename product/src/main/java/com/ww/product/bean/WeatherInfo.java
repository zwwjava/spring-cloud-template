package com.ww.product.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description -
 * @Author zww
 * @Date 2019/3/13 15:04
 */
@Data
@Entity
@Table(name = "weather")
public class WeatherInfo {
    @Id
    private Integer wId;
    private String wDate;
    private String wDetail;
    private String wTemperatureLow;
    private String wTemperatureHigh;
}
