package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import math.Calculator;

/**
 * ButtonHandler - responds to button presses and inputs.
 * 
 * Modifications: - Anderson (3/25) - added .strip before displaying input string. Ali (3/28) -
 * clear up and javadoc comments. Ali (3/30) Editing, clarifications, and comments.
 * 
 * @author Eric Anderson, Eric Hernandez-Diaz
 * @version 3/25/2021
 */
public class ButtonHandler implements ActionListener, KeyListener
{
  Calculator calc = new Calculator();
  String currentOperand = "";
  boolean missingParam = false;
  String rPar = "(";
  String lPar = ") ";
  
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
    else if (buttonPressed.equals("\u2190")) // backspace
    {
      String displayText = getDisplayText();
      updateDisplay(displayText.substring(0, displayText.length() - 1));

    }
    else if (calc.isOperation(buttonPressed))
    { // operation symbol
      if (!missingParam)
      { // if there is a left paren but no right paren
        RimplexWindow.expression.add(currentOperand);
        RimplexWindow.expression.add(buttonPressed);
        appendToDisplay(buttonPressed);
        currentOperand = "";
      }
      else
      { // if there are no paren missing
        currentOperand = currentOperand + buttonPressed;
        appendToDisplay(buttonPressed);
      }

    }
    else if (buttonPressed.equals(lPar))
    { // left paren
      missingParam = true;
      appendToDisplay(buttonPressed);
      currentOperand = currentOperand + buttonPressed;
    }
    else if (buttonPressed.equals(rPar))
    {
      // right paren
      missingParam = false;
      appendToDisplay(buttonPressed);
      currentOperand = currentOperand + buttonPressed;
    }
    else
    {
      appendToDisplay(buttonPressed);
      currentOperand = currentOperand + buttonPressed;
    }
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    if (keyCode == KeyEvent.VK_0)
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
  }

  /**
   * Clears the input field.
   */
  private static void clear()
  {
    RimplexWindow.display.setText("<html>");
  }

  /**
   * Resets the display and clears the input field.
   */
  private static void reset()
  {
    RimplexWindow.display.setText("<html>");
    RimplexWindow.expression = new ArrayList<>();
    clear();
  }

  /**
   * Updates the display, italicizes i.
   * 
   * @param newDisplay
   *          to be displayed
   */
  private static void updateDisplay(final String newDisplay)
  {
    RimplexWindow.display.setText(italicize(newDisplay));
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

  static void appendToDisplay(String str)
  {
    RimplexWindow.display.setText(RimplexWindow.display.getText() + italicize(str));
  }

  private static void addToExpression(String str)
  {
    RimplexWindow.expression.add(str);
  }

  @Override
  public void keyTyped(KeyEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    // TODO Auto-generated method stub

  }

}
