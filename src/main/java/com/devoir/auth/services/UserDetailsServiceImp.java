package com.devoir.auth.services;


import com.devoir.auth.models.MyUserDetails;
import com.devoir.auth.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findFirstByEmail(email);

        user.orElseThrow(()->new UsernameNotFoundException("Not found:" + email));
        final MyUserDetails myUserDetails = user.map(MyUserDetails::new).get();

        return myUserDetails;
    }
}
