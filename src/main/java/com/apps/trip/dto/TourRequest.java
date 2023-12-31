package com.apps.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TourRequest {
    private String name;

    private String country;

    private String duration;

    private String type;

    private String scale;

    private String place;

    private String description;

    private int price;

    private String img;

    private List<String> favorite;
}
