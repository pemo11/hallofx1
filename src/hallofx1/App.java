/**
 * Ein JavaFx-Beispiel für den Einstieg
 * Erstellt: 03/02/2022
 * 
 * Das kleine Beispiel hat mich noch etwas "Nerven" gekostet aufgrund der Pfadproblematik
 * Am Morgen des 04/02/2022 lief es aber gut und alle Bundespräsidenten werden schön angezeigt
 * Wichtig: In den Einstellungen muss noch eine Option gesetzt werden unter Java Debugger->Settings->VM Args
 * --module-path /Library/JavaFx/javafx-sdk-17.0.2/lib --add-modules=javafx.controls
 * Es wird keine Launch.json-Datei benötigt
 * 
 * Ideen für Erweiterugnen:
 * >Anzeigen der Amtszeit
 * >Geburtsjahr oder Lebensdauer
 * >Anzeigen von Geburtsort
 * >Alles soll in der Ich-Form angezeigt werden
 * >Ein hervorragendes Beispiel für mein Trainingsbuch - Java 3 - hier geht um Datenbanken und JavaFx
 */
package hallofx1;

import java.util.Hashtable;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    String imgPfad = "";
    String imgName = "";
    String lastName = "";
    ImageView imageView;
    Button btn1;
    Button btn2;
    Label label1;
    static Hashtable<String, String> imgDic = new Hashtable<>();

    public static void main(String[] args) {
        imgDic.put("Walter Scheel", "WalterScheel.png");
        imgDic.put("Gustav Heinemann", "GustavHeinemann.png");
        imgDic.put("Johannes Rau", "JohannesRau.png");
        imgDic.put("Roman Herzog", "RomanHerzog.png");
        imgDic.put("Christian Wulf", "ChristianWulf.png");
        imgDic.put("Frank Walter Steinmeier", "FrankWalterSteinmeier.png");
        imgDic.put("Richard von Weizsäcker", "RichardVonWeizsäcker.png");
        imgDic.put("Karl Carstens", "KarlCarstens.png");
        imgDic.put("Theodor Heuss", "TheodorHeuss.png");
        imgDic.put("Walter Lübke", "WalterLübke.png");
        launch(args);
    }

    private void showPresi() {
        // imgPfad = "file:/images/WalterScheel.png";
        try {
            do {
                int z = (int)Math.floor(Math.random() * imgDic.values().size());
                // Eventuell etwas umständlich?
                imgName = imgDic.keySet().toArray()[z].toString();
            }  while(imgName == lastName);
            // Derselbe Name soll nicht 2x nacheinander kommen
            // Für ein späteres Erklärvideo bzw. meinen Java-Kurs: == oder != macht hier ein großen Unterschied;)
            lastName = imgName;
            // Wichtig: resources ist ein Verzeichnis direkt im bin-Verzeichnis (das Unterverzeichnis images nicht vergessen;)
            // Es kann sein, dass der resources-Ordner im bin-Verzeichnis gelöscht wird
            // Ansonsten droht eine ganz üble NullPointerException;)
            imgPfad = getClass().getResource("/resources/images/" + imgName.replace(" ", "") + ".png").toURI().toString();
            // Geht nur mit einem "richtigen" Pfad - generell sollte es auch ohne resources gehen, aber mit ist wahrscheinlich der
            // bessere Weg, vor allem in Hinblick auf eine spätere Ausführung im Rahmen einer Dmg unter MacOs
            // InputStream stream = new FileInputStream(imgPfad);
            Image image = new Image(imgPfad);
            imageView.setImage(image);
            label1.setText("");
        } catch (Exception e1) {
            System.out.println("!!! Fehler: " + e1.toString());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hallo Fx!");
        btn1 = new Button();
        btn1.setMinWidth(120);
        btn1.setText("Wer bin ich?");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPresi();
            }
        });
        btn2 = new Button();
        btn2.setMinWidth(120);
        btn2.setText("Antwort");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            label1.setText(String.format("Das ist %s.", imgName));
            }
        });
        
        StackPane root = new StackPane();
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.BASELINE_CENTER);
        label1 = new Label();
        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        vbox1.getChildren().addAll(btn1, imageView, btn2, label1);
        VBox.setMargin(imageView, new Insets(5,5,10,10));
        VBox.setMargin(btn1, new Insets(5,5,10,10));
        VBox.setMargin(btn2, new Insets(5,5,10,10));
        VBox.setMargin(label1, new Insets(5,5,10,10));
        root.getChildren().add(vbox1);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
        showPresi();
    }

}
