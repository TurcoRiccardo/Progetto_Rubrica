package src;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Image;


public class Utility {

    public static ImageIcon caricaIcona(String path, int width, int height) {
        File f = new File(path);
        if (f.exists()) {
            Image img = new ImageIcon(path).getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } else {
            return (ImageIcon) UIManager.getIcon("FileView.fileIcon");
        }
    }

}
