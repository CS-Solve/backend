package com.server.computer_science.question.normal_question.common.repository;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NormalQuestionRepository extends JpaRepository<NormalQuestion,Long> {
    /**
     *
     허용되지 않은 문제까지 조회
     */
    @Query("SELECT DISTINCT nq FROM NormalQuestion nq " +
            "LEFT JOIN FETCH nq.normalQuestionChoices " +
            "WHERE nq.questionCategory IN :questionCategories " +
            "AND nq.questionLevel IN :questionLevels")
    List<NormalQuestion> findNormalQuestionsFetchChoicesWithCategoriesAndLevels(@Param("questionCategories") List<QuestionCategory> questionCategories,
                                                                                @Param("questionLevels") List<QuestionLevel> questionLevels);
    @Query("SELECT DISTINCT nq FROM NormalQuestion nq " +
            "LEFT JOIN FETCH nq.normalQuestionChoices " +
    "ORDER BY nq.ifApproved, nq.canBeShortAnswered")
    List<NormalQuestion> findNormalQuestionsFetchChoicesSortedByIfApprovedAndCanBeShortAnswered();

    @Query("SELECT DISTINCT nq FROM NormalQuestion nq " +
            "LEFT JOIN FETCH nq.normalQuestionChoices ")
    List<NormalQuestion> findNormalQuestionsFetchChoices();

    @Query("SELECT DISTINCT nq FROM NormalQuestion nq " +
            "LEFT JOIN FETCH nq.normalQuestionChoices "+
    "WHERE nq.id = :id")
    Optional<NormalQuestion> findNormalQuestionsByIdFetchChoices(@Param("id") Long id);


    /**
     * 허용된 문제들만 조회 (ifApproved가 true인 경우)
     */
    @Query("SELECT DISTINCT nq FROM NormalQuestion nq " +
            "LEFT JOIN FETCH nq.normalQuestionChoices " +
            "WHERE nq.questionCategory IN :questionCategories " +
            "AND nq.questionLevel IN :questionLevels " +
            "AND nq.ifApproved = true")
    List<NormalQuestion> findNormalQuestionsFetchChoicesWithCategoriesAndLevelsAndIfApproved(@Param("questionCategories") List<QuestionCategory> questionCategories,
                                                                                              @Param("questionLevels") List<QuestionLevel> questionLevels);
}
