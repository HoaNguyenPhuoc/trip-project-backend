package com.apps.trip.dto;

import com.apps.trip.models.Comment;
import com.apps.trip.models.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TourResponse {
    private Long id;

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
    private List<Comment> comment;
    private List<Rating> ratings;
}
