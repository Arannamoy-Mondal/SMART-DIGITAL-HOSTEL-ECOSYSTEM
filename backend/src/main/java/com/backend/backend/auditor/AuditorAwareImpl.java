package com.backend.backend.auditor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.backend.backend.model.User;
import com.backend.backend.repo.UserRepo;



public class AuditorAwareImpl implements AuditorAware<String> {


    @Autowired
    private UserRepo userRepo;
    @Override
    public Optional<String> getCurrentAuditor() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || 
            authentication instanceof AnonymousAuthenticationToken) {
            return Optional.of("SYSTEM".toLowerCase());
        }
        String loginUserName = authentication.getName();
        User user=userRepo.findByUserName(loginUserName).orElse(null);
        if(user==null){
            return Optional.of("SYSTEM".toLowerCase());
        }
        return Optional.of(user.getUserName().toLowerCase());
    }
    
}
