package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Rules.Commutation;
import org.FunctionSimplifier.Variable;

public class Addition extends Function implements Commutation, Arithmetic {
    private Function left;
    private Function right;

    public Addition() {
        this.left = new Function(0);
        this.right = new Function(0);
    }

    public Addition(int _left, int _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Addition(double _left, double _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Addition(Variable _left, Variable _right) {
        this.left = new Function(_left);
        this.right = new Function(_right);
    }

    public Addition(Function _left, Function _right) {
        this.left = _left;
        this.right = _right;
    }

    @Override
    public void applyCommutation() {
        Function temp = this.left;
        this.left = this.right;
        this.right = temp;
        /*String tempLabel = this.left.getLabel();
        double tempConstant = this.left.getConstant();
        double tempExponent = this.left.getExponent();

        this.left.setLabel(this.right.getLabel());
        this.left.setConstant(this.right.getConstant());
        this.left.setExponent(this.right.getExponent());

        this.right.setLabel(tempLabel);
        this.right.setConstant(tempConstant);
        this.right.setExponent(tempExponent);*/
    }

    public String toString() {
        return this.left + " + " + this.right;
    }

    public Function evaluate() {
        Function newLeft = this.left.evaluate();
        Function newRight = this.right.evaluate();
        if (newLeft.isVariable() && newRight.isVariable()) {
            Variable leftVariable = (Variable) newLeft.getBody();
            Variable rightVariable = (Variable) newRight.getBody();
            if (leftVariable.getLabel().equals(rightVariable.getLabel()) && leftVariable.getExponent() == rightVariable.getExponent()) {
                return new Function(new Variable(leftVariable.getLabel(),
                        leftVariable.getConstant() + rightVariable.getConstant(),
                        leftVariable.getExponent()));
            } else {
                return new Function(new Addition(leftVariable, rightVariable));
            }
        } else if (newLeft.isVariable()) {
            newLeft = new Addition(newLeft, newRight.getLeftVariable()).evaluate();
            newRight = newRight.getRightFunction().evaluate();
        } else if (newRight.isVariable()) {
            newRight = new Addition(newLeft.getRightVariable(), newRight).evaluate();
            newLeft = newLeft.getLeftFunction().evaluate();
        }

        if (newLeft.toString().equals(this.left.toString()) && newRight.toString().equals(this.right.toString()))
            return new Addition(newLeft, newRight);
        else
            return new Addition(newLeft, newRight);
    }

    @Override
    public Function getLeft() {
        return this.left;
    }

    @Override
    public Function getRight() {
        return this.right;
    }
/*
    public Variable evaluate(double leftValue, double rightValue) {
        return new Variable(this.left.evaluate(leftValue) + this.right.evaluate(rightValue));
    }*/
}
