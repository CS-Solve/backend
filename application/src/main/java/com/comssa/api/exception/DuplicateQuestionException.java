package com.comssa.api.exception;

public class DuplicateQuestionException extends Exception {
	public DuplicateQuestionException() {
		super("duplicate question");
	}
}
