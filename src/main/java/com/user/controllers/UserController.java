package com.user.controllers;

import com.user.dto.UserDto;
import com.user.models.Contact;
import com.user.models.User;
import com.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id)
    {
        log.info("Request came on the getUser controller");
        UserDto temporaryUser = this.userService.getUser(id);

        List<Contact> listOfContact = this.restTemplate.getForObject("http://CONTACT-SERVICE/contact/user/"+id, List.class);
        temporaryUser.setContact(listOfContact);
        return ResponseEntity.status(HttpStatus.OK).body(temporaryUser);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user)
    {
        log.info("Request came on the addUser controller");
        User temporaryUser = this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(temporaryUser);

    }
}
