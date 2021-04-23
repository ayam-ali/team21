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
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class RimplexJMenuBar extends JMenuBar implements ActionListener
{

  // private JMenuBar menu;
  private JMenu file, settings, languages, help;
  private JMenuItem about, download, print, english, french, spanish;

  String aboutInfo = "<html><i>Rimplex calculator for educational organizations <br>" + "<br>"
      + "<html><i>Version: 2021(3.0)<br>" + "<html><i>Build id: 56739734<br>"
      + "<html><i>(c) Copyright Rimplex contributors and others 2021.  All rights reserved.<br>"
      + "<html><i>This calculator allows the user to work with real numbers, imaginary numbers, and complex numbers."
      + "This calculator can only be distributed among educational organizations."
      + "The Rimplex logo cannot be altered without Rimplexs permission. <br>";

  RimplexJMenuBar(final ResourceBundle strings)
  {
    super();
    createJMenuBar(strings);
  }

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
    else if (e.getActionCommand().equals("About"))
    {
      JFrame a = new JFrame("About Rimplex");
      a.setPreferredSize(new Dimension(500, 300));
      JLabel text = new JLabel(aboutInfo);
      a.getContentPane().add(text);
      a.pack();
      a.setVisible(true);
    }
  }

  /**
   * 
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

    help.add(about);

    about.addActionListener(this);
    english.addActionListener(this);
    spanish.addActionListener(this);
    french.addActionListener(this);

    this.add(file);
    this.add(settings);
    this.add(help);
    setMenuTexts(strings);
  }

  private void setMenuTexts(final ResourceBundle strs)
  {
    about.setText(strs.getString("about"));
    about.setActionCommand("About");

    file.setText(strs.getString("file"));
    download.setText(strs.getString("download"));
    print.setText(strs.getString("print"));
    settings.setText(strs.getString("setting"));
    languages.setText(strs.getString("languages"));
    english.setText(strs.getString("english"));
    spanish.setText(strs.getString("spanish"));
    french.setText(strs.getString("french"));
    help.setText(strs.getString("help"));

  }
}
