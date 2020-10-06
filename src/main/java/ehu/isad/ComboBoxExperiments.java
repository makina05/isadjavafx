package ehu.isad;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboBoxExperiments extends Application {

    private Label label=new Label();
    private String location;
    private ImageIcon imageView;

    public ComboBoxExperiments() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ComboBox Experiment 1");

        /*ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("BTC");
        comboBox.getItems().add("ETH");
        comboBox.getItems().add("LTC");


        comboBox.setEditable(true);

        comboBox.setOnAction(e -> {
            HBox row = new HBox();
            row.setAlignment(Pos.BASELINE_CENTER);
            // row.setPadding(new Insets(50,0,0,0));
            String a = comboBox.getValue().toString() ;
            Txanpona b=null;
            try {
                b = readFromUrl(a.toLowerCase());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            label.setText("1 " + a + " = " + b.price + " euro");

        }); */ //txanpon bihurketa egiteko.


        ComboBox comboBilduma = new ComboBox();
        List<String> bildumak =
                List.of("abereak","landareak","frutak");
        ObservableList<String> bildumaList =
                FXCollections.observableArrayList(bildumak);
        comboBilduma.setItems(bildumaList);

        Map<String, List<Argazki>> bildumaMap = new HashMap<>();

        bildumaMap.put("abereak",List.of(
                new Argazki("Elefantea", "elefantea.jpeg"),
                new Argazki("Txakurra", "txakurra.jpeg"),
                new Argazki("Untxia", "untxia.png")
        ));

        bildumaMap.put("landareak",List.of(
                new Argazki("Kaktusa", "cactus.png"),
                new Argazki("Landare Berdea", "landareberdea.jpeg"),
                new Argazki("Landare Horia", "landarehoria.jpeg")
        ));

        bildumaMap.put("frutak",List.of(
                new Argazki("Marrubia", "fresa.jpeg"),
                new Argazki("Sagarra", "sagarra.jpeg"),
                new Argazki("Sandia", "sandia.png")
        ));

        ObservableList<Argazki> argazkiList =
                FXCollections.observableArrayList();
        argazkiList.addAll(bildumaMap.get("abereak"));
        argazkiList.addAll(bildumaMap.get("landareak"));
        argazkiList.addAll(bildumaMap.get("frutak"));



        ListView<Argazki> listViewOfArgazki = new ListView<>(argazkiList);

        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener(  (observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return;

            String fitx = observable.getValue().getFitx();

            try {
                imageView.setImage(lortuIrudia(fitx /* 48x48 */));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        //HBox hbox = new HBox(listViewOfArgazki);



/*
        InputStream is = getClass().getResourceAsStream("/cactus.png");
        BufferedImage reader = ImageIO.read(is);
        Image image = SwingFXUtils.toFXImage(reader,null);
        ImageView imageView = new ImageView(image); */

        HBox hbox = new HBox(label, comboBilduma,listViewOfArgazki);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setPadding(new Insets(10,0,0,0));
        Scene scene = new Scene(hbox, 400, 120);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private Image lortuIrudia(String fitx) {
        return this.lortuIrudia(fitx);

    }

    /*private Image lortuIrudia(String fitx) throws IOException {
        BufferedImage image = ImageIO.read(getClass().getResource("/resources/"+fitx));
        ImageIcon icon = new ImageIcon(image);
        return icon; }*/

/*
    public static Txanpona readFromUrl(String txanpon) throws IOException {

        String inputLine;

        URL coinmarket = new URL("https://api.gdax.com/products/"
                + txanpon + "-eur/ticker");
        URLConnection yc = coinmarket.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        inputLine = in.readLine();
        in.close();

        Gson gson = new Gson();
        return gson.fromJson(inputLine, Txanpona.class);

    } */

    public static void main(String[] args) {
        Application.launch(args);
    }
}