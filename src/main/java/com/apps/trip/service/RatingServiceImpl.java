package com.apps.trip.service;

import com.apps.trip.dto.RatingDto;
import com.apps.trip.dto.RatingResponse;
import com.apps.trip.models.Rating;
import com.apps.trip.models.Tour;
import com.apps.trip.repository.RatingRepository;
import com.apps.trip.utils.AppsUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final TourService tourService;

    @Override
    public boolean rating(RatingDto request, long tourId) {
        String username = AppsUtils.getUsername();
        Optional<Rating> optionalRating = ratingRepository.findByUsernameAndTourId(username, tourId);
        if (optionalRating.isPresent()) {
            return false;
        }
        Tour tour = tourService.findById(tourId);

        Rating rating = new Rating();
        rating.setUsername(username);
        rating.setTour(tour);
        rating.setStar(request.getRating());
        rating.setCreatedAt(LocalDateTime.now().toString());

        ratingRepository.save(rating);
        return true;
    }

    @Override
    public RatingResponse ratingByTourId(long tourId) {
        String username = AppsUtils.getUsername();
        double myRating = 0;
        List<Rating> ratings = ratingRepository.findByTourId(tourId).stream().map(rating -> {
            rating.setTour(null);
            return rating;
        }).collect(Collectors.toList());
        Optional<Rating> byUsername = ratingRepository.findByUsernameAndTourId(username, tourId);
        if (byUsername.isPresent()) {
            myRating = byUsername.get().getStar();
        }
        double avgRating = ratings.stream()
                .mapToDouble(rating -> Math.abs(rating.getStar()))
                .average() // Calculate the average of the absolute ratings
                .orElse(0.0);

        return new RatingResponse(myRating, avgRating, ratings);
    }

    @Override
    @Transactional
    public void update(RatingDto request, long tourId) {
        String username = AppsUtils.getUsername();

        Optional<Rating> optionalRating = ratingRepository.findByUsernameAndTourId(username, tourId);
        if (optionalRating.isPresent()) {
            Rating rating = optionalRating.get();
            rating.setStar(request.getRating());

            ratingRepository.save(rating);
        }
    }

    @Override
    public Rating get(long tourId) {
        return ratingRepository.findByUsernameAndTourId(AppsUtils.getUsername(), tourId).orElseThrow();
    }
}
