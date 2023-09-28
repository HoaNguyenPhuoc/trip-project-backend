package com.apps.trip.service;

import com.apps.trip.dto.SearchRequest;
import com.apps.trip.dto.TourRequest;
import com.apps.trip.models.Tour;
import com.apps.trip.repository.TourRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public Page<Tour> findAll(Pageable pageable, SearchRequest request) {
        return tourRepository.findByNameContainsIgnoreCase(request.getSearchKey(), pageable);
    }

    @Override
    @Transactional
    public boolean save(TourRequest request) {
        Tour tour = Tour.builder()
                .name(request.getName())
                .country(request.getCountry())
                .duration(request.getDuration())
                .type(request.getType())
                .scale(request.getScale())
                .place(request.getPlace())
                .description(request.getDescription())
                .price(request.getPrice())
                .rating(5)
                .build();

        tourRepository.save(tour);
        return true;
    }

    @Override
    public Tour findById(long id) {
        return tourRepository.findById(id).orElseThrow(() -> new RuntimeException("Cant not fount this id"));
    }

    @Override
    @Transactional
    public boolean update(long id, TourRequest request) {
        Tour tour = findById(id);
        tour.setName(request.getName());
        tour.setCountry(request.getCountry());
        tour.setDuration(request.getDuration());
        tour.setType(request.getType());
        tour.setScale(request.getScale());
        tour.setPlace(request.getPlace());
        tour.setDescription(request.getDescription());
        tour.setPrice(request.getPrice());

        tourRepository.save(tour);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Tour tour = findById(id);

        tourRepository.delete(tour);
        return true;
    }
}
