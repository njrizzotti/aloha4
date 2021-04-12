package com.salesforce.tests.fs;

import org.junit.Test;

import java.io.IOException;

/**
 * Sample Unit Tests
 */
public class SampleTest extends BaseTest {

    @Test
    public void testPwd() throws IOException {
        String[] expectedResults = {
                "/root\n"
        };
        runTest(expectedResults, "pwd", "quit");
    }

    @Test
    public void testQuit() throws IOException {
        String[] expectedResults = {
                ""
        };
        runTest(expectedResults, "quit");
    }

    @Test
    public void testListCommand() throws IOException {
        String[] expectedResults = {
                "/root\n"
        };
        runTest(expectedResults, "list", "quit");
    }

    @Test
    public void testListCommandRootWithChildren() throws IOException {
        String[] expectedResults = {
                "/root\n\t/childRoot1\n"
        };
        runTest(expectedResults, "mkdir childRoot1","list", "quit");
    }

    @Test
    public void testListCommandRootWithChildrenAndFileInsideIt() throws IOException {
        String[] expectedResults = {
                "/childRoot1\n\t/testFile.txt\n"
        };
        runTest(expectedResults, "mkdir childRoot1","cd childRoot1", "touch testFile.txt", "list", "quit");
    }

    @Test
    public void testChangeDirectoryCommandFromRootToChildDirectory() throws IOException {
        String[] expectedResults = {
                "/childRoot1\n"
        };
        runTest(expectedResults, "mkdir childRoot1","cd childRoot1", "pwd", "quit");
    }

    @Test
    public void testChangeDirectoryfromChildDirectoryToParentDirectory() throws IOException {
        String[] expectedResults = {
                "/root\n"
        };
        runTest(expectedResults, "mkdir childRoot1","cd childRoot1","cd ..", "pwd", "quit");
    }

    @Test
    public void testChangeDirectoryToAnUnexistingDirectory() {
        String[] expectedResults = {
                "Directory not found\n"
        };
        runTest(expectedResults, "cd childRoot1","quit");
    }
}
