package com.apps.trip.service;

import com.apps.trip.dto.ReviewRequest;
import com.apps.trip.models.Review;
import com.apps.trip.repository.ReviewRepository;
import com.apps.trip.utils.AppsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public boolean save(ReviewRequest request) {
        Review review = new Review();
        review.setTitle(request.getTitle());
        review.setImgPreview(request.getImgPreview());
        review.setShortDescription(request.getShortDescription());
        review.setDescription(request.getDescription());
        review.setStatus(false);
        review.setUsername(AppsUtils.getUsername());
        review.setCreatedTime(LocalDateTime.now().toString());

        reviewRepository.save(review);
        return true;
    }

    @Override
    public Page<Review> filter(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    @Transactional
    public boolean update(ReviewRequest request, Long id) {
        Review review = findById(id);
        review.setTitle(request.getTitle());
        review.setImgPreview(request.getImgPreview());
        review.setShortDescription(request.getShortDescription());
        review.setDescription(request.getDescription());

        reviewRepository.save(review);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Review review = findById(id);
        reviewRepository.delete(review);

        return true;
    }

    @Override
    @Transactional
    public boolean changeStatus(Long id) {
        Review review = findById(id);
        review.setStatus(!review.isStatus());

        reviewRepository.save(review);
        return true;
    }
}
