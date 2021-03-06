package app;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * Printer.java - Handles printing for the history.
 *
 *
 * Modifications: None
 *
 * @author Eric Anderson
 * @version Apr 26, 2021
 */
public class Printer implements Printable
{
  final Component comp;

  /**
   * Printer Constructor.
   * @param comp A component.
   */
  public Printer(final Component comp)
  {
    this.comp = comp;
  }

  /**
   * print - prints the history page after formatting.
   * 
   * @param g - The graphics rendering of the page to be printed.
   * @param format - portrait or landscape format.
   * @param pageIndex - the page number. 
   * 
   * @throws PrinterException - thrown if there is an error in the print system.
   */
  @Override
  public int print(final Graphics g, final PageFormat format, final int pageIndex)
      throws PrinterException
  {
    if (pageIndex > 0)
    {
      return Printable.NO_SUCH_PAGE;
    }

    // get the bounds of the component
    Dimension dim = comp.getSize();
    double cHeight = dim.getHeight();
    double cWidth = dim.getWidth();

    // get the bounds of the printable area
    double pHeight = format.getImageableHeight();
    double pWidth = format.getImageableWidth();

    double pXStart = format.getImageableX();
    double pYStart = format.getImageableY();

    double xRatio = pWidth / cWidth;
    double yRatio = pHeight / cHeight;

    Graphics2D g2 = (Graphics2D) g;
    g2.translate(pXStart, pYStart);
    g2.scale(xRatio / 4, yRatio / 4);
    comp.paint(g2);

    return Printable.PAGE_EXISTS;
  }
}
