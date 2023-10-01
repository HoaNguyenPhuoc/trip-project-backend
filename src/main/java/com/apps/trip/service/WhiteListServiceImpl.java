package com.apps.trip.service;

import com.apps.trip.models.Tour;
import com.apps.trip.models.WhiteList;
import com.apps.trip.repository.WhiteListRepository;
import com.apps.trip.utils.AppsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WhiteListServiceImpl implements WhiteListService {
    private final TourService tourService;
    private final WhiteListRepository whiteListRepository;

    public WhiteListServiceImpl(TourService tourService, WhiteListRepository whiteListRepository) {
        this.tourService = tourService;
        this.whiteListRepository = whiteListRepository;
    }

    @Override
    @Transactional
    public boolean addWhiteList(long tourId) {
        String username = AppsUtils.getUsername();
        Optional<WhiteList> whiteList1 = whiteListRepository.findByUsernameAndTourId(username, tourId);
        if (whiteList1.isPresent()) {
            return false;
        }
        Tour tour = tourService.findById(tourId);

        WhiteList whiteList = new WhiteList();
        whiteList.setUsername(username);
        whiteList.setTour(tour);
        whiteList.setSubscriptionDate(LocalDateTime.now().toString());

        whiteListRepository.save(whiteList);
        return true;
    }

    @Override
    public List<Tour> findByUsername() {
        String username = AppsUtils.getUsername();
        return whiteListRepository.findByUsername(username).stream()
                .map(WhiteList::getTour)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        WhiteList whiteList = whiteListRepository.findById(id).orElseThrow();
        whiteListRepository.delete(whiteList);
    }
}
