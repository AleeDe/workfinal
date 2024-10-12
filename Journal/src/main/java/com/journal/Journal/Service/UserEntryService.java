package com.journal.Journal.Service;

import com.journal.Journal.Repository.UserEntryRepository;
import com.journal.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;
    private static final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();


    public boolean saveEntry(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
        return true;
    }

    public List<User> GetAll(){
        return userEntryRepository.findAll();
    }

    public User findByUserName(String userName){
        return userEntryRepository.findByuserName(userName);
    }

    public void delByUsername(User user){
        userEntryRepository.delete(user);
    }







}
