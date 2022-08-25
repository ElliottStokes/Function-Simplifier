package org.FunctionSimplifier;

import org.FunctionSimplifier.BasicArithmetic.Addition;
import org.FunctionSimplifier.GUI.FunctionSimplifierGUI;

public class FunctionSimplifier {
    public static void main(String[] args) {
        System.out.println("org.FunctionSimplifier.Function Simplifier");
        Addition test = new Addition(2, 3);
        FunctionSimplifierGUI app = new FunctionSimplifierGUI();
    }
}
