import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize(){
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuScreen.fxml"));
        Pane pane = null;
        try {
            pane= fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController menuController = fxmlLoader.getController();
        menuController.setMainController(this);
        setScreen(pane);
    }

    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }
}