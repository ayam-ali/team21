package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.KeyStroke;

import math.Calculator;
import math.ComplexNumber;

/**
 * EventHandler - responds to button presses and inputs.
 * 
 * @author Eric Anderson, Eric Hernandez-Diaz, Ayam Ali
 * @version 3/25/2021
 */
public class EventHandler extends KeyAdapter implements ActionListener
{
  private static String currentOperand = "";
  private static boolean isFraction = false;
  private static boolean missingParam = false;

  private Calculator calc = new Calculator();
  private boolean exponential = false;

  private String rPar = ")";
  private String lPar = "(";
  private String zero = "0";
  private String one = "1";
  private String two = "2";
  private String three = "3";
  private String four = "4";
  private String five = "5";
  private String six = "6";
  private String seven = "7";
  private String eight = "8";
  private String nine = "9";
  private String sqrt = "\u221A";
  private String inv = "Inv";
  private String con = "Con";

  /**
   * Reactions for when a button is pressed.
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {

    String buttonPressed = e.getActionCommand();

    if (buttonPressed.equals("R"))
    {
      reset();
    }
    else if (buttonPressed.equals("C"))
    {
      clear();
    }
    else if (buttonPressed.equals("Frac/Dec"))
    {
      isFraction = !isFraction;
    }
    // Square root
    else if (buttonPressed.equals(sqrt))
    {
      currentOperand = currentOperand + sqrt;
      appendToDisplay(sqrt);
    }
    // inverse
    else if (buttonPressed.equals(inv))
    {
      currentOperand = currentOperand + inv;
      appendToDisplay(inv);
    }
    else if (buttonPressed.equals(con)) // con
    {
      currentOperand = currentOperand + con;
      appendToDisplay(con);
    }
    else if (buttonPressed.equals("Re")) // real and img part
    {
      currentOperand = currentOperand + "Re";
      appendToDisplay("Re");
    }
    else if (buttonPressed.equals("Im"))
    {
      currentOperand = currentOperand + "Im";
      appendToDisplay("Im");
    }
    else if (buttonPressed.equals("LOG"))
    {
      currentOperand = currentOperand + "LOG";
      appendToDisplay("Log");
    }
    else if (buttonPressed.equals("\u00B1")) // +- sign
    {

      ComplexNumber num = ComplexNumber.parse(currentOperand);
      num = num.multiply(ComplexNumber.parse("-1"));
      clear();
      currentOperand = num.toString();

      if (!currentOperand.startsWith(lPar))
      {
        currentOperand = lPar + currentOperand;
      }
      if (!currentOperand.endsWith(rPar))
      {
        currentOperand = currentOperand + rPar;
      }

      appendToDisplay(currentOperand);
    }
    else if (buttonPressed.equals("\u2190")) // backspace
    {
      backspace();
    }
    else if (calc.isOperation(buttonPressed))
    { // operation symbol
      if (missingParam)
      { // if there is a left paren but no right paren
        currentOperand = currentOperand + buttonPressed;
        appendToDisplay(buttonPressed);

      }
      else
      { // if there are no paren missing
        addOperator(buttonPressed);
      }
    }
    else if (buttonPressed.equals("\uD835\uDC8A"))
    {
      currentOperand = currentOperand + "i";
      appendToDisplay("i");
    }
    else if (buttonPressed.equals(lPar))
    { // left paren
      missingParam = true;
      appendToDisplay(buttonPressed);
      currentOperand = currentOperand + lPar;
    }
    else if (buttonPressed.equals(rPar))
    {
      missingParam = false;
      appendToDisplay(buttonPressed);
      currentOperand = currentOperand + rPar;

    }
    else if (buttonPressed.equals("="))
    {
      findSolution();
    }
    else if (buttonPressed.equals("x^y"))
    {
      currentOperand = currentOperand + "^";
      appendToDisplay("^");
    }
    else if (buttonPressed.equals(sqrt))
    {
      currentOperand = currentOperand + sqrt;
      appendToDisplay(sqrt);
    }
    else
    {
      appendToDisplay(buttonPressed);
      currentOperand = currentOperand + buttonPressed;
    }
  }

  /**
   * Reactions for when a user presses a key on their device.
   */
  @Override
  public void keyPressed(final KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    KeyStroke eKeyStroke = KeyStroke.getKeyStroke(e.getKeyChar());
    if (eKeyStroke == KeyStroke.getKeyStroke('('))
    {
      missingParam = true;
      currentOperand = currentOperand + lPar;
      appendToDisplay(lPar);
    }
    else if (eKeyStroke == KeyStroke.getKeyStroke(')'))
    {
      missingParam = false;
      currentOperand = currentOperand + rPar;
      appendToDisplay(rPar);
    }
    else if (keyCode == KeyEvent.VK_0)
    {
      currentOperand = currentOperand + zero;
      appendToDisplay(zero);

    }
    else if (keyCode == KeyEvent.VK_1)
    {
      currentOperand = currentOperand + one;
      appendToDisplay(one);
    }
    else if (keyCode == KeyEvent.VK_2)
    {
      currentOperand = currentOperand + two;
      appendToDisplay(two);
    }
    else if (keyCode == KeyEvent.VK_3)
    {
      currentOperand = currentOperand + three;
      appendToDisplay(three);
    }
    else if (keyCode == KeyEvent.VK_4)
    {
      currentOperand = currentOperand + four;
      appendToDisplay(four);
    }
    else if (keyCode == KeyEvent.VK_5)
    {
      currentOperand = currentOperand + five;
      appendToDisplay(five);
    }
    else if (keyCode == KeyEvent.VK_6)
    {
      currentOperand = currentOperand + six;
      appendToDisplay(six);
    }
    else if (keyCode == KeyEvent.VK_7)
    {
      currentOperand = currentOperand + seven;
      appendToDisplay(seven);
    }

    else if (keyCode == KeyEvent.VK_BACK_SPACE)
    {
      backspace();
    }
    else if (eKeyStroke.equals(KeyStroke.getKeyStroke('*')))
    {
      if (missingParam)
      { // if there is a left paren but no right paren
        currentOperand = currentOperand + "\u00D7";
        appendToDisplay("\u00D7");

      }
      else
      { // if there are no paren missing
        addOperator("*");
      }
    }
    else if (keyCode == KeyEvent.VK_8)
    {
      currentOperand = currentOperand + eight;
      appendToDisplay(eight);
    }
    else if (keyCode == KeyEvent.VK_9)
    {
      currentOperand = currentOperand + nine;
      appendToDisplay(nine);
    }
    else if (keyCode == KeyEvent.VK_ENTER)
    {
      findSolution();
    }
    else if (keyCode == KeyEvent.VK_MINUS) // subtract on keys
    {
      if (missingParam)
      { // if there is a left paren but no right paren
        currentOperand = currentOperand + "-";
        appendToDisplay("-");

      }
      else
      { // if there are no paren missing
        addOperator("-");
      }
    }
    else if (keyCode == KeyEvent.VK_SLASH)
    { // division on key
      if (missingParam)
      { // if there is a left paren but no right paren
        currentOperand = currentOperand + "\u00F7";
        appendToDisplay("\u00F7");

      }
      else
      { // if there are no paren missing
        addOperator("/");
      }
    }
    else if (keyCode == KeyEvent.VK_PERIOD)
    {
      currentOperand = currentOperand + ".";
      appendToDisplay(".");
    }
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    KeyStroke eKeyStroke = KeyStroke.getKeyStroke(e.getKeyChar());

    if (eKeyStroke.equals(KeyStroke.getKeyStroke('+')))
    {
      if (missingParam)
      { // if there is a left paren but no right paren
        currentOperand = currentOperand + "+";
        appendToDisplay("+");

      }
      else
      { // if there are no paren missing
        addOperator("+");
      }
    }
  }

