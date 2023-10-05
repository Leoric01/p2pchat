package com.urban.p2pchatapp.controllers;

import com.urban.p2pchatapp.models.MessageResult;
import com.urban.p2pchatapp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ApiController {
    private final MessageService messageService;

    @Autowired
    public ApiController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/api/message/receive")
    public ResponseEntity<?> receiveMsg(@RequestBody HashMap<String, HashMap<String, String>> inputJson) {
        MessageResult result = messageService.receiveMsg(inputJson);
        if (result.isSuccess()) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(400).body(result.getError());
        }
    }
}
