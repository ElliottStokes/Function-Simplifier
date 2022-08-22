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
    private RootNode rootNode;
    private RPNParser rpnParser = new RPNParser();
    private HashMap<String, RootNode> epsilonSubTrees = new HashMap<>();

    private final String EPSILON = "epsilon";

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
        LinkedList<String> components = new LinkedList<>(List.of(rpnExpression.split(" ")));

        LeafNode leftNode, rightNode;
        BranchNode leftSubTree, rightSubTree;
        RootNode treeRootNode;

        String epsilonLabel;

        int operationIndex = 0;
        while (!components.get(operationIndex).equals(this.orderOfOperations.peek().toString()))
            operationIndex++;

        rightNode = new LeafNode(this.variables.get(components.remove(--operationIndex)));
        leftNode = new LeafNode(this.variables.get(components.remove(--operationIndex)));
        treeRootNode = new RootNode(this.orderOfOperations.remove(), leftNode, rightNode);
        components.remove(operationIndex);
        if (operationIndex >= 1 && components.get(operationIndex).equals(")") && components.get(operationIndex-1).equals("(")) {
            components.remove(operationIndex--); // Remove close bracket
            components.remove(operationIndex);   // Remove open bracket
        }

        while(!this.orderOfOperations.isEmpty()) {
            // Create the placeholder of the epsilon-subTree and add it in the position of the root node operator in the RPN expression
            epsilonLabel = EPSILON+this.epsilonSubTrees.size();
            components.add(operationIndex, epsilonLabel);
            this.epsilonSubTrees.put(epsilonLabel, treeRootNode);

            while (!components.get(operationIndex).equals(this.orderOfOperations.peek().toString()))
                operationIndex++;

            // If the current operator will be applied to two epsilon subtrees
            if (components.get(operationIndex-1).contains(EPSILON) && components.get(operationIndex-2).contains(EPSILON)) {
                rightSubTree = this.epsilonSubTrees.remove(components.remove(--operationIndex)).convertToBranch();
                leftSubTree = this.epsilonSubTrees.remove(components.remove(--operationIndex)).convertToBranch();
                treeRootNode = new RootNode(this.orderOfOperations.remove(), leftSubTree, rightSubTree);
                components.remove(operationIndex); // Remove operator
            }
            // If the current operator will be applied to an epsilon subtree in the right node
            else if (components.get(operationIndex-1).contains(EPSILON)) {
                rightSubTree = this.epsilonSubTrees.remove(components.remove(--operationIndex)).convertToBranch();
                leftNode = new LeafNode(this.variables.get(components.remove(--operationIndex)));
                treeRootNode = new RootNode(this.orderOfOperations.remove(), leftNode, rightSubTree);
                components.remove(operationIndex); // Remove operator
            }
            // If the current operator will be applied to an epsilon subtree in the left node
            else if (components.get(operationIndex-2).contains(EPSILON)) {
                rightNode = new LeafNode(this.variables.get(components.remove(--operationIndex)));
                leftSubTree = this.epsilonSubTrees.remove(components.remove(--operationIndex)).convertToBranch();
                treeRootNode = new RootNode(this.orderOfOperations.remove(), leftSubTree, rightNode);
                components.remove(operationIndex); // Remove operator
            }
            else {
                rightNode = new LeafNode(this.variables.get(components.remove(--operationIndex)));
                leftNode = new LeafNode(this.variables.get(components.remove(--operationIndex)));
                treeRootNode = new RootNode(this.orderOfOperations.remove(), leftNode, rightNode);
                components.remove(operationIndex); // Remove operator
            }

            if (components.size() > 1 && components.get(operationIndex).equals(")") && components.get(operationIndex-1).equals("(")) {
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
            return depthFirstTraversal_R(mainNode.getLeftNode())  + depthFirstTraversal_R(mainNode.getRightNode()) + mainNode;
    }

    public RootNode getRootNode() {
        return this.rootNode;
    }

    public String getReversePolishNotation() {
        return this.reversePolishNotation;
    }
}
