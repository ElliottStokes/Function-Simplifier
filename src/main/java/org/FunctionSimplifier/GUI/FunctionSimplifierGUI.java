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
    public FunctionSimplifierGUI() {
        setTitle("Function Simplifier");
        setSize(500, 500);
        setLocation(0, 0);
        Container contentPane = getContentPane();

        JPanel functionPanel = new JPanel();
        JTextField input = new JTextField(36);
        input.setEditable(true);
        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setLabelFor(input);
        JTextField output = new JTextField(36);
        output.setEditable(false);
        JLabel outputLabel = new JLabel("Output:");
        outputLabel.setLabelFor(output);
        functionPanel.add(inputLabel);
        functionPanel.add(input);
        functionPanel.add(outputLabel);
        functionPanel.add(output);

        JButton run = new JButton("Run");
        run.addActionListener(e -> {
            SyntaxTree syntaxTree = new SyntaxTree(input.getText());
            String dotGraphInputString = syntaxTree.toDigraph();
            output.setText(syntaxTree.simplify());
            String dotGraphOutputString = syntaxTree.toDigraph();
            try {
                MutableGraph dotGraphInput = new Parser().read(dotGraphInputString);
                Graphviz.fromGraph(dotGraphInput).width(700).render(Format.PNG).toFile(new File("syntaxTreeInput.png"));
                MutableGraph dotGraphOutput = new Parser().read(dotGraphOutputString);
                Graphviz.fromGraph(dotGraphOutput).width(700).render(Format.PNG).toFile(new File("syntaxTreeOutput.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        functionPanel.add(run);

        add(functionPanel);

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

    }
}
