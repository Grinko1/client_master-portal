package com.clientmaster.app.user.service;

import com.clientmaster.app.config.user.UserDetailsImpl;
import com.clientmaster.app.user.entity.MyUser;
import com.clientmaster.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByEmail(email);
        return user.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }
    public MyUser saveUser(MyUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
      return  userRepository.save(user);
    }
    public List<MyUser> getAllUsers(){
        return userRepository.findAll();
    }

}
