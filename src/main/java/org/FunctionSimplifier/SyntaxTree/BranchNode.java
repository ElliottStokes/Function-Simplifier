package org.FunctionSimplifier.SyntaxTree;

import org.FunctionSimplifier.ReversePolishNotation.Operators.Operator;

public class BranchNode implements Node {
    private Operator operator;
    private Node leftNode;
    private Node rightNode;
    private int order;

    public BranchNode() {
        this.operator = null;
        this.leftNode = null;
        this.rightNode = null;
    }

    public BranchNode(Operator _operator) {
        this.operator = _operator;
        this.leftNode = null;
        this.rightNode = null;
    }

    public BranchNode(Operator _operator, Node _leftNode, Node _rightNode) {
        this.operator = _operator;
        this.leftNode = _leftNode;
        this.rightNode = _rightNode;
    }

    public Node evaluate() {
        return this.operator.evaluate(this.leftNode, this.rightNode);
    }

    public void setLeftNode(BranchNode node) {
        this.leftNode = node;
    }

    public void setRightNode(BranchNode node) {
        this.rightNode = node;
    }

    public RootNode convertToRoot() {
        return new RootNode(this.operator, this.leftNode, this.rightNode);
    }

    public BranchNode convertToBranch() {
        return this;
    }

    public String toString() {
        return this.operator.toString();
    }

    public Node getLeftNode() {
        return this.leftNode;
    }
    public void setLeftNode(Node node) { this.leftNode = node; }

    public Node getRightNode() {
        return this.rightNode;
    }
    public void setRightNode(Node node) { this.rightNode = node; }

    public void setOrder(int order) {
        this.order = order;
    }
    public int getOrder() {
        return this.order;
    }

    public int getPriority() {
        return this.operator.getPriority();
    }

    public Operator getOperator() {
        return this.operator;
    }
}
