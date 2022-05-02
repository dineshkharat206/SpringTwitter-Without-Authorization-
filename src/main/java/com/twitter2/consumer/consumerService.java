package com.twitter2.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter2.model.Tweet;
import com.twitter2.repo.TweetRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class consumerService {
	
	@Autowired
    ObjectMapper objectMapper;
	
	@Autowired
    private TweetRepo tweetRepo;
	
	public void process(Tweet record) throws JsonProcessingException {
        log.info("emp event" + record);
        tweetRepo.save(record);
    }

}
