package com.klass.lab01.services;

import com.klass.lab01.dto.LocalUserDetail;
import com.klass.lab01.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {
    @Autowired
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var userOpt = userService.findUserByUsername(username);
       if(userOpt.isPresent()) {
           var user = userOpt.get();
           return new LocalUserDetail(user);
       }else {
           throw new UsernameNotFoundException("Username not found");
       }
    }

}
