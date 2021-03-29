package app;

import javax.swing.JFrame;

public class Driver
{
  /**
   * The main method to run rimplex.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    RimplexWindow gui = new RimplexWindow(new ButtonHandler());
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setVisible(true);
  }

  // static JPanel createButtons()
  // {
  // JButton addButton, subtrButton, multButton, divButton, resetButton, clearButton, equalsButton;
  //
  // JPanel buttonPanel = new JPanel();
  // buttonPanel.setLayout(new FlowLayout());
  //
  // addButton = new JButton("+");
  // divButton = new JButton("\u00F7");
  // multButton = new JButton("\u00D7");
  // subtrButton = new JButton("-");
  // resetButton = new JButton("Reset");
  // clearButton = new JButton("Clear");
  // equalsButton = new JButton("=");
  //
  // ButtonHandler h = new ButtonHandler();
  // resetButton.addActionListener(h);
  // addButton.addActionListener(h);
  // multButton.addActionListener(h);
  // divButton.addActionListener(h);
  // subtrButton.addActionListener(h);
  // clearButton.addActionListener(h);
  // equalsButton.addActionListener(h);
  //
  // buttonPanel.add(clearButton);
  // buttonPanel.add(resetButton);
  // buttonPanel.add(subtrButton);
  // buttonPanel.add(multButton);
  // buttonPanel.add(divButton);
  // buttonPanel.add(addButton);
  // buttonPanel.add(equalsButton);
  //
  // return buttonPanel;
  // }
  //
  // static void reset()
  // {
  // GUI.display.setText("<html>");
  // GUI.expression.clear();
  // clear();
  // }
  //
  // static void clear()
  // {
  // GUI.inputField.setText("");
  // }
  //
  // static void updateFields(final String newDisplay)
  // {
  // GUI.display.setText(GUI.display.getText() + italicize(newDisplay) + " ");
  // clear();
  // }
  //
  // /**
  // * getInputFieldText - gets user input from field.
  // *
  // *
  // * *EMA (3/25) - added logic to remove newlines from expressions.
  // *
  // * @return inputField string.
  // */
  // static String getInputFieldText()
  // {
  // return GUI.inputField.getText().replaceAll("\r", "").replaceAll("\n", "");
  // }
  //
  // private static String italicize(final String string) {
  // String str = string;
  // str = str.replaceAll("i", "<i>i</i>");
  // return str;
  // }
}
