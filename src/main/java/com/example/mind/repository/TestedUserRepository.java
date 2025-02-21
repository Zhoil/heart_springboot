package com.example.mind.repository;

import com.example.mind.Entries.TestedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 从TestedUser中实现JPA
public interface TestedUserRepository extends JpaRepository<TestedUser, String> {
    // 登录
    Optional<TestedUser> findTestedUserByTestedUserId(String testedUserid);

    // 通过测试人ID获取其名下的所有被测试人员
    List<TestedUser> findAllByUser_UserId(String userUserId);

}
