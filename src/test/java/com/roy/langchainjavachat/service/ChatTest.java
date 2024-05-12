package com.roy.langchainjavachat.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.Test;

import java.util.List;

import static dev.langchain4j.data.message.SystemMessage.systemMessage;
import static dev.langchain4j.data.message.UserMessage.userMessage;
import static java.util.Arrays.asList;

public class ChatTest {

    @Test
    public void StreamChat() {
        OpenAiStreamingChatModel model = OpenAiStreamingChatModel.withApiKey("demo");
        List<ChatMessage> messages = asList(
                systemMessage("You are a very sarcastic assistant"),
                userMessage("Tell me a joke")
        );

        model.generate(messages, new StreamingResponseHandler<AiMessage>() {

            @Override
            public void onNext(String token) {
                System.out.println("New token: '" + token + "'");
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                System.out.println("Streaming completed: " + response);
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    @Test
    public void Chat() {
        OpenAiChatModel model = OpenAiChatModel.withApiKey("demo");
        String generate = model.generate("你是谁");
        System.out.println(generate);
    }
}