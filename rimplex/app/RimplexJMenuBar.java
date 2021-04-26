package app;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
 * @author Eric Hernandez-Diaz, Ayam Ali
 * @version 04/28/2021
 */
public class RimplexJMenuBar extends JMenuBar implements ActionListener
{

  // private JMenuBar menu;
  private JMenu file, settings, languages, help;
  private JMenuItem about, download, print, english, french, helpPage, spanish;

  String aboutInfoEng = "<html><i>Rimplex calculator for educational organizations <br>" + "<br>"
      + "<html><i>Version: 2021(3.0)<br>" + "<html><i>Build id: 56739734<br>"
      + "<html><i>(c) Copyright Rimplex contributors and others 2021.  All rights reserved.<br>"
      + "<html><i>This calculator allows the user to work with real numbers, imaginary numbers, and complex numbers."
      + "This calculator can only be distributed among educational organizations."
      + "The Rimplex logo cannot be altered without Rimplexs permission. <br>";

  String aboutInfoFre = "<html><i>Calculateur Rimplex pour les organisations éducatives <br>"
      + "<br>" + "<html><i>Version: 2021(3.0)<br>" + "<html><i>ID de construction: 56739734<br>"
      + "<html><i>(c) Copyright contributeurs Rimplex et autres 2021. Tous droits réservés.<br>"
      + "<html><i>Cette calculatrice permet à l'utilisateur de travailler avec des nombres réels, des nombres imaginaires et des nombres complexes."
      + "Cette calculatrice ne peut être distribuée que parmi les organisations éducatives."
      + "Le logo Rimplex ne peut pas être modifié sans l'autorisation de Rimplexs. <br>";

  String aboutInfoSpa = "<html><i>Calculadora Rimplex para organizaciones educativas <br>" + "<br>"
      + "<html><i>Versión:2021(3.0)<br>" + "<html><i>ID de compilación: 56739734<br>"
      + "<html><i>(c) Copyright de los colaboradores de Rimplex y otros 2021. Todos los derechos reservados.<br>"
      + "<html><i>Esta calculadora permite al usuario trabajar con números reales, números imaginarios y números complejos."
      + "Esta calculadora solo se puede distribuir entre organizaciones educativas."
      + "El logotipo de Rimplex no se puede modificar sin el permiso de Rimplex.<br>";

  /**
   * Constructor for this RimplexJMenuBar.
   * 
   * @param strings
   *          bundle that will be used for translations
   */
  RimplexJMenuBar(final ResourceBundle strings)
  {
    super();
    createJMenuBar(strings);
  }

  /**
   * Reactions for menu action events.
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getActionCommand().equals("Español"))
    {
      setMenuTexts(ResourceBundle.getBundle("languages/Strings_es_SP", new Locale("es")));
    }
    else if (e.getActionCommand().equals("Français"))
    {
      setMenuTexts(ResourceBundle.getBundle("languages/Strings_fr_FR", new Locale("fr")));
    }
    else if (e.getActionCommand().equals("English"))
    {
      setMenuTexts(ResourceBundle.getBundle("languages/Strings_en_US", Locale.US));
    }
    else if (e.getActionCommand().equals("hp"))
    {
      try
      {
        loadHelpPage();
      }
      catch (URISyntaxException | IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    else if (e.getActionCommand().equals("À Propos"))
    {
      aboutPage("À Propos De Rimplex", aboutInfoFre);
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
   * To add the info for the about page
   * 
   * @param about
   *          for the title of the JFrame
   * @param str
   *          for the translated information
   */
  private void aboutPage(final String about, final String str)
  {
    JFrame a = new JFrame(about);
    a.setPreferredSize(new Dimension(500, 300));
    JLabel text = new JLabel(str);
    a.getContentPane().add(text);
    a.pack();
    a.setVisible(true);
  }

  /**
   * Creates the layout for the RimplexJMenuBar and its menus/menuitems.
   * 
   * @param strings
   *          the bundle of strings that will be used to set the text of all menus and menu items
   *          and translate them to other languages when appropriate.
   */
  void createJMenuBar(final ResourceBundle strings)
  {

    // file menu
    file = new JMenu();
    download = new JMenuItem();
    print = new JMenuItem();

    file.add(download);
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
    helpPage = new JMenuItem();
    helpPage.setActionCommand("hp");

    help.add(about);
    help.add(helpPage);

    about.addActionListener(this);
    english.addActionListener(this);
    helpPage.addActionListener(this);
    spanish.addActionListener(this);
    french.addActionListener(this);

    this.add(file);
    this.add(settings);
    this.add(help);
    setMenuTexts(strings);
   
  }

  /**
   * Loads the help page via a web browser on the user's device.
   * 
   * @throws URISyntaxException
   * @throws IOException
   */
  private void loadHelpPage() throws URISyntaxException, IOException
  {
    Desktop desktop = Desktop.getDesktop();
    URI uri = null;
    uri = new URI("http://www.jmu.edu");
    desktop.browse(uri);
  }

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
    download.setText(strs.getString("download"));
    print.setText(strs.getString("print"));
    settings.setText(strs.getString("setting"));
    languages.setText(strs.getString("languages"));
    english.setText(strs.getString("english"));
    spanish.setText(strs.getString("spanish"));
    french.setText(strs.getString("french"));
    help.setText(strs.getString("help"));
    helpPage.setText(strs.getString("help_page"));
  }
}
