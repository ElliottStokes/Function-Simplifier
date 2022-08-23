package org.FunctionSimplifier.ReversePolishNotation.Operators;

import org.FunctionSimplifier.SyntaxTree.BranchNode;
import org.FunctionSimplifier.SyntaxTree.LeafNode;
import org.FunctionSimplifier.SyntaxTree.Node;

public interface Operator {
    int getPriority();
    Node evaluate(LeafNode leftNode, LeafNode rightNode);
    Node evaluate(BranchNode leftNode, BranchNode rightNode);
    Node evaluate(LeafNode leftNode, BranchNode rightNode);
    Node evaluate(BranchNode leftNode, LeafNode rightNode);
    Node evaluate(Node leftNode, Node rightNode);
}
