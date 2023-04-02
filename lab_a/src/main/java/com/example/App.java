package com.example;

import java.util.List;
import java.util.Scanner;

import com.example.Clasess.Conversion;
import com.example.Clasess.Simplifier;
import com.example.Clasess.DFA.DirectDfa;
import com.example.Clasess.Tokens.Token;
import com.example.Clasess.NFA;
import com.example.Clasess.Graph;


public class App 
{
    /**
     * @param args
     */
    public static void main( String[] args ){
        boolean exit = false;
        System.out.println("Welcome to the Thompson-Algorithm Implementation in Java....\n");
        System.setProperty("org.graphstream.ui", "swing");

        try (Scanner sc1 = new Scanner(System.in)) {
            while(exit != true && !Simplifier.isBalanced){
                System.out.println("----- ----- ----- ----- ----- ----- ----- -----\nPlease select one of the options in the menu: \n1. NFA \n2. NFA to DFA \n3. Direct DFA \n4. Minimized DFA  \n5. Exit\n ----- ----- ----- ----- ----- ----- ----- -----");
                String in = sc1.nextLine();

                switch(in){
                    case "1": //NFA 
                        Scanner scNFA = new Scanner(System.in);
                        System.out.println("Please enter a valid regex: ");
                        String inputNFA = scNFA.nextLine();
                        Simplifier simplifier = new Simplifier(inputNFA);
                        //in = sc.nextLine();
                        
                        Conversion conv = new Conversion(simplifier.toString());
                        String postfix = conv.Converter(); 
                        NFA nfa = new NFA(postfix);
                        nfa.thompsonAlgotithm();
                        String tBlock = """
                                                NFA
                            ----- ----- ----- ----- ----- ----- ----- -----
                                Postfix: %s
                                Symbols: %s
                                Start State: %s
                                End State: %s
                                Transitions: %s
                            ----- ----- ----- ----- ----- ----- ----- -----
                                """.formatted(postfix, NFA.SymbolGetting(), NFA.getStartNodesList(), NFA.getEndtNodesList(), NFA.getTransitions());
                        
                        System.out.println(tBlock);
            
                        Graph.multiGraph.setAttribute("ui.stylesheet", "url('/home/zombiewafle/Documentos/lab_a/src/main/java/com/example/Clasess/styles.css')");
                        Graph.NFAgraphConstruction();
                        break;

                    case "2": //NFA to DFA
                        // System.out.println(NFA.getTransitions());
                        // System.out.println(NFA.SymbolGetting());
                      /*   System.out.println(NFA.getiNodesList());
                        // System.out.println(NFA.getfNodeslist());
                        NewDFATransitions ntransitions = new NewDFATransitions(NFA.getTransitions(),NFA.SymbolGetting(), NFA.getiNodesList(), NFA.getfNodeslist());
                        // dfa = new DFA(transformation.getDfaTable(), transformation.getDfaStates(),transformation.getDfaStatesWithNumbering(),transformation.getSymbolList());
                        //dfaMap == dfaTable, dfaStates == states, withnumbering == getDfaNodes
                        DirectDFA dDFA = new DirectDFA(ntransitions.getDfaMap(), ntransitions.getStates() , ntransitions.getDfaNodes(), ntransitions.getSymbols());
                        
                        String block = """
                                                NFA
                            ----- ----- ----- ----- ----- ----- ----- -----
                                Start State: %s
                                End State: %s
                                Transitions: %s
                            ----- ----- ----- ----- ----- ----- ----- -----
                                """.formatted(dDFA.getInitialStates(), dDFA.getFinalStates(),dDFA.getTransitionsList());
                        
                        System.out.println(block); */

                        break;

                    case "3": //Direct DFA
                        Scanner scDdfa = new Scanner(System.in);
                        System.out.println("Please enter a valid regex: ");
                        String inputDdfa = scDdfa.nextLine();
                        Simplifier simplifierDdfa = new Simplifier(inputDdfa);
                        //in = sc.nextLine();
                    
                        Conversion convDdfa = new Conversion(simplifierDdfa.toString());
                        String postfixDdfa = convDdfa.Converter();
                        DirectDfa dDFA = new DirectDfa(postfixDdfa);
                        System.out.println(dDFA.getNodesList());
                        // DirectDfa.printTree(dDFA.getNodesList());
                        // DirectDFA dfa = new DirectDFA()
                        break;

                    case "4": //Minimized DFA
                        // Scanner scDFAdir = new Scanner(System.in);
                        // System.out.println("Please enter a valid regex: ");
                        // String iDFAdir = scDFAdir.nextLine();
                        // Simplifier simpDFAdir = new Simplifier(iDFAdir);
                        // //in = sc.nextLine();                        
                        break; 

                    case "5":
                        // FileReaderLexer freader = new FileReaderLexer("/home/zombiewafle/Documentos/lab_a/src/main/java/com/example/Clasess/Tokens/slr.yal");
                        
                        break;
                        

                    case "6": //Exit
                        System.out.println("Bye!");
                        return;

                    
                    default:
                        System.out.println("Please enter a valid regex: ");
                        break;
                }
            }
        }
    }
}
