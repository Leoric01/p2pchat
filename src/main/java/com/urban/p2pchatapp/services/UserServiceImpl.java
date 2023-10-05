package com.urban.p2pchatapp.services;

import com.urban.p2pchatapp.models.LogMessage;
import com.urban.p2pchatapp.models.User;
import com.urban.p2pchatapp.repositories.MessageRepository;
import com.urban.p2pchatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LogMessageService logMessageService;
    private final MessageRepository messageRepository;
    private final Environment env;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LogMessageService logMessageService, MessageRepository messageRepository, Environment env) {
        this.userRepository = userRepository;
        this.logMessageService = logMessageService;
        this.messageRepository = messageRepository;
        this.env = env;
    }

    @Override
    public void addNewUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByNick(String nick) {
        if (userRepository.findByNick(nick).isPresent()){
            return userRepository.findByNick(nick).get();
        }
        return null;
    }

    @Override
    public User getLoggedUser() {
        return userRepository.findAll()
                .stream()
                .filter(User::isLogged)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateUserName(String nick) {
        User user = getLoggedUser();
        user.setNick(nick);
        userRepository.save(user);
    }

    @Override
    public String registerUser(String nick) {
        if (nick == null || nick.isEmpty()){
            return "redirect:/register";
        }
        User user = getUserByNick(nick);
        if (user == null || user.getNick().isEmpty()){
            return "redirect:/register";
        }
        user.setLogged(true);
        addNewUser(user);
        return "redirect:/";
    }

    @Override
    public String mainLogger() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateAndTime = localDateTime.format(formatter);
        String path = "/";
        String method = "GET";
        String logLevel = "INFO";
        String requestData = "";
        LogMessage logMessage = new LogMessage(path,method,dateAndTime,logLevel,requestData);
        logMessageService.addNewLog(logMessage);
        String consolePrint = dateAndTime+" " + logLevel+" Request "+requestData + " " + path + " "+method;
        System.out.println(consolePrint);
        env.getProperty("CHAT_APP_LOGLEVEL");
        return "main";
    }
}
