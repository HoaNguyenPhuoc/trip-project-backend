package com.apps.trip.service;

import com.apps.trip.dto.SearchRequest;
import com.apps.trip.dto.TourRequest;
import com.apps.trip.dto.TourResponse;
import com.apps.trip.models.Tour;
import com.apps.trip.models.User;
import com.apps.trip.repository.TourRepository;
import com.apps.trip.utils.AppsUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final UserService userService;

    public TourServiceImpl(TourRepository tourRepository, UserService userService) {
        this.tourRepository = tourRepository;
        this.userService = userService;
    }

    @Override
    public Page<TourResponse> findAll(Pageable pageable, SearchRequest request) {
        Page<Tour> tourPage = tourRepository.findByNameContainsIgnoreCase(request.getSearchKey(), pageable);
        List<Tour> tours = tourPage.getContent();
        List<TourResponse> collect = tours.stream().map(item -> {
            List<String> favorite = new ArrayList<>();
            String favoriteString = item.getFavorite();
            if (StringUtils.isNotBlank(favoriteString)) {
                favorite = Arrays.stream(favoriteString.split(", ")).collect(Collectors.toList());
            }
            return new TourResponse(
                    item.getId(),
                    item.getName(),
                    item.getCountry(),
                    item.getDuration(),
                    item.getType(),
                    item.getScale(),
                    item.getPlace(),
                    item.getDescription(),
                    item.getPrice(),
                    item.getImg(),
                    favorite,
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        }).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, tourPage.getTotalPages());
    }

    @Override
    @Transactional
    public boolean save(TourRequest request) {
        String joined = "";
        if (!request.getFavorite().isEmpty()) {
            joined = String.join(", ", request.getFavorite());
        }
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
                .favorite(joined)
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
        String joined = "";

        if (tour.isPresent()) {
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
            if (!request.getFavorite().isEmpty()) {
                joined = String.join(", ", request.getFavorite());
                tour1.setFavorite(joined);
            }
            tourRepository.save(tour1);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Optional<Tour> tour = findById(id);
        if (tour.isPresent()) {
            tourRepository.delete(tour.get());
            return true;
        }
        return false;
    }

    @Override
    public Set<Tour> getTourRecomment() {
        Set<Tour> tours = new HashSet<>();
        if (!StringUtils.isBlank(AppsUtils.getUsername())) {
            User user = userService.findByUsername(AppsUtils.getUsername());
            List<String> collect = Arrays.stream(user.getFavorite().split(", ")).collect(Collectors.toList());

            List<Tour> tourList = tourRepository.findAll();
            collect.forEach(item -> {
                List<Tour> collect1 = tourList.stream()
                        .filter(ObjectUtils::isNotEmpty)
                        .filter(abc -> StringUtils.isNotBlank(abc.getFavorite()))
                        .filter(it -> it.getFavorite().contains(item))
                        .collect(Collectors.toList());
                tours.addAll(collect1);
            });
        }

        return tours;
    }
}
