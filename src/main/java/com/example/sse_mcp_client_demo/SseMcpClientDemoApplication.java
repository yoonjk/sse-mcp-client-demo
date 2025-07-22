package com.example.sse_mcp_client_demo;

import com.example.sse_mcp_client_demo.service.AiService;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SseMcpClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SseMcpClientDemoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(List<McpSyncClient> clients, AiService aiService) {
//		return args -> {
//			McpSyncClient mcpSyncClient = clients.get(0);
//			McpSchema.ListToolsResult listToolsResult = mcpSyncClient.listTools();
//			listToolsResult.tools().stream().map(McpSchema.Tool::name).forEach( System.out::println);
//
//			McpSchema.CallToolResult getCurrentDateTime = mcpSyncClient.callTool(new McpSchema.CallToolRequest("getCurrentDateTime", "{}"));
//			getCurrentDateTime.content().stream().map(Object::toString).forEach(System.out::println);
//
//			String params = """
//				{
//				"number1" : 5,
//				"number2" : 5
//				}
//			""";
//
//			McpSchema.CallToolResult multipleNumbers = mcpSyncClient.callTool(new McpSchema.CallToolRequest("multipleNumbers", params));
//			multipleNumbers.content().stream().map(Object::toString).forEach(System.out::println);
//
//			String complete = aiService.complete("5*4+7=? Use tools if possible");
//			System.out.println(complete);
//		};
//	}

	@Bean
	@Qualifier("my-mcp-server-callback-tool-provider")
	public SyncMcpToolCallbackProvider toolCallbackProvider(List<McpSyncClient> mcpSyncClients) {
		return new SyncMcpToolCallbackProvider(mcpSyncClients);
	}
}
