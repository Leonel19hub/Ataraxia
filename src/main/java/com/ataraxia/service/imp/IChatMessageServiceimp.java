package com.ataraxia.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ataraxia.exception.ResourceNotFoundException;
import com.ataraxia.model.ChatMessage;
import com.ataraxia.model.MessageStatus;
import com.ataraxia.repository.ChatMessageRepository;
import com.ataraxia.service.IChatMessageService;
import com.ataraxia.service.IChatRoomService;

@Service
public class IChatMessageServiceimp implements IChatMessageService{

    @Autowired
    private ChatMessageRepository repository;

    @Autowired
    private IChatRoomService chatRoomService; 

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public List<ChatMessage> findChatMessage(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages = chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

        if (messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    @Override
    public ChatMessage findById(String id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(()-> new ResourceNotFoundException("NO se pudo encontar el mensage (" + id + ")"));
    }

    @Override
    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        
        // ENCONTRAR UNA MENERA DE HACERLO CON MYSQL
        // Query query = new Query(
        //         Criteria
        //                 .where("senderId").is(senderId)
        //                 .and("recipientId").is(recipientId));
        // Update update = Update.update("status", status);
        // mongoOperations.updateMulti(query, update, ChatMessage.class);
    }
    
}
