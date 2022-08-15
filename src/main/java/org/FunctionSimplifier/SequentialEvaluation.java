package org.FunctionSimplifier;

import org.FunctionSimplifier.BasicArithmetic.*;

public class SequentialEvaluation {
    public SequentialEvaluation() { }

    public String evaluate(String expression) {
        while (expression.contains("("))
            expression = this.evaluateBrackets(expression);
        if (expression.contains(" ^ "))
            expression = this.evaluate(expression, new Indices(), "^");
        if (expression.contains(" / "))
            expression = this.evaluate(expression, new Division(), "/");
        if (expression.contains(" * "))
            expression = this.evaluate(expression, new Multiplication(), "*");
        if (expression.contains(" + "))
            expression = this.evaluate(expression, new Addition(), "+");
        if (expression.contains(" - "))
            expression = this.evaluate(expression, new Subtraction(), "-");
        return expression;
    }

    private String evaluateBrackets(String expression) {
        String[] splitExpression = expression.split(" ");
        StringBuilder bracket = new StringBuilder();
        StringBuilder expressionOutput = new StringBuilder();
        String bracketOutput;

        int openBracketIndex = -1, closeBracketIndex, counter = 1;
        String s;
        for (int i = 0; i < splitExpression.length; i++)
            if (splitExpression[i].equals("(")) {
                openBracketIndex = i;
                break;
            }

        if (openBracketIndex >= 0) {
            closeBracketIndex = openBracketIndex + 1;
            while (counter > 0) {
                s = splitExpression[closeBracketIndex];
                if (s.equals(")"))
                    counter--;
                else if (s.equals("("))
                    counter++;
                else
                    bracket.append(splitExpression[closeBracketIndex]).append(" ");
                closeBracketIndex++;
            }
            bracketOutput = evaluate(bracket.toString());

            for (int i = 0; i < splitExpression.length; i++) {
                if (i == openBracketIndex) {
                    expressionOutput.append(bracketOutput).append(" ");
                    i = closeBracketIndex - 1;
                } else {
                    expressionOutput.append(splitExpression[i]).append(" ");
                }
            }
            return expressionOutput.toString();
        }
        return expression;
    }

    private String evaluate(String expression, Arithmetic operation, String operator) {
        String[] splitExpression = expression.split(" ");
        Function result;
        for (int i = 0; i < splitExpression.length; i++) {
            if (splitExpression[i].equals(operator)) {
                operation.setLeft(splitExpression[i - 1]);
                operation.setRight(splitExpression[i + 1]);
                result = operation.evaluate();
                if (result.isVariable()) {
                    splitExpression[i - 1] = "";
                    splitExpression[i] = "";
                    splitExpression[i + 1] = result.toString();
                }
                else {
                    splitExpression[i - 1] = result.getLeftVariable().toString();
                    splitExpression[i] = operator;
                    splitExpression[i + 1] = result.getRightVariable().toString();
                }
            }
        }

        StringBuilder resultExpression = new StringBuilder();
        for (String s : splitExpression)
            if (!s.equals(""))
                resultExpression.append(s).append(" ");

        return resultExpression.toString().strip();
    }
}
