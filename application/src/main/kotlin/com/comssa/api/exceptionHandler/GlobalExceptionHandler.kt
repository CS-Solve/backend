package com.comssa.api.exceptionHandler

import com.comssa.persistence.exception.NotLoginException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
	@ExceptionHandler(NotLoginException::class)
	fun handleNotLoginException(e: NotLoginException): ResponseEntity<String> =
		ResponseEntity(e.message, HttpStatus.UNAUTHORIZED)
}
