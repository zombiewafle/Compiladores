package com.example.Clasess;

import java.util.Map;
import java.util.Set;

public class Transition {
    private String symbol;
    private Node initialState;
    private Node finalState;
    private Map<Character, Set<Node>> tMap;
    // private Set<String> subSet;

    /**
     * @param iniState
     * @param symbol
     * @param finalState
     */
    public Transition(Node iniState, String symbol, Node finalState){
        this.symbol = symbol;
        this.initialState = iniState;
        this.finalState = finalState;

        this.initialState.addNextState(finalState);
        this.finalState.addPreviousState(iniState);
    }

    /**
     * @param symbol
     * @param map
     */
    public Transition(Node node, Set<Transition> transition1, String symbol, Node node2, Set<Transition> transition2) {
        this.symbol = symbol;
        this.initialState = new Node(NFA.ID);
        this.finalState = new Node(NFA.ID);

        this.initialState.addNextSubgroup(node, transition1);
        this.finalState.addNextSubgroup(node2, transition2);
    }

    /**
     * @param symbol
     */
    public Transition(String symbol) {
        this.symbol = symbol;
        this.initialState = new Node(NFA.ID);
        this.finalState = new Node(NFA.ID);

        this.initialState.addNextState(finalState);
        this.finalState.addPreviousState(initialState);
    }

    public Transition(String symbol, Set<String> subSet) {
        this.symbol = symbol;
        // this.subSet = subSet;
        this.initialState = new Node(NFA.ID);
        this.finalState = new Node(NFA.ID);

        // DFA.dfaNodes.add(initialState);
        // DFA.dfaNodes.add(finalState);

        this.initialState.addNextState(finalState);
        this.finalState.addPreviousState(initialState);
    }

    /* public Transition(Map<Node, Set<String>> map){
        for (String string : subSet) {
            
        }
    } */

    //#region getters and setters
    /**
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return initialState
     */
    public Node getInitialState() {
        return initialState;
    }

    /**
     * @return finalState
     */
    public Node getFinalState() {
        return finalState;
    }

    //In case the symbol is "null", that will be an epsilon transition. 
    public String toString() {
        return String.format(initialState.toString() + "--"+symbol+"-->" + finalState.toString());
    }

    /**
     * @return tMap
     */
    public Map<Character, Set<Node>> gettMap() {
        return tMap;
    }

    //#endregion


}