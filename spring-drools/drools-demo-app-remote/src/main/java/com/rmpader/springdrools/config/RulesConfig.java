package com.rmpader.springdrools.config;

import com.rmpader.springdrools.config.property.RulesProperties;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author RMPader
 */
@Configuration
public class RulesConfig {

    @Autowired
    private RulesProperties rulesProperties;

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices ks = KieServices.Factory.get();

        KieResources resources = ks.getResources();
        String url = rulesProperties.getRulesRepoPath() + "com/rmpader/course-suggestion/1.0/course-suggestion-1.0.jar";
        UrlResource urlResource = (UrlResource) resources.newUrlResource(url);
        urlResource.setUsername(rulesProperties.getUsername());
        urlResource.setPassword(rulesProperties.getPassword());
        urlResource.setBasicAuthentication("enabled");
        InputStream stream = urlResource.getInputStream();

        KieRepository repo = ks.getRepository();
        KieModule k = repo.addKieModule(resources.newInputStreamResource(stream));

        return ks.newKieContainer(k.getReleaseId());
    }
}
