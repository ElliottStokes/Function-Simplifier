package org.FunctionSimplifier.ReversePolishNotation;

import org.FunctionSimplifier.ReversePolishNotation.Operators.*;

import java.util.Stack;

public class RPNParser {
    private Stack<Operator> operatorStack;
    private String operators = "+-*/^";

    public RPNParser() {
        this.operatorStack = new Stack<>();
    }

    public String parse(String input) {
        String[] inputCharacters = input.split(" ");
        StringBuilder outputString = new StringBuilder();
        Operator operator;

        for (String c : inputCharacters) {
            if (this.isOperator(c)) {
                if (c.equals("+"))
                    operator = new Add();
                else if (c.equals("-"))
                    operator = new Subtract();
                else if (c.equals("*"))
                    operator = new Multiply();
                else if (c.equals("/"))
                    operator = new Divide();
                else
                    operator = new Power();

                while (!this.operatorStack.isEmpty() && this.operatorStack.peek().getPriority() <= operator.getPriority())
                    outputString.append(this.operatorStack.pop().toString()).append(" ");

                this.operatorStack.push(operator);
            }
            else if (c.equals("(")) {
                this.operatorStack.push(new OpenBracket());
                outputString.append(c).append(" ");
            }
            else if (c.equals(")")) {
                if (!this.operatorStack.isEmpty()) {
                    while (!(this.operatorStack.peek() instanceof OpenBracket))
                        outputString.append(this.operatorStack.pop().toString()).append(" ");
                    this.operatorStack.pop();
                }
                outputString.append(c).append(" ");
            }
            else
                outputString.append(c).append(" ");
        }

        while (!this.operatorStack.isEmpty())
            outputString.append(this.operatorStack.pop().toString()).append(" ");

        return outputString.toString().strip();
    }

    public boolean isOperator(String value) {
        return this.operators.contains(value);
    }
    public boolean isOperator(Character value) {
        return this.isOperator(value.toString());
    }

    public boolean containsOperator(String values) {
        for (int i = 0; i < values.length(); i++)
            if (isOperator(values.charAt(i)))
                return true;

        return false;
    }
}
