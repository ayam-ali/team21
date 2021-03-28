package app;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GUIUtils
{
  static JPanel createButtons()
  {
    JButton addButton, subtrButton, multButton, divButton, resetButton, clearButton, equalsButton;

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    addButton = new JButton("+");
    divButton = new JButton("\u00F7");
    multButton = new JButton("\u00D7");
    subtrButton = new JButton("-");
    resetButton = new JButton("Reset");
    clearButton = new JButton("Clear");
    equalsButton = new JButton("=");

    ButtonHandler h = new ButtonHandler();
    resetButton.addActionListener(h);
    addButton.addActionListener(h);
    multButton.addActionListener(h);
    divButton.addActionListener(h);
    subtrButton.addActionListener(h);
    clearButton.addActionListener(h);
    equalsButton.addActionListener(h);

    buttonPanel.add(clearButton);
    buttonPanel.add(resetButton);
    buttonPanel.add(subtrButton);
    buttonPanel.add(multButton);
    buttonPanel.add(divButton);
    buttonPanel.add(addButton);
    buttonPanel.add(equalsButton);

    return buttonPanel;
  }

  static void reset()
  {
    GUI.display.setText("");
    GUI.expression = new ArrayList<>();
    clear();
  }

  static void clear()
  {
    GUI.inputField.setText("");
  }

  static void updateFields(final String newDisplay)
  {
    GUI.display.setText(GUI.display.getText() + newDisplay + " ");
    clear();
  }

  /**
   * getInputFieldText - gets user input from field.
   * 
   * 
   * *EMA (3/25) - added logic to remove newlines from expressions.
   * 
   * @return inputField string.
   */
  static String getInputFieldText()
  {
    return GUI.inputField.getText().replaceAll("\r", "").replaceAll("\n", "");
  }
}
