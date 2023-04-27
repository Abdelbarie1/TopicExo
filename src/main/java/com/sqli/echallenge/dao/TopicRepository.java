package com.sqli.echallenge.dao;


import com.sqli.echallenge.modele.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long>{

}
