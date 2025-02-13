package com.spring.ai.controller;

import org.springframework.ui.Model;
import com.spring.ai.model.ChartData;
import com.spring.ai.model.QueryAnalysis;
import com.spring.ai.model.QueryRequest;
import com.spring.ai.service.AIService;
import com.spring.ai.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
public class DashboardController {

    @Autowired
    private AIService aiService;

    @Autowired
    private DataService dataService;


    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("query", new QueryRequest());
        return "dashboard";
    }

    @GetMapping("/query")
    @ResponseBody
    public Flux<String> handleQuery(@RequestParam String query) {
        return aiService.analyzeQueryStream(query);
    }
}