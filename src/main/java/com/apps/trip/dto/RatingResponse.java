package com.apps.trip.dto;

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
public class RatingResponse {
    private double myRating;
    private double avgRating;
    private List<Rating> ratings;
}
