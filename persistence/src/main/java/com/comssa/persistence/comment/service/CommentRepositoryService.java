package com.comssa.persistence.comment.service;

import com.comssa.persistence.comment.domain.Comment;
import com.comssa.persistence.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentRepositoryService {
	private final CommentRepository commentRepository;

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	public void deleteById(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}
