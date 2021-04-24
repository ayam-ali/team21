package app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

/**
 * HistoryHandler.java - Handles actions of the menu for showing history. Action listeners are for
 * menuItems and popout.
 *
 * 
 * This work complies with the JMU Honor Code.
 *
 * Modifications: None
 *
 * @author Eric Anderson
 * @version Apr 8, 2021
 */
public class HistoryHandler implements ActionListener
{
  
  JWindow history;
  JWindow about;

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String buttonPressed = e.getActionCommand();
    switch (buttonPressed)
    {
      case "<":
        RimplexWindow.animateHistory(false);
        break;
      case ">":
        RimplexWindow.animateHistory(true);
        break;
      default:
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Error creating menu items", "Alert",
            JOptionPane.WARNING_MESSAGE);
        break;

    }

  }

}
