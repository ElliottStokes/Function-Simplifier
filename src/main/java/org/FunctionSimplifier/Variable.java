package org.FunctionSimplifier;

public class Variable {
    private String label;
    private double constant;
    private double exponent;

    public Variable(String label) {
        int labelIndex = 0, exponentIndex;
        for (int i = 0; i < label.length(); i++) {
            if (isNumeric(String.valueOf(label.charAt(i))) || label.charAt(i) == '-')
                labelIndex++;
            else
                break;
        }
        if (label.contains("^")) {
            exponentIndex = label.indexOf('^');
        } else
            exponentIndex = label.length();

        this.label = label.substring(labelIndex, exponentIndex);

        if (labelIndex > 0)
            this.constant = Double.parseDouble(label.substring(0, labelIndex));
        else
            this.constant = 1;

        if (label.contains("^"))
            this.exponent = Double.parseDouble(label.substring(label.indexOf('^')+1));
        else
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
    public double evaluate() { return Math.pow(this.constant, this.exponent); }

    public String toString() {
        if (this.constant == 0)
            return "0";
        if (this.exponent == 0)
            return "1";

        String variableString = this.label;
        if (this.label.equals("")) {
            variableString = checkDecimal(this.constant) + variableString;
        } else if (this.constant == -1) {
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

    public boolean hasLabel() {
        return !this.label.equals("");
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
