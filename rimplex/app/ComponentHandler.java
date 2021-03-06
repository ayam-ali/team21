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
  public void componentResized(final ComponentEvent e)
  {
    // TODO Auto-generated method stub

  } // end componentResized

  /**
   * Moves the history window with the Rimplex main window.
   */
  @Override
  public void componentMoved(final ComponentEvent e)
  {
    int xOffset = 611;
    int yOffset = 51;

    RimplexWindow.moveHistoryWindow(e.getComponent().getX() + xOffset,
        e.getComponent().getY() + yOffset);

  } // end componentMoved

  @Override
  public void componentShown(final ComponentEvent e)
  {
    // TODO Auto-generated method stub

  } // end componentShown

  @Override
  public void componentHidden(final ComponentEvent e)
  {
    // TODO Auto-generated method stub

  } // end componentHidden

}
