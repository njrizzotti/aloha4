package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.Node;
import com.salesforce.tests.fs.utils.MessageUtils;
import java.util.List;

public class MkDirCommand implements Command {
    @Override
    public Node process(Node actualNode, List<String> parameters) {
        String parameter = parameters.get(0);
        if(!isValidName(parameters)){
            System.out.println(MessageUtils.INVALID_PARAMETER_LENGTH);
            return actualNode;
        }
        if(!areValidParameters(parameters)){
            System.out.println(MessageUtils.INVALID_PARAMETER_MESSAGE);
            return actualNode;
        }
        for(Node n: actualNode.getChildNodes()){
            if(n.getCurrentDirectory().equals("/"+ parameter)){
                System.out.println(MessageUtils.DIRECTORY_ALREADY_EXIST);
                return actualNode;
            }

        }
        Node node = new Node(parameter, true, new StringBuilder("/").append(parameter) ,actualNode);
        actualNode.addChildNode(node);
        return actualNode;
    }

    private boolean areValidParameters(List<String> parameters) {
        return parameters.size() ==1;
    }

    private boolean isValidName(List<String> parameters){
        String name = parameters.get(0);
        return name.length()<=100;
    }

}
