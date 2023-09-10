package com.apps.trip.service;

import com.apps.trip.dto.TourRequest;
import com.apps.trip.models.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TourService {
    Page<Tour> findAll(Pageable pageable);

    boolean save(TourRequest request);

    Tour findById(int id);

    boolean update(int id, TourRequest request);

    boolean delete(int id);
}
