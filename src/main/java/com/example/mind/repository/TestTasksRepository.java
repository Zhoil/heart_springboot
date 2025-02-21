package com.example.mind.repository;

import com.example.mind.Entries.TestTasks;
import com.example.mind.enumtion.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 从TestTasks中实现JPA
public interface TestTasksRepository extends JpaRepository<TestTasks, String> {

    // 通过测试者ID获取历史任务
    @Query("SELECT " +
            "t.taskId AS task_id, " +
            "t.title AS title, " +
            "t.description AS description, " +
            "COUNT(tu) AS personCount " +
            "FROM TestTasks t " +
            "LEFT JOIN t.testedUsers tu " +  // 假设 testedUsers 是 TestTasks 中关联的被测试用户集合
            "WHERE t.testerId.userId = :testerId " +
            "GROUP BY t.taskId, t.title, t.description") // GROUP BY 所有非聚合字段
    List<Object[]> findTaskHistoryByTesterId(@Param("testerId") String testerId);

    // 查询指定被测人的所有测试任务(answer_history)
    @Query("SELECT t FROM TestTasks t LEFT JOIN FETCH t.testedUsers q WHERE q.testedUserId = :testedUserId")
    List<TestTasks> findByTestedUserId(@Param("testedUserId") String testedUserId);

    // 根据任务ID查询问卷信息(test_task_result_detail)
    @Query("SELECT q FROM TestTasks q WHERE q.taskId = :taskId")
    Optional<TestTasks> findByTaskId(@Param("taskId") String taskId);

    // 通过被测试用户ID获取待测任务
    @Query("SELECT " +
            "t.taskId AS task_id, " +
            "t.testerId.userId AS tester_id, " +
            "tu.testedUserId AS testeduser_id, " +
            "s.scaleId AS scale_id, " +
            "t.status AS status, " +
            "t.title AS title, " +
            "t.description AS description " +
            "FROM TestTasks t " +
            "JOIN t.testedUsers tu " +         // 关联被测用户（必须存在关联）
            "LEFT JOIN t.scales s " +          // 关联量表（允许为空）
            "WHERE tu.testedUserId = :testedUserId") // 根据被测用户ID过滤
    List<Object[]> findTasksByTestedUserId(@Param("testedUserId") String testedUserId);

    // 通过任务ID获取问卷详情
    @Query("SELECT DISTINCT t FROM TestTasks t " +
            "LEFT JOIN FETCH t.scales s " +
            "LEFT JOIN FETCH s.category c " +
            "LEFT JOIN FETCH s.questions q " +
            "LEFT JOIN FETCH q.options o " +
            "WHERE t.taskId = :taskId")
    Optional<TestTasks> findByIdWithDetails(@Param("taskId") String taskId);

    @Modifying
    @Transactional
    @Query("UPDATE TestTasks t SET t.status = :status WHERE t.taskId = :taskId")
    int updateTaskStatus(@Param("taskId") String taskId, @Param("status") TaskStatus status);

}
