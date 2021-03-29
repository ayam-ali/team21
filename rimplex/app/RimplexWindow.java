package app;

import java.awt.FlowLayout;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * GUI - Creates objects for displays and input fields.
 * 
 * Modifications: - Anderson (3/25) - changed input field to JTextField to prevent \n chars. Added
 * string formatter to remove \n for redundancy. Ali (3/28) - Changed symbols, changed window size,
 * cleared up, and added javadoc comments.
 * 
 * @author Eric Anderson,
 * @version 3/25/2021
 */
public class RimplexWindow extends JFrame
{

  static JLabel display;
  static JTextField inputField;
  static ArrayList<String> expression;

  private static final long serialVersionUID = 1L;
  private ButtonHandler buttonHandler;
  private JPanel buttonPanel;

  /**
   * The constructor for the rimplex window.
   * 
   * @param buttonHandler
   *          to deal with the buttons
   */
  public RimplexWindow(final ButtonHandler buttonHandler)
  {
    super("Rimplex");
    this.buttonHandler = buttonHandler;
    this.buttonPanel = createButtonPanel();
    createDisplay();
    createExpression();
    createInputField();

    makeLayout();
    setSize(400, 250);
  }

  /**
   * Adds the buttons to the panel and provides the action listener.
   * 
   * @param name
   *          for what is going to be on the button
   */
  private void addButton(final String name)
  {
    JButton button = new JButton(name);
    button.addActionListener(buttonHandler);
    buttonPanel.add(button);
  }

  /**
   * Creates the button panel for what is to be layout.
   * 
   * @return a JPanel for the panel
   */
  private JPanel createButtonPanel()
  {
    JPanel result = new JPanel();
    result.setLayout(new FlowLayout());
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
  }

  /**
   * Creates the array list for the expressions.
   */
  private static void createExpression()
  {
    expression = new ArrayList<>();
  }

  /**
   * Creates an input field for the user to input numbers.
   */
  private static void createInputField()
  {
    inputField = new JTextField();
    inputField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
  }

  /**
   * Creates the layout and sets the buttons.
   */
  private void makeLayout()
  {
    this.setLayout(new GridLayout(3, 1));
    this.add(display);
    this.add(inputField);

    // create buttons
    addButton("Clear");
    addButton("Reset");
    addButton("-");
    addButton("+");
    addButton("×");
    addButton("÷");
    addButton("=");

    this.add(buttonPanel);
  }

  /*
   * static ArrayList<String> expression = new ArrayList<String>(); static JLabel display = new
   * JLabel(); static JTextField inputField = new JTextField();
   * 
   * public static void main(final String[] args) { JFrame main; JPanel buttonPanel;
   * 
   * main = new JFrame("Rimplex"); main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   * main.setLayout(new GridLayout(3, 1)); main.setSize(400, 200);
   * main.setIconImage(Toolkit.getDefaultToolkit().getImage("..\\iconRimplex.png"));
   * 
   * buttonPanel = GUIUtils.createButtons();
   * 
   * Font f = display.getFont(); display.setFont(f.deriveFont(Font.PLAIN));
   * 
   * display.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED)); // temporary border
   * inputField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED)); // temporary
   * border
   * 
   * display.setText("<html>"); main.add(display); main.add(inputField); main.add(buttonPanel);
   * 
   * main.setVisible(true); }
   */
}
