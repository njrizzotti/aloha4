package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.Node;
import com.salesforce.tests.fs.utils.MessageUtils;
import java.util.List;

public class PwdCommand implements Command{
    public Node process(Node node, List<String> parameters) {
        if(!areValidParameters(parameters)) {
            System.out.println(MessageUtils.INVALID_PARAMETER_MESSAGE);
            return node;
        }
        print(node);
        return node;
    }

    private void print(Node node){
            System.out.println(node.getCurrentDirectory());
    }

    private boolean areValidParameters(List<String> parameters) {
        return parameters.size() == 0;
    }
}