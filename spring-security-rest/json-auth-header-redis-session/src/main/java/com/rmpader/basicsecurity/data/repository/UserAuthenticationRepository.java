package com.rmpader.basicsecurity.data.repository;

import com.rmpader.basicsecurity.data.model.UserAuthentication;
import org.springframework.data.repository.CrudRepository;

/**
 * @author RMPader
 */
public interface UserAuthenticationRepository extends CrudRepository<UserAuthentication, String> {

}
