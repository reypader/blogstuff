Basic Authentication with Custom User Profile Sample
----

Sample application to demonstrate how to configure an application that uses basic HTTP authentication via `Authorization: Basic xxxx`.
The application also contains sample configurations for declaring Users with complex profile structures as opposed to Spring's default User class.

## Package structure

* `com.rmpader.security.api.endpoint` contains classes annotated with `@RestController`
* `com.rmpader.security.api.resource` contains request and response classes that are serialized/deserialized.
* `com.rmpader.config` contains classes annotated with `@Configuration`
* `com.rmpader.data` contains JPA entity classes and repositories
* `com.rmpader.security.util` contains classes that extend, implement, or otherwise supplement Spring Security features.

## How to run

Before running, make sure that you setup your MySQL server locally and update the values in `src/main/resources/application.properties`.
Also, make sure that the values in `<root_folder>/liquibase/liquibase.properties` are correct.

In the terminal:

```
    $ mvn spring-boot:run
```

### How to test

1. Install [Postman Chrome Extension](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en).
2. Import the `sample.json.postman_collection` file.
3. Execute requests as you please.

**The existing user is admin/password**
