package com.ataraxia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ataraxia.model.ChatRoom;

@Repository
public interface ChatRoomRepository extends CrudRepository <ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
