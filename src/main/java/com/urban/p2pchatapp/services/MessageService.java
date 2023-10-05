package com.urban.p2pchatapp.services;

import com.urban.p2pchatapp.models.Message;
import com.urban.p2pchatapp.models.MessageResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface MessageService {
    List<Message> allMsgsSortedByTimeStamp(List<Message> messages);
    void sendMessage(String msgTxt);
    MessageResult receiveMsg(HashMap<String, HashMap<String, String>> inputJson);
}
