package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String buttonPressed = e.getActionCommand();
    String inputString = GUIUtils.getInputFieldText();

    if (buttonPressed.equals("Reset"))
    {
      GUIUtils.reset();
    }
    else if (buttonPressed.equals("Clear"))
    {
      GUIUtils.clear();
    }
    else if (buttonPressed.equals("="))
    {
      String solved = "";
      // expression = new ArrayList<>();
      // expression.add(solved);
      // GUIUtils.updateFields(inputString.strip() + buttonPressed);

    }
    else
    {
      if (!inputString.isEmpty())
      {
        GUI.expression.add(inputString.strip());
        GUI.expression.add(buttonPressed);
        GUIUtils.updateFields("(" + inputString.strip() + ") " + e.getActionCommand());
      }
    }
  }

}
