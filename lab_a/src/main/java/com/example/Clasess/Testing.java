package com.example.Clasess;

import java.util.Arrays;
import java.util.List;
/* This class is just for testing, please ignore it... */
public class Testing {
    String reg;

    /**
     * @param regex
     */
    public Testing(String regex){
        this.reg = regex;
        //region to execute the methods 
        epsilonHandler();
        duplicates();
        sumHandler();

    }

    public String getReg() {
        return reg;
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
        
        for(int i = 0; i < reg.length(); i++){
            if(reg.charAt(i) == '+'){
                Character c = reg.charAt(i-1);
                if (reg.charAt(i-1) != ')') {
                    newFormat = (c.toString() + c.toString() + "*");
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
                            String a = (String) reg.substring(i, reg.length());
                            //System.out.print(parenthesis);
                            String simplified = b + parenthesis + a;
                            //System.out.println(a);
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

    /** This method will handle all the extra parenthesis, by making substrings. 
     * @return reg
     */
    public String nParenthesisHandler(String regex){
        regex = this.reg;
        String newString = "";
        Integer parCounter = 0;
        Integer location1 = 0, location2 = 0;

        for(int i = 0; i<regex.length(); i++){
            Character c = regex.charAt(i);

            if(c == '('){
                location1 = regex.indexOf(c);
                parCounter++;
            }
            else if(c == ')'){
                location2 = regex.indexOf(c);
                parCounter--;
            }

            if(parCounter == 0){
                newString = regex.substring(location1, location2+1);
            }
        }
        reg = newString;
        return reg;
    }

    @Override
    public String toString(){
        return reg;
    }
}