package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Rules.Commutation;
import org.FunctionSimplifier.Variable;

public class Addition extends Function implements Commutation, Arithmetic {
    private Variable left;
    private Variable right;

    public Addition(String _left, String _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Addition(int _left, int _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Addition(double _left, double _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Addition(Variable _left, Variable _right) {
        this.left = _left;
        this.right = _right;
    }

    public Addition() {
        this.left = null;
        this.right = null;
    }

    @Override
    public void applyCommutation() {
        Variable temp = this.left;
        this.left = this.right;
        this.right = temp;
    }

    public String toString() {
        return this.left + " + " + this.right;
    }

    public Function evaluate() {
        if (this.left.getLabel().equals(this.right.getLabel()) && this.left.getExponent() == this.right.getExponent()) {
            return new Function(new Variable(this.left.getLabel(),
                    this.left.getConstant() + this.right.getConstant(),
                    this.right.getExponent()));
        } else {
            return new Function(new Addition(this.left, this.right));
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
