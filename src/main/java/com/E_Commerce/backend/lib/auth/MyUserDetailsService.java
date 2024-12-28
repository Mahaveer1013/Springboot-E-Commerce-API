package com.E_Commerce.backend.lib.auth;

import com.E_Commerce.backend.model.Users;
import com.E_Commerce.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    private UserRepository userRepo;

    static public UserDetails getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || !(authentication.getPrincipal() instanceof UserDetails)) {
            logger.warn("No authenticated user found in the security context");
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            logger.warn("User Not Found");
            throw new UsernameNotFoundException("user not found"); //delete the token if /user gets this error
        }

        return new UserPrincipal(user);
    }
}