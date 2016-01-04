package com.rmpader.springdrools.data.repository;

import com.rmpader.springdrools.data.domain.RuleArtifact;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author RMPader
 */
public interface RuleArtifactRepository extends PagingAndSortingRepository<RuleArtifact, Long> {

    RuleArtifact findByActiveTrue();

}
