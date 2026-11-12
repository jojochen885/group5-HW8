package com.example.boogle.controller;

import com.example.boogle.service.TavilySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private TavilySearchService tavilySearchService;

    @GetMapping("/search")
    public String goSearch(@RequestParam(name = "q", required = false) String query, Model model) {
        if (query != null && !query.trim().isEmpty()) {
            Map<String, String> results = tavilySearchService.search(query);
            model.addAttribute("results", results);
            model.addAttribute("query", query);
            
            if (results.isEmpty()) {
                model.addAttribute("message", "沒有找到結果");
            }
        }
        return "search"; 
    }
}