package app;

import java.io.FileNotFoundException;

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
   * @throws FileNotFoundException 
   */
  public static void main(final String[] args) throws FileNotFoundException
  {
    RimplexWindow gui = new RimplexWindow(new EventHandler());
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setSize(620, 320);
    gui.setVisible(true);
  }
}
