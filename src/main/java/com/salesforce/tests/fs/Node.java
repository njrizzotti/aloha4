package com.salesforce.tests.fs;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    boolean isDirectory;
    StringBuilder path;
    List<Node> childNodes;
    Node parent;

    public Node(String name, boolean isDirectory, StringBuilder path, Node parent) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.path = path;
        this.childNodes = new ArrayList<>();
        this.parent = parent;
    }

    public String getCurrentDirectory(){
         return path.toString();
    }

    public String getNodeName(){
        return this.name;
    }

    public boolean isDirectory(){
        return this.isDirectory;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public Node getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChildNode(Node node){
        this.getChildNodes().add(node);
    }
}
