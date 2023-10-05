package com.urban.p2pchatapp.services;

import com.urban.p2pchatapp.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void addNewUser(User user);
    User getUserByNick(String nick);
    User getLoggedUser();
    void updateUserName(String nick);
    String registerUser(String nick);
    String mainLogger();
}
