package app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

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
public class HistoryHandler implements ActionListener, MenuListener
{
  
  JWindow history;

  @Override
  public void menuSelected(MenuEvent e)
  {

  }

  @Override
  public void menuDeselected(MenuEvent e)
  {
    // TODO Auto-generated method stub
  }

  @Override
  public void menuCanceled(MenuEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String buttonPressed = e.getActionCommand();

    switch (buttonPressed)
    {
      case ">":
        System.out.println("History Opened");
        openHistory();
        break;
      case "<":
        System.out.println("History Closed");
        history.setVisible(false);
        break;
      default:
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Error creating menu items", "Alert",
            JOptionPane.WARNING_MESSAGE);
        break;

    }

  }
  
  private void openHistory() {
    
    history = new JWindow();
    history.setSize(200, 200);
    history.setBackground(Color.RED);
    history.setVisible(true);
    
  }

}
