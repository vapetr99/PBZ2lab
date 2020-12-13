import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AutorizationWindow {
    public AutorizationWindow(Controller controller, String person) {
        Stage primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label surname = new Label("Фамилия:");
        gridPane.add(surname, 0, 0);

        TextField surnameTextField = new TextField();
        gridPane.add(surnameTextField, 1, 0);

        Label pass = new Label("Пароль:");
        gridPane.add(pass, 0, 1);

        TextField passwordTextField = new TextField();
        gridPane.add(passwordTextField, 1, 1);

        Button signInButton = new Button("              Войти                ");
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (surnameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
                    Alert message = new Alert(Alert.AlertType.WARNING);
                    message.setTitle("Предупреждение");
                    message.setContentText("Некоторые поля пустые!");
                    message.showAndWait();
                    return;
                }
                else if (controller.checkPerson(surnameTextField.getText(), passwordTextField.getText(), person)){
                    new ActionsWithDocumentsWindow(primaryStage, controller, person);
                }
                else {
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Ошибка");
                    message.setContentText("Такой человек не найден!");
                    message.showAndWait();
                    return;
                }

                if (controller.checkDate() && person == "секретарь "){
                    Alert message = new Alert(Alert.AlertType.WARNING);
                    message.setTitle("Предупреждение");
                    message.setContentText("У некоторых документов дедлайн через 2 дня!");
                    message.showAndWait();
                    return;
                }
            }
        });

        gridPane.add(signInButton, 1, 3);

        primaryStage.setTitle("Авторизация");
        primaryStage.setScene(new Scene(gridPane, 400, 300));
        primaryStage.show();
    }
}

