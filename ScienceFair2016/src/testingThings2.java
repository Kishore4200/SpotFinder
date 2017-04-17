import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class testingThings2
{
    public static void main(String[] args)
    {
        final ImageIcon icon = new ImageIcon("C:\\Kishore\\Grade9(2015-2016)\\ScienceFair\\SpotsMaps\\spot1.jpg");
        JOptionPane.showMessageDialog(null, "Blah blah blah" + "\n" + "YAY", "About", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}