package com.twitter2.repo;

import org.springframework.data.repository.CrudRepository;

import com.twitter2.model.Tweet;

public interface TweetRepo extends CrudRepository<Tweet, String>{

}
