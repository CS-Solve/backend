package com.comssa.persistence.comment.service;

import com.comssa.persistence.comment.domain.Comment;
import com.comssa.persistence.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentRepositoryService {
	private final CommentRepository commentRepository;

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
}
