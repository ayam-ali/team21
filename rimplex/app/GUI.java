package app;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * GUI - Creates objects for displays and input fields. 
 * 
 * Modifications:
 * - Anderson (3/25) - changed input field to JTextField to prevent \n chars.
 *                     Added string formatter to remove \n for redundancy.
 * 
 * @author Eric Anderson, 
 * @version 3/25/2021
 */
public class GUI
{
  
  static ArrayList<String> expression = new ArrayList<String>();
  private static JTextArea display = new JTextArea();
  private static JTextField inputField = new JTextField();

  public static void main(String[] args) {
    JFrame main;
    JPanel buttonPanel;
    
    main = new JFrame("Rimplex");
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setLayout(new GridLayout(3, 1));
    main.setSize(400, 200);
    
    buttonPanel = createButtons();

    display.setEditable(false);
    display.setBorder(BorderFactory.createTitledBorder("Display")); // temporary border
    inputField.setBorder(BorderFactory.createTitledBorder("Input")); // temporary border
    
    main.add(display);
    main.add(inputField);
    main.add(buttonPanel);
    
    main.setVisible(true);
  }
  
  private static JPanel createButtons() {
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
  
  static void reset() {
    display.setText("");
    expression = new ArrayList<>();
    clear();
  }
  
  static void clear() {
    inputField.setText("");
  }
  
  static void updateFields(String newDisplay) {
    display.setText(display.getText() + newDisplay + " ");
    clear();
  }
  
  /**
   * getInputFieldText - gets user input from field.
   * 
   * 
   * *EMA (3/25) - added logic to remove newlines from expressions. 
   * @return inputField string.
   */
  static String getInputFieldText() {
    return inputField.getText().replaceAll("\r", "").replaceAll("\n", "");
  }
}
