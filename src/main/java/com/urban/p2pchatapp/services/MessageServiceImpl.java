package com.urban.p2pchatapp.services;

import com.urban.p2pchatapp.models.Message;
import com.urban.p2pchatapp.models.MessageResult;
import com.urban.p2pchatapp.models.User;
import com.urban.p2pchatapp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public List<Message> allMsgsSortedByTimeStamp(List<Message> messages) {
        return null;
    }

    @Override
    public void sendMessage(String msgTxt) {

    }

    @Override
    public MessageResult receiveMsg(HashMap<String, HashMap<String, String>> inputJson) {
        HashMap<String, String> messageJson = inputJson.get("message");
        HashMap<String, String> clientJson = inputJson.get("client");

        List<String> missingFields = new ArrayList<>();

        if (messageJson == null || messageJson.isEmpty()) {
            missingFields.add("message");
        } else {
            if (StringUtils.isEmpty(messageJson.get("id"))) {
                missingFields.add("message.id");
            }
            if (StringUtils.isEmpty(messageJson.get("username"))) {
                missingFields.add("message.username");
            }
            if (StringUtils.isEmpty(messageJson.get("text"))) {
                missingFields.add("message.text");
            }
            if (StringUtils.isEmpty(messageJson.get("timestamp"))) {
                missingFields.add("message.timestamp");
            }
        }

        if (clientJson == null || clientJson.isEmpty()) {
            missingFields.add("client");
        } else {
            if (StringUtils.isEmpty(clientJson.get("id"))) {
                missingFields.add("client.id");
            }
        }

        if (!missingFields.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Missing field(s): ");
            for (int i = 0; i < missingFields.size(); i++) {
                errorMessage.append(missingFields.get(i));
                if (i < missingFields.size() - 1) {
                    errorMessage.append(", ");
                }
            }

            return new MessageResult(false, errorMessage.toString());
        }

        String messageIdStr = messageJson.get("id");
        String username = messageJson.get("username");
        String text = messageJson.get("text");
        String timestamp = messageJson.get("timestamp");

        long messageId;
        try {
            messageId = Long.parseLong(messageIdStr);
        } catch (NumberFormatException e) {
            return new MessageResult(false, "Invalid message ID format");
        }

        User user = userService.getUserByNick(username);
        if (user == null) {
            user = new User(username);
            userService.addNewUser(user);
        }

        Message message = new Message(messageId, text, timestamp, user);
        messageRepository.save(message);

        return new MessageResult(true, null);
    }
}
