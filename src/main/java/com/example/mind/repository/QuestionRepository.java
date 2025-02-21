package com.example.mind.repository;

import com.example.mind.Entries.Factor;
import com.example.mind.Entries.Option;
import com.example.mind.Entries.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String> {

    // 查询量表的所有问题
    @Query("SELECT q FROM Question q WHERE q.scale.scaleId = :scaleId")
    List<Question> findQuestionsByScaleId(@Param("scaleId") String scaleId);

    // 查询某个量表下的所有问题
    @Query("SELECT q FROM Question q WHERE q.scale.scaleId IN :scaleIds")
    List<Question> findByScaleIds(@Param("scaleIds") List<String> scaleIds);


}
