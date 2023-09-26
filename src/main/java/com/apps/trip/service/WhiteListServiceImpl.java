package com.apps.trip.service;

import com.apps.trip.models.Tour;
import com.apps.trip.models.User;
import com.apps.trip.models.WhiteList;
import com.apps.trip.repository.WhiteListRepository;
import com.apps.trip.utils.AppsUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WhiteListServiceImpl implements WhiteListService {
    private final TourService tourService;
    private final WhiteListRepository whiteListRepository;
    private final UserService userService;

    public WhiteListServiceImpl(TourService tourService, WhiteListRepository whiteListRepository, UserService userService) {
        this.tourService = tourService;
        this.whiteListRepository = whiteListRepository;
        this.userService = userService;
    }

    @Override
    public void addWhiteList(int tourId) {
        User user = userService.findByUsername(AppsUtils.getUsername());
        Tour tour = tourService.findById(tourId);

        WhiteList whiteList = new WhiteList();
        whiteList.setUser(user);
        whiteList.setTour(tour);
        whiteList.setSubscriptionDate(LocalDateTime.now().toString());

        whiteListRepository.save(whiteList);
    }

    @Override
    public List<WhiteList> findByUsername() {
        return whiteListRepository.findByUserUsername(AppsUtils.getUsername());
    }

    @Override
    public void deleteById(long id) {
        WhiteList whiteList = whiteListRepository.findById(id).orElseThrow();
        whiteListRepository.delete(whiteList);
    }
}
