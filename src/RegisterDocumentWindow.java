import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class RegisterDocumentWindow {
    public RegisterDocumentWindow(Controller controller){
        Stage primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label correspondent = new Label("Корреспондент (название компании / id сотрудника)");
        gridPane.add(correspondent, 0, 0);

        TextField correspondentTextField = new TextField();
        gridPane.add(correspondentTextField, 0, 1);

        Label correspondents = new Label("Cотрудники");
        gridPane.add(correspondents, 0, 2);

        gridPane.add(controller.getAllEmployees(), 0, 3);

        Label documentName = new Label("Название документа:");
        gridPane.add(documentName, 0, 4);

        TextField documentNameTextField = new TextField();
        gridPane.add(documentNameTextField, 0, 5);

        Label type = new Label("Тип документа (Входящий документ/Исходящий документ/Внутренний документ):");
        gridPane.add(type, 0, 6);

        TextField typeTextField = new TextField();
        gridPane.add(typeTextField, 0, 7);

        Label taskName = new Label("Название задачи:");
        gridPane.add(taskName, 0, 8);

        TextField taskNameTextField = new TextField();
        gridPane.add(taskNameTextField, 0, 9);

        Label creationDate = new Label("Дата создания:");
        gridPane.add(creationDate, 0, 10);

        DatePicker creationDateInput = new DatePicker(LocalDate.of(2020, 10, 19));
        gridPane.add(creationDateInput, 0, 11);

        Button enterButton = new Button("                                                                                          Добавить                                                                                          ");
        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (correspondentTextField.getText().isEmpty() || documentNameTextField.getText().isEmpty() ||
                        typeTextField.getText().isEmpty() || taskNameTextField.getText().isEmpty()) {
                    Alert message = new Alert(Alert.AlertType.WARNING);
                    message.setTitle("Предупреждение");
                    message.setContentText("Некоторые поля пустые!");
                    message.showAndWait();
                    return;
                }
                if (!typeTextField.getText().equals("Входящий документ") && !typeTextField.getText().equals("Исходящий документ") &&
                        !typeTextField.getText().equals("Внутренний документ")) {
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Ошибка");
                    message.setContentText("Такого вида документа не существует!");
                    message.showAndWait();
                    return;
                }
                if (typeTextField.getText().equals("Входящий документ") || typeTextField.getText().equals("Исходящий документ")) {
                    controller.registerDocument(Integer.toString(controller.countId() + 1), documentNameTextField.getText(),
                            typeTextField.getText(), taskNameTextField.getText(), creationDateInput.getValue().toString());
                    controller.registerExternalCorrespondent(Integer.toString(controller.countExternalCorrespondent() + 1),
                            Integer.toString(controller.countId() + 1), correspondentTextField.getText());
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("Информация");
                    message.setContentText("Документ добавлен!");
                    message.showAndWait();
                    primaryStage.close();
                    return;
                }
                if (typeTextField.getText().equals("Внутренний документ") &&
                        (Integer.valueOf(correspondentTextField.getText()) <= 7 && Integer.valueOf(correspondentTextField.getText()) >= 2)){
                    controller.registerDocument(Integer.toString(controller.countId() + 1), documentNameTextField.getText(),
                            typeTextField.getText(), taskNameTextField.getText(), creationDateInput.getValue().toString());
                    controller.registerInternalCorrespondent(Integer.toString(controller.countInternalCorrespondent() + 1),
                            Integer.toString(controller.countId() + 1), correspondentTextField.getText());
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("Информация");
                    message.setContentText("Документ добавлен!");
                    message.showAndWait();
                    primaryStage.close();
                    return;
                }
                else{
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Ошибка");
                    message.setContentText("Сотрудника с таким id не существует!");
                    message.showAndWait();
                    return;
                }
            }
        });

        gridPane.add(enterButton, 0, 12);

        primaryStage.setTitle("Добавление документа");
        primaryStage.setScene(new Scene(gridPane, 800, 600));
        primaryStage.show();
    }
}
