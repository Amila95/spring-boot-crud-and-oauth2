package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.BusinessRegistionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/26/2019
 * Time: 2:00 PM
 */
@Repository
public interface BusinessRegistionTypeRepository extends JpaRepository<BusinessRegistionType, Long> {
}
