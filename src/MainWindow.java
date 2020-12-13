import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private Controller controller = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Button signInAsDirectorButton = new Button("  Войти как директор  ");
        signInAsDirectorButton.setOnAction(ActionEvent -> new AutorizationWindow(controller, "директор"));
        Button signInAsSecretaryButton = new Button(" Войти как секретарь ");
        signInAsSecretaryButton.setOnAction(ActionEvent -> new AutorizationWindow(controller, "секретарь"));

        gridPane.add(signInAsDirectorButton, 0, 0);
        gridPane.add(signInAsSecretaryButton, 0, 1);

        primaryStage.setTitle("Авторизация");
        primaryStage.setScene(new Scene(gridPane, 400, 300));
        primaryStage.show();
    }
}
