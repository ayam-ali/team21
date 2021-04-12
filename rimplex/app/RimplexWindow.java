package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * GUI - Creates objects for displays and input fields.
 * 
 * Modifications: - Anderson (3/25) - changed input field to JTextField to prevent \n chars. Added
 * string formatter to remove \n for redundancy. Ali (3/28) - Changed symbols, changed window size,
 * cleared up, and added javadoc comments. Ali (3/30) color change, and clarifications
 * 
 * @author Eric Anderson, Eric Hernandez-Diaz, Ayam Ali
 * @version 3/25/2021
 */
public class RimplexWindow extends JFrame
{

  static JLabel display;
  static ArrayList<String> expression;

  private static final long serialVersionUID = 1L;
  private EventHandler eventHandler;
  private JPanel buttonPanel;

  /**
   * The constructor for the rimplex window.
   * 
   * @param eventHandler
   *          to deal with the buttons
   */
  public RimplexWindow(final EventHandler eventHandler)
  {
    super("Rimplex");
    this.eventHandler = eventHandler;
    this.buttonPanel = createButtonPanel();
    createDisplay();
    createExpression();

    makeLayout();
    setSize(400, 250);
  }

  /**
   * Adds the buttons to the panel and provides the action listener.
   * 
   * @param name
   *          for what is going to be on the button
   */
  private JButton addButton(final String name, final int x, final int y, final int width, final int height)
  {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.gridwidth = width;
    gbc.gridheight = height;
    gbc.weightx = .25;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    JButton button = new JButton(name);
    button.setSize(width, height);
    button.setFocusable(false);
    button.addActionListener(eventHandler);
    buttonPanel.add(button, gbc);

    // changes the color of the button
    Color purple = new Color(175, 175, 225);
    changeColor(button, purple);
    return button;
  }

  /**
   * Creates the button panel for what is to be layout.
   * 
   * @return a JPanel for the panel
   */
  private JPanel createButtonPanel()
  {
    JPanel result = new JPanel();
    result.setLayout(new GridBagLayout());
    return result;
  }

  /**
   * Creates the display that is going to contain the expressions.
   */
  private static void createDisplay()
  {
    display = new JLabel();
    Font font = display.getFont();
    display.setFont(font.deriveFont(Font.PLAIN));
    display.setText("<html>");
    display.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    display.setFocusable(true);
    display.addKeyListener(new EventHandler());
  }

  /**
   * Creates the array list for the expressions.
   */
  private static void createExpression()
  {
    expression = new ArrayList<>();
  }

  /**
   * To change the color of a button.
   * 
   * @param button
   *          to change color of
   * @param color
   *          to change to
   */
  private void changeColor(final JButton button, final Color color)
  {
    button.setBackground(color);
    button.setOpaque(true);
    button.setBorderPainted(false);
  }

  /**
   * Creates the layout and sets the buttons.
   */
  private void makeLayout()
  {
    this.setLayout(new BorderLayout());
    // this.add(display);
    // this.add(inputField);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 2;
    gbc.gridwidth = 6;
    gbc.weightx = 1 / 12;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    buttonPanel.add(display, gbc);

    // create buttons and adds color
    // row 1
    addButton("\u00B1", 0, 3, 1, 1); // plus minus sign
    addButton("C", 1, 3, 1, 1);
    addButton("\u2190", 2, 3, 1, 1); // if "" does not work use "<-" instead
    addButton("+", 3, 3, 1, 1);
    addButton("R", 4, 3, 1, 1);

    // row 2
    addButton("1", 0, 4, 1, 1);
    addButton("2", 1, 4, 1, 1);
    addButton("3", 2, 4, 1, 1);
    addButton("-", 3, 4, 1, 1);
    addButton("Inv", 4, 4, 1, 1);

    // row 3
    addButton("4", 0, 5, 1, 1);
    addButton("5", 1, 5, 1, 1);
    addButton("6", 2, 5, 1, 1);
    addButton("\u00D7", 3, 5, 1, 1); // unicode for multiplication \u00D7
    addButton("(", 4, 5, 1, 1); 
    // row 4
    addButton("7", 0, 6, 1, 1);
    addButton("8", 1, 6, 1, 1);
    addButton("9", 2, 6, 1, 1);
    addButton("\u00F7", 3, 6, 1, 1); // division sign
    addButton(")", 4, 6, 1, 1);

    // row 5
    addButton("0", 0, 7, 2, 1);
    addButton("\uD835\uDC8A", 2, 7, 1, 1); // math i sign
    addButton("=", 3, 7, 1, 1);
    addButton(".", 4, 7, 1, 1);

    // row 6
    addButton("\u221A", 5, 4, 1, 1); // unicode for square root is \u221A
    addButton("LOG", 5, 3, 1, 1);
   

    // resizing the words so they fit better
    addButton("Frac/Dec", 5, 7, 1, 1);
    addButton("Con", 5, 5, 1, 1);
    addButton("x^y", 5, 6, 1, 1);

    this.add(buttonPanel, BorderLayout.CENTER);
    JMenuBar menu = new JMenuBar();
    JMenu item = new JMenu(">");
    // item.add(display);
    menu.add(item);
    this.add(menu, BorderLayout.EAST);
    this.pack();
  }

  /**
   * Private method to make the words underneath each other
   */
  // private JButton overUnder(JButton button, String str1, String str2)
  // {
  // button.setLayout(new BorderLayout());
  // JLabel label1 = new JLabel(str1);
  // JLabel label2 = new JLabel(str2);
  // button.add(BorderLayout.NORTH, label1).setFont(new Font("", Font.PLAIN, 6));
  // button.add(BorderLayout.SOUTH, label2).setFont(new Font("", Font.PLAIN, 6));
  // return button;
  // }
}
