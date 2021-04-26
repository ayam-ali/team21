package app;

import java.io.IOException;

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
   * @throws IOException 
   */
  public static void main(final String[] args) throws IOException
  {
    RimplexWindow gui = RimplexWindow.createRimplexWindow();
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setSize(620, 320);
    gui.setVisible(true);
  }
}
