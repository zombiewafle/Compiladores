package com.example.Clasess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NFAtoDFA {
    
    List<Node> nodes;
    Set<String> symboSet;
    public Integer counter;
    List<Transition> transitions;
    
    Set<Transition> realTransitions;
    Set<Transition> epsilonTransitions;
    
    public Map<Node, Set<String>> tableMap;
    public Map<String, Set<String>> transitionsTable;
    

    public NFAtoDFA(){
        // this.regex = "";
        this.nodes = new LinkedList<>();
        this.transitions = new LinkedList<>();
        this.tableMap = new HashMap<>();
        this.symboSet = new HashSet<>();
        this.realTransitions = new HashSet<>();
        this.epsilonTransitions = new HashSet<>();
        this.transitionsTable = new HashMap<>();
        this.counter = 0;
    }

    public void nodesCounter(){
        Set<Transition> varSet = new HashSet<>();
        for (Transition transition : NFA.transitions) {
            if(!transition.getSymbol().equals("ε")){
                varSet.add(transition);
                counter++;
            }
        }
    }

    /* public void Agrupation() {
        // I need to go through all the nodes and get their ε-Transitions
        Set<String> firstNode = new HashSet<>();
        Map<Node, Set<String>> tempMap = new HashMap<>();
        for (Transition transition : NFA.transitions) {
            if(transition.getSymbol().equals("ε")){
                Node node = new Node(NFA.ID);
                firstNode.add(transition.getFinalState().toString());
                tempMap.put(node, firstNode);
                break;
            }
            
        }
        tableMap.putAll(tempMap);
        System.out.println(tableMap);
        
        for (Transition t : NFA.transitions) {
        Set<String> epTransitions = new HashSet<>();
        String epString = t.getFinalState().toString(); //Initial of the epsilon
        // transition
        // 
        for (Node n : NFA.nodes) {
        if(t.getInitialState().toString().equals(n.toString())){
        if(t.getSymbol().equals("ε") && n.nextStatesList()){
        // System.out.println(t.getInitialState()+"->"+t.getFinalState());
        epTransitions.add(epString);
        }
        }
        tableMap.put(n, epTransitions);
        }
        }
        System.out.println(tableMap);
          
       
    } */

   /*  public void GroupsSetting(){
        // create a new map to store the new sets of nodes
        Map<Node, Set<String>> newTableMap = new HashMap<>();
        
        for(Map.Entry<Node, Set<String>> entry: tableMap.entrySet()){
            Node tempNode = entry.getKey();
            Set<String> values = entry.getValue();
    
            for (String value : values) {
                Set<String> eClosure = new HashSet<>();
    
                for (Transition transition : NFA.transitions) {
    
                    if(value.equals(transition.getInitialState().toString())){
                        //Now we store the end points of all the ε-Closure transitions for the nodes
                        for (String eTransition : symboSet) {
    
                            if(transition.getSymbol().equals(eTransition)){
                                Node n = new Node(NFA.ID);
                                eClosure.add(transition.getFinalState().toString());
                                newTableMap.put(n, eClosure);
                                // transitions.add(newTableMap);
                                // System.out.println(newTableMap);
                            }
                        }
                    }                    
                }
            }
        }
        // update tableMap with the new sets of nodes
        tableMap.putAll(newTableMap);
    } */
    
    /* private Set<String> computeEpsilonClosure(Set<String> nodes) {
        // create a new set to store the epsilon-closure of the input nodes
        Set<String> eClosure = new HashSet<>();
    
        // add the input nodes to the epsilon-closure
        eClosure.addAll(nodes);
    
        // iterate over the input nodes
        for (String node : nodes) {
            // find all transitions with an epsilon symbol that start from the input node
            List<Transition> transitions = NFA.transitions.stream()
                .filter(t -> t.getSymbol().equals("ε") && t.getInitialState().toString().equals(node))
                .collect(Collectors.toList());
    
            // recursively compute the epsilon-closure of each transition's end node
            for (Transition t : transitions) {
                String endNode = t.getFinalState().toString();
                if (!eClosure.contains(endNode)) {
                    eClosure.add(endNode);
                    eClosure.addAll(computeEpsilonClosure(Collections.singleton(endNode)));
                }
            }
        }
    
        return eClosure;
    } */
    
    /* public void GroupsSetting(){
        // create a new map to store the new sets of nodes
        Map<Node, Set<String>> newTableMap = new HashMap<>();
        
        for(Map.Entry<Node, Set<String>> entry: tableMap.entrySet()){
            Node tempNode = entry.getKey();
            Set<String> values = entry.getValue();
    
            for (String value : values) {
                Set<String> eClosure = new HashSet<>();
    
                for (Transition transition : NFA.transitions) {
    
                    if(value.equals(transition.getInitialState().toString())){
                        //Now we store the end points of all the ε-Closure transitions for the nodes
                        for (String eTransition : symboSet) {
    
                            if(transition.getSymbol().equals(eTransition)){
                                Node n = new Node(NFA.ID);
                                eClosure.add(transition.getFinalState().toString());
                                newTableMap.put(n, eClosure);
                                System.out.println(newTableMap);
                            }
                        }
                    }                    
                }
            }
        }
        // update tableMap with the new sets of nodes
        tableMap.putAll(newTableMap);
    }
    

    public Set<String> eClosure(Set<String> nodes) {
        Set<String> result = new HashSet<>(nodes);
    
        for (String node : nodes) {
            for (Transition transition : NFA.transitions) {
                if (transition.getInitialState().toString().equals(node) &&
                    transition.getSymbol().equals("ε") &&
                    !result.contains(transition.getFinalState().toString())) {
    
                    // recursively call eClosure for the final state of the transition
                    Set<String> eClosure = eClosure(Collections.singleton(transition.getFinalState().toString()));
    
                    // add the result of the recursive call to the result set
                    result.addAll(eClosure);
                }
            }
        }
    
        return result;
    } */
    
    public void TransitionsGenerator() {
        Map<String, Set<String>> transitionsByState = new HashMap<>();
        
        for (Transition transition : NFA.transitions) {
            String initialState = transition.getInitialState().toString();
            String inputSymbol = transition.getSymbol();
    
            transitionsByState.computeIfAbsent(initialState, k -> new HashSet<>()).add(inputSymbol);
        }
    
        // Output the transitions for each unique initial state
        for (Map.Entry<String, Set<String>> entry : transitionsByState.entrySet()) {
            String initialState = entry.getKey();
            Set<String> inputSymbols = entry.getValue();
    
            System.out.println("Transitions for initial state " + initialState + ": " + inputSymbols);
            transitionsTable.put(initialState, inputSymbols);
        }
    }

    public void eTransitions(){
        Set<String> visited = new HashSet<>();
        
    }

    public void SymbolGetting(){
        for (Transition transition : NFA.transitions) {
            if(!transition.getSymbol().equals("ε")){
                symboSet.add(transition.getSymbol());
            }
        }
        System.out.println(symboSet);
    }

    public void NFAtransitions(){
        for (Transition transition : NFA.transitions) {
            if(!transition.getSymbol().equals("ε")){
                symboSet.add(transition.getSymbol());
                realTransitions.add(transition);
            }            
        }
    }

    public void EpsilonNFATransitions(){
        for (Transition transition : NFA.transitions) {
            if(transition.getSymbol().equals("ε")){
                epsilonTransitions.add(transition);
            }            
        }
    }

}