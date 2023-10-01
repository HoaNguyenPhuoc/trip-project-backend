package com.apps.trip.service;

import com.apps.trip.dto.SearchRequest;
import com.apps.trip.dto.TourRequest;
import com.apps.trip.models.Tour;
import com.apps.trip.repository.TourRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
                .img(request.getImg())
                .build();

        tourRepository.save(tour);
        return true;
    }

    @Override
    public Optional<Tour> findById(long id) {
        return tourRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean update(long id, TourRequest request) {
        Optional<Tour> tour = findById(id);
        if (tour.isPresent()){
            Tour tour1 = tour.get();
            tour1.setName(request.getName());
            tour1.setCountry(request.getCountry());
            tour1.setDuration(request.getDuration());
            tour1.setType(request.getType());
            tour1.setScale(request.getScale());
            tour1.setPlace(request.getPlace());
            tour1.setDescription(request.getDescription());
            tour1.setPrice(request.getPrice());
            tour1.setImg(request.getImg());

            tourRepository.save(tour1);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Optional<Tour> tour = findById(id);
        if (tour.isPresent()){
            tourRepository.delete(tour.get());
            return true;
        }
        return false;
    }
}
