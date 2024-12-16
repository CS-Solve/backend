package com.comssa.api.question.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HtmlTag {
	private String title;
	private String questionSession;
	private String description;
}
