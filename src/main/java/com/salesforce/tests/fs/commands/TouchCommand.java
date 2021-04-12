package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.Node;
import com.salesforce.tests.fs.utils.MessageUtils;
import java.util.List;

public class TouchCommand implements Command {
    @Override
    public Node process(Node node, List<String> parameters) {
        String parameter = parameters.get(0);
        if(!isValidName(parameters)){
            System.out.println(MessageUtils.INVALID_PARAMETER_LENGTH);
            return node;
        }
        if(!areValidParameters(parameters)){
            System.out.println(MessageUtils.INVALID_PARAMETER_MESSAGE);
            return node;
        }
        for(Node n: node.getChildNodes()){
            if(!n.isDirectory()) {
                if (n.getNodeName().equals(parameter)) {
                    System.out.println(MessageUtils.FILE_ALREADY_EXISTS);
                    return node;
                }
            }
        }
        Node newNode = new Node(parameter, false, new StringBuilder("/").append(parameter) ,node);
        node.addChildNode(newNode);
        return node;
    }

    private boolean isValidName(List<String> parameters){
        return parameters.get(0).length()<=100;
    }

    private boolean areValidParameters(List<String> parameters) {
        return parameters.size() == 1;
    }
}
