package com.lazarev.postgresstreamingreplication.service;

import com.lazarev.postgresstreamingreplication.entity.User;
import com.lazarev.postgresstreamingreplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println(actualTransactionActive);
        return userRepository.findAll();
    }

    @Transactional
    public User saveUser(User user){
        boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println(actualTransactionActive);
        return userRepository.save(user);
    }
}
