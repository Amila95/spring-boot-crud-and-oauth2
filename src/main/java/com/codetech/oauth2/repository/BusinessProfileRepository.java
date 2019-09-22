package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:22 PM
 */
@Repository
public interface BusinessProfileRepository extends JpaRepository<BusinessProfile, Long> {
    BusinessProfile findProfileById(Long id);

    boolean existsByEmailId(String emailId);


}
