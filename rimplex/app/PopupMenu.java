package app;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 * PopupMenu.java - Gives copy and paste functionality to history window.
 *
 * Modifications: None
 *
 * @author Eric Anderson
 * @version Apr 27, 2021
 */
public class PopupMenu
{
  /**
   * Allows copy and paste to work.
   * 
   * @param historyOutputArea
   *          to copy from
   */
  public static void addTo(final JTextArea historyOutputArea)
  {

    ResourceBundle strings = ResourceBundle.getBundle("languages/Strings_en_US", Locale.US);

    RimplexWindow.updateHistory();
    JPopupMenu popup = new JPopupMenu();

    Action copyAction = new AbstractAction(strings.getString("copy"))
    {
      private static final long serialVersionUID = 834850189170044294L;

      @Override
      public void actionPerformed(final ActionEvent ae)
      {
        historyOutputArea.copy();
      }
    };

    Action pasteAction = new AbstractAction(strings.getString("paste"))
    {
      private static final long serialVersionUID = 4292559549538669190L;

      @Override
      public void actionPerformed(final ActionEvent ae)
      {
        if (historyOutputArea != null)
        {
          historyOutputArea.paste();
          try
          {
            EventHandler.addToCalculations((String) Toolkit.getDefaultToolkit().getSystemClipboard()
                .getData(DataFlavor.stringFlavor));
          }
          catch (HeadlessException e)
          {
            e.printStackTrace();
          }
          catch (UnsupportedFlavorException e)
          {
            e.printStackTrace();
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
        }
      }
    };
    copyAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
    pasteAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));

    popup.add(copyAction);
    popup.addSeparator();
    popup.add(pasteAction);

    historyOutputArea.setComponentPopupMenu(popup);
  }

  // public static void setLanguage(ResourceBundle strs)
  // {
  // ((JTextComponent) copyAction).setText(strs.getString("copy"));
  // ((JTextComponent) pasteAction).setText(strs.getString("paste"));
  // }

}
