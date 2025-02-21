package com.example.mind.repository;

import com.example.mind.Entries.TestAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestAnswersRepository extends JpaRepository<TestAnswers, String> {

    // 通过任务ID统计问卷详情
    @Query("SELECT o.optionId AS optionId, COUNT(ta.answerId) AS personCount " +
            "FROM TestAnswers ta " +
            "JOIN ta.selectedOption o " +          // 关联选项
            "WHERE ta.task.taskId = :taskId " +     // 按任务（问卷）ID 过滤
            "GROUP BY o.optionId")
    List<Object[]> countSelectionsByTaskId(@Param("taskId") String taskId);

    // 查询指定任务的所有答题记录(answer_history)
    @Query("SELECT ta FROM TestAnswers ta WHERE ta.task.taskId = :taskId")
    List<TestAnswers> findByTaskId(@Param("taskId") String taskId);


    // 通过量表ID统计量表详情
    @Query("SELECT o.optionId AS optionId, COUNT(ta) AS count " +
            "FROM TestAnswers ta " +
            "JOIN ta.selectedOption o " +
            "WHERE o.question.scale.scaleId = :scaleId " +
            "GROUP BY o.optionId")
    List<Object[]> countSelectionsByScaleId(@Param("scaleId") String scaleId);

}
