package ehu.isad;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboBoxExperiments extends Application {

    private ComboBox comboBilduma = new ComboBox();
    private ImageView imageView = new ImageView();
    private ListView<Argazki> listViewOfArgazki;

    public ComboBoxExperiments() throws IOException {
    }


    private Image lortuIrudia(String location) throws IOException {
        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader,null);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ComboBox Experiment 1");


        List<String> bildumak =
                List.of("abereak","landareak","frutak");
        ObservableList<String> bildumaList =
                FXCollections.observableArrayList(bildumak);
        comboBilduma.setItems(bildumaList);
        comboBilduma.getSelectionModel().selectFirst();

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
        listViewOfArgazki = new ListView<>(argazkiList);

        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener(  (observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return;

            String fitx = observable.getValue().getFitx();

            try {
                imageView.setImage(lortuIrudia(fitx /* 48x48 */));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });



        comboBilduma.setOnAction(e -> {
            String non = (String)comboBilduma.getValue();

            argazkiList.clear();
            argazkiList.addAll(bildumaMap.get(non));

        });


        VBox vbox = new VBox(comboBilduma,listViewOfArgazki, imageView);

        Scene scene = new Scene(vbox, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}