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
        this.convertToCodedSyntaxTree();
    }

    private RootNode createTree(String rpnExpression) {
        LinkedList<String> components = new LinkedList<>(List.of(rpnExpression.split(" ")));

        LeafNode leftNode, rightNode;
        BranchNode leftSubTree, rightSubTree;
        RootNode treeRootNode;

        String epsilonLabel;
        int epsilonNumber = 1;

        int operationIndex = 0;
        while (!components.get(operationIndex).equals(this.orderOfOperations.peek().toString()))
            operationIndex++;

        rightNode = new LeafNode(this.variables.get(components.remove(--operationIndex)));
        leftNode = new LeafNode(this.variables.get(components.remove(--operationIndex)));
        treeRootNode = new RootNode(this.orderOfOperations.remove(), leftNode, rightNode);
        components.remove(operationIndex);
        if (operationIndex >= 1 && components.get(operationIndex).equals(")") && components.get(operationIndex-1).equals("(")) {
            rightNode = new LeafNode(new Variable(")"));
            leftSubTree = treeRootNode.convertToBranch();
            treeRootNode = new RootNode(new OpenBracket(), leftSubTree, rightNode);
            components.remove(operationIndex--); // Remove close bracket
            components.remove(operationIndex);   // Remove open bracket
        }

        while(!this.orderOfOperations.isEmpty()) {
            // Create the placeholder of the epsilon-subTree and add it in the position of the root node operator in the RPN expression
            epsilonLabel = EPSILON+(epsilonNumber++);
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

            if (operationIndex >= 1 && components.get(operationIndex).equals(")") && components.get(operationIndex-1).equals("(")) {
                rightNode = new LeafNode(new Variable(")"));
                leftSubTree = treeRootNode.convertToBranch();
                treeRootNode = new RootNode(new OpenBracket(), leftSubTree, rightNode);
                components.remove(operationIndex--); // Remove close bracket
                components.remove(operationIndex);   // Remove open bracket
            }
        }
        return treeRootNode;
    }

    public void convertToCodedSyntaxTree() {
        int order = 1;
        order = codedSyntaxTree_DFT(this.rootNode, order);
    }

    private int codedSyntaxTree_DFT(Node node, int order) {
        if (node instanceof BranchNode) {
            order = codedSyntaxTree_DFT(node.getLeftNode(), order);
            order = codedSyntaxTree_DFT(node.getRightNode(), order);
            if (!node.toString().equals("("))
                ((BranchNode) node).setOrder(order++);
        }
        else if (node instanceof RootNode) {
            order = codedSyntaxTree_DFT(node.getLeftNode(), order);
            order = codedSyntaxTree_DFT(node.getRightNode(), order);
            ((RootNode) node).setOrder(order++);
        }

        return order;
    }

    public String depthFirstTraversal() {
        return depthFirstTraversal_R(this.rootNode);
    }

    private String depthFirstTraversal_R(Node mainNode) {
        if (mainNode instanceof LeafNode)
            return mainNode.toString();
        else if (mainNode.toString().equals("("))
            return mainNode + depthFirstTraversal_R(mainNode.getLeftNode()) + depthFirstTraversal_R(mainNode.getRightNode());
        else
            return depthFirstTraversal_R(mainNode.getLeftNode()) + depthFirstTraversal_R(mainNode.getRightNode()) + mainNode;
    }

    public String inOrderTraversal() {
        return inOrderTraversal_R(this.rootNode);
    }

    private String inOrderTraversal_R(Node mainNode) {
        if (mainNode instanceof LeafNode)
            return mainNode.toString();
        else if (mainNode.toString().equals("("))
            return mainNode + inOrderTraversal_R(mainNode.getLeftNode()) + inOrderTraversal_R(mainNode.getRightNode());
        else
            return inOrderTraversal_R(mainNode.getLeftNode()) + mainNode + inOrderTraversal_R(mainNode.getRightNode());
    }

    public String simplify() {
        Node simplifiedTree = this.rootNode;
        Node previousTree;
        do {
            previousTree = simplifiedTree;
            simplifiedTree = simplify_DFT(previousTree);
        } while (!this.inOrderTraversal_R(simplifiedTree).equals(this.inOrderTraversal_R(previousTree)));
        return this.inOrderTraversal_R(simplifiedTree);
    }

    private Node simplify_DFT(Node node) {
        if (node instanceof BranchNode) {
            ((BranchNode) node).setLeftNode(simplify_DFT(node.getLeftNode()));
            ((BranchNode) node).setRightNode(simplify_DFT(node.getRightNode()));
            return ((BranchNode) node).evaluate();
        }
        else if (node instanceof RootNode) {
            ((RootNode) node).setLeftNode(simplify_DFT(node.getLeftNode()));
            ((RootNode) node).setRightNode(simplify_DFT(node.getRightNode()));
            return ((RootNode) node).evaluate();
        }
        return node;
    }

    public String toDigraph() {
        StringBuffer dotGraph = new StringBuffer();
        dotGraph.append("digraph syntaxTree{\n" +
                "graph [rankdir=TB]\n");
        dotGraph.append(digraph_DFT(this.rootNode, 1));
        dotGraph.append("}");

        return dotGraph.toString();
    }

    public String digraph_DFT(Node node, int label) {
        StringBuffer subTree = new StringBuffer();
        if (!(node instanceof LeafNode)) {
            int leftLabel = (2 * label);
            int rightLabel = ((2 * label) + 1);
            subTree.append("\""+label+"\" [label=\""+node.toString()+"\"];\n"+
                           "\""+leftLabel+"\" [label=\""+node.getLeftNode().toString()+"\"];\n"+
                           "\""+rightLabel+"\" [label=\""+node.getRightNode().toString()+"\"];\n"+
                           "\""+label+"\"->\""+leftLabel+"\";\n"+
                           "\""+label+"\"->\""+rightLabel+"\";\n");

            subTree.append(digraph_DFT(node.getLeftNode(), (2 * label)));
            subTree.append(digraph_DFT(node.getRightNode(), (2 * label) + 1));
        }

        return subTree.toString();
    }

    public RootNode getRootNode() {
        return this.rootNode;
    }

    public String getReversePolishNotation() {
        return this.reversePolishNotation;
    }
}
