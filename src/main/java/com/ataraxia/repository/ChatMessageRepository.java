package com.ataraxia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ataraxia.model.ChatMessage;
import com.ataraxia.model.MessageStatus;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, String>{
    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
}
