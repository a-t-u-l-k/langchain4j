package com.langchain4j.sample.services;

import com.langchain4j.sample.AiModels;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

class TranslatorTest {
//    private final ChatLanguageModel model = OpenAiChatModel.builder()
//            .apiKey(ApiKeys.OPENAI_API_KEY)
//            .timeout(ofSeconds(60))
//            .build();
//ChatLanguageModel model = OllamaChatModel.builder()
//    .baseUrl("http://localhost:11434")
//    .modelName("llama3.2")
//    .logRequests(true)
//    .logResponses(true)
//    .build();

    @Test
    void translateUsingService() {
        Translator translator = AiServices.create(Translator.class,
                AiModels.OLLAMA_CHAT_MODEL);

        String italian = translator.translate("Hello, how are you?", "Italian");
        System.out.println(italian);

//        assertThat(italian).isEqualTo("Ciao, come stai?");
    }

    @Test
    void usePromptTemplateInstead() {
        PromptTemplate promptTemplate = PromptTemplate.from("Say '{{text}}' in {{language}}.");
        Prompt prompt = promptTemplate.apply(
                Map.ofEntries(
                        Map.entry("text", "Hello, how are you?"),
                        Map.entry("language", "Italian")));

        String italian = AiModels.OLLAMA_CHAT_MODEL.generate(prompt.text());
        System.out.println(italian);
        assertThat(italian).contains("Ciao");
    }
}