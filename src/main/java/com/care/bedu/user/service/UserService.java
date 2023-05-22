package com.care.bedu.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.bedu.user.Dto.User;
import com.care.bedu.user.Dto.UserForm;
import com.care.bedu.user.exception.DuplicateEmailException;
import com.care.bedu.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(UserForm userForm) throws Exception {
        validateDuplicateEmail(userForm.getEmail());
        User user = new User(userForm.getEmail(), passwordEncoder.encode(userForm.getPassword()));
        userRepository.save(user);
    }

    private void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException();
        }
    }
}