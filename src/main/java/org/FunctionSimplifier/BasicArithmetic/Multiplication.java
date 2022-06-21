package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Rules.Commutation;
import org.FunctionSimplifier.Rules.Distributive;
import org.FunctionSimplifier.Variable;

public class Multiplication extends Function implements Commutation, Distributive {
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

    public Multiplication(int _left, Variable _right) {
        this.left = new Variable(_left);
        this.right = _right;
    }

    public Multiplication(double _left, Variable _right) {
        this.left = new Variable(_left);
        this.right = _right;
    }

    public Multiplication(Variable _left, int _right) {
        this.left = _left;
        this.right = new Variable(_right);
    }

    public Multiplication(Variable _left, double _right) {
        this.left = _left;
        this.right = new Variable(_right);
    }

    public String toString() {
        return this.left + " * " + this.right;
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

    public Variable evaluate() {
        // When the variables are only constant variables
        if (this.left.getLabel().equals("") && this.right.getLabel().equals(""))
            return new Variable(this.left.evaluate() * this.right.evaluate());
        // When the variables include the same labels
        else if (this.left.getLabel().equals(this.right.getLabel()))
            return new Variable(this.left.getLabel(),
                    this.left.getConstant() * this.right.getConstant(),
                    this.left.getExponent() + this.right.getExponent());
        // When the left side of the operation includes a label but the other is constant
        else if (this.left.getLabel().equals("")) {
            this.right.setConstant(this.right.getConstant() * this.left.getConstant());
            return this.right;
        }
        // When the right side of the operation includes a label but the other is constant
        else {
            this.left.setConstant(this.left.getConstant() * this.right.getConstant());
            return this.left;
        }
    }

    public Variable evaluate(int leftValue, int rightValue) {
        return new Variable(this.left.evaluate(leftValue) * this.right.evaluate(rightValue));
    }
}
