package com.apps.trip.service;

import com.apps.trip.dto.SearchRequest;
import com.apps.trip.dto.TourRequest;
import com.apps.trip.models.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TourService {
    Page<Tour> findAll(Pageable pageable, SearchRequest request);

    boolean save(TourRequest request);

    Optional<Tour> findById(long id);

    boolean update(long id, TourRequest request);

    boolean delete(long id);
}
