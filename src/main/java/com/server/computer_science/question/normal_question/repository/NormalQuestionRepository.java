package com.server.computer_science.question.normal_question.repository;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormalQuestionRepository extends JpaRepository<NormalQuestion,Long> {
    @Query("SELECT DISTINCT nq FROM NormalQuestion nq " +
            "LEFT JOIN FETCH nq.normalQuestionChoices " +
            "WHERE nq.questionCategory IN :questionCategories " +
            "AND nq.questionLevel IN :questionLevels")
    List<NormalQuestion> findNormalQuestionsFetchChoicesWithCategoriesAndLevels(@Param("questionCategories") List<QuestionCategory> questionCategories,
                                                                                @Param("questionLevels") List<QuestionLevel> questionLevels);
    @Query("SELECT DISTINCT nq FROM NormalQuestion nq " +
            "LEFT JOIN FETCH nq.normalQuestionChoices ")
    List<NormalQuestion> findNormalQuestionsFetchChoices();
}
