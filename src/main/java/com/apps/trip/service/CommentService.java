package com.apps.trip.service;

import com.apps.trip.dto.CommentDto;

public interface CommentService {
    void addCommentToTour(Integer tourId, CommentDto request);
    void deleteComment(Long commentId);
}
