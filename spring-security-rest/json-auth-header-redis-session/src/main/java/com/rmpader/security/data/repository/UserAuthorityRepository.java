package com.rmpader.security.data.repository;

import com.rmpader.security.data.model.UserAuthority;
import org.springframework.data.repository.CrudRepository;

/**
 * @author RMPader
 */
public interface UserAuthorityRepository extends CrudRepository<UserAuthority, UserAuthority.UserAuthoritiesId> {

}
