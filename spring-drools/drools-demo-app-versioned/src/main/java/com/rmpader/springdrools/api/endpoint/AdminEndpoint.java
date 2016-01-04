package com.rmpader.springdrools.api.endpoint;

import com.rmpader.springdrools.api.resource.ArtifactActivationRequest;
import com.rmpader.springdrools.api.resource.RuleArtifactRequest;
import com.rmpader.springdrools.service.RuleAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author RMPader
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminEndpoint {

    @Autowired
    private RuleAdminService ruleAdminService;

    @RequestMapping(value = "/rules/activate",
                    method = RequestMethod.POST)
    public void activateRuleArtifact(
            @RequestBody ArtifactActivationRequest artifactActivationRequest) throws IOException {
        ruleAdminService.activateRuleArtifact(artifactActivationRequest);
    }

    @RequestMapping(value = "/rules/add",
                    method = RequestMethod.POST)
    public void addRuleArtifact(@RequestBody RuleArtifactRequest ruleArtifactRequest) throws IOException {
        ruleAdminService.addRuleArtifact(ruleArtifactRequest);
    }

}
