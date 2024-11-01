package com.server.computer_science.question.major_question.common.repository;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MajorQuestionRepository extends JpaRepository<MajorMultipleChoiceQuestion,Long> {
    /**
     *
     허용되지 않은 문제까지 조회
     */
    @Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq " +
            "LEFT JOIN FETCH nq.questionChoices " +
            "WHERE nq.questionCategory IN :questionCategories " +
            "AND nq.questionLevel IN :questionLevels")
    List<MajorMultipleChoiceQuestion> findFetchChoicesWithCategoriesAndLevels(@Param("questionCategories") List<QuestionCategory> questionCategories,
                                                                              @Param("questionLevels") List<QuestionLevel> questionLevels);
    @Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq " +
            "LEFT JOIN FETCH nq.questionChoices " +
    "ORDER BY nq.ifApproved, nq.canBeShortAnswered")
    List<MajorMultipleChoiceQuestion> findFetchChoicesSortedByIfApprovedAndCanBeShortAnswered();

    @Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq " +
            "LEFT JOIN FETCH nq.questionChoices ")
    List<MajorMultipleChoiceQuestion> findFetchChoices();

    @Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq " +
            "LEFT JOIN FETCH nq.questionChoices "+
    "WHERE nq.id = :id")
    Optional<MajorMultipleChoiceQuestion> findByIdFetchChoices(@Param("id") Long id);

    @Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq " +
            "LEFT JOIN FETCH nq.questionChoices " +
    "WHERE nq.canBeShortAnswered = true")
    List<MajorMultipleChoiceQuestion> findFetchChoicesShortAnswered();


    /**
     * 허용된 문제들만 조회 (ifApproved가 true인 경우)
     */
    @Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq " +
            "LEFT JOIN FETCH nq.questionChoices " +
            "WHERE nq.questionCategory IN :questionCategories " +
            "AND nq.questionLevel IN :questionLevels " +
            "AND nq.ifApproved = true")
    List<MajorMultipleChoiceQuestion> findFetchChoicesWithCategoriesAndLevelsAndIfApproved(@Param("questionCategories") List<QuestionCategory> questionCategories,
                                                                                           @Param("questionLevels") List<QuestionLevel> questionLevels);
    @Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq " +
            "LEFT JOIN FETCH nq.questionChoices " +
            "WHERE nq.questionCategory IN :questionCategories " +
            "AND nq.questionLevel IN :questionLevels " +
            "AND nq.ifApproved = true "+
    "AND nq.canBeShortAnswered = true")
    List<MajorMultipleChoiceQuestion> findFetchChoicesWithCategoriesAndLevelsAndIfApprovedAndCanBeShortAnswered(@Param("questionCategories") List<QuestionCategory> questionCategories,
                                                                                                                @Param("questionLevels") List<QuestionLevel> questionLevels);
}
