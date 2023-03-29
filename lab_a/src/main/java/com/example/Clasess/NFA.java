package com.example.Clasess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.graphstream.graph.implementations.MultiGraph;

public class NFA {
    //This class will receive all the formated Strings, in order to make the NFA 
    public static String regex;

    Node center;

    public static List<Node> nodes;
    public static List<Transition> transitions;
    static List<Character> symbols;

    static ArrayList<Node> startNodesList;
    static ArrayList<Node> endtNodesList;
    static Transition transition;

    Stack<Node> stack;
    static Integer ID;
    
    /**The class will receive a String, to then process and return the NFA
     * @param reg
     */
    public NFA(String reg){
        ID = 0;
        NFA.regex = reg;

        nodes = new LinkedList<>();
        transitions = new LinkedList<>();
        symbols = new LinkedList<>();

        startNodesList = new ArrayList<>();
        endtNodesList = new ArrayList<>();

        stack = new Stack<>();
        Graph.multiGraph = new MultiGraph("NFA");
    }

    /**
     * A method that will handle the kleene operation
     * @param iNode
     * @param eNode
     * 0 --E--> 1, 0 --E--> 3, 1 --A--> 2, 2 --E--> 1, 2 --E--> 3
    */
    public void klenne(Node iNode, Node eNode){
        Node connectingNodeA = new Node(ID);
        Node connectingNodeB = new Node(ID);

        Graph.multiGraph.addNode(connectingNodeA.toString()).setAttribute("ui.label",connectingNodeA.toString());
        Graph.multiGraph.addNode(connectingNodeB.toString()).setAttribute("ui.label",connectingNodeB.toString());

        Transition t1 = new Transition(eNode ,"ε", iNode); 
        Transition t2 = new Transition(connectingNodeA ,"ε",  connectingNodeB); 
        Transition t3 = new Transition(connectingNodeA, "ε",  iNode); 
        Transition t4 = new Transition(eNode , "ε", connectingNodeB); 
        
        transitions.add(t1);
        transitions.add(t2);
        transitions.add(t3);
        transitions.add(t4);
        
        stack.push(connectingNodeA);
        stack.push(connectingNodeB);
    }

    /**
     * Concatenation using the Thompson Algorithm
     * @param iNode
     * @param eNode
     */
    public void concat(Node iNode, Node eNode){        
        Transition t1 = new Transition(iNode , "ε",  eNode);
        
        transitions.add(t1);
        stack.push(center);
    
    }

    /**
     * This method will create the connections between the nodes to make the OR ( | ) form, using the Thompson Algorithm
     * @param n1
     * @param n2
     * @param n3
     * @param n4
     */
    public void or(Node n1, Node n2, Node n3, Node n4){
        Node cNodeA = new Node(ID);
        Node cNodeB = new Node(ID);

        Graph.multiGraph.addNode(cNodeA.toString()).setAttribute("ui.label",cNodeA.toString());
        Graph.multiGraph.addNode(cNodeB.toString()).setAttribute("ui.label",cNodeB.toString());        

        Transition t1 = new Transition(cNodeA, "ε",  n3); 
        Transition t2 = new Transition(cNodeA, "ε",  n4); 
        Transition t3 = new Transition(n1, "ε",  cNodeB);
        Transition t4 = new Transition(n2, "ε",  cNodeB);

        transitions.add(t1);
        transitions.add(t2);
        transitions.add(t3);
        transitions.add(t4);

        stack.push(cNodeA);
        stack.push(cNodeB);
    }

    /**
     * This method will use all the methods to make one big NFA, using the thompson algorithm for each mini-graph
     */
    public void thompsonAlgotithm(){
        for(Integer i = 0; i < regex.length(); i++){
            Character c = regex.charAt(i);

            switch(c){

                case '.':
                    center = stack.pop();
                    
                    Node lNode = stack.pop();
                    Node rNode = stack.pop();
                    
                    concat(rNode, lNode);
                    break;

                case '*':
                    Node left = stack.pop();
                    Node right = stack.pop();
                    
                    klenne(right, left);
                    
                    break;

                case '|':
                    Node up1 = stack.pop();
                    Node low1 = stack.pop();
                    Node up2 = stack.pop();
                    Node low2 = stack.pop();

                    or(up1, up2, low1, low2);
                    
                    break;

                default:
                    transition = new Transition(c.toString());
                    transitions.add(transition);

                    Node iNode = transition.getInitialState();
                    nodes.add(iNode);
                    
                    startNodesList.add(iNode);
                    
                    Node eNode = transition.getFinalState();
                    endtNodesList.add(eNode);
                    
                    stack.push(iNode);
                    stack.push(eNode);
                    
                    nodes.add(eNode);
                    
                    break;
            }
         }
    }

     //#region Getters
     public static List<Character> SymbolGetting(){
        //This method will let us get all the info in the precendence map. To then show it in the output of the program
        for(int i = 0; i < regex.length(); i++){
            Character character = regex.charAt(i);
            boolean val = Conversion.pMap.containsKey(character);
            
            if(!val){
                symbols.add(character);
            }
        }
        return symbols;
    }

    public static List<Transition> getTransitions() {
        return transitions;
    }

    public static String getStartNodesList() {
        return "["+ startNodesList.get(0) + "]";
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public static String getEndtNodesList() {
        NFA.ID = 0;
        return "[" +endtNodesList.get(endtNodesList.size()-1) + "]";
    }

    public Integer getID(int i){
        return ID;
    }

    public static void setID(Integer iD) {
        NFA.ID = iD;
    }

    public static Set<Transition> getTransitions(Node node) {
        Set<Transition> transitions = new HashSet<>();
    
        for (Transition transition : NFA.transitions) {
            if (transition.getInitialState().equals(node)) {
                transitions.add(transition);
            }
        }
    
        return transitions;
    }
    
       
    //#endregion

}