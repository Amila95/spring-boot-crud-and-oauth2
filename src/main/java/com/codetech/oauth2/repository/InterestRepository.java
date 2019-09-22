package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/28/2019
 * Time: 9:25 AM
 */
@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
}
