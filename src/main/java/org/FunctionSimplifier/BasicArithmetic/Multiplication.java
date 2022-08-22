package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Rules.Commutation;
import org.FunctionSimplifier.Rules.Distributive;
import org.FunctionSimplifier.Variable;

public class Multiplication extends Function implements Arithmetic, Commutation, Distributive {
    private Variable left;
    private Variable right;

    public Multiplication(int _left, int _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Multiplication(double _left, double _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Multiplication(Variable _left, Variable _right) {
        this.left = _left;
        this.right = _right;
    }

    public Multiplication(String _left, String _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Multiplication() {
        this.left = null;
        this.right = null;
    }

    public String toString() {
        return this.left.toString() + " * " + this.right.toString();
    }

    @Override
    public void applyCommutation() {
        Variable temp = this.left;
        this.left = this.right;
        this.right = temp;
    }

    @Override
    public void applyDistributive() {

    }

    public Function evaluate() {
        // When the variables are only constant variables
        if (!left.hasLabel() && !right.hasLabel())
            return new Function(left.evaluate() * right.evaluate());
            // When the variables include the same labels
        else if (left.getLabel().equals(right.getLabel()))
            return new Function(new Variable(left.getLabel(),
                    left.getConstant() * right.getConstant(),
                    left.getExponent() + right.getExponent()));
            // When the left side of the operation includes a label but the other is constant
        else if (!left.hasLabel()) {
            right.setConstant(right.getConstant() * left.getConstant());
            return new Function(right);
        }
        // When the right side of the operation includes a label but the other is constant
        else if (!right.hasLabel()) {
            left.setConstant(left.getConstant() * right.getConstant());
            return new Function(left);
        } else {
            return new Function(new Variable(this.left.getLabel() + this.right.getLabel()));
        }
    }

    @Override
    public Variable getRight() {
        return this.right;
    }

    @Override
    public void setRight(String _right) {
        this.right = new Variable(_right);
    }

    @Override
    public Variable getLeft() {
        return this.left;
    }

    @Override
    public void setLeft(String _left) {
        this.left = new Variable(_left);
    }
}
