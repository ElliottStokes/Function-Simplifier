package org.FunctionSimplifier.SyntaxTree;

import org.FunctionSimplifier.ReversePolishNotation.Operators.Operator;

public class RootNode implements Node {
    private Operator operator;
    private Node leftNode;
    private Node rightNode;
    private int order;

    public RootNode() {
        this.operator = null;
        this.leftNode = null;
        this.rightNode = null;
    }
    public RootNode(Operator _operator) {
        this.operator = _operator;
        this.leftNode = null;
        this.rightNode = null;
    }

    public RootNode(Operator _operator, Node _leftNode, Node _rightNode) {
        this.operator = _operator;
        this.leftNode = _leftNode;
        this.rightNode = _rightNode;
    }

    public RootNode convertToRoot() {
        return this;
    }

    public BranchNode convertToBranch() {
        return new BranchNode(this.operator, this.leftNode, this.rightNode);
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

    public Node evaluate() {
        return this.operator.evaluate(this.leftNode, this.rightNode);
    }
}
