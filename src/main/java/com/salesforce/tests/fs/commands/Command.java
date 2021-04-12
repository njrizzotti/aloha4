package com.salesforce.tests.fs.commands;

import com.salesforce.tests.fs.Node;
import java.util.List;

public interface Command {
    Node process(Node node, List<String> parameters);
}