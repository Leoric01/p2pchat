package com.urban.p2pchatapp.services;

import com.urban.p2pchatapp.models.LogMessage;
import org.springframework.stereotype.Service;

@Service
public interface LogMessageService {
    void addNewLog(LogMessage logMessage);

}
