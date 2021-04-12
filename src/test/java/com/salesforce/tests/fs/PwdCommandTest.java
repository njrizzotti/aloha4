package com.salesforce.tests.fs;

import com.salesforce.tests.fs.commands.PwdCommand;
import com.salesforce.tests.fs.utils.MessageUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Place holder for your unit tests
 */
public class PwdCommandTest {

    private Node actualNode = new Node("root",  true, new StringBuilder("/root"), null);
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void printCurrentDirectory(){
        PwdCommand pwd = new PwdCommand();
        Node result = pwd.process(actualNode, new ArrayList<>());
        Assert.assertTrue(actualNode == result);
        Assert.assertEquals("/root", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void printCurrentDirectoryWithInvalidParams(){
        PwdCommand pwd = new PwdCommand();
        Node result = pwd.process(actualNode, Arrays.asList("test"));
        Assert.assertTrue(actualNode == result);
        Assert.assertEquals(MessageUtils.INVALID_PARAMETER_MESSAGE, outputStreamCaptor.toString()
                .trim());
    }
}
