package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import math.Calculator;
import math.ComplexNumber;

/**
 * ButtonHandler - responds to button presses and inputs.
 * 
 * Modifications: - Anderson (3/25) - added .strip before displaying input string.
 * 
 * @author Eric Anderson,
 * @version 3/25/2021
 */
public class ButtonHandler implements ActionListener
{
  Calculator calc = new Calculator();
  
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String buttonPressed = e.getActionCommand();
    String inputString = getInputFieldText();

    if (buttonPressed.equals("Reset"))
    {
      reset();
    }
    else if (buttonPressed.equals("Clear"))
    {
      clear();
    }
    else if (buttonPressed.equals("="))
    {
      RimplexWindow.expression.add(inputString.strip());
      ComplexNumber solved = calc.calculate(RimplexWindow.expression);
      
      updateFields("(" + inputString.strip() + ") " + buttonPressed + " (" + solved.toString() + ")");

    }
    else
    {
      if (!inputString.isEmpty())
      {
        RimplexWindow.expression.add(inputString.strip());
        updateFields("(" + inputString.strip() + ") " + e.getActionCommand());
      }
      else
      {
        updateFields(e.getActionCommand());
      }
      RimplexWindow.expression.add(buttonPressed);

    }
  }

  private static void clear()
  {
    RimplexWindow.inputField.setText("");
  }

  private static void reset()
  {
    RimplexWindow.display.setText("<html>");
    clear();
  }

  private static void updateFields(final String newDisplay)
  {
    RimplexWindow.display.setText(RimplexWindow.display.getText() + italicize(newDisplay) + " ");
    clear();
  }

  private static String italicize(final String toItalicize)
  {
    String str = toItalicize.replace("i", "<i>i</i>");
    return str;
  }

  private static String getInputFieldText()
  {
    return RimplexWindow.inputField.getText().replaceAll("\r", "").replaceAll("\n", "");
  }
}
