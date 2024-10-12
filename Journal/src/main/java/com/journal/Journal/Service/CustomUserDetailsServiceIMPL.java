package com.journal.Journal.Service;

import com.journal.Journal.Repository.UserEntryRepository;
import com.journal.Journal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsServiceIMPL implements UserDetailsService {

    @Autowired
    UserEntryRepository userEntryRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userEntryRepository.findByuserName(username);
        if(user!=null){
            return org.springframework.security.core.userdetails
                    .User.builder().username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User Not Found  with username: " +  username);
    }
}
