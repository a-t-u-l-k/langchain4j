package com.langchain4j.sample.tools;

import com.langchain4j.sample.AiModels;
import com.langchain4j.sample.services.Assistant;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToolTest {
    private final Assistant assistant = AiServices.builder(Assistant.class)
            .chatLanguageModel(AiModels.OLLAMA_CHAT_MODEL)
            .tools(new Calculator())
            //.chatMemory(MessageWindowChatMemory.withMaxMessages(10))
            .build();

    @Test
    void testFromLangChain4JExampleProject() {
        String question = """
        What is the square root of the sum of the numbers of letters
        in the words "hello" and "world"
        """;
        String answer = assistant.chat(question);
        System.out.println(answer);
        assertThat(answer).contains("3.162");
    }
}
