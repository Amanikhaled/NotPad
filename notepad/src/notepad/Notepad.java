/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package notepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Amany Khaled
 */
public class Notepad extends Application {

    @Override
    public void start(Stage stage) {
//        Menu
        MenuBar bar = new MenuBar();
        Menu file = new Menu("file");
        Menu edit = new Menu("edit");
        Menu Help = new Menu("Help");

        MenuItem item1 = new MenuItem("New");
        MenuItem item2 = new MenuItem("Open");
        MenuItem item3 = new MenuItem("Save");
        MenuItem item4 = new MenuItem("Exit");

        MenuItem item5 = new MenuItem("Undo");
        MenuItem item6 = new MenuItem("Cut");
        MenuItem item7 = new MenuItem("Copy");
        MenuItem item8 = new MenuItem("Paste");
        MenuItem item9 = new MenuItem("Delete");
        MenuItem item10 = new MenuItem("Select All");

        MenuItem item11 = new MenuItem("About NotePad");

        file.getItems().addAll(item1, item2, item3, item4);
        edit.getItems().addAll(item5, item6, item7, item8, item9, item10);
        Help.getItems().addAll(item11);
        bar.getMenus().addAll(file, edit, Help);
        TextArea area = new TextArea();
        
        BorderPane p = new BorderPane();
//        Event

        item1.setOnAction(e -> {
            area.setText(" ");
        });
        item2.setOnAction(e -> {
                   FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Open Text File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File selectedFile = fileChooser.showOpenDialog(stage);

            

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile)))
            {
                String currentLine, content = "";
                while ((currentLine = reader.readLine()) != null) {
                    content += currentLine + "\n";
                }
                area.setText(content);
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });

        item3.setOnAction(e -> {
            FileChooser fileCho = new FileChooser();
            FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("tex files", "*.txt");
            File saveFile = fileCho.showSaveDialog(null);
            try {
                FileWriter fileWr = new FileWriter(saveFile);
                fileWr.write(area.getText());
                fileWr.close();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        item4.setOnAction(e -> {
            stage.close();
        });

        item5.setOnAction(e -> {
            area.undo();

        });

        item6.setOnAction(e -> {
            area.cut();
        });
        item7.setOnAction(e -> {
            area.copy();

        });

        item8.setOnAction(e -> {
            area.paste();

        });

        item9.setOnAction(e -> {

            area.setText(" ");
        });

        item10.setOnAction(e -> {
            area.selectAll();
        });

        item11.setOnAction(e -> {
            Dialog dialog = new Dialog();
            dialog.setTitle("ِAbout");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.getDialogPane().setContentText("Amany Khaled");

            dialog.show();
        });

//        filechooser
        FileChooser ch = new FileChooser();

        p.setTop(bar);
        p.setCenter(area);

        Scene scene = new Scene(p, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Amany NotePad");
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }

}
