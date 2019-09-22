package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.User;
import com.codetech.oauth2.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:21 PM
 */
@Repository
public interface UserProfileRepository  extends JpaRepository<UserProfile, Long> {
    UserProfile findByUser(User user);


}
