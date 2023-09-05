package com.example.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.chatbot.entity.ChatbotModel;
@Component
@Repository

public interface ChatbotRepo extends JpaRepository<ChatbotModel, Long>{

}
