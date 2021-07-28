package com.example.demo2.service;

import com.example.demo2.config.bruteforce.LoginAttemptService;
import com.example.demo2.domain.User;
import com.example.demo2.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    private final LoginAttemptService loginAttemptService;

    private final HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new UsernameNotFoundException("IP blocked");
        }

        User user = userRepository.findByUid(name).orElseThrow(() -> new UsernameNotFoundException("user is not exists"));
        detailsChecker.check(user);
        return user;
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
