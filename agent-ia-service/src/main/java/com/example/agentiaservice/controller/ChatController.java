package com.example.agentiaservice.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;
    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("You are a helpful assistant that can answer questions about products and stock. " +
                        "Use the available tools to get real-time information when the user asks for it. " +
                        "Do not make up information about products or stock levels.")
                .build();
    }

    @PostMapping(value = "/stream", produces = "text/event-stream")
    public Flux<String> stream(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}