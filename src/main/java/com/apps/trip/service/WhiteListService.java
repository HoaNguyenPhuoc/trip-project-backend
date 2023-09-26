package com.apps.trip.service;

import com.apps.trip.models.WhiteList;

import java.util.List;

public interface WhiteListService {
    void addWhiteList(int tourId);

    List<WhiteList> findByUsername();

    void deleteById(long id);
}
