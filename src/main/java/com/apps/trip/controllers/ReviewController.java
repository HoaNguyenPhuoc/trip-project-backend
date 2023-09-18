package com.apps.trip.controllers;

import com.apps.trip.dto.ReviewRequest;
import com.apps.trip.models.Review;
import com.apps.trip.payload.response.ResponseJson;
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

import static com.apps.trip.controllers.UserController.SUCCESS;

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
    public ResponseEntity<ResponseJson> save(ReviewRequest request) {
        reviewService.save(request);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @GetMapping("{id}")
    public ResponseEntity<Review> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseJson> update(@PathVariable("id") Long id, @RequestBody ReviewRequest request) {
        reviewService.update(request, id);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseJson> delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }

    @PutMapping("change-status/{id}")
    public ResponseEntity<ResponseJson> changeStatus(@PathVariable("id") Long id) {
        reviewService.changeStatus(id);
        return ResponseEntity.ok(new ResponseJson(200, SUCCESS));
    }
}
