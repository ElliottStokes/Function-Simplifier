package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Rules.Commutation;
import org.FunctionSimplifier.Rules.Distributive;
import org.FunctionSimplifier.Variable;

public class Multiplication extends Function implements Commutation, Distributive {
    private Function left;
    private Function right;

    public Multiplication(int _left, int _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Multiplication(double _left, double _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Multiplication(Variable _left, Variable _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Multiplication(int _left, Variable _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Multiplication(double _left, Variable _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Multiplication(Variable _left, int _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Multiplication(Variable _left, double _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public String toString() {
        return this.left.toString() + " * " + this.right.toString();
    }

    @Override
    public void applyCommutation() {
        Function temp = this.left;
        this.left = this.right;
        this.right = temp;
    }

    @Override
    public void applyDistributive() {

    }

    public Variable evaluate() {
        if (this.left.isVariable() && this.right.isVariable()) {
            Variable left = (Variable) this.left.getBody();
            Variable right = (Variable) this.right.getBody();
            // When the variables are only constant variables
            if (!left.hasLabel() && !right.hasLabel())
                return new Variable(left.evaluate() * right.evaluate());
                // When the variables include the same labels
            else if (left.getLabel().equals(right.getLabel()))
                return new Variable(left.getLabel(),
                        left.getConstant() * right.getConstant(),
                        left.getExponent() + right.getExponent());
                // When the left side of the operation includes a label but the other is constant
            else if (!left.hasLabel()) {
                right.setConstant(right.getConstant() * left.getConstant());
                return right;
            }
            // When the right side of the operation includes a label but the other is constant
            else {
                left.setConstant(left.getConstant() * right.getConstant());
                return left;
            }
        } else {
            return new Variable(0);
        }
    }

    public Variable evaluate(int leftValue, int rightValue) {
        if (this.left.isVariable() && this.right.isVariable()) {
            Variable left = (Variable) this.left.getBody();
            Variable right = (Variable) this.right.getBody();
            return new Variable(left.evaluate(leftValue) * right.evaluate(rightValue));
        } else {
            return new Variable(0 );
        }
    }
}
