package org.FunctionSimplifier;

public class Function {
    private String functionString;
    private Object body;

    public Function() {
        this.body = new Variable(0);
        this.functionString = this.body.toString();
    }

    public Function(Variable variable) {
        this.body = variable;
        this.functionString = this.body.toString();
    }

    public Function(Variable variable, String funcString) {
        this.body = variable;
        this.functionString = funcString;
    }

    public void print() {
        // Print the formatted function
        System.out.println(this.functionString);
    }

    public boolean isVariable() {
        return this.body instanceof Variable;
    }
}
