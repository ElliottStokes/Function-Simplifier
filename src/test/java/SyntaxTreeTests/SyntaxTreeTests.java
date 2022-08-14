package SyntaxTreeTests;

import org.FunctionSimplifier.SyntaxTree.SyntaxTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SyntaxTreeTests {
    @Test
    public void creatingBinaryTreeTest() {
        SyntaxTree tree1 = new SyntaxTree("x+y");
        Assertions.assertEquals("+", tree1.getRootNode().toString());
        Assertions.assertEquals("x", tree1.getRootNode().getLeftNode().toString());
        Assertions.assertEquals("y", tree1.getRootNode().getRightNode().toString());

        SyntaxTree tree2 = new SyntaxTree("x+y+z");
        Assertions.assertEquals("+", tree2.getRootNode().toString());
        Assertions.assertEquals("+", tree2.getRootNode().getLeftNode().toString());
        Assertions.assertEquals("z", tree2.getRootNode().getRightNode().toString());
        Assertions.assertEquals("x", tree2.getRootNode().getLeftNode().getLeftNode().toString());
        Assertions.assertEquals("y", tree2.getRootNode().getLeftNode().getRightNode().toString());
    }

    @Test
    public void depthFirstTraversalTests() {
        SyntaxTree tree1 = new SyntaxTree("x+y");
        Assertions.assertEquals("xy+", tree1.depthFirstTraversal());

        SyntaxTree tree2 = new SyntaxTree("x+y+z");
        Assertions.assertEquals("xy+z+", tree2.depthFirstTraversal());

        SyntaxTree tree3 = new SyntaxTree("(((((x + y) - v) / x) * y) - z)");
        Assertions.assertEquals("xy+v-x/y*z-", tree3.depthFirstTraversal());

        SyntaxTree tree4 = new SyntaxTree("(x - y) / (z - m)");
        Assertions.assertEquals("xy-zm-/", tree4.depthFirstTraversal());

        SyntaxTree tree5 = new SyntaxTree("x+y+z-a*b");
        Assertions.assertEquals("xy+z+ab*-", tree5.depthFirstTraversal());

        SyntaxTree tree6 = new SyntaxTree("x-(y+z*(a/b^n))");
        Assertions.assertEquals("xyzabn^/*+-", tree6.depthFirstTraversal());
    }
}
