package org.FunctionSimplifier.SyntaxTree;

import org.FunctionSimplifier.ReversePolishNotation.Operators.*;
import org.FunctionSimplifier.ReversePolishNotation.RPNParser;
import org.FunctionSimplifier.Variable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SyntaxTreeWithBrackets {
    private String infixNotation;
    private String reversePolishNotation;
    private Queue<Operator> orderOfOperations;
    private HashMap<Character, Variable> variables;
    private RootNode rootNode = null;
    private RPNParser rpnParser = new RPNParser();

    public SyntaxTreeWithBrackets(String _infixExpression) {
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
        if (rpnCopy.indexOf("()") >= 0)
            rpnCopy.delete(operationIndex-1, operationIndex+1);
        //rpnCopy.deleteCharAt(operationIndex--);
        //rpnCopy.deleteCharAt(operationIndex--);
        //rpnCopy.deleteCharAt(operationIndex);


        while(!this.orderOfOperations.isEmpty()) {
            operationIndex = rpnCopy.indexOf(this.orderOfOperations.peek().toString());
            rightNode = new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-1)));

            if (this.orderOfOperations.peek() instanceof OpenBracket) {
                int closingBracketIndex = findClosingBracketIndex(rpnExpression, operationIndex);
                subTree = this.createSubTree(rpnCopy.substring(operationIndex, closingBracketIndex));

                /*this.orderOfOperations.remove();
                subTrees.push(new BranchNode());
                while(!subTrees.isEmpty()) {
                    subTree = subTrees.pop();
                    if (this.orderOfOperations.peek() instanceof OpenBracket)
                        subTrees.push(subTree);
                    else if (this.orderOfOperations.peek() instanceof CloseBracket) {
                        if (subTrees.size() > 1)

                    }
                }*/
            }
            else if (operationIndex > 2 && !rpnParser.containsOperator(rpnCopy.substring(0, operationIndex))) {
                while(!this.orderOfOperations.isEmpty()) {
                    leftNode = new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex)));
                    treeRootNode = new RootNode(this.orderOfOperations.remove(), leftNode, treeRootNode);
                    rpnCopy.delete(operationIndex, operationIndex+2);
                    if (rpnCopy.indexOf("()") >= 0) {
                        rpnCopy.delete(operationIndex-1, operationIndex+1);
                    }
                    operationIndex -= 1;
                }
            }
            else if (operationIndex >= 2 && !rpnParser.isOperator(rpnCopy.charAt(operationIndex-2))) {
                subTree = new BranchNode(this.orderOfOperations.remove(), new LeafNode(this.variables.get(rpnCopy.charAt(operationIndex-2))), rightNode);
                treeRootNode = new RootNode(this.orderOfOperations.remove(), treeRootNode.convertToBranch(), subTree);
                rpnCopy.delete(operationIndex-2, operationIndex+1);
                operationIndex -= 2;
                //rpnCopy.deleteCharAt(operationIndex--);
                //rpnCopy.deleteCharAt(operationIndex--);
                //rpnCopy.deleteCharAt(operationIndex);
            }
            else {
                treeRootNode = new RootNode(this.orderOfOperations.remove(), treeRootNode.convertToBranch(), rightNode);
                rpnCopy.delete(operationIndex-1, operationIndex+1);
                operationIndex -= 1;
                //rpnCopy.deleteCharAt(operationIndex--);
                //rpnCopy.deleteCharAt(operationIndex);
            }
        }
        return treeRootNode;
    }

    private BranchNode createSubTree(String rpnExtract) {
        RootNode subTreeRootNode = createTree(rpnExtract);

        return subTreeRootNode.convertToBranch();
    }

    private int findClosingBracketIndex(String expression, int openBracketIndex) {
        int bracketCount = 1, closingBracketIndex = openBracketIndex + 1;
        while (bracketCount > 0) {
            if (expression.charAt(closingBracketIndex) == ')')
                bracketCount--;
            else if (expression.charAt(closingBracketIndex) == '(')
                bracketCount++;
            closingBracketIndex++;
        }

        return closingBracketIndex;
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

    /*public void convertToRegularTree() {
        int order = 1;
    }

    private int inOrderTraversal_R(Node mainNode, int order) {
        if (!(mainNode instanceof LeafNode)) {
            order = inOrderTraversal_R(mainNode, order);
            this.assignOrder(mainNode.getLeftNode(), order);
            this.assignOrder(mainNode.getLeftNode(), order);
            this.assignOrder(mainNode, order);
        }
        return order;
    }

    private void assignOrder(Node mainNode, int order) {
        mainNode.setOrder(order);
    }*/

    public RootNode getRootNode() {
        return this.rootNode;
    }

    public String getReversePolishNotation() {
        return this.reversePolishNotation;
    }
}
