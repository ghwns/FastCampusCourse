package com.example.memorydb.user.db;

import com.example.memorydb.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //SELECT * from user where score >= [??]
    public List<UserEntity> findAllByScoreGreaterThanEqual(int score);

    //SELECT * from user where score >= ?? AND score <=??
    public List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int min, int max);
    @Query(
//            value = "select u from user u where u.score >= ?1 AND u.score <= ?2" //jpql 쿼리
            value = "select * from user as u where u.score >= :min AND u.score <= :max", //-> 복잡한 쿼리문 작성 시 이용
            nativeQuery = true
    )
    List<UserEntity> score(
            @Param(value = "min") int min,
            @Param(value = "max") int max);
}
