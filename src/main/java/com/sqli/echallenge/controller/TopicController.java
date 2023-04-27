package com.sqli.echallenge.controller;

import com.sqli.echallenge.modele.Topic;
import com.sqli.echallenge.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TopicController {

    final TopicService topicService;
    final Logger logger = LoggerFactory.getLogger(TopicController.class);

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping( method = RequestMethod.GET, value = "/topics")
    public String getAllTopics(Model model) {
        logger.debug("I am in getAllTopics");
        List<Topic> topics=topicService.getAllTopics();
        model.addAttribute("topics",topics);
        model.addAttribute("topic",new Topic());
        return "/home";
    }


    @RequestMapping(method = RequestMethod.GET,value = "/topics/{id}")
    public Topic getTopics(@PathVariable Long id) {
        return topicService.getTopic(id).get();
    }

    @RequestMapping(method = RequestMethod.POST, value ="/topics")
    public String addTopic(@ModelAttribute Topic topic) {
        topicService.addTopic(topic);
        System.out.println(topic);
        return "redirect:/topics";
    }

    @RequestMapping(method = RequestMethod.PUT, value ="/topics/update/{id}")
    public void updateTopic(@RequestBody Topic topic, @PathVariable Long id) {
        logger.debug("I am in updateTopic");
        topicService.updateTopic(id, topic);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/topics/update/{id}")
    public String updateTopic(Model model , @PathVariable Long id){
        Optional<Topic> topic=topicService.getTopic(id);
        model.addAttribute("topic",topic);
        return "/updateTopic";
    }
    @RequestMapping(method = RequestMethod.GET, value ="/topics/delete/{id}")
    public String deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return "redirect:/topics";

    }

}
