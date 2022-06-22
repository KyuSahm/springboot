package com.gusami.naveropenapitest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class NaverRegionResponse {
    //private LocalDateTime lastBuildDate;
    private int total;
    private int start;
    private int display;
    private Item[] items;
    @Data
    @NoArgsConstructor
    public static class Item {
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
}
