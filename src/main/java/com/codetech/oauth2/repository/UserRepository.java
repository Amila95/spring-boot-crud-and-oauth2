package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by A Majutharan.
 * Date: 6/16/2019
 * Time: 12:28 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
//    User findById(Long id);
//    Optional<User> findById(Long id);
    User findUserModelById(Long id);

    @Transactional
    @Modifying
    @Query(value ="delete  from oauth_access_token where user_name = :id", nativeQuery = true)
    void deletetoken(@Param("id") String id);
}
