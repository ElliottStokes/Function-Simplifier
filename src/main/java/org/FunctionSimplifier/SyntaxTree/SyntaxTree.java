package org.FunctionSimplifier.SyntaxTree;

import org.FunctionSimplifier.ReversePolishNotation.Operators.*;
import org.FunctionSimplifier.ReversePolishNotation.RPNParser;
import org.FunctionSimplifier.Variable;

import java.util.*;

public class SyntaxTree {
    private String infixNotation;
    private String reversePolishNotation;
    private Queue<Operator> orderOfOperations;
    private HashMap<String, Variable> variables;
    private RootNode rootNode = null;
    private RPNParser rpnParser = new RPNParser();

    public SyntaxTree(String _infixExpression) {
        this.infixNotation = _infixExpression;
        this.reversePolishNotation = rpnParser.parse(this.infixNotation);

        this.orderOfOperations = new LinkedList<>();
        this.variables = new HashMap<>();

        String[] components = this.reversePolishNotation.split(" ");

        for (String component : components)
            if (rpnParser.isOperator(component))
                if (component.equals("+"))
                    this.orderOfOperations.add(new Add());
                else if (component.equals("-"))
                    this.orderOfOperations.add(new Subtract());
                else if (component.equals("*"))
                    this.orderOfOperations.add(new Multiply());
                else if (component.equals("/"))
                    this.orderOfOperations.add(new Divide());
                else
                    this.orderOfOperations.add(new Power());
            else
                if (!this.variables.containsKey(component))
                    this.variables.put(component, new Variable(component));

        this.rootNode = createTree(this.reversePolishNotation);
    }

    private RootNode createTree(String rpnExpression) {
        //StringBuilder rpnCopy = new StringBuilder(rpnExpression);
        LinkedList<String> components = new LinkedList<>(List.of(rpnExpression.split(" ")));

        LeafNode leftNode, rightNode;
        BranchNode leftSubTree, rightSubTree;

        int operationIndex = 0;
        while (!components.get(operationIndex).equals(this.orderOfOperations.peek().toString()))
            operationIndex++;

        rightNode = new LeafNode(this.variables.get(components.get(operationIndex-1)));
        leftNode = new LeafNode(this.variables.get(components.get(operationIndex-2)));
        RootNode treeRootNode = new RootNode(this.orderOfOperations.remove(), leftNode, rightNode);
        components.remove(operationIndex--);
        components.remove(operationIndex--);
        components.remove(operationIndex);

        while(!this.orderOfOperations.isEmpty()) {
            while (!components.get(operationIndex).equals(this.orderOfOperations.peek().toString()))
                operationIndex++;
            rightNode = new LeafNode(this.variables.get(components.get(operationIndex-1)));

            if (operationIndex >= 2 && components.get(operationIndex-2).equals("(")) {
                treeRootNode = new RootNode(this.orderOfOperations.remove(), treeRootNode.convertToBranch(), rightNode);
                components.remove(operationIndex + 1); // Remove close bracket
                components.remove(operationIndex--);         // Remove operator
                components.remove(operationIndex--);         // Remove variable
                components.remove(operationIndex);         // Remove open bracket
            }
            else if (operationIndex >= 2 && !rpnParser.isOperator(components.get(operationIndex-2))) {
                rightSubTree = new BranchNode(this.orderOfOperations.remove(), new LeafNode(this.variables.get(components.get(operationIndex-2))), rightNode);
                treeRootNode = new RootNode(this.orderOfOperations.remove(), treeRootNode.convertToBranch(), rightSubTree);
                components.remove(operationIndex--);
                components.remove(operationIndex--);
                components.remove(operationIndex);
            }
            else {
                treeRootNode = new RootNode(this.orderOfOperations.remove(), treeRootNode.convertToBranch(), rightNode);
                components.remove(operationIndex--);
                components.remove(operationIndex);
            }
        }
        return treeRootNode;
    }

    public String depthFirstTraversal() {
        return depthFirstTraversal_R(this.rootNode);
    }

    private String depthFirstTraversal_R(Node mainNode) {
        if (mainNode instanceof LeafNode)
            return mainNode.toString();
        else
            return depthFirstTraversal_R(mainNode.getLeftNode())  + depthFirstTraversal_R(mainNode.getRightNode()) + mainNode.toString();
    }

    public RootNode getRootNode() {
        return this.rootNode;
    }

    public String getReversePolishNotation() {
        return this.reversePolishNotation;
    }
}
