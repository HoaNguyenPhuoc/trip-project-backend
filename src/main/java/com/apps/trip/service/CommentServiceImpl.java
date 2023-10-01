package com.apps.trip.service;

import com.apps.trip.dto.CommentDto;
import com.apps.trip.models.Comment;
import com.apps.trip.models.Tour;
import com.apps.trip.models.User;
import com.apps.trip.repository.CommentRepository;
import com.apps.trip.repository.TourRepository;
import com.apps.trip.utils.AppsUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TourRepository tourRepository;
    private final UserService userService;
    private final TourService tourService;

    public CommentServiceImpl(CommentRepository commentRepository, TourRepository tourRepository,
                              UserService userService, TourService tourService) {
        this.commentRepository = commentRepository;
        this.tourRepository = tourRepository;
        this.userService = userService;
        this.tourService = tourService;
    }

    @Override
    @Transactional
    public void addCommentToTour(Integer tourId, CommentDto request) {
        Optional<Tour> tour = tourService.findById(tourId);
        User user = userService.findByUsername(AppsUtils.getUsername());
        if (tour.isPresent()) {
            Comment cmm = new Comment();
            cmm.setTour(tour.get());
            cmm.setUsername(ObjectUtils.isEmpty(user.getUsername()) ? "" : user.getUsername());
            cmm.setContent(request.getContent());
            cmm.setCreatedTime(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).toString());

            Comment comment = commentRepository.save(cmm);
            tour.get().getComment().add(comment);
            tourRepository.save(tour.get());
        }
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
