package com.apps.trip.controllers;

import com.apps.trip.dto.ReviewRequest;
import com.apps.trip.models.Review;
import com.apps.trip.service.ReviewService;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<Page<Review>> filter(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(reviewService.filter(PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<Boolean> save(ReviewRequest request) {
        return ResponseEntity.ok(reviewService.save(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<Review> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Long id, @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.update(request, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.delete(id));
    }

    @PutMapping("change-status/{id}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.changeStatus(id));
    }
}
