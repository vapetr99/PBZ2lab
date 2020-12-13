import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class AddAndEditExecutorsAndExecutionDateWindow {
    public AddAndEditExecutorsAndExecutionDateWindow(Controller controller, String action){
        Stage primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10, 25, 10, 25));

        String selectDocumentQuery = "";

        if (action == "edit"){
            selectDocumentQuery = "select document_id, document_name, document_type, task_name, employee_id," +
                    " creation_date, registration_date from documents where employee_id <> '' and registration_date <> ''";

            Label allDocuments = new Label("Все документы, в которых указаны исполнитель и дедлайн");
            gridPane.add(allDocuments, 0, 0);

            primaryStage.setTitle("Редактирование");
        }
        else if (action == "add"){
            selectDocumentQuery = "select document_id, document_name, document_type, " +
                    "task_name, employee_id, creation_date, registration_date from documents " +
                    "where employee_id = '' or registration_date = ''";

            Label allDocuments = new Label("Все документы, в которых НЕ указаны исполнитель и дедлайн");
            gridPane.add(allDocuments, 0, 0);

            primaryStage.setTitle("Добавление");
        }

        gridPane.add(controller.getAllDocuments(selectDocumentQuery), 0, 1);

        VBox vBoxId = new VBox(10);
        vBoxId.setAlignment(Pos.CENTER);
        vBoxId.setPadding(new Insets(10, 25, 10, 25));
        gridPane.add(vBoxId, 0, 4);

        Label id = new Label("Введите id документа, который Вы хотите отредактировать:");
        vBoxId.getChildren().add(id);

        TextField idTextField = new TextField();
        vBoxId.getChildren().add(idTextField);

        Label allExecutors = new Label("Все исполнители");
        gridPane.add(allExecutors, 0, 5);

        gridPane.add(controller.getAllExecutors(), 0, 6);

        VBox vBoxInformation = new VBox(10);
        vBoxInformation.setAlignment(Pos.CENTER);
        vBoxInformation.setPadding(new Insets(10, 25, 10, 25));
        gridPane.add(vBoxInformation, 0, 7);

        Label newId = new Label("Введите новый id исполнителя:");
        vBoxInformation.getChildren().add(newId);

        TextField newIdTextField = new TextField();
        vBoxInformation.getChildren().add(newIdTextField);

        Label newRegistrationDate = new Label("Введите новый дедлайн:");
        vBoxInformation.getChildren().add(newRegistrationDate);

        DatePicker newRegistrationDateInput = new DatePicker(LocalDate.of(2020, 10, 28));
        vBoxInformation.getChildren().add(newRegistrationDateInput);

        Button enterButton = new Button("Ок");
        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (newIdTextField.getText().isEmpty() || idTextField.getText().isEmpty()) {
                    Alert message = new Alert(Alert.AlertType.WARNING);
                    message.setTitle("Предупреждение");
                    message.setContentText("Некоторые поля пустые!");
                    message.showAndWait();
                    return;
                }
                if (Integer.valueOf(newIdTextField.getText()) <= 7 && Integer.valueOf(newIdTextField.getText()) >= 3){
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Ошибка");
                    message.setContentText("Исполнителя с таким id не существует!");
                    message.showAndWait();
                    return;
                }
                if (controller.checkDocument(idTextField.getText())) {
                    controller.updateExecutorSurnameAndExecutionDateSurname(idTextField.getText(),
                            newIdTextField.getText(), newRegistrationDateInput.getValue().toString());
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("Информация");
                    message.setContentText("Информация обновлена!");
                    message.showAndWait();
                    primaryStage.close();
                    return;
                }
                else{
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Ошибка");
                    message.setContentText("Документ не найден!");
                    message.showAndWait();
                    return;
                }
            }
        });
        vBoxInformation.getChildren().add(enterButton);
        primaryStage.setScene(new Scene(gridPane, 700, 700));
        primaryStage.show();
    }
}