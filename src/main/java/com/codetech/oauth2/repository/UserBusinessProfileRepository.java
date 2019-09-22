package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.BusinessProfile;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.model.UserBusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:23 PM
 */
@Repository
public interface UserBusinessProfileRepository extends JpaRepository<UserBusinessProfile, Long> {
    boolean existsByBusinessProfileAndUser(Optional<BusinessProfile> businessProfile, Optional<User> user);

    UserBusinessProfile findByBusinessProfile(BusinessProfile oldBusinessProfile);


    List<UserBusinessProfile> findByUserId(Long id);
}
