package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Variable;

public class Indices implements Arithmetic {
    private Variable left;
    private Variable right;

    public Indices(String _left, String _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Indices() {
        this.left = null;
        this.right = null;
    }

    public Function evaluate() {
        if (!this.left.getLabel().equals("") && this.right.getLabel().equals("")) {
            return new Function(new Variable(this.left.getLabel(),
                    this.left.getConstant(),
                    this.left.getExponent() * this.right.getConstant()));
        } else if (this.left.getLabel().equals(this.right.getLabel()) && this.left.getExponent() == this.right.getExponent()) {
            return new Function(new Variable(this.left.getLabel(),
                    Math.pow(this.left.getConstant(), this.right.getConstant()),
                    this.left.getExponent()));
        } else {
            return new Function(this.left.evaluate() - this.right.evaluate());
        }
    }

    @Override
    public Variable getLeft() {
        return this.left;
    }

    @Override
    public void setLeft(String _left) {
        this.left = new Variable(_left);
    }

    @Override
    public Variable getRight() {
        return this.right;
    }

    @Override
    public void setRight(String _right) {
        this.right = new Variable(_right);
    }
}
