package org.FunctionSimplifier.GUI;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import org.FunctionSimplifier.SyntaxTree.SyntaxTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class FunctionSimplifierGUI extends JFrame implements ActionListener {
    private final String inputImageFileName = "syntaxTreeInput.png";
    private final String outputImageFileName = "syntaxTreeOutput.png";
    private String inputFunction = "";
    private static JTextField input;
    private static JTextField output;
    private static syntaxTreePanel inputImagePanel;
    private static syntaxTreePanel outputImagePanel;
    private static JFrame inputSyntaxTreeFrame;
    private static JFrame outputSyntaxTreeFrame;

    public FunctionSimplifierGUI() {
        setTitle("Function Simplifier");
        setSize(300, 470);
        setLocation(0, 0);
        Container contentPane = getContentPane();

        JPanel functionPanel = new JPanel();
        input = new JTextField(22);
        input.setEditable(false);
        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setLabelFor(input);
        output = new JTextField(22);
        output.setEditable(false);
        JLabel outputLabel = new JLabel("Output:");
        outputLabel.setLabelFor(output);
        functionPanel.add(inputLabel);
        functionPanel.add(input);
        functionPanel.add(outputLabel);
        functionPanel.add(output);

        // create number buttons and some operators
        JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bx, by, bz, ba, bb, bc, badd, bsubtract, bdivision, bmultiply, be, bpower, bopenBracket, bcloseBracket, bdel, bac;

        // create number buttons
        b0 = new JButton("0");
        b0.setPreferredSize(new Dimension(50, 50));
        b0.setBackground(Color.cyan);
        b1 = new JButton("1");
        b1.setPreferredSize(new Dimension(50, 50));
        b1.setBackground(Color.cyan);
        b2 = new JButton("2");
        b2.setPreferredSize(new Dimension(50, 50));
        b2.setBackground(Color.cyan);
        b3 = new JButton("3");
        b3.setPreferredSize(new Dimension(50, 50));
        b3.setBackground(Color.cyan);
        b4 = new JButton("4");
        b4.setPreferredSize(new Dimension(50, 50));
        b4.setBackground(Color.cyan);
        b5 = new JButton("5");
        b5.setPreferredSize(new Dimension(50, 50));
        b5.setBackground(Color.cyan);
        b6 = new JButton("6");
        b6.setPreferredSize(new Dimension(50, 50));
        b6.setBackground(Color.cyan);
        b7 = new JButton("7");
        b7.setPreferredSize(new Dimension(50, 50));
        b7.setBackground(Color.cyan);
        b8 = new JButton("8");
        b8.setPreferredSize(new Dimension(50, 50));
        b8.setBackground(Color.cyan);
        b9 = new JButton("9");
        b9.setPreferredSize(new Dimension(50, 50));
        b9.setBackground(Color.cyan);

        // create the algebraic value buttons
        bx = new JButton("x");
        bx.setPreferredSize(new Dimension(50, 50));
        bx.setBackground(Color.cyan);
        by = new JButton("y");
        by.setPreferredSize(new Dimension(50, 50));
        by.setBackground(Color.cyan);
        bz = new JButton("z");
        bz.setPreferredSize(new Dimension(50, 50));
        bz.setBackground(Color.cyan);
        ba = new JButton("a");
        ba.setPreferredSize(new Dimension(50, 50));
        ba.setBackground(Color.cyan);
        bb = new JButton("b");
        bb.setPreferredSize(new Dimension(50, 50));
        bb.setBackground(Color.cyan);
        bc = new JButton("c");
        bc.setPreferredSize(new Dimension(50, 50));
        bc.setBackground(Color.cyan);

        // create operator buttons
        badd = new JButton("+");
        badd.setPreferredSize(new Dimension(50, 50));
        badd.setBackground(Color.orange);
        bsubtract = new JButton("-");
        bsubtract.setPreferredSize(new Dimension(50, 50));
        bsubtract.setBackground(Color.orange);
        bdivision = new JButton("/");
        bdivision.setPreferredSize(new Dimension(50, 50));
        bdivision.setBackground(Color.orange);
        bmultiply = new JButton("*");
        bmultiply.setPreferredSize(new Dimension(50, 50));
        bmultiply.setBackground(Color.orange);
        bpower = new JButton("^");
        bpower.setPreferredSize(new Dimension(50, 50));
        bpower.setBackground(Color.orange);
        bopenBracket = new JButton("(");
        bopenBracket.setPreferredSize(new Dimension(50, 50));
        bopenBracket.setBackground(Color.orange);
        bcloseBracket = new JButton(")");
        bcloseBracket.setPreferredSize(new Dimension(50, 50));
        bcloseBracket.setBackground(Color.orange);
        bdel = new JButton("DEL");
        bdel.setPreferredSize(new Dimension(105, 50));
        bdel.setBackground(Color.red);
        bac = new JButton("AC");
        bac.setPreferredSize(new Dimension(50, 50));
        bac.setBackground(Color.red);

        // create . button
        be = new JButton(".");
        be.setPreferredSize(new Dimension(50, 50));
        be.setBackground(Color.orange);

        // run button
        JButton run = new JButton("Run");
        run.setPreferredSize(new Dimension(105, 50));
        run.setBackground(Color.green);
        run.addActionListener(this);

        // padding
        JButton padding1 = new JButton();
        padding1.setPreferredSize(new Dimension(50, 50));

        // add action listeners
        bmultiply.addActionListener(this);
        bdivision.addActionListener(this);
        bsubtract.addActionListener(this);
        badd.addActionListener(this);
        bpower.addActionListener(this);
        b9.addActionListener(this);
        b8.addActionListener(this);
        b7.addActionListener(this);
        b6.addActionListener(this);
        b5.addActionListener(this);
        b4.addActionListener(this);
        b3.addActionListener(this);
        b2.addActionListener(this);
        b1.addActionListener(this);
        b0.addActionListener(this);
        bx.addActionListener(this);
        by.addActionListener(this);
        bz.addActionListener(this);
        ba.addActionListener(this);
        bb.addActionListener(this);
        bc.addActionListener(this);
        be.addActionListener(this);
        bopenBracket.addActionListener(this);
        bcloseBracket.addActionListener(this);
        bdel.addActionListener(this);
        bac.addActionListener(this);

        // add elements to panel
        functionPanel.add(bopenBracket);
        functionPanel.add(bcloseBracket);
        functionPanel.add(bdel);
        functionPanel.add(bac);
        functionPanel.add(badd);
        functionPanel.add(bsubtract);
        functionPanel.add(b1);
        functionPanel.add(b2);
        functionPanel.add(b3);
        functionPanel.add(bmultiply);
        functionPanel.add(bdivision);
        functionPanel.add(b4);
        functionPanel.add(b5);
        functionPanel.add(b6);
        functionPanel.add(bpower);
        functionPanel.add(be);
        functionPanel.add(b7);
        functionPanel.add(b8);
        functionPanel.add(b9);
        functionPanel.add(bx);
        functionPanel.add(by);
        functionPanel.add(bz);
        functionPanel.add(b0);
        functionPanel.add(padding1);
        functionPanel.add(ba);
        functionPanel.add(bb);
        functionPanel.add(bc);
        functionPanel.add(run);

        add(functionPanel);
        //add(inputImagePanel, BorderLayout.WEST);
        //add(outputImagePanel, BorderLayout.EAST);

        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        switch (s) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
            case "(":
            case ")":
                if (!this.inputFunction.endsWith(" "))
                    this.inputFunction = this.inputFunction + " ";
                this.inputFunction = this.inputFunction + s + " ";
                break;
            case "DEL":
                if (this.inputFunction.endsWith(" "))
                    this.inputFunction = this.inputFunction.substring(0, this.inputFunction.length() - 3);
                else
                    this.inputFunction = this.inputFunction.substring(0, this.inputFunction.length() - 1);
                break;
            case "AC":
                this.inputFunction = "";
                break;
            case "Run":
                SyntaxTree syntaxTree = new SyntaxTree(input.getText());
                String dotGraphInputString = syntaxTree.toDigraph();
                output.setText(syntaxTree.simplify());
                String dotGraphOutputString = syntaxTree.toDigraph();
                try {
                    MutableGraph dotGraphInput = new Parser().read(dotGraphInputString);
                    Graphviz.fromGraph(dotGraphInput).width(400).height(400).render(Format.PNG).toFile(new File("syntaxTreeInput.png"));
                    MutableGraph dotGraphOutput = new Parser().read(dotGraphOutputString);
                    Graphviz.fromGraph(dotGraphOutput).width(400).height(400).render(Format.PNG).toFile(new File("syntaxTreeOutput.png"));

                    /*inputSyntaxTreeFrame = new JFrame("Input Syntax Binary Tree");
                    inputSyntaxTreeFrame.setSize(420, 440);
                    inputSyntaxTreeFrame.setLocation(325, 0);
                    inputImagePanel = new syntaxTreePanel(inputImageFileName);
                    inputImagePanel.setPic(new ImageIcon(inputImageFileName));
                    inputImagePanel.repaint();
                    inputSyntaxTreeFrame.repaint();
                    inputSyntaxTreeFrame.add(inputImagePanel);

                    outputSyntaxTreeFrame = new JFrame("Output Syntax Binary Tree");
                    outputSyntaxTreeFrame.setSize(420, 440);
                    outputSyntaxTreeFrame.setLocation(325, 450);
                    outputImagePanel = new syntaxTreePanel(outputImageFileName);
                    outputImagePanel.setPic(new ImageIcon(outputImageFileName));
                    outputImagePanel.repaint();
                    outputSyntaxTreeFrame.repaint();
                    outputSyntaxTreeFrame.add(outputImagePanel);

                    inputSyntaxTreeFrame.setVisible(true);
                    outputSyntaxTreeFrame.setVisible(true);*/
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                this.inputFunction = this.inputFunction + s;
                break;
        }

        if (this.inputFunction.startsWith(" "))
            this.inputFunction = this.inputFunction.substring(1);
        input.setText(this.inputFunction);
    }
}
