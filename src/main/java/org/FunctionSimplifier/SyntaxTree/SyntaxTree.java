package org.FunctionSimplifier.SyntaxTree;

import org.FunctionSimplifier.ReversePolishNotation.Operators.*;
import org.FunctionSimplifier.ReversePolishNotation.RPNParser;
import org.FunctionSimplifier.Variable;

import java.util.*;

public class SyntaxTree {
    private String infixNotation;
    private String reversePolishNotation;
    private Queue<Operator> orderOfOperations;
    private HashMap<Character, Variable> variables;
    private RootNode rootNode = null;
    private RPNParser rpnParser = new RPNParser();

    public SyntaxTree(String _infixExpression) {
        this.infixNotation = _infixExpression;
        this.reversePolishNotation = rpnParser.parse(this.infixNotation);

        this.orderOfOperations = new LinkedList<>();
        this.variables = new HashMap<>();

        for (Character c : this.reversePolishNotation.toCharArray())
            if (rpnParser.isOperator(c))
                if (c.equals('+'))
                    this.orderOfOperations.add(new Add());
                else if (c.equals('-'))
                    this.orderOfOperations.add(new Subtract());
                else if (c.equals('*'))
                    this.orderOfOperations.add(new Multiply());
                else if (c.equals('/'))
                    this.orderOfOperations.add(new Divide());
                else
                    this.orderOfOperations.add(new Power());
            else
                if (!this.variables.containsKey(c))
                    this.variables.put(c, new Variable(c));

        this.rootNode = createTree(this.reversePolishNotation);
    }

    private RootNode createTree(String rpnExpression) {
        StringBuilder rpnCopy = new StringBuilder(rpnExpression);

        LeafNode leftNode, rightNode;
        BranchNode subTree;

        int operationIndex = rpnCopy.indexOf(this.orderOfOperations.peek().toString());

        rightNode = new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-1)));
        leftNode = new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-2)));
        RootNode treeRootNode = new RootNode(this.orderOfOperations.remove(), leftNode, rightNode);
        rpnCopy.delete(operationIndex-2, operationIndex+1);
        operationIndex -= 2;


        while(!this.orderOfOperations.isEmpty()) {
            operationIndex = rpnCopy.indexOf(this.orderOfOperations.peek().toString());
            rightNode = new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-1)));

            if (operationIndex > 2 && !rpnParser.containsOperator(rpnCopy.substring(0, operationIndex))) {
                while(!this.orderOfOperations.isEmpty()) {
                    leftNode = new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-1)));
                    treeRootNode = new RootNode(this.orderOfOperations.remove(), leftNode, treeRootNode);
                    rpnCopy.delete(operationIndex-1, operationIndex+1);
                    operationIndex -= 1;
                }
            }
            else if (operationIndex >= 2 && !rpnParser.isOperator(rpnCopy.charAt(operationIndex-2))) {
                subTree = new BranchNode(this.orderOfOperations.remove(), new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-2))), rightNode);
                treeRootNode = new RootNode(this.orderOfOperations.remove(), treeRootNode.convertToBranch(), subTree);
                rpnCopy.delete(operationIndex-2, operationIndex+1);
                operationIndex -= 2;
            }
            else {
                treeRootNode = new RootNode(this.orderOfOperations.remove(), treeRootNode.convertToBranch(), rightNode);
                rpnCopy.delete(operationIndex-1, operationIndex+1);
                operationIndex -= 1;
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
