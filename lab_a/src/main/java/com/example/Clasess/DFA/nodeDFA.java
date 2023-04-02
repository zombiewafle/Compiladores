package com.example.Clasess.DFA;

import java.util.LinkedList;
import java.util.List;

public class nodeDFA {

    List<nodeDFA> firstPos;
    List<nodeDFA> lastPos;
    
    nodeDFA rightNode;
    nodeDFA leftNode;
    nodeDFA node;

    String symbol;
    Integer ID;
    Boolean hasChildren;
    Boolean isNullable;
    Boolean end;
    Boolean start;
    
    //Normal node, has two children
    public nodeDFA(nodeDFA leftNode, String symbol, nodeDFA rightNode){
        this.ID = DirectDfa.ID++;
        this.symbol = symbol;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.firstPos = new LinkedList<>();
        this.lastPos = new LinkedList<>();
        this.end = false;
        this.start = false;
        this.isNullable =false;
        this.hasChildren = true;
    }

    //Operation node, only has his own symbol
    public nodeDFA(String symbol){
        this.ID = DirectDfa.ID++;
        this.symbol = symbol;
        this.firstPos = new LinkedList<>();
        this.lastPos = new LinkedList<>();
        this.firstPos = new LinkedList<>();
        this.lastPos = new LinkedList<>();
        this.end = false;
        this.start = false;
        this.isNullable = false;
        this.hasChildren = false;
    }

    //Node alone 
    public nodeDFA(nodeDFA nodeDFA){
        this.ID = DirectDfa.ID++;
        this.node = nodeDFA;
        this.firstPos = new LinkedList<>();
        this.lastPos = new LinkedList<>();
        this.firstPos = new LinkedList<>();
        this.lastPos = new LinkedList<>();
        this.end = false;
        this.start = false;
        this.isNullable = false;
        this.hasChildren = true;
    }


    //#region getters and setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<nodeDFA> getFirstPos() {
        return firstPos;
    }

    public void addToFirstPos(List<nodeDFA> firstPos){
        for (nodeDFA nodeDFA : firstPos) {
            this.firstPos.add(nodeDFA);
        }
        
    }

    public void addToLastPos(List<nodeDFA> lastPos){
        for (nodeDFA nodeDFA : lastPos) {
            this.lastPos.add(nodeDFA);
        }
        
    }

    public void setFirstPos(List<nodeDFA> firstPos) {
        this.firstPos = firstPos;
    }

    public List<nodeDFA> getLastPos() {
        return lastPos;
    }

    public void setLastPos(List<nodeDFA> lastPos) {
        this.lastPos = lastPos;
    }

    public nodeDFA getRightNode() {
        return rightNode;
    }

    public void setRightNode(nodeDFA rightNode) {
        this.rightNode = rightNode;
    }

    public nodeDFA getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(nodeDFA leftNode) {
        this.leftNode = leftNode;
    }

    public nodeDFA getNode() {
        return node;
    }

    public void setNode(nodeDFA node) {
        this.node = node;
    }

    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public Boolean getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Boolean isNullable) {
        this.isNullable = isNullable;
    }

    //#endregion
    
    @Override
    public String toString(){
        return ""+symbol+""  ;
    }
    
}
