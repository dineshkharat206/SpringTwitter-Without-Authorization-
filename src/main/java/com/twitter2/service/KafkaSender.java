package com.twitter2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.twitter2.model.Tweet;


@Service
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String, Tweet> kafkaTemplate;
	
	String kafkaTopic = "Twitter";
	
	public void send(Tweet tweet) {
	    
	    kafkaTemplate.send(kafkaTopic, tweet);
	}
}
