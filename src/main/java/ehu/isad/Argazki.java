package ehu.isad;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Argazki {
    String izena;
    String irudia;

    public Argazki(String izena, String irudia) {
        this.izena=izena;
        this.irudia=irudia;

    }

    @Override
    public String toString() {
        return izena;
    }

    public String getFitx(){
        return irudia;
    }
}
