package com.apps.trip.controllers;

import com.apps.trip.dto.RatingDto;
import com.apps.trip.dto.RatingResponse;
import com.apps.trip.payload.response.ResponseJson;
import com.apps.trip.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.apps.trip.controllers.UserController.SUCCESS;

@RestController
@RequestMapping("rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("{tourId}")
    public ResponseEntity<ResponseJson> save(@PathVariable("tourId") Long tourId, @RequestBody RatingDto request) {
        boolean rating = ratingService.rating(request, tourId);
        if (rating) {
            return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
        }
        return ResponseEntity.ok(new ResponseJson(400, "User already rating this tour"));
    }

    @GetMapping("{id}")
    public ResponseEntity<RatingResponse> getRating(@PathVariable("id") Long tourId) {
        RatingResponse ratingByTourId = ratingService.ratingByTourId(tourId);

        return ResponseEntity.ok(ratingByTourId);
    }

    @PutMapping("{tourId}")
    public ResponseEntity<ResponseJson> update(@PathVariable("tourId") Long tourId, @RequestBody RatingDto request) {
        ratingService.update(request, tourId);

        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }
}
