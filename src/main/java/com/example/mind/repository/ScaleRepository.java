package com.example.mind.repository;

import com.example.mind.DTO.SclaeDTO.ScaleGetResponse;
import com.example.mind.DTO.SclaeDTO.ScaleSimpleDTO;
import com.example.mind.Entries.Question;
import com.example.mind.Entries.Scale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// 从Scale中实现JPA
public interface ScaleRepository extends JpaRepository<Scale, String> {

    // 查询量表
    @Query("SELECT s FROM Scale s WHERE s.scaleId = :scaleId")
    Optional<Scale> findById(@Param("scaleId") String scaleId);

    // 查询属于某个问卷的所有量表
    @Query("SELECT s FROM Scale s LEFT JOIN FETCH s.taskss q WHERE q.taskId = :taskId")
    List<Scale> findByTaskId(@Param("taskId") String taskId);

    // 通过量表ID获取量表详情
    @Query("SELECT s FROM Scale s " +
            "LEFT JOIN FETCH s.questions q " +
            "LEFT JOIN FETCH q.options o " +
            "LEFT JOIN FETCH q.factors f " +
            "WHERE s.scaleId = :scaleId")
    Optional<Scale> findByIdWithDetails(@Param("scaleId") String scaleId);

    // 获取所有量表的简单信息
    @Query("SELECT new com.example.mind.DTO.SclaeDTO.ScaleSimpleDTO(" +
            "s.scaleId AS scale_id, " +
            "s.title AS title, " +
            "c.categoryName AS category, " +
            "s.managerId AS manager_id )" +  // 假设 Scales 表直接存储 manager_id 字段
            "FROM Scale s " +
            "LEFT JOIN s.category c")       // 关联量表分类表
    List<ScaleSimpleDTO> findAllScalesWithCategory();

    // 查询量表及其所有问题(scheme_result)
    @Query("SELECT s FROM Scale s LEFT JOIN FETCH s.questions q WHERE s.scaleId = :scaleId")
    Optional<Scale> findScaleWithQuestions(@Param("scaleId") String scaleId);

    // 查询量表及其问题(answer_history)
    @Query("SELECT s FROM Scale s LEFT JOIN FETCH s.questions q WHERE s.scaleId IN :scaleIds")
    List<Scale> findScalesWithQuestions(@Param("scaleIds") List<String> scaleIds);
}
