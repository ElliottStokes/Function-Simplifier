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

        LeafNode leftNode, rightNode;
        int operationIndex;

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

        StringBuilder rpnCopy = new StringBuilder(this.reversePolishNotation);

        while(!this.orderOfOperations.isEmpty()) {
            operationIndex = rpnCopy.indexOf(this.orderOfOperations.peek().toString());
            rightNode = new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-1)));

            if (this.rootNode == null) {
                leftNode = new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-2)));
                this.rootNode = new RootNode(this.orderOfOperations.remove(), leftNode, rightNode);
                rpnCopy.deleteCharAt(operationIndex);
                rpnCopy.deleteCharAt(operationIndex-1);
                rpnCopy.deleteCharAt(operationIndex-2);
            }
            else {
                this.rootNode = new RootNode(this.orderOfOperations.remove(), this.rootNode.convertToBranch(), rightNode);
                rpnCopy.deleteCharAt(operationIndex);
                rpnCopy.deleteCharAt(operationIndex-1);
            }
        }
    }

    public String depthFirstTraversal() {
        return depthFirstTraversal_R(this.rootNode);
    }

    private String depthFirstTraversal_R(Node mainNode) {
        if (mainNode instanceof LeafNode)
            return mainNode.toString();
        else
            return depthFirstTraversal_R(mainNode.getLeftNode())  + depthFirstTraversal_R(mainNode.getRightNode()) + mainNode.toString();
        /*if (mainNode instanceof RootNode)
            return depthFirstTraversal_R(mainNode.getLeftNode()) + mainNode.toString() + depthFirstTraversal_R(mainNode.getRightNode());
        else if (mainNode instanceof BranchNode)
            return */
    }

    public RootNode getRootNode() {
        return this.rootNode;
    }
}
