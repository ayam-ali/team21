package app;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * ComponentHandler.java - Handles dynamic resizing of history window when main JFrame wins.
 *
 * 
 * This work complies with the JMU Honor Code.
 *
 * Modifications: None
 *
 * @author Eric Anderson
 * @version Apr 27, 2021
 */
public class ComponentHandler implements ComponentListener
{

  @Override
  public void componentResized(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  } // end componentResized

  @Override
  public void componentMoved(ComponentEvent e)
  {
    int xOffset = 611;
    int yOffset = 51;

    RimplexWindow.moveHistoryWindow(e.getComponent().getX() + xOffset,
        e.getComponent().getY() + yOffset);

  } // end componentMoved

  @Override
  public void componentShown(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  } // end componentShown

  @Override
  public void componentHidden(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  } // end componentHidden

}
