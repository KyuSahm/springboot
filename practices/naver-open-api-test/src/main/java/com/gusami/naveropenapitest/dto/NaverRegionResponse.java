package com.gusami.naveropenapitest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NaverRegionResponse {
    //private LocalDateTime lastBuildDate;
    private int total;
    private int start;
    private int display;
    private String category;
    private String title;
    private String link;
    private String description;
    private String telephone;
    private String address;
    private String roadAddress;
    private int mapx;
    private int mapy;
}
