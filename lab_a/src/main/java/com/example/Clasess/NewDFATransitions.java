package com.example.Clasess;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class NewDFATransitions {
    // public Map<Map<Transition, Boolean>, Map<List<Integer>, List<Integer>> > registry;
    String regString;
    Integer nodeCounter;
    
    List<List<Node>> states;
    List<Transition> transitions;
    
    List<Character> symbols;
    List<Node> iStates;
    List<Node> eStates;

    Map<Integer, Map<String, List<Node>>> transitionsMap;
    Map<List<Node>, Map<Character, List<Node>>> dfaMap;
    Map<List<Node>, Integer> dfaNodes;

    public NewDFATransitions(List<Transition> transitions, List<Character> symbols, List<Node> iNodes, List<Node> eNodes){
        // this.regString = regexString+"#";
        this.dfaMap = new HashMap<>();
        this.states = new LinkedList<>();
        this.transitions = transitions;
        this.transitionsMap = new HashMap<>();
        this.dfaNodes = new HashMap<>();
        this.symbols = symbols;
        this.iStates = iNodes;
        this.eStates = eNodes;
        symbols.add('ε');
        this.nodeCounter = 0;

        createTransitionTable();
        createTable();
    }   

    private void createTable() {
        // Add initial state
        states.add(transitionsMap.get(iStates.get(0).getID()).get("ε"));
    
        // Add rest of table
        for (int k = 0; k < states.size(); k++) {
            HashMap<Character, List<Node>> tmpCols = new HashMap<>();
            for (Character symbol : symbols.subList(0, symbols.size()-1)) {
                List<Node> symbolEpsilonKleene = states.get(k).stream()
                    .map(state -> transitionsMap.get(state.getID()).get(symbol))
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .flatMap(state -> transitionsMap.get(state.getID()).get("ε").stream())
                    .collect(Collectors.toList());
                if (!symbolEpsilonKleene.isEmpty() && !states.contains(symbolEpsilonKleene)) {
                    states.add(symbolEpsilonKleene);
                }
                tmpCols.put(symbol, symbolEpsilonKleene);
            }
            dfaMap.putIfAbsent(states.get(k), tmpCols);
            dfaNodes.put(states.get(k), k);
        }

        System.out.println(dfaNodes);
    }
    
    public List<Node> eClosureGenerator(Node iNode, List<Node> teClosure, Node pNode){
        for(Transition transition : transitions){
            if(transition.getInitialState().equals(iNode) && transition.getSymbol().equals("ε")){
                Node finalState = transition.getFinalState();

                if(!teClosure.contains(finalState)){
                    teClosure.add(finalState);
                    eClosureGenerator(finalState, teClosure, pNode);

                } else if(finalState.equals(iNode) && teClosure.size() == 1){
                    eClosureGenerator(finalState, teClosure, pNode);
                }
            }
        }
        System.out.println(teClosure);
        return teClosure;
    }

    private void createTransitionTable() {
        for (Transition transition : transitions) {
            int initialStateId = transition.getInitialState().getID();
            if (!transitionsMap.containsKey(initialStateId)) {
                HashMap<String, List<Node>> columnMap = new HashMap<>();
                for (Character symbol : symbols) {
                    String symbolStr = String.valueOf(symbol);
                    List<Node> closure = null;
                    if (transition.getSymbol().equals(symbolStr)) {
                        closure = transition.getInitialState().getNextStatesList();
                        if (symbolStr.equals("ε")) {
                            closure.addAll(eClosureGenerator(transition.getInitialState(), closure, transition.getInitialState()));
                            closure.add(transition.getInitialState());
                        }
                    } else if (symbolStr.equals("ε")) {
                        closure = new LinkedList<>();
                        closure.addAll(eClosureGenerator(transition.getInitialState(), closure, transition.getInitialState()));
                        closure.add(transition.getInitialState());
                    }
                    columnMap.put(symbolStr, closure);
                }
                transitionsMap.put(initialStateId, columnMap);
            }
        }
        
        HashMap<String, List<Node>> finalColumnMap = new HashMap<>();
        for (Character symbol : symbols) {
            String symbolStr = String.valueOf(symbol);
            List<Node> closure = symbolStr.equals("ε") ? Collections.singletonList(eStates.get(0)) : null;
            finalColumnMap.put(symbolStr, closure);
        }
        transitionsMap.put(eStates.get(0).getID(), finalColumnMap);
        System.out.println(transitionsMap);
    }


    //#region getters and setters
    public String getRegString() {
        return regString;
    }

    public void setRegString(String regString) {
        this.regString = regString;
    }

    public Integer getNodeCounter() {
        return nodeCounter;
    }

    public void setNodeCounter(Integer nodeCounter) {
        this.nodeCounter = nodeCounter;
    }

    public List<List<Node>> getStates() {
        return states;
    }

    public void setStates(List<List<Node>> states) {
        this.states = states;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public List<Character> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Character> symbols) {
        this.symbols = symbols;
    }

    public List<Node> getiStates() {
        return iStates;
    }

    public void setiStates(List<Node> iStates) {
        this.iStates = iStates;
    }

    public List<Node> geteStates() {
        return eStates;
    }

    public void seteStates(List<Node> eStates) {
        this.eStates = eStates;
    }

    public Map<Integer, Map<String, List<Node>>> getTransitionsMap() {
        return transitionsMap;
    }

    public void setTransitionsMap(Map<Integer, Map<String, List<Node>>> transitionsMap) {
        this.transitionsMap = transitionsMap;
    }

    public Map<List<Node>, Map<Character, List<Node>>> getDfaMap() {
        return dfaMap;
    }

    public void setDfaMap(Map<List<Node>, Map<Character, List<Node>>> dfaMap) {
        this.dfaMap = dfaMap;
    }

    public Map<List<Node>, Integer> getDfaNodes() {
        return dfaNodes;
    }

    public void setDfaNodes(Map<List<Node>, Integer> dfaNodes) {
        this.dfaNodes = dfaNodes;
    }
 
    //#endregion
    
    
}