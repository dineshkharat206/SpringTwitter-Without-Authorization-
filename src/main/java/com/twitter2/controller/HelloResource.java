package com.twitter2.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.twitter2.model.Tweet;
import com.twitter2.service.KafkaSender;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@RestController
public class HelloResource {
	
	@Autowired
	KafkaSender kafkaSender;
	
	@GetMapping("/hello")
	public String hello1() {
		return "Hello World";
	}
	
	@GetMapping("/callhello")
	public String hello2() {
		
		String url = "http://localhost:8080/hello";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
	
	//@PathVariable("hashtag") String hashtag
	@GetMapping("/{hashtag}")
	public LinkedHashMap<String, List> hello3(@PathVariable("hashtag") String hashtag) {
		
		String url = "https://api.twitter.com/2/tweets/search/recent?query=";
		url = url+hashtag;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + "AAAAAAAAAAAAAAAAAAAAAA%2BtXgEAAAAAjev%2B%2BZsBpuvVOqMreINxgg9%2FM48%3DzYMJjst3FTaI2KYiLagBcCtuIUTmgAiH5sUlomqAUZEgP7vRmI");
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<LinkedHashMap> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, LinkedHashMap.class);
		LinkedHashMap<String, List> tweets = responseEntity.getBody();
		
		//System.out.println(tweets);
		//Set<Entry<String, List>> = tweets.entrySet();
		
		
		Object values =tweets.get("data");
		
		System.out.println(tweets.size());
//		JSONObject jsonObject = new JSONObject(tweets.get("data"));
//		System.out.println(jsonObject.length());
		//System.out.println(jsonObject.get("data"));
		
		//for(int i = 0; jsonObject.get("data"))
		
		//8-27
		//37
		
		//String id = jsonObject.getString("id");
		
		//System.out.println(id);
		//System.out.println(values.get(0).toString().charAt(0));
		System.out.println(tweets.get("data").size());
		//System.out.println(values.toString());
		
		for(int i = 0; i<tweets.get("data").size(); i++) {
			String id = tweets.get("data").get(i).toString().substring(4, 23);
			int length = tweets.get("data").get(i).toString().length();
			String text = tweets.get("data").get(i).toString().substring(30,length-1);
			System.out.println(id+" "+text);
			
			Tweet t = new Tweet();
			t.setId(id);
			t.setText(text);
			kafkaSender.send(t);
		}
		
		return tweets;
		
	}
	
	
	

}

