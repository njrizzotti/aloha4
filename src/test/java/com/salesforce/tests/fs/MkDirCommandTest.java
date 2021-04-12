package com.salesforce.tests.fs;

import com.salesforce.tests.fs.commands.MkDirCommand;
import com.salesforce.tests.fs.utils.MessageUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Place holder for your unit tests
 */
public class MkDirCommandTest {

    private Node actualNode = new Node("root",  true, new StringBuilder("/root"), null);
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void createDirectoryOnRoot(){
        MkDirCommand mkdir = new MkDirCommand();
        Node result = mkdir.process(actualNode, Arrays.asList("child"));
        Assert.assertTrue(actualNode == result);
        Assert.assertEquals(actualNode.getChildNodes().get(0).path.toString(),"/child");
    }

    @Test
    public void createDirectoryThatAlreadyExist(){
        actualNode.getChildNodes().add(new Node("child", true, new StringBuilder("/child"), actualNode));
        MkDirCommand mkdir = new MkDirCommand();
        Node result = mkdir.process(actualNode, Arrays.asList("child"));
        Assert.assertTrue(actualNode == result);
        Assert.assertTrue(actualNode.getChildNodes().size() == 1);
        Assert.assertEquals(actualNode.getChildNodes().get(0).path.toString(),"/child");
        Assert.assertEquals(MessageUtils.DIRECTORY_ALREADY_EXIST, outputStreamCaptor.toString()
                .trim());
    }
}
