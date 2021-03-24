package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener
{

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String buttonPressed = e.getActionCommand();
    if (buttonPressed.equals("Reset")) {
      GUI.reset();
    } else if (buttonPressed.equals("Clear")) {
      GUI.clear();
    } else {
      GUI.updateFields("("+ GUI.getInputFieldText() + ") " + e.getActionCommand());
    }    
  }

}
