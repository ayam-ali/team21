package app;

import javax.swing.JFrame;

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
    gui.setVisible(true);
  }
}
