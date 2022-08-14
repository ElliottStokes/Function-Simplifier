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
        char[] inputCharacters = input.replace(" ", "").toCharArray();
        StringBuilder outputString = new StringBuilder();
        Operator operator;

        for (Character c : inputCharacters) {
            if (this.isOperator(c)) {
                if (c.equals('+'))
                    operator = new Add();
                else if (c.equals('-'))
                    operator = new Subtract();
                else if (c.equals('*'))
                    operator = new Multiply();
                else if (c.equals('/'))
                    operator = new Divide();
                else
                    operator = new Power();

                while (!this.operatorStack.isEmpty() && this.operatorStack.peek().getPriority() <= operator.getPriority())
                    outputString.append(this.operatorStack.pop().toString());

                this.operatorStack.push(operator);
            }
            else if (c.equals('(')) {
                this.operatorStack.push(new OpenBracket());
                //outputString.append(c);
            }
            else if (c.equals(')')) {
                if (!this.operatorStack.isEmpty()) {
                    while (!(this.operatorStack.peek() instanceof OpenBracket))
                        outputString.append(this.operatorStack.pop().toString());
                    this.operatorStack.pop();
                }
                //outputString.append(c);
            }
            else
                outputString.append(c);
        }

        while (!this.operatorStack.isEmpty())
            outputString.append(this.operatorStack.pop().toString());

        return outputString.toString();
    }

    public boolean isOperator(Character value) {
        return this.operators.contains(value.toString());
    }

    public boolean containsOperator(String values) {
        for (int i = 0; i < values.length(); i++)
            if (isOperator(values.charAt(i)))
                return true;

        return false;
    }
}
