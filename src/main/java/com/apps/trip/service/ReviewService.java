package com.apps.trip.service;

import com.apps.trip.dto.ReviewRequest;
import com.apps.trip.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    boolean save(ReviewRequest request);

    Page<Review> filter(Pageable pageable);

    Review findById(Long id);

    boolean update(ReviewRequest request, Long id);

    boolean delete(Long id);

    boolean changeStatus(Long id);
}
