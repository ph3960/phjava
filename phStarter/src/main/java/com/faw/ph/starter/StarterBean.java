package com.faw.ph.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author : PeiHang
 * @create 2023/4/13 19:24
 */
@EnableConfigurationProperties(StarterBean.class)
@ConfigurationProperties(prefix = "starterbean")
public class StarterBean {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "starterbean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
