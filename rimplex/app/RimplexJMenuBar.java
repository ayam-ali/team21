package app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class RimplexJMenuBar extends JMenuBar implements ActionListener
{

  // private JMenuBar menu;
  private JMenu file, settings, languages, help;
  private JMenuItem about, download, print, english, spanish;

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
    else if (e.getActionCommand().equals("English"))
    {
      setMenuTexts(ResourceBundle.getBundle("languages/Strings_en_US", Locale.US));
    }
    else if (e.getActionCommand().equals("About"))
    {
      JFrame a = new JFrame("About Rimplex");
      a.setPreferredSize(new Dimension(400, 300));
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

    languages.add(english);
    languages.add(spanish);
    settings.add(languages);

    // help menu
    help = new JMenu();
    about = new JMenuItem(); // about

    help.add(about);

    about.addActionListener(this);
    english.addActionListener(this);
    spanish.addActionListener(this);

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
    help.setText(strs.getString("help"));

  }
}
