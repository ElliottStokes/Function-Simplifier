package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Variable;

public class Division extends Function implements Arithmetic{
    private Variable left;
    private Variable right;

    public Division(int _left, int _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Division(double _left, double _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Division(Variable _left, Variable _right) {
        this.left = _left;
        this.right = _right;
    }

    public Division(String _left, String _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Division() {
        this.left = null;
        this.right = null;
    }

    public String toString() {
        return this.left + " / " + this.right;
    }

    public Function evaluate() {
        if (this.left.hasLabel() && this.left.getLabel().equals(this.right.getLabel())) {
            double exponentValue = this.left.getExponent() - this.right.getExponent();
            double constantValue = this.left.getConstant() / this.right.getConstant();

            if (exponentValue == 0) {
                return new Function(constantValue);
            } else {
                return new Function(new Variable(this.left.getLabel(), constantValue, exponentValue));
            }
        } else {
            return new Function(this.left.evaluate() / this.right.evaluate());
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
        return null;
    }

    @Override
    public void setRight(String _right) {
        this.right = new Variable(_right);
    }

    public Variable evaluate(double leftValue, double rightValue) {
        return new Variable(this.left.evaluate(leftValue) / this.right.evaluate(rightValue));
    }
}
