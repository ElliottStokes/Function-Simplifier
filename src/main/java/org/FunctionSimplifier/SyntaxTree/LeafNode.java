package org.FunctionSimplifier.SyntaxTree;

import org.FunctionSimplifier.Variable;

public class LeafNode implements Node {
    private Variable variable;

    public LeafNode(Variable _variable) {
        this.variable = _variable;
    }

    public String toString() {
        return this.variable.toString();
    }

    public Variable getVariable() {
        return this.variable;
    }
    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Node getLeftNode() {
        return null;
    }

    public Node getRightNode() {
        return null;
    }

    public int getPriority() {
        return 0;
    }
}
