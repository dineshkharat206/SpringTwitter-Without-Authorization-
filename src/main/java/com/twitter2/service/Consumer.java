package com.twitter2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter2.consumer.consumerService;
import com.twitter2.model.Tweet;

import lombok.extern.slf4j.Slf4j;

@Component
//@Service
@Slf4j
public class Consumer {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	consumerService consumerService;
	

	
	@KafkaListener(topics="Twitter")
	public void consumeFromTopic(Tweet record) throws JsonMappingException, JsonProcessingException {
		 
		log.info("Consumer record" + record);
		consumerService.process(record);
		//System.out.println(e.toString());
		//employeeRepository.save(emp);
		
	}

}

