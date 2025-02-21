package com.example.mind.repository;

import com.example.mind.Entries.Factor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FactorRepository extends JpaRepository<Factor, String> {

    // 查询指定问题的所有因子
    @Query("SELECT f FROM Factor f JOIN f.questions q WHERE q.questionId IN :questionIds")
    List<Factor> findFactorsByQuestionIds(@Param("questionIds") List<String> questionIds);

    // 查询某个问题的所有因子
    @Query("SELECT f FROM Factor f JOIN f.questions q WHERE q.questionId = :questionId")
    List<Factor> findFactorsByQuestionId(@Param("questionId") String questionId);

}
