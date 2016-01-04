package com.rmpader.springdrools.service;

import com.rmpader.springdrools.api.exception.InvalidArtifactException;
import com.rmpader.springdrools.api.resource.AddRuleArtifactRequest;
import com.rmpader.springdrools.api.resource.ArtifactActivationRequest;
import com.rmpader.springdrools.api.resource.RuleArtifactResponse;
import com.rmpader.springdrools.config.property.RulesProperties;
import com.rmpader.springdrools.data.domain.RuleArtifact;
import com.rmpader.springdrools.data.repository.RuleArtifactRepository;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.rmpader.springdrools.util.RuleUtil.createPath;

/**
 * @author RMPader
 */
@Service
public class RuleAdminService {

    @Autowired
    private RulesProperties rulesProperties;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private RuleArtifactRepository ruleArtifactRepository;
    private List<RuleArtifactResponse> allArtifacts;

    @Transactional
    public void activateRuleArtifact(ArtifactActivationRequest artifactActivationRequest) throws IOException {
        RuleArtifact ruleArtifact = null;

        Iterable<RuleArtifact> artifacts = ruleArtifactRepository.findAll();

        for (RuleArtifact artifact : artifacts) {
            if (artifact.getId()
                        .equals(artifactActivationRequest.getId())) {
                artifact.setActive(true);
                ruleArtifact = artifact;
            } else {
                artifact.setActive(false);
            }
        }
        ruleArtifactRepository.save(artifacts);

        if (ruleArtifact != null) {
            KieServices ks = KieServices.Factory.get();
            KieRepository repo = ks.getRepository();
            KieResources resources = ks.getResources();

            String url = rulesProperties.getRulesRepoPath()
                    + createPath(ruleArtifact.getGroupId(),
                                 ruleArtifact.getArtifactId(),
                                 ruleArtifact.getVersion());

            UrlResource urlResource = (UrlResource) resources
                    .newUrlResource(url);
            urlResource.setUsername(rulesProperties.getUsername());
            urlResource.setPassword(rulesProperties.getPassword());
            urlResource.setBasicAuthentication("enabled");
            InputStream is = urlResource.getInputStream();
            KieModule k = repo.addKieModule(resources.newInputStreamResource(is));
            kieContainer.updateToVersion(k.getReleaseId());
        } else {
            throw new InvalidArtifactException();
        }
    }

    public void addRuleArtifact(AddRuleArtifactRequest addRuleArtifactRequest) {
        RuleArtifact ruleArtifact = new RuleArtifact(addRuleArtifactRequest.getGroupId(),
                                                     addRuleArtifactRequest.getArtifactId(),
                                                     addRuleArtifactRequest.getVersion());

        ruleArtifactRepository.save(ruleArtifact);
    }

    public List<RuleArtifactResponse> getAllArtifacts() {
        Iterable<RuleArtifact> artifacts = ruleArtifactRepository.findAll();
        List<RuleArtifactResponse> response = new ArrayList<>();

        for (RuleArtifact artifact : artifacts) {
            RuleArtifactResponse r = new RuleArtifactResponse();
            r.setId(artifact.getId()
                            .toString());
            r.setGroupId(artifact.getGroupId());
            r.setArtifactId(artifact.getArtifactId());
            r.setVersion(artifact.getVersion());
            response.add(r);
        }

        return response;
    }
}
