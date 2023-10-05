package com.urban.p2pchatapp.services;

import com.urban.p2pchatapp.models.LogMessage;
import com.urban.p2pchatapp.repositories.LogMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogMessageServiceImpl implements LogMessageService{

    private final LogMessageRepository logMessageRepository;
    @Autowired
    public LogMessageServiceImpl(LogMessageRepository logMessageRepository) {
        this.logMessageRepository = logMessageRepository;
    }


    @Override
    public void addNewLog(LogMessage logMessage) {
        logMessageRepository.save(logMessage);
    }
}
