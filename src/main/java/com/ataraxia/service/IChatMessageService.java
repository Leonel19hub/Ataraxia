package com.ataraxia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ataraxia.model.ChatMessage;
import com.ataraxia.model.MessageStatus;

@Service
public interface IChatMessageService {
    
    public ChatMessage save(ChatMessage chatMessage);
    public long countNewMessages(String senderId, String recipientId);
    public List<ChatMessage> findChatMessage(String senderId, String recipientId);
    public ChatMessage findById(String id);
    public void updateStatuses(String senderId, String recipientId, MessageStatus status);
}
