package com.apps.trip.service;

import com.apps.trip.dto.RatingDto;
import com.apps.trip.models.Rating;

public interface RatingService {
    void rating(RatingDto request, long tourId);

    double ratingByTourId(long tourId);

    void update(RatingDto request, long tourId);

    Rating get(long tourId);
}
