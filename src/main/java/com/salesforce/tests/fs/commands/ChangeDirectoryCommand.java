package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.Node;
import com.salesforce.tests.fs.utils.MessageUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeDirectoryCommand implements Command{

    private final String CHANGE_DIRECTORY_TO_PARENT_PARAM = "..";

    @Override
    public Node process(Node actualNode, List<String> parameters) {
        String parameter = parameters.get(0).trim();
        if(!areValidParameters(parameters)){
            System.out.println(MessageUtils.INVALID_PARAMETER_MESSAGE);
            return actualNode;
        }
        if(parameter.equals(CHANGE_DIRECTORY_TO_PARENT_PARAM)){
            if(actualNode.getParent() != null){
                return actualNode.getParent();
            }
        }
        else{
            for(Node n: actualNode.getChildNodes()){
                if(n.getCurrentDirectory().equals(new StringBuilder("/").append(parameter).toString()))
                    return n;
            }
            System.out.println(MessageUtils.DIRECTORY_NOT_FOUND_MESSAGE);
        }
        return actualNode;
    }

    private boolean areValidParameters(List<String> parameters) {

        String parameter = parameters.get(0);
        if (parameter.equals(CHANGE_DIRECTORY_TO_PARENT_PARAM) || isValidName(parameter)) {
            return true;
        } else
            return false;
    }

    private boolean isValidName(String name){
        Pattern patter = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = patter.matcher(name);
        return matcher.matches();
    }

}
