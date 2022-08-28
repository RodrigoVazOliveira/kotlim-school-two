package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.models.Topic
import br.dev.rvz.forum.services.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicRestController(val topicService: TopicService) {

    @GetMapping
    fun list(): List<Topic> {
        return topicService.list()
    }
}