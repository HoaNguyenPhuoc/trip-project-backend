package com.apps.trip.service;

import com.apps.trip.dto.CommentDto;
import com.apps.trip.models.Comment;
import com.apps.trip.models.Tour;
import com.apps.trip.repository.CommentRepository;
import com.apps.trip.utils.AppsUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TourService tourService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, TourService tourService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.tourService = tourService;
    }

    @Override
    public void addCommentToTour(Integer tourId, CommentDto comment) {
        Tour tour = tourService.findById(tourId);
        Comment c = new Comment();
        c.setTour(tour);
        c.setUser(userService.findByUsername(AppsUtils.getUsername()));
        c.setContent(comment.getContent());
        c.setCreatedTime(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).toString());

        commentRepository.save(c);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
