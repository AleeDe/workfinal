package com.journal.Journal.Controller;

import com.journal.Journal.Service.UserEntryService;
import com.journal.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserEntryService userEntryService;
    @PostMapping("/create_user")
    public ResponseEntity<Boolean> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userEntryService.saveEntry(user), HttpStatus.CREATED);
    }
}
