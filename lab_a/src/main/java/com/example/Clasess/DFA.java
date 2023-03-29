package com.example.Clasess;

//#region imports
import org.graphstream.graph.implementations.MultiGraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
//#endregion
import java.util.Stack;

public class DFA {
    /* This class will create the DFA using 2 methods 
     * Agrupation and forming the DFA from scractch 
     * To make the agrupations, we need a list of transitions and the node 
    */
    //#region variables
    public String regex;
    static Integer ID;
    
    String aString;
    String bString;
    String cString;

    List<Character> symbols;
    public static Stack<Node> dfaNodes;
    public static List<Node> generalDFANodes;
    public static Transition dfTransition;
    
    private static Set<Integer>[] followPos;    
    public static List<Transition> transitions;
    static Map<Map<Node, Set<Transition>>, Map<Node, Set<Transition>>> gMap;
    public static Map<Map<Node, String>, Set<Transition>> graphMap;

    // public static Set<Transition> nodeSetGeneral = new HashSet<>();
    // public static Set<Transition> transitionsClsoure1General = new HashSet<>(); 
    // Map<String, Set<Transition>> tableMapGeneral = new HashMap<>(); 
    

    //#endregion

    public DFA(){
        ID = 0;
        bString = "";
        aString = "";
        cString = "";
        // dfaNodes = new LinkedList<>();
        dfaNodes = new Stack<>();
        generalDFANodes = new LinkedList<>();
        transitions = new LinkedList<>();
        gMap = new HashMap<>();
        graphMap = new HashMap<>();
        Graph.multiGraph = new MultiGraph("DFA");
    }

    public void Agrupation(){
        /* First we need to make the transitions table. Node | transition A | Transition B | Transition N | ε-Closure */
        Set<String> epTransitions = new HashSet<>();
        Set<Transition> varSet = new HashSet<>();
        Set<Transition> epsilon = new HashSet<>();
        // Integer counter = 0;

        Set<String> var = new HashSet<>();
        //#region filling the sets of transitions
        for (Transition transition : NFA.transitions) {
            if(!transition.getSymbol().equals("ε")){
                // System.out.println(transition);
                // Node node = new Node(ID);
                // dfaNodes.push(node); 
                // generalDFANodes.add(node);
                var.add(transition.getSymbol());
                varSet.add(transition);
            }
        }

        
        for (Transition transition : NFA.transitions) {
            if(transition.getSymbol().equals("ε")){
                // System.out.println(transition);
                epsilon.add(transition);
            }
        }
        //#endregion
        // System.out.println(dfaNodes);
        
        for (Transition transition : varSet) {
            aString = transition.getInitialState().toString(); //Initial of the normal transitions
            bString = transition.getFinalState().toString(); //Final of the normal transitions
            cString = transition.getSymbol(); //Symbol of the transition
            
            
            // Transition dString = transition; //Epsilon transition
            
            /*  nodo |  a  |  b  | epsilon     nodo  |  a  |  b  
             * --------------------------   ---------------Map<Integer, Set<String>> nodes = new HashMap<>();--------
             *    0  |     |     |          [0,1,2,3]|     |     
             *    1  |     |     |               []  |     |     
             *    2  |     |     |               []  |     |     
             *    3  |     |     |               []  |     |     
             *    4  |     |     |               []  |     |     
             *    5  |     |     |               []  |     |     
            */


            for (Transition transition1 : epsilon) {
                String eString = transition1.getInitialState().toString(); //Initial of the epsilon transition
                String epString = transition1.getFinalState().toString(); //Initial of the epsilon transition
                // List<Map<Integer, Set<String>>> gList = new LinkedList<>();
                

                if(bString.equals(eString)){
                    epTransitions.add(epString);
                    // System.out.println(epTransitions);
                    // System.out.println(aString + "->" + eString);
                    /* // //Each set of transitions will have an ID
                    // //First we need to make subgroups for all the epsilon transitions
                    // Map<Integer, Set<String>> nodes = new HashMap<>(); //Epsilon transition
                    
                    // epTransitions.add(epString);
                    // nodes.put(counter, epTransitions);
                    // // System.out.println(nodes);
                    // Node n = new Node(counter, nodes);
                    // dfaNodes.add(n);
                    // gList.add(nodes);
                    // System.out.println(gList);
                    // System.out.println(transition + " " + transition1);
                    // System.out.println(var); */
                    /* for (String ch : var) {
                        if(cString.equals(ch)){
                            String temp = ch;
                            System.out.println(ch  + "Transitions");
                            epTransitions.add(epString);
                            System.out.println(epTransitions);
                            System.out.println(aString + "->" + eString);
                        }
                        else{
                            
                            epTransitions.add(epString);
                        }
                    }   */                  
                }
            }
        }     
    } 


    //#region getters y setters
    public static Set<Integer>[] getFollowPos() {
        return followPos;
    }

    public static List<Transition> getTransitions() {
        return transitions;
    }

    public static Map<Map<Node, Set<Transition>>, Map<Node, Set<Transition>>> getgMap() {
        return gMap;
    }

    //#endregion

}