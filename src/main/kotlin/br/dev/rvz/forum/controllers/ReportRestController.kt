package br.dev.rvz.forum.controllers

import br.dev.rvz.forum.models.dto.topics.TopicReportDTO
import br.dev.rvz.forum.services.TopicService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reports")
@SecurityRequirement(name = "bearerAuth")
class ReportRestController(
    private val topicService: TopicService
) {

    @GetMapping
    fun reportTopicByCategory(): List<TopicReportDTO> {
        return topicService.reports()
    }
}