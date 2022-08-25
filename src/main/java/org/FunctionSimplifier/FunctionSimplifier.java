package org.FunctionSimplifier;

import org.FunctionSimplifier.BasicArithmetic.Addition;
import org.FunctionSimplifier.GUI.FunctionSimplifierGUI;
import org.FunctionSimplifier.SyntaxTree.SyntaxTree;

import java.io.IOException;

public class FunctionSimplifier {
    public static void main(String[] args) {
        System.out.println("org.FunctionSimplifier.Function Simplifier");
        Addition test = new Addition(2, 3);
        SyntaxTree tree = new SyntaxTree("x - ( ( y + z + p ) * ( a / b ^ n ) )");
        System.out.println(tree.toDigraph());
        FunctionSimplifierGUI app = new FunctionSimplifierGUI();

    }
}
