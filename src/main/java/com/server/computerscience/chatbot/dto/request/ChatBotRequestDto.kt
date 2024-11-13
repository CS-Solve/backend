package com.server.computerscience.chatbot.dto.request

import com.fasterxml.jackson.annotation.JsonCreator

/*
파라미터가 한 곳인 곳에는 Jackson이 Delegating(통쨰로 매핑),Properties(필드대로 매핑)중 어떤 방식을 써야할지 몰라서 에러가 발생한다.
@JsonCreator로 방식 지정 가능(기본은 Properties)
 */
data class ChatBotRequestDto @JsonCreator constructor(
    val prompt: String
)