  /**
   * Backspace to work with soft and keyboard, avoid code duplication.
   */
  private void backspace()
  {
    if (!currentOperand.isEmpty())
    {
      String displayText = getDisplayText();

      if (currentOperand.endsWith("i"))
      {
        updateDisplay(displayText.substring(0, displayText.length() - 8));
      }
      else if (currentOperand.endsWith(lPar))
      {
        updateDisplay(displayText.substring(0, displayText.length() - 1));
        missingParam = false;
      }
      else if (currentOperand.endsWith(rPar))
      {
        updateDisplay(displayText.substring(0, displayText.length() - 1));
        missingParam = true;
      }
      else if (currentOperand.endsWith("LOG") || currentOperand.endsWith(inv)
          || currentOperand.endsWith(con))
      {
        updateDisplay(displayText.substring(0, displayText.length() - 3));
        currentOperand = currentOperand.substring(0, currentOperand.length() - 3);
        return;
      }
      else
      {
        updateDisplay(displayText.substring(0, displayText.length() - 1));
      }
      currentOperand = currentOperand.substring(0, currentOperand.length() - 1);
    }
  }

  /**
   * Clears the input field.
   */
  private static void clear()
  {
    currentOperand = currentOperand.replaceAll("i", "<i>i</i>");
    RimplexWindow.display.setText(
        getDisplayText().substring(0, getDisplayText().length() - currentOperand.length()));
    currentOperand = "";
  }

