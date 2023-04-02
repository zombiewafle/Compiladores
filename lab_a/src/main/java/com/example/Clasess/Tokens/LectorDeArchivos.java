package com.example.Clasess.Tokens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LectorDeArchivos {
    private static final String LET_PATTERN = "let\\s+(\\w+)\\s*=\\s*(.+)";
    private static final String RULE_PATTERN = "rule\\s+(\\w+)\\s*=\\s*(.+)";
    private static String letter = "";

    public static void main(String[] args) {
        String fileName = "/home/zombiewafle/Documentos/lab_a/src/main/java/com/example/Clasess/Tokens/slr.yal";

        Map<String, String> definitions = new HashMap<>();
        List<String> header = new ArrayList<>();
        List<String> trailer = new ArrayList<>();
        List<String> rules = new ArrayList<>();
        List<List<String>> actions = new ArrayList<>();

        String currentSection = "header";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher letMatcher = Pattern.compile(LET_PATTERN).matcher(line);
                Matcher ruleMatcher = Pattern.compile(RULE_PATTERN).matcher(line);
                Matcher letterMatcher = Pattern.compile("letter").matcher(line);

                if(letterMatcher.find()){
                    try (BufferedReader b = new BufferedReader(new FileReader(fileName))) {
                        String l;
                        while ((l = b.readLine()) != null) {
                            Matcher matcher = Pattern.compile("letter\\s*=\\s*(.+)").matcher(l);
                            if (matcher.find()) {
                                String letterValue = matcher.group(1);
                                LectorDeArchivos.letter = letterValue;
                                System.out.println("Value of letter: " + letterValue);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (letMatcher.find()) {
                    definitions.put(letMatcher.group(1), letMatcher.group(2));
                } else if (ruleMatcher.find()) {
                    rules.add(ruleMatcher.group(2));
                    actions.add(new ArrayList<>());
                    currentSection = "rules";
                } else if (line.startsWith("{") && "header".equals(currentSection)) {
                    currentSection = "trailer";
                }
                
                else {
                    if ("header".equals(currentSection)) {
                        header.add(line);
                    } else if ("trailer".equals(currentSection)) {
                        trailer.add(line);
                    } else if ("rules".equals(currentSection)) {
                        actions.get(actions.size() - 1).add(line);
                    } /* else if("letter".equals(currentSection)){
                        try (BufferedReader b = new BufferedReader(new FileReader(fileName))) {
                            String l;
                            while ((l = b.readLine()) != null) {
                                Matcher matcher = Pattern.compile("letter\\s*=\\s*(.+)").matcher(l);
                                if (matcher.find()) {
                                    String letterValue = matcher.group(1);
                                    LectorDeArchivos.letter = letterValue;
                                    System.out.println("Value of letter: " + letterValue);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } */
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Definitions:");
        definitions.forEach((key, value) -> System.out.println(key + " = " + value));

        System.out.println("\nHeader:");
        header.forEach(System.out::println);

        System.out.println("\nRules:");
        rules.forEach(System.out::println);

        System.out.println("\nActions:");
        actions.forEach(actionList -> {
            actionList.forEach(System.out::println);
            System.out.println("---");
        });

        System.out.println("\nTrailer:");
        trailer.forEach(System.out::println);
        
        // Reemplazar las definiciones "let" en las acciones y eliminar comentarios y
        // contenido entre llaves
        List<String> expandedActions = new ArrayList<>();
        int actionCount = 0;
        for (List<String> actionList : actions) {
            for (String action : actionList) {
                String expandedAction = action;
                for (Map.Entry<String, String> definitionEntry : definitions.entrySet()) {
                    String key = definitionEntry.getKey();
                    String value = definitionEntry.getValue();
                    expandedAction = expandedAction.replaceAll("\\b" + key + "\\b", value);
                }
                expandedAction = expandedAction.replaceAll("\\{.*?}", ""); // Eliminar contenido entre llaves
                expandedAction = expandedAction.replaceAll("\\(\\*.*?\\*\\)", ""); // Eliminar comentarios
                
                // Reemplazar la definición de letter
                expandedAction = expandedAction.replaceAll("letter", letter);

                
                if (actionCount > 0) {
                    expandedActions.add(expandedAction.trim());
                } else {
                    expandedActions.add(expandedAction.trim());
                }
                actionCount++;
            }
        }
    
        // Construir la expresión regular final
        StringBuilder finalRegex = new StringBuilder();
        for (String action : expandedActions) {
            finalRegex.append(action);
        }
    
        System.out.println("Expresión regular generada: " + finalRegex.toString());
    }
}    
