package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ButtonHandler - responds to button presses and inputs.
 * 
 * Modifications:
 * - Anderson (3/25) - added .strip before displaying input string.
 * 
 * @author Eric Anderson, 
 * @version 3/25/2021
 */
public class ButtonHandler implements ActionListener
{

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String buttonPressed = e.getActionCommand();
    String inputString = GUI.getInputFieldText();
    
    if (buttonPressed.equals("Reset"))
    {
      GUI.reset();
    }
    else if (buttonPressed.equals("Clear"))
    {
      GUI.clear();
    }
    else
    {
      if (!inputString.isEmpty())
      {
        GUI.expression.add(inputString.strip());
        GUI.expression.add(buttonPressed);
        GUI.updateFields("(" + inputString.strip() + ") " + e.getActionCommand());
      }
    }
  }

}
