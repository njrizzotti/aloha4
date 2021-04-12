package com.salesforce.tests.fs;

import com.salesforce.tests.fs.commands.ListCommand;
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
public class ListCommandTest {

    private Node actualNode = new Node("root",  true, new StringBuilder("/root"), null);
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @Before
    public void setUp() {
       System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void listCommandWithoutRecursion(){
        ListCommand list = new ListCommand();
        Node result = list.process(actualNode, Arrays.asList(""));
        Assert.assertEquals(actualNode, result);
        Assert.assertEquals("/root", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void listDirectoryWithRecursion(){
        // al nodo padre le agrego un directorio hijo
        actualNode.getChildNodes().add(new Node("child", true, new StringBuilder("/child"), actualNode));
        //al directorio hijo le agrego un file
        actualNode.getChildNodes().get(0).getChildNodes().add(new Node("child", false, new StringBuilder("/test.file"), actualNode.getChildNodes().get(0)));
        ListCommand list = new ListCommand();
        Node result = list.process(actualNode, Arrays.asList("-r"));
        Assert.assertEquals(actualNode, result);
        Assert.assertEquals("/root\n\t/child\n\t\t/test.file", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void listDirectoryWithInvalidParams(){
        ListCommand list = new ListCommand();
        Node result = list.process(actualNode, Arrays.asList("test"));
        Assert.assertTrue(actualNode == result);
        Assert.assertEquals(MessageUtils.INVALID_PARAMETER_MESSAGE, outputStreamCaptor.toString()
                .trim());
    }
}
