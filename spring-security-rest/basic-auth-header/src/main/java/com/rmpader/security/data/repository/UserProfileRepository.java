package com.rmpader.security.data.repository;

import com.rmpader.security.data.model.UserProfile;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author RMPader
 */
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, String> {

}
