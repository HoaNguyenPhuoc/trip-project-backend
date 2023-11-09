package com.apps.trip.service;

import com.apps.trip.models.Comment;
import com.apps.trip.models.Images;
import com.apps.trip.models.Tour;
import com.apps.trip.repository.ImagesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImagesServiceImpl implements ImagesService {
    private final ImagesRepository imagesRepository;
    private final TourService tourService;

    public ImagesServiceImpl(ImagesRepository imagesRepository, TourService tourService) {
        this.imagesRepository = imagesRepository;
        this.tourService = tourService;
    }

    @Override
    public void addImageToTour(Integer tourId, Images request) {
        Optional<Tour> tour = tourService.findById(tourId);
        if (tour.isPresent()) {
            Images cmm = new Images();
            cmm.setTour(tour.get());
            cmm.setImage(request.getImage());

            Images image = imagesRepository.save(cmm);
            tour.get().getImages().add(image);
//            tourService.save(tour.get());
        }
    }

    @Override
    public void deleteComment(Integer imageId) {

    }
}
