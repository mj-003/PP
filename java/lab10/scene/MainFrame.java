package scene;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public enum Mode { NONE, CIRCLE, RECTANGLE, SPIRAL, TRIANGLE, FILL, SELECT, COMPLEX, TEXT, SEGMENT }
    private Mode currentMode = Mode.NONE;
    private Color chosenFillColor = Color.BLACK;
    JCheckBox complexModeCheckbox = new JCheckBox("Complex");

    public MainFrame()
    {
        setTitle("Java Paint");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Scene scene = new Scene(this);
        add(scene, BorderLayout.CENTER);


        /* ------------ Buttons ------------- */

        JPanel buttonPanel = new JPanel();
        JButton addCircleButton = new JButton("○");
        JButton addRectangleButton = new JButton("☐");
        JButton addSpiralButton = new JButton("✺");
        JButton addTriangleButton = new JButton("△");
        JButton clearButton = new JButton("⌫");
        JButton fillButton = new JButton("🎨");
        JButton selectButton = new JButton("☞");
        JButton addTextButton = new JButton("✎");
        JButton addSegmentButton = new JButton("⌇");


        /*------------ Button Properties -------------*/

        Dimension buttonSize = new Dimension(60, 60);
        addCircleButton.setPreferredSize(buttonSize);
        addRectangleButton.setPreferredSize(buttonSize);
        addSpiralButton.setPreferredSize(buttonSize);
        addTriangleButton.setPreferredSize(buttonSize);
        clearButton.setPreferredSize(buttonSize);
        selectButton.setPreferredSize(buttonSize);
        fillButton.setPreferredSize(buttonSize);
        addTextButton.setPreferredSize(buttonSize);
        addSegmentButton.setPreferredSize(buttonSize);

        Font buttonFont = new Font("Arial", Font.PLAIN, 30);
        addCircleButton.setFont(new Font("Arial", Font.PLAIN, 40));
        addRectangleButton.setFont(new Font("Arial", Font.PLAIN, 35));
        addSpiralButton.setFont(buttonFont);
        addTriangleButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);
        fillButton.setFont(buttonFont);
        selectButton.setFont(buttonFont);
        addTextButton.setFont(buttonFont);
        addSegmentButton.setFont(buttonFont);


        /* ------------ Button Actions ------------- */

        addCircleButton.addActionListener(e -> currentMode = Mode.CIRCLE);
        addRectangleButton.addActionListener(e -> currentMode = Mode.RECTANGLE);
        addSpiralButton.addActionListener(e -> currentMode = Mode.SPIRAL);
        addTriangleButton.addActionListener(e -> currentMode = Mode.TRIANGLE);
        fillButton.addActionListener(e -> handleFillButtonClick());
        selectButton.addActionListener(e -> currentMode = Mode.SELECT);
        addTextButton.addActionListener(e -> currentMode = Mode.TEXT);
        addSegmentButton.addActionListener(e -> currentMode = Mode.SEGMENT);

        clearButton.addActionListener(e -> {
            currentMode = Mode.NONE;
            scene.clear();
            complexModeCheckbox.setSelected(false);
        });

        complexModeCheckbox.addActionListener(e -> currentMode = complexModeCheckbox.isSelected() ? Mode.COMPLEX : Mode.NONE);


        /* ------------ Add Buttons to Panel ------------- */

        buttonPanel.add(addCircleButton);
        buttonPanel.add(addRectangleButton);
        buttonPanel.add(addSpiralButton);
        buttonPanel.add(addTriangleButton);
        buttonPanel.add(addSegmentButton);
        buttonPanel.add(selectButton);
        buttonPanel.add(addTextButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(fillButton);
        buttonPanel.add(complexModeCheckbox);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }


    /* ------------ Helper Methods ------------- */
    private void handleFillButtonClick()
    {
        currentMode = Mode.FILL;
        Color newColor = JColorChooser.showDialog(this, "Choose Fill Color", chosenFillColor);

        if (newColor != null) {
            chosenFillColor = newColor;
        }
    }


    /* ------------ Getters and Setters ------------- */

    public Color getChosenFillColor() { return chosenFillColor; }

    public Mode getCurrentMode() { return currentMode; }

    public boolean isComplexMode() { return complexModeCheckbox.isSelected(); }
}
