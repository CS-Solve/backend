package com.comssa.api.exceptionHandler

import com.comssa.persistence.exception.NotLoginException
import com.comssa.persistence.exception.WrongQuestionTypeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
	@ExceptionHandler(NotLoginException::class)
	fun handleNotLoginException(e: NotLoginException): ResponseEntity<String> =
		ResponseEntity(e.message, HttpStatus.UNAUTHORIZED)

	@ExceptionHandler(NoSuchElementException::class)
	fun handleNoSuchElementException(e: NoSuchElementException): ResponseEntity<String> =
		ResponseEntity(e.message, HttpStatus.NOT_FOUND)

	@ExceptionHandler(WrongQuestionTypeException::class)
	fun handleWrongQuestionTypeException(e: WrongQuestionTypeException): ResponseEntity<String> =
		ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
}
