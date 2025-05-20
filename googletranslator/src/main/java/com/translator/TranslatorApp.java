package com.translator;

import java.io.IOException;
import java.util.Scanner;

import com.translator.service.TranslationService;

/**
 * Command-line application for Google Translation API with prompt capabilities
 */
public class TranslatorApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TranslationService translationService = new TranslationService();
    
    public static void main(String[] args) {
        System.out.println("Google Translation API CLI");
        System.out.println("=========================");
        
        // Get API key
        System.out.print("Enter your Google Cloud API key: ");
        String apiKey = scanner.nextLine();
        translationService.setGoogleApiKey(apiKey);
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    performBasicTranslation();
                    break;
                case 2:
                    performPromptTranslation();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        System.out.println("Thank you for using Google Translation API CLI. Goodbye!");
    }
    
    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Basic Translation");
        System.out.println("2. Advanced Translation with Prompt Engineering");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void performBasicTranslation() {
        System.out.print("Enter text to translate: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter source language code (e.g., 'en', 'fi', leave blank for auto-detection): ");
        String sourceLanguage = scanner.nextLine();
        
        System.out.print("Enter target language code (e.g., 'en', 'fi'): ");
        String targetLanguage = scanner.nextLine();
        
        try {
            String translatedText = translationService.translateText(
                text,
                sourceLanguage.isEmpty() ? null : sourceLanguage,
                targetLanguage
            );
            
            System.out.println("\nTranslation Result:");
            System.out.println(translatedText);
        } catch (Exception e) {
            System.out.println("Translation failed: " + e.getMessage());
        }
    }
    
    private static void performPromptTranslation() {
        System.out.print("Enter text to translate: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter source language code (e.g., 'en', 'fi', leave blank for auto-detection): ");
        String sourceLanguage = scanner.nextLine();
        
        System.out.print("Enter target language code (e.g., 'en', 'fi'): ");
        String targetLanguage = scanner.nextLine();
        
        System.out.print("Enter context (e.g., 'technical manual', 'marketing material', optional): ");
        String context = scanner.nextLine();
        
        System.out.print("Enter style (e.g., 'formal', 'casual', optional): ");
        String style = scanner.nextLine();
        
        System.out.print("Enter industry for terminology (e.g., 'medical', 'legal', optional): ");
        String industry = scanner.nextLine();
        
        try {
            String translatedText = translationService.translateWithPrompt(
                text,
                sourceLanguage.isEmpty() ? null : sourceLanguage,
                targetLanguage,
                context,
                style,
                industry
            );
            
            System.out.println("\nTranslation Result:");
            System.out.println(translatedText);
        } catch (IOException | InterruptedException e) {
            System.out.println("Translation failed: " + e.getMessage());
        }
    }
}