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
            if (this.operators.contains(c.toString())) {
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
            }
            else if (c.equals(')')) {
                while (!(this.operatorStack.peek() instanceof OpenBracket))
                    outputString.append(this.operatorStack.pop().toString());
                this.operatorStack.pop();
            }
            else
                outputString.append(c);
        }

        while (!this.operatorStack.isEmpty())
            outputString.append(this.operatorStack.pop().toString());

        return outputString.toString();
    }
}
