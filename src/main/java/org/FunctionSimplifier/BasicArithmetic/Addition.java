package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Rules.Commutation;
import org.FunctionSimplifier.Variable;

public class Addition extends Function implements Commutation {
    private Variable left;
    private Variable right;

    public Addition() {
        this.left = new Variable(0);
        this.right = new Variable(0);
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

    @Override
    public void applyCommutation() {
        String tempLabel = this.left.getLabel();
        double tempConstant = this.left.getConstant();
        double tempExponent = this.left.getExponent();

        this.left.setLabel(this.right.getLabel());
        this.left.setConstant(this.right.getConstant());
        this.left.setExponent(this.right.getExponent());

        this.right.setLabel(tempLabel);
        this.right.setConstant(tempConstant);
        this.right.setExponent(tempExponent);
    }

    public String toString() {
        return this.left + " + " + this.right;
    }

    public Variable evaluate() {
        if (this.left.getLabel().equals(this.right.getLabel()) && this.left.getExponent() == this.right.getExponent()) {
            return new Variable(this.left.getLabel(),
                    this.left.getConstant() + this.right.getConstant(),
                    this.left.getExponent());
        } else {
            return new Variable(this.left.evaluate() + this.right.evaluate());
        }
    }

    public Variable evaluate(double leftValue, double rightValue) {
        return new Variable(this.left.evaluate(leftValue) + this.right.evaluate(rightValue));
    }
}
