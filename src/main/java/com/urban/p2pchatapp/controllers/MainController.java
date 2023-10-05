package com.urban.p2pchatapp.controllers;

import com.urban.p2pchatapp.models.User;
import com.urban.p2pchatapp.repositories.MessageRepository;
import com.urban.p2pchatapp.services.MessageService;
import com.urban.p2pchatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final UserService userService;
    private final MessageRepository messageRepository;
    private final MessageService messageService;
    @Autowired
    public MainController(UserService userService, MessageRepository messageRepository, MessageService messageService) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.messageService = messageService;
    }
    @GetMapping("/")
    public String mainPage(Model model){
        User user = userService.getLoggedUser();
        if(user == null){
            return "redirect:/register";
        }
        model.addAttribute("messages", messageService.allMsgsSortedByTimeStamp(messageRepository.findAll()));
        model.addAttribute("currentName", user.getNick());
        return userService.mainLogger();
    }
    @GetMapping("/register")
    public String registerPage(){
        return "register-page";
    }

    @PostMapping("/register")
    public String registerPost(@RequestParam String nick){
        return userService.registerUser(nick);
    }

    @PostMapping("/updateName")
    public String updateName(@RequestParam String nick){
        userService.updateUserName(nick);
        return "redirect:/";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String message){
        messageService.sendMessage(message);
        return "redirect:/";
    }
}
