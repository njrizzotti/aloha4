package com.salesforce.tests.fs;

import com.salesforce.tests.fs.commands.ChangeDirectoryCommand;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Place holder for your unit tests
 */
public class ChangeDirectoryCommandTest{

    private Node actualNode = new Node("root",  true, new StringBuilder("/root"), null);

    @Test
    public void changeDirectoryfromRootToRoot(){
        ChangeDirectoryCommand cd = new ChangeDirectoryCommand();
        Node result = cd.process(actualNode, Arrays.asList(".."));
        Assert.assertEquals(actualNode, result);
    }

    @Test
    public void changeDirectoryFromRootToChild(){
        actualNode.getChildNodes().add(new Node("child", true, new StringBuilder("/child"), actualNode));
        ChangeDirectoryCommand cd = new ChangeDirectoryCommand();
        Node result = cd.process(actualNode, Arrays.asList("child"));
        Assert.assertTrue(actualNode.getChildNodes().get(0) == result);
    }

    @Test
    public void changeDirectoryFromRootToAnUnexistingDirectory(){
        ChangeDirectoryCommand cd = new ChangeDirectoryCommand();
        Node result = cd.process(actualNode, Arrays.asList("child"));
        Assert.assertEquals(actualNode, result);
    }
}
