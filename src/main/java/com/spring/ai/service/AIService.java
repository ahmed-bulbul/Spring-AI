package com.spring.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class AIService {


    private final ChatClient client;

    private final SchemaService schemaService;

    @Autowired
    private ObjectMapper objectMapper; // For JSON parsing

    public AIService(ChatClient.Builder client, SchemaService schemaService, ObjectMapper objectMapper) {
        this.client = client.build();
        this.schemaService = schemaService;
        this.objectMapper = objectMapper;
    }

    public Flux<String> analyzeQueryStream(String userQuery) {
        String schemaContext = schemaService.getSchemaContext(); // Cached Schema

        // Updated prompt to include simulated results as well
        String prompt = """
        You are an expert SQL assistant. Convert the user's natural language query into a **valid PostgreSQL SQL query** based on the provided schema.
        Also, simulate the result of the query based on the schema data.

        ## **Database Schema**:
        %s

        ## **User Query**:
        "%s"

        ## **Guidelines**:
        - Use **ONLY** tables and columns from the schema.
        - Ensure the query is **valid PostgreSQL** syntax.
        - **Never make up** table or column names.
        - If the query requires **JOINs**, ensure the correct relationships.
        - If a column stores **timestamps**, use `created_at` or relevant columns for date filtering.
        - **Simulate the results** based on the schema.
        - **Do not return user passwords** or sensitive data.
        - **Format the output strictly as JSON**:
        - ** Output should be actual result of the query.**

        ```json
        {
            "sql": "Generated SQL query here",
            "result": [
                { "column1": "value1", ....continue show until column found ... }
            ]
        }
        ```

    """.formatted(schemaContext, userQuery);

        // Call the model to generate the SQL query and simulated result
        return client.prompt().user(prompt).stream().content();
    }


}