  /**
   * Resets the display and clears the input field.
   */
  private static void reset()
  {
    RimplexWindow.expression = new ArrayList<>();
    RimplexWindow.display.setText("<html><br>");
    currentOperand = "";
  }

  /**
   * Updates the display, italicizes i.
   * 
   * @param newDisplay
   *          to be displayed
   */
  private static void updateDisplay(final String newDisplay)
  {
    RimplexWindow.display.setText(newDisplay);
  }

  /**
   * Italicizes i in the imaginary numbers.
   * 
   * @param toItalicize
   *          to change to italics
   * @return italicized version of the string
   */
  private static String italicize(final String toItalicize)
  {
    String str = toItalicize.replace("i", "<i>i</i>");
    return str;
  }

  /**
   * Gets the current display field.
   * 
   * @return the input field as a string
   */
  private static String getDisplayText()
  {
    return RimplexWindow.display.getText();
  }

  /**
   * Appends strings to the display.
   * 
   * @param str
   *          the string that will be appended.
   */
  static void appendToDisplay(final String str)
  {
    RimplexWindow.display.setText(getDisplayText() + italicize(str));
  }

  /**
   * Add an operand or operator to the expression.
   * 
   * @param str
   *          the string that will be added to the expression.
   */
  private static void addToExpression(final String str)
  {
    RimplexWindow.expression.add(str);
  }

  /**
   * Helper method for requesting operators.
   * 
   * @param operator
   *          current operator being inputted.
   */
  private void addOperator(final String operator)
  {
    String op = operator;
    if (op.equals("*"))
    {
      op = "\u00D7";
    }
    else if (op.equals("/"))
    {
      op = "\u00F7";
    }

    if (!currentOperand.isEmpty())
    {
      RimplexWindow.expression.add(currentOperand);
    }
    addToExpression(op);
    // appendToDisplay(op);
    String displayText = getDisplayText();
    String upperLine = displayText.substring(displayText.indexOf("<html>"),
        displayText.indexOf("<br>"));
    updateDisplay(upperLine + italicize(currentOperand) + op + "<br>");
    currentOperand = "";

  }

  /**
   * Helper method used to find calculations.
   */
  private void findSolution()
  {
    RimplexWindow.expression.add(currentOperand);
    ComplexNumber solved = calc.calculate(RimplexWindow.expression);
    RimplexWindow.expression.clear();
    RimplexWindow.expression.add(solved.toString());

    String displayText = getDisplayText();
    String upperLine = displayText.substring(displayText.indexOf("<html>"),
        displayText.indexOf("<br>"));
    updateDisplay(
        upperLine + italicize(currentOperand) + "=" + italicize(solved.toString(isFraction)) + "<br>");

    currentOperand = "";

    RimplexWindow.addToHistory(getDisplayText());
    RimplexWindow.updateHistory();
  }
}
