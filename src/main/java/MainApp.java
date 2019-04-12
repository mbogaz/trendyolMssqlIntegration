import controller.RootPanelController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    RootPanelController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Trendyol Entegrasyon");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rootPanel.fxml"));
        GridPane root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setViews();
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

}
