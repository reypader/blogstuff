Drools Demo Application
===

## How to run
In the `drools-demo-app-remote` folder:
    
```
$ mvn spring-boot:run
```

    
## Testing
Use [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en) to interact with
the API.

Sample GET request:

* `http://localhost:8081/course/suggest?math=7&software=10&electronics=5&arts=10&social_studies=7`
* Change the values as needed.