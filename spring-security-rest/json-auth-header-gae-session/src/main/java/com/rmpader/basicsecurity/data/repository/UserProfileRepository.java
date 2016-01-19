package com.rmpader.basicsecurity.data.repository;

import com.rmpader.basicsecurity.data.model.UserProfile;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author RMPader
 */
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, String> {

}
