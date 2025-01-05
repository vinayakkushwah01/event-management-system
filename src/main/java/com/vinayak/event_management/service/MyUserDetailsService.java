package com.vinayak.event_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinayak.event_management.entity.MyUserDetails;
import com.vinayak.event_management.entity.User;
import com.vinayak.event_management.repository.UserRepository;




@Service
public class MyUserDetailsService  implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user ==null){
            System.out.println("The user with Username "+ username +"not found ");
            throw new UsernameNotFoundException("The user with Username "+ username +"not found ");
        }

        return new MyUserDetails(user);
    }

}
