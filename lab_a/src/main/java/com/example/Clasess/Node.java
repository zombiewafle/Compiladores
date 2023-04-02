package com.example.Clasess;

import java.util.HashSet;
// import java.util.HashMap;
// import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Node {
    //private Character symbol;
    public static Integer fID = 0;
    private int ID;
    Integer dfaIDF = 0;
    private String STID;

    public List<Node> nextStatesList;
    public List<Node> previousStatesList;

    public Set<Node> nextSetNodes;
    public Set<Node> previousSetNodes;

    private Boolean start;
    private Boolean end;

    public Map<Node, Set<Transition>> nextGroupSet;
    public Map<Node, Set<Transition>> previousGroupSet;

    Set<Transition> nextSubSet;
    Set<Transition> previousSubSet;
    

    public Node(Integer ID /*, Character symbol*/){
        this.ID =  fID++;
        this.previousStatesList = new LinkedList<Node>();
        this.nextStatesList = new LinkedList<Node>();
        this.nextSetNodes = new HashSet<>();
        this.previousSetNodes = new HashSet<>();
        this.end = false;
        this.start = false;
    }

    public Node(Integer ID, String symbol /*, Character symbol*/){
        this.ID =  fID++;
        this.STID = symbol;
        this.nextSetNodes = new HashSet<>();
        this.previousSetNodes = new HashSet<>();
        this.previousStatesList = new LinkedList<Node>();
        this.nextStatesList = new LinkedList<Node>();
        this.end = false;
        this.start = false;
    }

    public Node(Integer ID, List<Node> nextStates, List<Node> previousStates){
        this.ID =  fID++;
        this.previousStatesList = new LinkedList<Node>();
        this.nextStatesList = new LinkedList<Node>();
        this.nextSetNodes = new HashSet<>();
        this.previousSetNodes = new HashSet<>();
        this.end = false;
        this.start = false;
    }

    public Node(Integer ID, Set<String> group){
        this.ID = fID++;
        this.previousStatesList = new LinkedList<Node>();
        this.nextStatesList = new LinkedList<Node>();
        this.nextSetNodes = new HashSet<>();
        this.previousSetNodes = new HashSet<>();
        this.end = false;
        this.start = false;
    }

    public Node(Integer ID, Map<Integer, Set<String>> subMap){
        this.ID = fID++;
        this.previousStatesList = new LinkedList<Node>();
        this.nextStatesList = new LinkedList<Node>();
        this.nextSetNodes = new HashSet<>();
        this.previousSetNodes = new HashSet<>();
        this.end = false;
        this.start = false;
    }

    
    public Node(int currStateListID, boolean b) {
        this.ID = fID++;
        this.previousStatesList = new LinkedList<>();
        this.nextStatesList = new LinkedList<>();
    }

    /* public State(int stateId, boolean dfa) {
        this.stateId = stateId;
        this.previousStates = new LinkedList<>();
        this.nextStates = new LinkedList<>();
    } */

    //#region Getters and setters 
    public Integer getDfaIDF() {
        return dfaIDF;
    }

    public Map<Node, Set<Transition>> getNextGroupSet() {
        return nextGroupSet;
    }

    public void setNextGroupSet(Map<Node, Set<Transition>>nextGroupSet) {
        this.nextGroupSet = nextGroupSet;
    }

    public Map<Node, Set<Transition>> getPreviousGroupSet() {
        return previousGroupSet;
    }

    public void setPreviousGroupSet(Map<Node, Set<Transition>> previousGroupSet) {
        this.previousGroupSet = previousGroupSet;
    }

    public void setDfaIDF(Integer dfaIDF) {
        this.dfaIDF = dfaIDF;
    }

    public String getSTID() {
        return STID;
    }

    public void setSTID(String sTID) {
        STID = sTID;
    }

    public Integer getID() {
        return ID;
    }
    
    public void setID(Integer ID){
        this.ID = ID;
    }


    public List<Node> getNextStatesList() {
        return nextStatesList;
    }

    public Set<Node> getNextNodesSet() {
        return nextSetNodes;
    }

    public Set<Node> getPreviousNodesSet() {
        return previousSetNodes;
    }
    

    public void setNextSetNodes(Set<Node> nextSetNodes) {
        this.nextSetNodes = nextSetNodes;
    }

    public void setPreviousSetNodes(Set<Node> previousSetNodes) {
        this.previousSetNodes = previousSetNodes;
    }

    public void setNextStatesList(List<Node> nextStatesList) {
        this.nextStatesList = nextStatesList;
    }

    public List<Node> getPreviousStatesList() {
        return previousStatesList;
    }

    public void setPreviousStatesList(List<Node> previousStatesList) {
        this.previousStatesList = previousStatesList;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }
    
    public void addNextState(Node nState){
        this.nextStatesList.add(nState);
    }

    public void addNextSubgroup(Node node, Set<Transition> transitions){
        this.nextGroupSet.put(node, transitions);
    }

    public void addPreviousSubgroup(Node node, Set<Transition> transitions){
        this.previousGroupSet.put(node, transitions);
    }
    
    public void addPreviousState(Node pState){
        this.previousStatesList.add(pState);   
    }
    
    //#endregion
    
    @Override
    public String toString(){
        return ""+ID+"";
    }
    
}
