package com.rmpader.basicsecurity.api.endpoint;

import com.rmpader.basicsecurity.api.resource.CsrfResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author RMPader
 */
@RestController
@RequestMapping("/csrf")
public class CsrfEndpoint {

    @RequestMapping(method = RequestMethod.GET)
    public CsrfResponse getCsrf(HttpServletRequest request) {
        CsrfResponse response = new CsrfResponse();
        response.setCsrf(((CsrfToken) request.getAttribute("_csrf")).getToken());
        return response;
    }

}
