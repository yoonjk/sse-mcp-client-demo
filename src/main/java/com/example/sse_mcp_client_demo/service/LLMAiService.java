package com.example.sse_mcp_client_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LLMAiService implements AiService{
    private final ChatClient chatClient;
    private final SyncMcpToolCallbackProvider syncMcpToolCallbackProvider;
    public LLMAiService(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider, @Qualifier("my-mcp-server-callback-tool-provider")SyncMcpToolCallbackProvider syncMcpToolCallbackProvider) {
        this.chatClient = builder.build();
        this.syncMcpToolCallbackProvider = syncMcpToolCallbackProvider;
    }

    @Override
    public String complete(String message) {
        return this.chatClient.prompt().user(message)
                .toolCallbacks(syncMcpToolCallbackProvider)
                .call()
                .content();
    }
}
