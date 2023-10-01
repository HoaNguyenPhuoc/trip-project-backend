package com.apps.trip.service;

import com.apps.trip.models.Tour;

import java.util.List;

public interface WhiteListService {
    boolean addWhiteList(long tourId);

    List<Tour> findByUsername();

    void deleteById(long id);
}
