package com.example.Clasess;

// import java.util.Map;
// import java.util.Set;

// import java.util.LinkedList;
// import java.util.List;

import org.graphstream.graph.implementations.*;


public class Graph {

    public static MultiGraph multiGraph;
    
    public Graph(){
        // nList = new LinkedList<Character>();
        Graph.multiGraph = new MultiGraph("Graph");

        System.setProperty("org.graphstream.ui", "swing");
        Graph.multiGraph.setAttribute("ui.stylesheet", "url('/home/zombiewafle/Documentos/lab_a/src/main/java/com/example/Clasess/styles.css')");
    }

    public static void NFAgraphConstruction(){
        Integer num = 0;
        
        for (Node node : NFA.nodes) {
            Graph.multiGraph.addNode(node.toString()).setAttribute("ui.label", node.getID());
        }
        
        for (Transition transition : NFA.transitions) {
            num++;
            Graph.multiGraph.addEdge(transition.getSymbol()+num, transition.getInitialState().toString(), transition.getFinalState().toString(), true).setAttribute("ui.label",transition.getSymbol()); 
        }
        // Graph.multiGraph.setAttribute("ui.stylesheet", "url('/home/zombiewafle/Documentos/lab_a/src/main/java/com/example/Clasess/styles.css')");
        multiGraph.display();
        Node.fID = 0;
    } 

    public static void DFAgraphConstruction(){
        Integer counter = 10;
        // System.out.println(DFA.dfaNodes);

        // for (Node node : DFA.generalDFANodes) {
        //     Graph.multiGraph.addNode(node.toString()).setAttribute("ui.label", node.getID());
        //     // System.out.println(node);
        // }
        // // System.out.println(DFA.transitions);
        // for (Transition t : DFA.transitions) {
        //     Graph.multiGraph.addEdge(t.getSymbol()+counter, t.getInitialState().toString(), t.getFinalState().toString(), false).setAttribute("ui.label",t.getSymbol()); 
        // }

        // Map<StartMap, EndMap> General Map
        
        

        multiGraph.display();
        Node.fID = 0;
    }
    
    //#region
    public void addNode(String label) {
        multiGraph.addNode(label);
    }
    
    public void setNodeAttribute(String nodeId, String attribute, Object value) {
        multiGraph.getNode(nodeId).setAttribute(attribute, value);
    }
     
     public void setEdgeAttribute(String edgeId, String attribute, Object value) {
        multiGraph.getEdge(edgeId).setAttribute(attribute, value);
    }
    //#endregion    
    
}
