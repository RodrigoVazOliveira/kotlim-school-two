package br.dev.rvz.forum.controllers.view

import br.dev.rvz.forum.services.TopicService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/reports")
@SecurityRequirement(name = "bearerAuth")
class ReportController(
    private val topicService: TopicService
) {

    @GetMapping
    fun reportTopicByCategory(model: Model): String {
        model.addAttribute(topicService.reports())
        return "report"
    }
}