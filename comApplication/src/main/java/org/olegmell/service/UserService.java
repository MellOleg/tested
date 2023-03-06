package org.olegmell.service;

import freemarker.template.utility.StringUtil;
import org.olegmell.domain.Types.Role;
import org.olegmell.domain.User;
import org.olegmell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

public List<org.olegmell.domain.User> findAll() {
        return userRepository.findAll();
    }


    public void saveUser(User user, Map<String, String> form, String username) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();
        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void updateProfile(User user, String password) {
        String userPassword = user.getPassword();

        boolean isPasswordChanged = (password != null && password.equals(userPassword) ||
                (userPassword != null && userPassword.equals(password)));
        if (isPasswordChanged){
            user.setPassword(password);
        }
        userRepository.save(user);
    }
}

