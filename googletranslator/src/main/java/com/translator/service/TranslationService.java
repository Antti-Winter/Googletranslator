package com.translator.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service class for translation using Google Cloud Translation API
 * with support for prompt-based translation
 */
public class TranslationService {
    
    // HTTP client for API calls
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    
    // API credentials
    private String googleApiKey;
    
    /**
     * Constructor initializing HTTP client
     */
    public TranslationService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        this.objectMapper = new ObjectMapper();
    }
    
    /**
     * Set the Google Cloud API key
     * @param apiKey Google Cloud API key
     */
    public void setGoogleApiKey(String apiKey) {
        this.googleApiKey = apiKey;
    }
    
    /**
     * Translate text using Google Cloud Translation API
     * @param text Text to translate
     * @param sourceLanguage Source language code (can be null for auto-detection)
     * @param targetLanguage Target language code
     * @return Translated text
     */
    public String translateText(String text, String sourceLanguage, String targetLanguage) 
            throws IOException, InterruptedException {
        
        if (googleApiKey == null || googleApiKey.isEmpty()) {
            throw new IllegalStateException("Google API key not set");
        }
        
        // Build request URL
        String apiUrl = "https://translation.googleapis.com/language/translate/v2";
        
        // Create request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("q", text);
        requestBody.put("target", targetLanguage);
        
        if (sourceLanguage != null && !sourceLanguage.isEmpty()) {
            requestBody.put("source", sourceLanguage);
        }
        
        // Convert request body to JSON
        String requestBodyJson = objectMapper.writeValueAsString(requestBody);
        
        // Build the full URL with API key
        String fullUrl = apiUrl + "?key=" + googleApiKey;
        
        // Create HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                .build();
        
        // Send request and get response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Check if request was successful
        if (response.statusCode() != 200) {
            throw new IOException("Google Translate API error: " + response.statusCode() + " " + response.body());
        }
        
        // Parse response
        Map<String, Object> responseMap = objectMapper.readValue(response.body(), Map.class);
        Map<String, Object> data = (Map<String, Object>) responseMap.get("data");
        List<Map<String, Object>> translations = (List<Map<String, Object>>) data.get("translations");
        
        return (String) translations.get(0).get("translatedText");
    }
    
    /**
     * Translate text using Google API with prompt engineering capabilities
     * This method enhances the regular translation by preprocessing the text with context
     * @param text Text to translate
     * @param sourceLanguage Source language
     * @param targetLanguage Target language
     * @param context Context of the translation
     * @param style Style of translation
     * @param industry Industry-specific terminology
     * @return Translated text
     */
    public String translateWithPrompt(String text, String sourceLanguage, String targetLanguage,
                                     String context, String style, String industry) 
            throws IOException, InterruptedException {
        
        // Build enhanced text with prompt-like instructions
        String promptText = buildPromptText(text, context, style, industry);
        
        // Translate using Google API
        String translatedText = translateText(promptText, sourceLanguage, targetLanguage);
        
        // Post-process to remove any instruction markers that might have been translated
        return cleanTranslatedPrompt(translatedText);
    }
    
    /**
     * Build text with prompt-like instructions
     */
    private String buildPromptText(String text, String context, String style, String industry) {
        StringBuilder builder = new StringBuilder();
        
        // Add prompt-like instructions as comments that Google Translate might consider
        if (context != null && !context.isEmpty()) {
            builder.append("/* Context: ").append(context).append(" */\n");
        }
        
        if (style != null && !style.isEmpty()) {
            builder.append("/* Style: ").append(style).append(" */\n");
        }
        
        if (industry != null && !industry.isEmpty()) {
            builder.append("/* Industry: ").append(industry).append(" */\n");
        }
        
        // Add the text to translate
        builder.append(text);
        
        return builder.toString();
    }
    
    /**
     * Clean the translated text by removing any prompt instructions
     */
    private String cleanTranslatedPrompt(String translatedText) {
        return translatedText
            .replaceAll("/\\* Context: .*? \\*/\\n?", "")
            .replaceAll("/\\* Style: .*? \\*/\\n?", "")
            .replaceAll("/\\* Industry: .*? \\*/\\n?", "");
    }
}