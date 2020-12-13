import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DeadlinesWindow {
    public DeadlinesWindow (Controller controller, String type){
        Stage primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        VBox vBoxGetInformation = new VBox(10);
        vBoxGetInformation.setAlignment(Pos.CENTER);
        vBoxGetInformation.setPadding(new Insets(10, 25, 10, 25));
        gridPane.add(vBoxGetInformation, 0, 0);

        VBox vBoxInformation = new VBox(10);
        vBoxInformation.setAlignment(Pos.CENTER);
        vBoxInformation.setPadding(new Insets(10, 25, 10, 25));
        gridPane.add(vBoxInformation, 0, 1);

        Label registrationDate = new Label("Выберите дату:");
        vBoxGetInformation.getChildren().add(registrationDate);

        DatePicker registrationDateInput = new DatePicker(LocalDate.of(2020, 10, 28));
        vBoxGetInformation.getChildren().add(registrationDateInput);

        Button enterButton = new Button("Показать документы");
        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                vBoxInformation.getChildren().clear();
                if (type == "approaching"){
                    vBoxInformation.getChildren().add(controller.getDocumentsWithApproachingDeadline(registrationDateInput.getValue().toString()));
                }
                else if (type == "overdue"){
                    vBoxInformation.getChildren().add(controller.getDocumentsWithOverdueDeadline(registrationDateInput.getValue().toString()));
                }
            }
        });

        vBoxGetInformation.getChildren().add(enterButton);

        primaryStage.setTitle("Дедлайны");
        primaryStage.setScene(new Scene(gridPane, 400, 400));
        primaryStage.show();
    }
}
