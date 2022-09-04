package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Variable;

public class Subtraction extends Function {
    private Variable left;
    private Variable right;

    public Subtraction() {
        this.left = new Variable(0);
        this.right = new Variable(0);
    }

    public Subtraction(int _left, int _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Subtraction(double _left, double _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public Subtraction(Variable _left, Variable _right) {
        this.left = _left;
        this.right = _right;
    }

    public String toString() { return this.left + " - " + this.right; }

    public Function evaluate() {
        if (this.left.getConstant() == 0) {
            this.right.setConstant(this.right.getConstant() * -1);
            return new Function(this.right);
        }
        else if (this.right.getConstant() == 0)
            return new Function(this.left);
        else if (!this.left.hasLabel() && !this.right.hasLabel())
            return new Function(this.left.evaluate() - this.right.evaluate());
        else if (this.left.getLabel().equals(this.right.getLabel()) && this.left.getExponent() == this.right.getExponent())
            return new Function(new Variable(this.left.getLabel(),
                this.left.getConstant() - this.right.getConstant(),
                this.left.getExponent()));
        else
            return new Function(this);
    }

    public Variable evaluate(double leftValue, double rightValue) {
        return new Variable(this.left.evaluate(leftValue) - this.right.evaluate(rightValue));
    }
}
