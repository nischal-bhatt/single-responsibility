package com.company.singleresp;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
//what is this class supposed to do ?
  //receive request from client - hand that request to rest of the application
  // not supposed to have any business logic 
//what is it doing right now ? 
//Handles incoming JSON requests that work on User resource/entity
// this UserController is violating the single responsibility principle ! 
public class UserController {
	//Store used by controller
    private UserPersistenceService userPersistenceService 
    = new UserPersistenceService();
    
    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //does it still need to parse!! 
        User user = mapper.readValue(userJson, User.class);

        UserValidator validator = new UserValidator();
        boolean valid = validator.validateUser(user);
        //if validation logic changes --> then usercontroller changes -- coz it needs to modify validations! 
        //the controller is validating the object! 
        if(!valid) {
            return "ERROR";
        }

        // if we start using an actual database, our user controller needs to change! 
        // and it is also taking care of storage! omg! 
        userPersistenceService.saveUser(user);
        
        return "SUCCESS";
    } 

   

}