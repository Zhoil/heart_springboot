package com.example.mind.repository;

import com.example.mind.Entries.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OptionRepository extends JpaRepository<Option, String> {

    // 查询指定一些问题的所有选项
    @Query("SELECT o FROM Option o WHERE o.question.questionId IN :questionIds")
    List<Option> findOptionsByQuestionIds(@Param("questionIds") List<String> questionIds);

    // 查询某个问题的所有选项(scheme_result)
    List<Option> findByQuestion_QuestionId(String questionId);

    // 查询某个选项的选择人数统计(scheme_result)
    @Query("SELECT COUNT(ta) FROM TestAnswers ta WHERE ta.selectedOption.optionId = :optionId")
    int countPersonForOption(@Param("optionId") String optionId);

    // 查询问题的所有选项(answer_history)
    @Query("SELECT o FROM Option o WHERE o.question.questionId = :questionId")
    List<Option> findByQuestionId(@Param("questionId") String questionId);

    // 统计每个选项被选中的人数
    @Query("SELECT o.optionId, COUNT(a.answerId) FROM TestAnswers a JOIN a.selectedOption o WHERE o.optionId IN :optionIds GROUP BY o.optionId")
    List<Object[]> countOptionSelections(@Param("optionIds") List<String> optionIds);

}
