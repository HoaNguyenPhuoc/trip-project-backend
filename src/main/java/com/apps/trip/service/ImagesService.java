package com.apps.trip.service;

import com.apps.trip.models.Images;

public interface ImagesService {
    void addImageToTour(Integer tourId, Images request);
    void deleteComment(Integer imageId);
}
