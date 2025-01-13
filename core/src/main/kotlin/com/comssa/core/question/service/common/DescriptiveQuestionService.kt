package com.comssa.core.question.service.common

import com.comssa.core.chatbot.service.implement.ChatManageService
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionGradeStandardDto
import com.comssa.persistence.question.dto.common.request.RequestDoGradeDescriptiveAnswerDto
import org.springframework.stereotype.Service

@Service
class DescriptiveQuestionService(
	private val questionGetService: QuestionGetService,
	private val chatManageService: ChatManageService,
) {
	fun gradeDescriptiveQuestion(
		id: Long,
		requestDoGradeDescriptiveAnswerDto: RequestDoGradeDescriptiveAnswerDto,
	): String {
		val question =
			questionGetService.getMajorDescriptiveQuestion(id)

		return chatManageService.talkForGradeDescriptiveQuestion(
			systemMessage + question.gradeStandard,
			question.content,
			requestDoGradeDescriptiveAnswerDto.content,
		)
	}

	fun changeGradeStandard(
		id: Long,
		requestChangeQuestionGradeStandardDto: RequestChangeQuestionGradeStandardDto,
	): MajorDescriptiveQuestion {
		val question =
			questionGetService.getMajorDescriptiveQuestion(id)
		question.changeGradeStandard(requestChangeQuestionGradeStandardDto.gradeStandard)
		return question
	}

	private val systemMessage =
		"당신은 면접관입니다. 사용자는 면접을 보는 사람입니다. " +
			"주어진 채점 기준에 맞추어 작성자의 답변을 점검해주세요. " +
			"사용자의 답변에 아쉬운 부분이 있다면 과감히 말씀해주세요. " +
			"1. 예를 들어 A에 대해 물어봤는데 답변자가 관련성이 적은 B까지 언급한다면, 지적해주셔도 됩니다.  " +
			"2. 사용자가 틀린 내용을 말한다면 틀렸다고 말씀해주세요." +
			"채점 기준에 사용자의 답변이 부합하지 않더라도, 채점 기준이 무조건 적인 정답이 되어선 안됩니다." +
			"그러니 지적할 때, ~이렇게 대답하셔야합니다. 보단 ~ 이런 내용을 보충하시면 더 좋을 거같습니다. 이런 식으로 지적해주세요." +
			"다만 당신의 답변도 Too Much Information을 담아선 안 됩니다. 예를 들어 JVM의 컴파일 과정에 대해 물어보았는데, " +
			"당신의 답변에 JIT 컴파일러 내용이 들어가있으면 좋지 않습니다. 관련성은 있지만, 물어본 질문에 대해서만 답변을 해주세요." +
			"문제 채점 기준은 다음과 같습니다. 주어진 채점 기준이 없다면 당신이 임의로 판단해서 채점해주세요. " +
			""
}
