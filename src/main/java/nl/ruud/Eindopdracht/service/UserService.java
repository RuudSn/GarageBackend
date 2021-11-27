package nl.ruud.Eindopdracht.service;


import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.exception.UserNotFoundException;
import nl.ruud.Eindopdracht.model.Authority;
import nl.ruud.Eindopdracht.model.User;
import nl.ruud.Eindopdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }


    public String createUser(User user) {
        String password = user.getPassword();
        String encrypted = passwordEncoder.encode(password);
        user.setPassword(encrypted);
        User newUser = userRepository.save(user);
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        }
        else { throw new UserNotFoundException(username); }
    }

    public void updateUser(String username, User newUser) {
        if(userRepository.existsById(username)){
            User user = userRepository.findById(username).get();
            String password = user.getPassword();
            String encrypted = passwordEncoder.encode(password);
            user.setPassword(encrypted);
            user.setEmail(newUser.getEmail());
            user.setEnabled(newUser.isEnabled());
            userRepository.save(user);
        }else{throw new RecordNotFoundException();}
        }


    public Set<Authority> getAuthorities(String username) {
            if(userRepository.existsById(username)){
                User user = userRepository.findById(username).get();
                return user.getAuthorities();
            }else{throw new RecordNotFoundException();}
        }



    public void addAuthority(String username, String authority) {
        if (userRepository.existsById(username)) {
            User user = userRepository.findById(username).get();
            Authority newAuthority = new Authority(username, authority);
            user.addAuthority(authority);
            userRepository.save(user);
        } else {
            throw new RecordNotFoundException(); }
    }


    public void removeAuthority(String username, String authority) {
        if (userRepository.existsById(username)) {
            User user = userRepository.findById(username).get();
            user.removeAuthority(authority);
            userRepository.save(user);
        } else {
            throw new RecordNotFoundException(); }
    }



}
