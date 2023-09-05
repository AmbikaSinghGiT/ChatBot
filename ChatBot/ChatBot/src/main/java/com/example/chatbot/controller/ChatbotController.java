package com.example.chatbot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatbot.entity.ChatbotModel;
import com.example.chatbot.repository.ChatbotRepo;


@CrossOrigin(origins = "*",allowCredentials = "false",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")



public class ChatbotController {
	@Autowired
	private ChatbotRepo  addbanks;
	@GetMapping("/addBank")
	public List<ChatbotModel> getallBank(){
		return addbanks.findAll();
	}
	@Autowired
	private ChatbotRepo repository;
	
	@GetMapping("/addBank/{id}")
	public ChatbotModel getCustomerBYID(@PathVariable Long id) {
		Optional<ChatbotModel> add = addbanks.findById(id);
		if(add.isPresent()) {
			return add.get();
		}
		throw new RuntimeException("customer is not found"+id);
	}
//	@PostMapping(value="/saveData")
//    public ResponseEntity<?> saveData(@RequestBody ChatbotModel cmodel) {
//        service.saveDetails(cmodel);
//        return ResponseEntity.ok().build();
//    }
	 @PostMapping("/saveData")
	    public ChatbotModel saveData(@RequestBody ChatbotModel cmodel) {
	        return repository.save(cmodel);
	    }
	@DeleteMapping("/addBank/{id}")
	public void delete(@PathVariable Long id) {
		addbanks .deleteById(id);
	}
}
