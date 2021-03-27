package app;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * GUI - Creates objects for displays and input fields.
 * 
 * Modifications: - Anderson (3/25) - changed input field to JTextField to prevent \n chars. Added
 * string formatter to remove \n for redundancy.
 * 
 * @author Eric Anderson,
 * @version 3/25/2021
 */
public class GUI
{

  static ArrayList<String> expression = new ArrayList<String>();
  static JTextArea display = new JTextArea();
  static JTextField inputField = new JTextField();

  public static void main(final String[] args)
  {
    JFrame main;
    JPanel buttonPanel;

    main = new JFrame("Rimplex");
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setLayout(new GridLayout(3, 1));
    main.setSize(400, 200);

    buttonPanel = GUIUtils.createButtons();

    display.setEditable(false);
    display.setBorder(BorderFactory.createTitledBorder("Display")); // temporary border
    inputField.setBorder(BorderFactory.createTitledBorder("Input")); // temporary border

    main.add(display);
    main.add(inputField);
    main.add(buttonPanel);

    main.setVisible(true);
  }
}
