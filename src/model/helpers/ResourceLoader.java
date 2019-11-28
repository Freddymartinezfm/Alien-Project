package model.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class ResourceLoader {
    private static final String TAG = "BufferedImageLoader";
    private BufferedImage image;
    private int imgWidth = 0;
    private int imgHeight = 0;
    private Font font;

    public  BufferedImage loadImageFile(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        System.out.println(url.toString());
            if (url == null) {
                System.out.println("Error....with fileName");
                System.exit(1);
            } else {
                try {
                    image = ImageIO.read(url);
                } catch (IOException ex) {
                    System.out.println("Error....with URL");
                    System.exit(1);
                }
        }

        imgWidth = image.getWidth();
        imgHeight = image.getHeight();
        return image;
    }

    public void registerFont(String fontName) {
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("model/resources/font/Emulogic-zrEw.ttf");
            if (stream != null) {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(18f);
                ge.registerFont(customFont);
                font = customFont;
            }
        } catch (IOException | FontFormatException e) {
            System.exit(1);
        }
    }


    public void showFonts() throws IOException {
        String[] fontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < fontFamilyNames.length; i++) {
            System.out.println("[" + i +  "] " + fontFamilyNames[i]);
        }
    }
}
