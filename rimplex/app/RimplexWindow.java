package app;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

/**
 * GUI - Creates objects for displays and input fields.
 * 
 * @author Eric Anderson, Eric Hernandez-Diaz, Ayam Ali
 * @version 3/25/2021
 */
public class RimplexWindow extends JFrame
{
  private static Timer timer;
  private static ArrayList<String> history;
  private static JScrollPane scrollList;
  private static JButton expand;
  private static JWindow historyWindow;
  private static JTextArea historyOutputArea;
  private static final int HISTORY_HEIGHT = 263;

  static JLabel display;
  static ArrayList<String> expression;
  private static final long serialVersionUID = 1L;
  private EventHandler eventHandler;
  private JPanel buttonPanel;
  private JButton contract;
  private JPanel historyPanel;

  private JButton inv;
  private JButton con; 
  private JButton fD;
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
    createHistory();
    setSize(400, 250);
  }

  /**
   * createHistory - creates the history jwindow.
   * 
   */
  private void createHistory()
  {

    // Windows and buttons ---
    historyWindow = new JWindow();
    historyWindow.setVisible(true);
    historyPanel = new JPanel();
    historyPanel.setLayout(new BorderLayout());
    contract = new JButton("<");
    contract.addActionListener(new HistoryHandler());

    // Text area and scroll
    history = new ArrayList<>();
    historyOutputArea = new JTextArea();
    scrollList = new JScrollPane(historyOutputArea);

    scrollList.setVisible(true);
    historyWindow.add(scrollList, BorderLayout.CENTER);
    historyWindow.add(contract, BorderLayout.EAST);

    // Default size: (294, ---)
  }

  /**
   * updateHistory - updates the history display with current expression list.
   */
  public static void updateHistory()
  {
    historyOutputArea.setText(null);
    if (history.size() != 0)
    {

      for (int i = 0; i < history.size(); i++)
      {
        String output = history.get(i);
        historyOutputArea.append(output + "\n");
      }
    }
  }

  /**
   * addToHistory - adds the given string to the history list.
   * 
   * 
   * @param result
   *          - the given expression.
   */
  public static void addToHistory(String result)
  {
    result = result.replaceAll("<html>", "");
    result = result.replaceAll("<i>i</i>", "i");
    history.add(result);
  }

  /**
   * Adds the buttons to the panel and provides the action listener.
   * 
   * @param name
   *          for what is going to be on the button
   */
  private JButton addButton(final String name, final int x, final int y, final int width,
      final int height)
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
    ResourceBundle strings = ResourceBundle.getBundle("languages/Strings_en_US", Locale.US);
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 2;
    gbc.gridwidth = 6;
    gbc.weightx = 1 / 12;
    gbc.weighty = 0;
    gbc.fill = GridBagConstraints.BOTH;

    JLabel logo = new JLabel();
    Image image = new ImageIcon(this.getClass().getResource("/icons/logoRimplex.png")).getImage();
    logo.setIcon(new ImageIcon(image));
    buttonPanel.add(logo, gbc);

    gbc.gridy = 0;
    gbc.gridy = 3;
    gbc.weighty = 1;
    buttonPanel.add(display, gbc);

    // create buttons and adds color
    // row 1
    addButton("\u00B1", 0, 6, 1, 1); // plus minus sign
    addButton("C", 1, 6, 1, 1);
    addButton("\u2190", 2, 6, 1, 1); // unicode for arrow
    addButton("+", 3, 6, 1, 1);
    addButton("R", 4, 6, 1, 1);

    // row 2
    addButton("1", 0, 7, 1, 1);
    addButton("2", 1, 7, 1, 1);
    addButton("3", 2, 7, 1, 1);
    addButton("-", 3, 7, 1, 1);
    addButton("Inv", 4, 7, 1, 1);

    // row 3
    addButton("4", 0, 8, 1, 1);
    addButton("5", 1, 8, 1, 1);
    addButton("6", 2, 8, 1, 1);
    addButton("\u00D7", 3, 8, 1, 1); // unicode for multiplication \u00D7
    addButton("(", 4, 8, 1, 1);
    // row 4
    addButton("7", 0, 9, 1, 1);
    addButton("8", 1, 9, 1, 1);
    addButton("9", 2, 9, 1, 1);
    addButton("\u00F7", 3, 9, 1, 1); // division sign
    addButton(")", 4, 9, 1, 1);

    // row 5
    addButton("0", 0, 10, 2, 1);
    addButton("\uD835\uDC8A", 2, 10, 1, 1); // math i sign
    addButton("=", 3, 10, 1, 1);
    addButton(".", 4, 10, 1, 1);

    // row 6
    addButton("\u221A", 5, 6, 1, 1); // unicode for square root is \u221A
    addButton(strings.getString("logarithm"), 5, 7, 1, 1);
    addButton("Frac/Dec", 5, 10, 1, 1);
    addButton("Con", 5, 8, 1, 1);
    addButton("x^y", 5, 9, 1, 1);

    this.add(buttonPanel, BorderLayout.CENTER);

    // History button ------
    expand = new JButton(">");
    expand.addActionListener(new HistoryHandler());
    this.add(expand, BorderLayout.EAST);
    this.createJMenuBar(ResourceBundle.getBundle("languages/Strings_en_US", Locale.US));
    
    
