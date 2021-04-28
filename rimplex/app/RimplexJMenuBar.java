package app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Creates the JMenuBar for the Rimplex application.
 * 
 * @author Eric Hernandez-Diaz, Ayam Ali, Rhea Morris
 * @version 04/28/2021
 */
public class RimplexJMenuBar extends JMenuBar implements ActionListener
{

  private static final long serialVersionUID = 1L;
  private static String en = "languages/Strings_en_US";
  // private JMenuBar menu;
  private JMenu file, settings, languages, help;
  private JMenuItem about, print, english, french, spanish;

//  private String hp = "hp";

  private String aboutInfoEng = "<html><i>Rimplex calculator for educational organizations <br> "
      + "<br><html><i>Version: 2021(3.0)<br>" + "<html><i>Build id: 56739734<br>"
      + "<html><i>(c) Copyright Rimplex contributors and others 2021.  All rights reserved.<br>"
      + "<html><i>This calculator allows the user to work with real numbers, imaginary numbers, "
      + "and complex numbers."
      + "This calculator can only be distributed among educational organizations."
      + "The Rimplex logo cannot be altered without Rimplexs permission. <br>";

  private String aboutInfoFre = "<html> <i> Calculateur Rimplex pour les "
      + "\u00E9tablissements d'enseignement <br> " + "<br> <html> <i> Version: 2021 (3.0) "
      + "<br> <html> ID de build: 56739734 "
      + "<br> <html> <i> (c) Copyright contributeurs Rimplex et autres 2021. "
      + "Tous droits r\u00E9serv\u00E9s. "
      + "<br> <html> <i> Cette calculatrice permet \u00E0 l'utilisateur de travailler avec des "
      + "nombres r\u00E9els, des nombres imaginaires et des nombres complexes."
      + " Cette calculatrice ne peut \u00EAtre distribu\u00E9e que parmi organisations "
      + "\u00E9ducatives. Le logo Rimplex ne peut pas \u00EAtre modifi\u00E9 "
      + "sans l'autorisation de Rimplexs. <br> ";

  private String aboutInfoSpa = "<html> <i> Calculadora Rimplex para organizaciones "
      + "educativas <br> <br> <html> <i> Versi\u00F3n: 2021 (3.0) "
      + "<br> <html> <i> Id. de compilaci\u00F3n: 56739734 "
      + "<br> <html> <i> (c) Copyright Rimplex colaboradores y otros 2021. "
      + "Todos los derechos reservados. <br> "
      + "<html> <i> Esta calculadora permite al usuario trabajar con n\u00FAmeros reales,"
      + " n\u00FAmeros imaginarios y n\u00FAmeros complejos. "
      + "Esta calculadora solo se puede distribuir entre organizaciones educativas. "
      + "El logotipo de Rimplex no se puede modificar sin el permiso de Rimplexs. <br> ";

  /**
   * Constructor for this RimplexJMenuBar.
   * 
   * @param strings
   *          bundle that will be used for translations
   */
  private RimplexJMenuBar(final ResourceBundle strings)
  {
    super();
    layoutJMenuBar(strings);
  }

  /**
   * Reactions for menu action events.
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getActionCommand().equals("Espa\u00F1ol"))
    {
      setMenuTexts(ResourceBundle.getBundle("languages/Strings_es_SP", new Locale("es")));
    }
    else if (e.getActionCommand().equals("Fran\u00E7ais"))
    {
      setMenuTexts(ResourceBundle.getBundle("languages/Strings_fr_FR", new Locale("fr")));
    }
    else if (e.getActionCommand().equals("English"))
    {
      setMenuTexts(ResourceBundle.getBundle(en, Locale.US));
    }
    // else if (e.getActionCommand().equals(hp))
    // {
    // try
    // {
    // loadHelpPage();
    // }
    // catch (URISyntaxException | IOException e1)
    // {
    // e1.printStackTrace();
    // }
    // }
    else if (e.getActionCommand().equals("\u00C0 Propos"))
    {
      aboutPage("\u00C0 Propos De Rimplex", aboutInfoFre);
    }
    else if (e.getActionCommand().equals("About"))
    {
      aboutPage("About Rimplex", aboutInfoEng);

    }
    else if (e.getActionCommand().equals("Acerca De"))
    {
      aboutPage("Sobre Rimplex", aboutInfoSpa);

    }

  }

  /**
   * To add the info for the about page.
   * 
   * @param abt
   *          for the title of the JFrame
   * @param str
   *          for the translated information
   */
  private void aboutPage(final String abt, final String str)
  {
    JFrame a = new JFrame(abt);
    a.setPreferredSize(new Dimension(500, 300));
    JLabel text = new JLabel(str);
    a.getContentPane().add(text);
    a.pack();
    a.setVisible(true);
  }

  /**
   * @return an instance of RimplexJMenuBar.
   */
  public static RimplexJMenuBar createJMenuBar()
  {
    RimplexJMenuBar rimplex = new RimplexJMenuBar(ResourceBundle.getBundle(en, Locale.US));
    return rimplex;
  }

  /**
   * Creates the layout for the RimplexJMenuBar and its menus/menuitems.
   * 
   * @param strings
   *          the bundle of strings that will be used to set the text of all menus and menu items
   *          and translate them to other languages when appropriate.
   */
  void layoutJMenuBar(final ResourceBundle strings)
  {

    // file menu
    file = new JMenu();

    // Print
    print = new JMenuItem();
    print.addActionListener(new HistoryHandler());

    file.add(print);

    // setting menu
    settings = new JMenu();
    languages = new JMenu();
    english = new JMenuItem();
    spanish = new JMenuItem();
    french = new JMenuItem();

    languages.add(english);
    languages.add(spanish);
    languages.add(french);
    settings.add(languages);

    // help menu
    help = new JMenu();
    about = new JMenuItem(); // about
    // helpPage = new JMenuItem();
    // helpPage.setActionCommand(hp);

    help.add(about);
    // help.add(helpPage);

    about.addActionListener(this);
    english.addActionListener(this);
    // helpPage.addActionListener(this);
    spanish.addActionListener(this);
    french.addActionListener(this);

    this.add(file);
    this.add(settings);
    this.add(help);
    setMenuTexts(strings);

  }

//  /**
//   * Loads the help page via a web browser on the user's device.
//   * 
//   * @throws URISyntaxException
//   * @throws IOException
//   */
//  private void loadHelpPage() throws URISyntaxException, IOException
//  {
//    Desktop desktop = Desktop.getDesktop();
//    URL url = this.getClass().getResource("/helpPage/helpPage.html");
//    desktop.browse(url.toURI());
//  }

  /**
   * Sets the appropriate text for all menu's.
   * 
   * @param strs
   *          the bundle that will be used to find the texts for all menu's.
   */
  private void setMenuTexts(final ResourceBundle strs)
  {
    about.setText(strs.getString("about"));
    file.setText(strs.getString("file"));
    print.setText(strs.getString("print"));
    settings.setText(strs.getString("setting"));
    languages.setText(strs.getString("languages"));
    english.setText(strs.getString("english"));
    spanish.setText(strs.getString("spanish"));
    french.setText(strs.getString("french"));
    help.setText(strs.getString("help"));
    // helpPage.setText(strs.getString("help_page"));
  }
}
