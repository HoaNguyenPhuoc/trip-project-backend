package com.apps.trip.service;

import com.apps.trip.dto.CommentDto;

public interface CommentService {
    void addCommentToTour(Integer tourId, CommentDto comment);
    void deleteComment(Long commentId);
}
