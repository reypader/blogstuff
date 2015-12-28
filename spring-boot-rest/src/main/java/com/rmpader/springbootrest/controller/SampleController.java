package com.rmpader.springbootrest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rmpader.springbootrest.exception.SampleTranslatedException;
import com.rmpader.springbootrest.resource.ErrorResource;
import com.rmpader.springbootrest.resource.PostRequest;
import com.rmpader.springbootrest.resource.PostXMLRequest;
import com.rmpader.springbootrest.resource.SampleResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author RMPader
 */
@RestController
@RequestMapping(value = "/sample")
public class SampleController {

    private ObjectMapper objectMapper = new ObjectMapper();

    public SampleController(){
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String sampleGet() {
        return "This is the '/sample' handler for GET requests";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String samplePost() {
        return "This is the '/sample' handler for POST requests";
    }

    @RequestMapping(value = "/get",
                    method = RequestMethod.GET)
    public String unreachable() {
        return "You will never hit this request handler. Why? Because sampleForFooNotBar() has a more strict 'criteria'. See RequestMappingInfo.compareTo()";
    }

    @RequestMapping(value = "/get",
                    method = RequestMethod.GET,
                    params = "foo!=bar")
    public String sampleForFooNotBar() {
        return "You've hit the '/sample/get' request handler where foo should not be bar";
    }

    @RequestMapping(value = "/get",
                    method = RequestMethod.GET,
                    params = "foo=bar")
    public String sampleForFooBar() {
        return "You've hit the '/sample/get' request handler where foo MUST be bar";
    }

    @RequestMapping(value = "/get-with-params",
                    method = RequestMethod.GET)
    public String sampleQueryParam(@RequestParam Map<String, String> params) {
        return "You've hit the '/sample/get-with-params' request handler with the following query params:\n" + params;
    }

    @RequestMapping(value = "/get/{var}",
                    method = RequestMethod.GET)
    public String samplePathVariable(@PathVariable("var") String var) {
        return "You've hit the '/sample/get/{var}' request handler with var=" + var;
    }

    @RequestMapping(value = "/post-with-body",
                    method = RequestMethod.POST)
    public String samplePostBody(@RequestBody PostRequest request) throws JsonProcessingException {
        return "You've hit the '/sample/post-with-body' request handler with the following body:\n"
                + objectMapper.writeValueAsString(request);
    }

    @RequestMapping(value = "/post-with-body",
                    method = RequestMethod.POST,
                    params = "json=true")
    public SampleResource sampleJsonResponse(@RequestBody PostRequest request) throws JsonProcessingException {
        SampleResource resource = new SampleResource();
        resource.setMessage(samplePostBody(request));
        return resource;
    }

    @RequestMapping(value = "/post-strict",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public String sampleStrictJson(@RequestBody PostRequest request) throws JsonProcessingException {
        return "You've hit the '/sample/post-strict' request handler for 'application/json' with the following body:\n"
                + objectMapper.writeValueAsString(request);
    }

    @RequestMapping(value = "/post-strict",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_XML_VALUE)
    public String sampleStrictXml(@RequestBody PostXMLRequest request) throws JsonProcessingException {
        return "You've hit the '/sample/post-strict' request handler for 'application/xml' with the following body (in JSON):\n"
                + objectMapper.writeValueAsString(request);
    }

    @RequestMapping(value = "/upload",
                    method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return "Contents: \n\n" + new String(bytes);
    }

    @RequestMapping(value = "/exception")
    public void error() {
        throw new SampleTranslatedException();
    }

    @ExceptionHandler({JsonProcessingException.class, IOException.class})
    public ResponseEntity<ErrorResource> handleAllExceptions(Exception e) {
        ErrorResource errorResource = new ErrorResource();
        errorResource.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(errorResource);
    }

}
