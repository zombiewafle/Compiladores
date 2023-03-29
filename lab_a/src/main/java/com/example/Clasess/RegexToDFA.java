package com.example.Clasess;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RegexToDFA {
    public Map<Map<Transition, Boolean>, Map<List<Integer>, List<Integer>> > registry;
    String regString;
    List<Integer> followPos;    
    Boolean nullable;
    // List<String> firstPos;
    // List<String> lastPos;
    Integer counter;
    private int[][] matrix;

    public RegexToDFA(String regexString){
        this.registry = new HashMap<>();
        this.regString = regexString+"#";
        this.followPos = new LinkedList<>();
        this.nullable = false;
        // this.firstPos = new LinkedList<>();
        // this.lastPos = new LinkedList<>();
        this.counter = 0;
    }   

    public void nodeGenerator(){
        for (Character ch : regString.toCharArray()) {
            if(!ch.equals('|') || !ch.equals('*') || !ch.equals('.') || !ch.equals('(') || !ch.equals(')')){

            }
        }
    }
    /* We will need to create some kind of tree, to be able to read the elements */
    /**
     * 
     */
    public void treeCreation(){
        Stack<Integer> stack = new Stack<>(); //It helps keeping the control of on which leaf we are        
        Stack<Boolean> stack2 = new Stack<>(); //It  keeps control storing the nullability
        Stack<String> stack3 = new Stack<>(); //It'll keep the value of the leafs, to be able to get all elements in the tree

        Iterator<String> iterator = null;
        String aNode = "";
        String bNode = "";
        List<Integer> firstPos = new LinkedList<>();
        List<Integer> lastPos = new LinkedList<>();
        
        Map<Boolean, Character> map1 = new HashMap<>();
        Map<List<Integer>, List<Integer>> map2 = new HashMap<>();
        Boolean recurBooleanC1 = false;
        Boolean recurBooleanC2 = false;

        // while(iterator.hasNext()){
        //     String current = iterator.next();
            
        // }

        for (Character c: regString.toCharArray()) {
            switch(c){
                case '.':
                    //If the 2 are nullable, IT is nullable
                    // stack2.push(nullable);
                    if (stack2.size() >= 2 && stack2.get(stack2.size() - 1) && stack2.get(stack2.size() - 2)) {
                        //Both are true
                        recurBooleanC1 = true; 
                        recurBooleanC2 = true; 

                        nullable = false;
                        stack2.add(nullable);
                    } 

                    else if (stack2.size() >= 2 && !stack2.get(stack2.size() - 1) && !stack2.get(stack2.size() - 2)) {
                        //Both are false
                        recurBooleanC1 = false; 
                        recurBooleanC2 = false;  
                        
                        nullable = true;
                        stack2.add(nullable);
                    }

                    else {
                        //They're different
                        recurBooleanC1 = stack2.get(stack2.size() - 2); 
                        recurBooleanC2 = stack2.get(stack2.size() - 1); 
                        
                        nullable = false;
                        stack2.add(nullable);
                    }

                    aNode = stack3.pop();
                    bNode = stack3.pop();
                    break;

                case '*':
                    //It IS nullable
                    nullable = true;
                    stack2.add(nullable);

                    aNode = stack3.pop();
                    bNode = stack3.pop();
                    break;

                case '|':
                    //If 1 of the 2 is nullable, IT is nullable
                    // nullable = false;
                    // stack2.push(nullable);

                    firstPos = stack;
                    lastPos = stack;

                    aNode = stack3.pop();
                    bNode = stack3.pop();

                    if (stack2.size() >= 2 && stack2.get(stack2.size() - 1) && stack2.get(stack2.size() - 2)) {
                        //Both are true
                        recurBooleanC1 = true; 
                        recurBooleanC2 = true; 

                        nullable = true;
                        stack2.add(nullable);

                    } 
                    
                    else if (stack2.size() >= 2 && !stack2.get(stack2.size() - 1) && !stack2.get(stack2.size() - 2)) {
                        //Both are false
                        recurBooleanC1 = false; 
                        recurBooleanC2 = false; 

                        nullable = true;
                        stack2.add(nullable);
                    }
                    
                    else {
                        //They're different
                        recurBooleanC1 = stack2.get(stack2.size() - 2); 
                        recurBooleanC2 = stack2.get(stack2.size() - 1); 
                        
                        nullable = true;
                        stack2.add(nullable);
                        
                    }

                    break;

                case 'ε':
                    //It IS nullable
                    nullable = true;
                    stack2.add(nullable);
                    break;

                default:
                    //It's NOT nullable
                    nullable = false;
                    counter++;
                    
                    firstPos.add(counter);
                    lastPos.add(counter);
                    
                    stack.add(counter);
                    stack2.add(nullable);
                    stack3.add(c.toString());
                    break;
            }
        }
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }


    public void getTreeLeafs(Stack<String> st){
        Iterator<String> iterator = st.iterator();
        String aNode = "";
        String bNode = "";


        while(iterator.hasNext()){
            String current = iterator.next();

        }
    }
    
    
}