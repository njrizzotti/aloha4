package com.salesforce.tests.fs;

import com.salesforce.tests.fs.commands.TouchCommand;
import com.salesforce.tests.fs.utils.MessageUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

/**
 * Place holder for your unit tests
 */
public class TouchCommandTest {

    private Node actualNode = new Node("root",  true, new StringBuilder("/root"), null);
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void createFileOnDirectory(){
        TouchCommand touch = new TouchCommand();
        Node result = touch.process(actualNode, Arrays.asList("file.test"));
        Assert.assertTrue(actualNode == result);
        Assert.assertEquals(actualNode.getChildNodes().get(0).path.toString(),"/file.test");
    }

    @Test
    public void createFileWithInvalidName(){
        TouchCommand touch = new TouchCommand();
        Node result = touch.process(actualNode, Arrays.asList(generateString(101)));
        Assert.assertTrue(actualNode == result);
        Assert.assertEquals(MessageUtils.INVALID_PARAMETER_LENGTH, outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void createFileThatAlreadyExist(){
        actualNode.getChildNodes().add(new Node("file.test", false, new StringBuilder("file.test"), actualNode));
        TouchCommand touch = new TouchCommand();
        Node result = touch.process(actualNode, Arrays.asList("file.test"));
        Assert.assertTrue(actualNode == result);
        Assert.assertEquals(MessageUtils.FILE_ALREADY_EXISTS, outputStreamCaptor.toString()
                .trim());
    }



    private String generateString(int characters) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = characters;
        Random random = new Random();

        return  random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
