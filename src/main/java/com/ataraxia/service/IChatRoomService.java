package com.ataraxia.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IChatRoomService {
    
    public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExist);
}
