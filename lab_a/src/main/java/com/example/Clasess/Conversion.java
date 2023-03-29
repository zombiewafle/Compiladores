package com.example.Clasess;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class Conversion {
    //This class will receive the infix formatted regex and will convert it into a postfix one. 
    private String regex;
    private String postfix;
   
    public static Map<Character, Integer> pMap;
    static {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('(', 1);
        map.put('|', 2);
        map.put('.', 3); // explicit concatenation operator
        map.put('?', 4);
        map.put('*', 4);
        map.put('+', 4);
        map.put('^', 5);
        pMap = Collections.unmodifiableMap(map);
    };

    /**
     * Precedence table:
     * <p>
     * ( -> 1
     * | -> 2
     * . -> 3
     * ? -> 4      * -> 4      + -> 4
     * ^ -> 5
     * ANOTHER -> 6
     **/
    
    public Conversion(String reg){
        this.postfix = "";
        this.regex = reg;
        //this.hasErrors = true;
        // Concatenator();
        // Converter();
    }

    static Integer getPrecedence(Character character){
        Integer precedence = pMap.get(character);
        if(precedence == null){
            precedence = 6;
        }
        return precedence;
    }

    private String Checker(String nRegex) {
        //First we need to check the whole input, read and start to identy the items that will interfire with 
        //the program

        for(int i = 0; i < nRegex.length(); i++){
            Character c = nRegex.charAt(i);
            boolean val = Conversion.pMap.containsKey(c);
            if(val && getPrecedence(c)>=3 && c != '?' && c != '+' && c != '*'){
                nRegex =  nRegex.replace(c, '@');
            }
        }
        return nRegex;
    }

    public String Converter(){
        Stack<Character> stack = new Stack<Character>();
        String concat = this.Concatenator();
        Simplifier newString = new Simplifier(concat);

        newString.epsilonHandler();
        newString.sumHandler();

        postfix = "";


        //To verify each element in the regex, it'll be needed to convert it to an array, to then loop through the whole array
        for (Character c: newString.toString().toCharArray()){
            switch (c){
                case '(':
                    stack.push(c);
                    break;

                case ')':
                    while (stack.peek() !='('){
                        postfix += stack.pop();
                    }
                    stack.pop();
                    break;

                default:
                    while (stack.size() > 0 ){
                        Character peekedChar = stack.peek();

                        int pPrecedence = getPrecedence(peekedChar);
                        int cPrecedence = getPrecedence(c);

                        if(pPrecedence >= cPrecedence){
                            postfix += stack.pop();

                        }
                        else {
                            break;
                        }

                    }

                    stack.push(c);
                    break;
            }
        }

        while(stack.size() > 0){
            postfix += stack.pop();
        }
        //System.out.println(postfix);
        return postfix; 
    }

    public String Concatenator(){
        List<Character> operators = Arrays.asList('|','?','+','*','^');
        List<Character> bOperators = Arrays.asList('^', '|');

        String newFoString = Checker(regex);
        //Simplifier simp = new Simplifier(newFoString);
        
        
        //System.out.println(newFoString);

        Character c1, c2;
        
        for (int i = 0 ; i < newFoString.toString().length() ; i++){
            c1 = newFoString.toString().charAt(i);

            if (i + 1 < newFoString.toString().length()){
                c2 = newFoString.toString().charAt(i + 1);

                postfix += c1;

                //After going through the regex, we will need to check that we're going to put a . between two valid symbols
                if (c1 != '(' && c2 != ')' && !operators.contains(c2) && !bOperators.contains(c1)){
                    postfix += '.';
                }
            }
        }

        postfix += newFoString.toString().charAt(newFoString.toString().length()-1);
        return postfix;
    };

    //An override is needed, in order to display the info as a String in the rest of the classes
    @Override
    public String toString(){
        return postfix;
    }

}