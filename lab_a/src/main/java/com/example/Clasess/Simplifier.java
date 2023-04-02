package com.example.Clasess;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Simplifier {
    String reg;
    public static Boolean isBalanced = false;
    //String newString;
    /**
     * @param regex
     */
    public Simplifier(String regex){
        this.reg = regex;
        //region to execute the methods 
        //newString = Checker(reg);
        /* epsilonHandler();
        duplicates();
        sumHandler();
        Balacing(); */

    }

    public String getReg() {
        return reg;
    }

    public String Balacing(){
        //Integer counter = 0;
        List<Character> operators = Arrays.asList('|','?','+','*','^');
        String newReg = "";

        Stack<Character> balancing = new Stack<Character>();
        
        for(int i = 0; i < reg.length(); i++){
            char character = reg.charAt(i);
            
            if(character ==('(')){
                if(reg.charAt(i+1) != ')'){
                    balancing.push(character);    
                }
                else{
                    //balancing.pop();
                    Simplifier.isBalanced = false;

                }
                
            }
            else if(character == (')')){
                balancing.pop();
            }
            //The first element cannot be an operator
            if(operators.contains(character)){
                Simplifier.isBalanced = false;
            }

            newReg += character;

            if(balancing.isEmpty()){
                Simplifier.isBalanced = true;
            }
            else{
                Simplifier.isBalanced = false;
            }

        }

        return newReg;
    }

    public String duplicates(){
        Boolean stopChecking = false;
        List<Character> operators = Arrays.asList('|', '?', '+', '*', '^');

        do{
            for(int i = 0; i < reg.length(); i++){
                Character c = reg.charAt(i);
                 
                if(i + 1 < reg.length()){
                    Character c2 = reg.charAt(i+1);

                    if(operators.contains(c) && operators.contains(c2) && c == c2){
                        //We have two operators that are the same, next to each other
                        //We need to substring the regex, without one of the operators                        
                        //We will make two substrings to remove the duplicate.
                        String p1 = reg.substring(0, i);
                        String p2 = reg.substring(i+1);
                        reg = p1 + p2;
                        break;
                    }
                    else{
                        stopChecking = true;
                    }
                }
            }

        }while(!stopChecking);
        return reg;
    }

    public String epsilonHandler(){
        //This method will check all the elements and will replace the ? symbol with 
        //the epsilon version ? ==  |ε
        String newFormat = "";
        //before a parenthesis
        //reg = reg.replace("?", "|ε");
        for(int i = 0; i < reg.length(); i++){
            if(reg.charAt(i) == '?'){
                Character c = reg.charAt(i-1);
                if (reg.charAt(i-1) != ')') {
                    newFormat = ("(" +  c + "|ε)");
                    String b = reg.substring(0, i-1);
                    String a = reg.substring(i+1, reg.length());
                    String simplified = b + newFormat + a;
                    //nParenthesisHandler(simplified);
                    reg = simplified;
                }

                else{
                    //Now that we found the ? symbol, we need to go back, to the first (
                    //We know that'll be between Index 0 and before the ?
                    for(int j = (i-1); j >= 0 ; j--){
                        if (reg.charAt(j) == '(') {
                            //Integer pos = j;
                            String b = (String) reg.substring(0, j); 
                            String parenthesis = (String) reg.substring(j, i); 
                            String a = (String) reg.substring(i+1, reg.length());
                            String simplified = b + "(" + parenthesis + "|ε)" + a;
                            //nParenthesisHandler(simplified);
                            reg = simplified;
                            break;
                        }
                    }
                }
            }
        }
        return reg;
    }

    public String sumHandler(){
        //This method will replace the + with double of the variables and *
         String newFormat = "";
         Integer counter = 0;
        
        for(int i = 0; i < reg.length(); i++){
            if(reg.charAt(i) == '+'){
                Character c = reg.charAt(i-1);

                if (reg.charAt(i-1) != ')') {
                    newFormat = (c.toString() + "." + c.toString() + "*");
                    String b = reg.substring(0, i-1);
                    String a = reg.substring(i+1, reg.length());
                    String simplified = b + newFormat + a;
                    reg = simplified;
                }

                else{
                    //To get the lower bound of the expression, we are just counting them, adding when is an opening
                    //and subtracting when is a closing, if the counter == 0, then we can continue
                    for(int j = (i-1); j >= 0 ; j--){
                        Character character = reg.charAt(j);

                        if (character == '(') {
                            counter++;
                        }

                        else if(character == ')'){
                            counter--;
                        }

                        if(counter == 0){
                            //Integer pos = j;
                            String b = (String) reg.substring(0, j);  //The beginning to the first parenthesis
                            String parenthesis = (String) reg.substring(j, i); //The substring of the elemnts inside of the parenthesis
                            String a = (String) reg.substring(i+1); //From the last point till the end of the expression
                            //String simplified = b + "(" + parenthesis + "|ε)" + a;
                            String simplified = b  + parenthesis + "•" + parenthesis + "*" + a; //All the elements are concatenaded in one String
                            //nParenthesisHandler(simplified);
                            //System.out.println(simplified);
                            reg = simplified;
                            break;
                        }
                    }
                }
            }
        }
        return reg;
    }
    
    public String toString(){
        return reg;
    }

}