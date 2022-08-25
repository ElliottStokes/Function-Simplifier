package org.FunctionSimplifier.GUI;

import org.FunctionSimplifier.SyntaxTree.SyntaxTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SyntaxTree syntaxTree = new SyntaxTree(input.getText());
                output.setText(syntaxTree.simplify());
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
