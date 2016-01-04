Drools Demo Application
===

## How to run
In the `drools-demo-app-versioned` folder:
    
```
$ mvn spring-boot:run
```

    
## Testing
Use [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) to interact with
the API.

Sample GET request:

* `http://localhost:8081/course/suggest?math=7&software=10&electronics=5&arts=10&social_studies=7`
    
Updating rules:

* Create, build and deploy the new rule.
* Send a POST request to `http://localhost:8081/admin/rules/add` with the following body:
```
    {
        "groupId":"com.rmpader",
        "artifactId":"course-suggestion",
        "version":"2.0"
    }
```
* Get the list of rule versions with `GET http://localhost:8081/admin/rules`.
* Activate the rule version you wish to use with `POST http://localhost:8081/admin/rules/activate` and the following body:
```
    {
        "id":<insert ID here>
    }
```
* Change the values as needed.