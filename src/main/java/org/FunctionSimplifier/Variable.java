package org.FunctionSimplifier;

public class Variable {
    private String label;
    private double constant;
    private double exponent;

    public Variable(String label) {
        this.label = label;
        this.constant = 1;
        this.exponent = 1;
    }

    public Variable(double constant) {
        this.label = "";
        this.constant = constant;
        this.exponent = 1;
    }

    public Variable(String label, double constant) {
        this.label = label;
        this.constant = constant;
        this.exponent = 1;
    }

    public Variable(String label, double constant, double exponent) {
        this.label = label;
        this.constant = constant;
        this.exponent = exponent;
    }

    public double evaluate(double value) {
        return this.constant * (Math.pow(value, this.exponent));
    }
    public double evaluate() { return (int) Math.pow(this.constant, this.exponent); }

    public String toString() {
        String variableString = this.label;
        if (this.constant == -1) {
            variableString = "-" + variableString;
        } else if (this.constant != 1) {
            variableString = checkDecimal(this.constant) + variableString;
        }

        if (this.exponent != 1) {
            variableString = variableString + "^" + checkDecimal(this.exponent);
        }

        return variableString;
    }

    public String getLabel() {
        return this.label;
    }

    public double getConstant() {
        return this.constant;
    }

    public double getExponent() {
        return this.exponent;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setConstant(double constant) {
        this.constant = constant;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    private String checkDecimal(double value) {
        String valueStr = Double.toString(value);
        return (valueStr.endsWith(".0")) ? valueStr.replace(".0", "") : valueStr;
    }
}
