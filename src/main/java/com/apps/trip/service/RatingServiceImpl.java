package com.apps.trip.service;

import com.apps.trip.dto.RatingDto;
import com.apps.trip.models.Rating;
import com.apps.trip.models.Tour;
import com.apps.trip.models.User;
import com.apps.trip.repository.RatingRepository;
import com.apps.trip.utils.AppsUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final TourService tourService;
    private final UserService userService;

    @Override
    public void rating(RatingDto request, long tourId) {
        String username = AppsUtils.getUsername();
        User user = userService.findByUsername(username);
        Tour tour = tourService.findById(tourId);

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setTour(tour);
        rating.setStar(request.getRating());
        rating.setCreatedAt(LocalDateTime.now().toString());

        ratingRepository.save(rating);
    }

    @Override
    public double ratingByTourId(long tourId) {
        List<Rating> tour = ratingRepository.findByTourId(tourId);
        return tour.stream()
                .mapToDouble(rating -> Math.abs(rating.getStar()))
                .average() // Calculate the average of the absolute ratings
                .orElse(0.0);
    }

    @Override
    public void update(RatingDto request, long tourId) {
        String username = AppsUtils.getUsername();

        Optional<Rating> optionalRating = ratingRepository.findByUsernameAndTourId(username, tourId);
        if (optionalRating.isPresent()){
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
