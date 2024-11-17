package com.comssa.persistence.exception

class NotLoginException : Exception("로그인이 필요합니다.") {
	override val message: String?
		get() = super.message
}
