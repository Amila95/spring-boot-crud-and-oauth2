package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/23/2019
 * Time: 11:22 AM
 */
@Repository
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {

}