//    this.pack();
  }

  /**
   * animateHistory - smoothly animates history.
   * 
   * @param isOpening
   *          - true if the window is opening.
   */
  public static void animateHistory(final boolean isOpening)
  {

    int delay = 1; // milliseconds
    // Dynamic location setting ---
    historyWindow.setLocation((int) expand.getLocationOnScreen().getX() + 75,
        (int) expand.getLocationOnScreen().getY() + 1); // Set location right on screen
    if (isOpening) // OPENING
    {
      expand.setEnabled(false);
      historyWindow.setSize(0, HISTORY_HEIGHT);
      ActionListener taskPerformer = new ActionListener()
      {
        public void actionPerformed(final ActionEvent evt)
        {
          if (historyWindow.getWidth() >= 294)
          {
            timer.stop();
            historyWindow.setSize(294, HISTORY_HEIGHT);
          }
          historyWindow.setSize((int) (historyWindow.getWidth() + 12), HISTORY_HEIGHT);
        }
      };
      timer = new Timer(delay, taskPerformer);
      timer.start();
    }
    else // CLOSING
    {
      expand.setEnabled(true);
      historyWindow.setSize(294, HISTORY_HEIGHT);

      ActionListener taskPerformer = new ActionListener()
      {
        public void actionPerformed(ActionEvent evt)
        {
          if (historyWindow.getWidth() <= 0)
          {
            timer.stop();
            historyWindow.setSize(0, HISTORY_HEIGHT);
          }
          historyWindow.setSize((int) (historyWindow.getWidth() - 12), HISTORY_HEIGHT);
        }
      };
      timer = new Timer(delay, taskPerformer);
      timer.start();

    }

  }
  
  /**
   * 
   */
  private void createJMenuBar(ResourceBundle strings) {
    JMenuBar menu;
    JMenu file, settings, languages, help;
    JMenuItem download, print, english, spanish;
    
    menu = new JMenuBar();
    
    // file menu
    file = new JMenu(strings.getString("file"));
    download = new JMenuItem(strings.getString("download"));
    print = new JMenuItem(strings.getString("print"));
    
    file.add(download);
    file.add(print);
    
    // setting menu
    settings = new JMenu(strings.getString("setting"));
    languages = new JMenu(strings.getString("languages"));
    english = new JMenuItem(strings.getString("english"));
    spanish = new JMenuItem(strings.getString("spanish"));
    
    languages.add(english);
    languages.add(spanish);
    settings.add(languages);
    
    // help menu
    help = new JMenu(strings.getString("help"));

    menu.add(file);
    menu.add(settings);
    menu.add(help);
    this.setJMenuBar(menu);
  }
}
