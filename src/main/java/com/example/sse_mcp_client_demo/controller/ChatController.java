package com.example.sse_mcp_client_demo.controller;

import com.example.sse_mcp_client_demo.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class ChatController {

    private final AiService aiService;

    public ChatController(AiService aiService) {
        this.aiService = aiService;
    }


    @GetMapping("/")
    public String index() {
        return "chat";
    }

    /**
     * chatting message
     * @param message
     * @param model
     * @return
     */
    @PostMapping("/chat/message")
    public String sendMessage(@RequestParam("message") String message, Model model) {
        log.info("chat message:{}", message);
        String response = aiService.complete(message);

        model.addAttribute("userMessage", message);
        model.addAttribute("aiResponse", response);
        return "chat-message :: messageFragment";
    }
}