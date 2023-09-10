package com.apps.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
