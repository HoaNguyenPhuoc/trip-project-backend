package com.apps.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewRequest {
    private String title;
    private String imgPreview;
    private String shortDescription;
    private String description;
}
