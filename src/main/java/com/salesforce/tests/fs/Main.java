package com.salesforce.tests.fs;

import com.salesforce.tests.fs.commands.ChangeDirectoryCommand;
import com.salesforce.tests.fs.commands.ListCommand;
import com.salesforce.tests.fs.commands.MkDirCommand;
import com.salesforce.tests.fs.commands.PwdCommand;
import com.salesforce.tests.fs.commands.TouchCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The entry point for the Test program
 */
public class Main {

    public static void main(String[] args) {
        Scanner command = new Scanner(System.in);
        boolean running = true;
        PwdCommand pwd = new PwdCommand();
        ListCommand list = new ListCommand();
        MkDirCommand mkdir = new MkDirCommand();
        ChangeDirectoryCommand changeDirectory = new ChangeDirectoryCommand();
        TouchCommand touch = new TouchCommand();
        Node actualNode = new Node("root",  true, new StringBuilder("/root"), null);
        while(running){
            String input = command.nextLine();
            String operation = getCommand(input);
            String parameter = "";
            if(hasParameter(input))
                parameter = input.substring(input.indexOf(' ')+1);
            switch(operation){
                case "pwd":
                    pwd.process(actualNode, new ArrayList());
                    break;
                case "list":
                    list.process(actualNode, Arrays.asList(parameter));
                    break;
                case "mkdir":
                    actualNode = mkdir.process(actualNode, Arrays.asList(parameter));
                    break;
                case "cd":
                    actualNode = changeDirectory.process(actualNode, Arrays.asList(parameter));
                    break;
                case "touch":
                    actualNode = touch.process(actualNode, Arrays.asList(parameter));
                    break;
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("Unrecognized command");
                    break;
            }
        }
        command.close();
    }

    public static boolean hasParameter(String input){
        String[] s = input.split(" ");
        return s.length>1;
    }

    public static String getCommand(String input){
        String[] s = input.split(" ");
        return s[0];
    }
}
