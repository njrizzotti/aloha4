package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.Node;
import com.salesforce.tests.fs.utils.MessageUtils;
import java.util.List;

public class ListCommand implements Command{

    private final String RECURSION_PARAM = "-r";
    @Override
    public Node process(Node node, List<String> parameters) {
        String parameter = parameters.get(0);
        if(!areValidParameters(parameters)){
            System.out.println(MessageUtils.INVALID_PARAMETER_MESSAGE);
            return node;
        }
        if(parameter.equals(RECURSION_PARAM))
            printTest(node, 0);
        else
            printWithoutRecursion(node);
        return node;
    }

    private void printTest(Node node, int value){
        for(int i = 0; i<value;i++)
            System.out.print("\t");
        System.out.println(node.getCurrentDirectory());
        if(node.isDirectory()){
            for(Node n: node.getChildNodes()){
                printTest(n, value+1);
            }
        }
    }

    private void printWithoutRecursion(Node node){
        System.out.println(node.getCurrentDirectory());
        for(Node n: node.getChildNodes()){
            System.out.println("\t" +n.getCurrentDirectory());
        }
    }

    private boolean areValidParameters(List<String> parameters) {
        if(parameters.size() == 1 && (parameters.get(0).equals(validParams())||parameters.get(0).equals(""))){
            return true;
        }
        else
            return false;
    }

    private String validParams(){
        return RECURSION_PARAM;
    }
}
