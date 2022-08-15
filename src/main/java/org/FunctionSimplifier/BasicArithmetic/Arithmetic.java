package org.FunctionSimplifier.BasicArithmetic;

import org.FunctionSimplifier.Function;
import org.FunctionSimplifier.Variable;

public interface Arithmetic {
    Function evaluate();
    Variable getLeft();
    void setLeft(String _left);
    Variable getRight();
    void setRight(String _right);
}
