package app;

import javax.swing.JFrame;

/**
 * Driver - creates new RimplexWindow.
 * 
 * @author Eric Hernandez-Diaz
 * @version 3/30/2021
 */
public class Driver
{
  /**
   * The main method to run rimplex.
   * 
   * @param args
   */
  public static void main(final String[] args)
  {
    RimplexWindow gui = new RimplexWindow(new ButtonHandler());
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setSize(300, 225);
    gui.setVisible(true);
  }
}
