package com.example.Clasess.DFA;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class DirectDfa {
    private String regex;
    public static Integer ID;
    
    private List<nodeDFA> nodesList;
    private List<String> symbolList;
    private List<Integer> iNodes;
    private List<Integer> eNodes;

    private Stack<nodeDFA> stack;
    private List<nodeDFA> treeList;
    // DFAnode temp;

    // Map<Integer, List<Integer>> followpos;
    
    
    public DirectDfa(String reg){
        this.regex = reg;
        DirectDfa.ID = 0;

        this.treeList = new LinkedList<>();
        this.nodesList = new LinkedList<>();
        this.symbolList = new LinkedList<>();
        this.iNodes = new LinkedList<>();
        this.eNodes = new LinkedList<>();

        this.stack = new Stack<>();
        firstPosNlastPos();
        

    }

    public void firstPosNlastPos(){
        for (Character c : this.regex.toCharArray()) {
            switch(c){
                case 'ε':
                    //This one doesn't have a firstpos
                    List<nodeDFA> tempEpsilon = new LinkedList<>();
                    nodeDFA epsilon = new nodeDFA(c.toString());
                    epsilon.setIsNullable(true);

                    epsilon.setFirstPos(tempEpsilon);
                    epsilon.setLastPos(tempEpsilon);

                    nodesList.add(epsilon);
                    stack.push(epsilon);
                    break;

                case '|':
                    List<nodeDFA> tempOR = new LinkedList<>();

                    nodeDFA operationOR = new nodeDFA(c.toString());
                    nodeDFA right =  stack.pop();
                    nodeDFA left =  stack.pop();    

                    operationOR.setLeftNode(left);
                    operationOR.setRightNode(right);

                    tempOR.add(left);
                    tempOR.add(right);

                    operationOR.addToFirstPos(tempOR);
                    operationOR.addToLastPos(tempOR);

                    if(left.getIsNullable() == true ||right.getIsNullable() == true ){
                        operationOR.setIsNullable(true);
                    }
                    else{
                        operationOR.setIsNullable(false);
                    }

                    nodesList.add(operationOR);
                    stack.push(operationOR);
                    break;

                case '•':
                    List<nodeDFA> temp = new LinkedList<>();

                    nodeDFA operationConcat = new nodeDFA(c.toString());
                    
                    nodeDFA c2 =  stack.pop();
                    nodeDFA c1 =  stack.pop();    

                    operationConcat.setLeftNode(c1);
                    operationConcat.setRightNode(c2);

                    if(c1.getIsNullable() == true && c2.getIsNullable() == true){
                        operationConcat.setIsNullable(true);
                    }
                    else{
                        operationConcat.setIsNullable(false);
                    }


                    if(c1.getIsNullable() == true){
                        temp.add(c1);
                        temp.add(c2);
                        operationConcat.addToFirstPos(temp);
                    }
                    else{
                        temp.add(c1);
                        operationConcat.addToFirstPos(temp);
                    }
                    
                    temp.clear();

                    if(c2.getIsNullable() == true){
                        temp.add(c1);
                        temp.add(c2);
                        operationConcat.addToLastPos(temp);
                    }
                    else{
                        temp.add(c2);
                        operationConcat.addToLastPos(temp);
                    }
                    
                    nodesList.add(operationConcat);
                    stack.push(operationConcat);
                    break;

                case '*':
                    List<nodeDFA> tempTimes = new LinkedList<>();
                    nodeDFA operationTimes = new nodeDFA(c.toString());
                    operationTimes.setIsNullable(true);

                    nodeDFA only = stack.pop();
                    tempTimes.add(only);

                    operationTimes.setFirstPos(tempTimes);
                    operationTimes.setLastPos(tempTimes);

                    operationTimes.setHasChildren(true);
                    operationTimes.setNode(only);

                    nodesList.add(operationTimes);
                    stack.push(operationTimes);
                    break;

                case '#':
                    //this will end the process
                    List<nodeDFA> tempEnd = new LinkedList<>();
                    nodeDFA end = new nodeDFA(c.toString());

                    tempEnd.add(end);
                    end.setFirstPos(tempEnd);
                    end.setLastPos(tempEnd);
                    end.setEnd(true);

                    nodesList.add(end);
                    return;

                default:
                    List<nodeDFA> tempNodes = new LinkedList<>();
                    nodeDFA node = new nodeDFA(c.toString());                    
                    nodesList.add(node);
                    tempNodes.add(node);

                    node.setFirstPos(tempNodes);
                    node.setLastPos(tempNodes);
                    
                    stack.push(node);

                    break;
            }
        }
        // printList(nodesList);
    }

   /*  public static void printTree(List<nodeDFA> nodes) {
        for (nodeDFA node : nodes) {
            printNode(node, 0, false);
        }
    }
    
    private static void printNode(nodeDFA node, int depth, boolean isRight) {
        if (node == null) {
            return;
        }
    
        printNode(node.getRightNode(), depth + 1, true);
    
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
    
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
    
        System.out.println(node);
    
        printNode(node.getLeftNode(), depth + 1, false);
    }
    

    public void printList(List<nodeDFA> nodes){
        for (nodeDFA nodeDFA : nodes) {
            System.out.println(nodeDFA);
        }
    } */
    // voy a implementar el IfAbsent the los maps, en caso de que no encuentre el followpos del nodo que estamos iterando, lo agrega a la lista.

    //#region getters and setters
    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public static Integer getID() {
        return ID;
    }

    public static void setID(Integer iD) {
        ID = iD;
    }

    public List<nodeDFA> getNodesList() {
        return nodesList;
    }

    public void setNodesList(List<nodeDFA> nodesList) {
        this.nodesList = nodesList;
    }

    public List<String> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<String> symbolList) {
        this.symbolList = symbolList;
    }

    public List<Integer> getiNodes() {
        return iNodes;
    }

    public void setiNodes(List<Integer> iNodes) {
        this.iNodes = iNodes;
    }

    public List<Integer> geteNodes() {
        return eNodes;
    }

    public void seteNodes(List<Integer> eNodes) {
        this.eNodes = eNodes;
    }

    public Stack<nodeDFA> getStack() {
        return stack;
    }

    public void setStack(Stack<nodeDFA> stack) {
        this.stack = stack;
    }
    
    //#endregion
    

}
