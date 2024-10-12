package com.journal.Journal.Controller;

import com.journal.Journal.Service.JournalEntryService;
import com.journal.Journal.Service.UserEntryService;
import com.journal.Journal.entity.Journalentry;
import com.journal.Journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;






    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getName();

        User old = userEntryService.findByUserName(username);
        if (old != null) {
            old.setUserName(user.getUserName());
            old.setPassword(user.getPassword());
            userEntryService.saveEntry(old);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> updateUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getName();

        User old = userEntryService.findByUserName(username);
        if (old != null) {
            userEntryService.delByUsername(old);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
