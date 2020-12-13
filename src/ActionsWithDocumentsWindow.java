import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ActionsWithDocumentsWindow {
    public ActionsWithDocumentsWindow(Stage primaryStage, Controller controller, String person){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        if (person == "директор") {
            Button addButton = new Button("           Добавить исполнителя и дедлайн         ");
            addButton.setOnAction(ActionEvent -> new AddAndEditExecutorsAndExecutionDateWindow(controller, "add"));

            Button editButton = new Button("       Редактировать исполнителя и дедлайн     ");
            editButton.setOnAction(ActionEvent -> new AddAndEditExecutorsAndExecutionDateWindow(controller, "edit"));

            gridPane.add(addButton, 0, 0);
            gridPane.add(editButton, 0, 1);
        }
        else if (person == "секретарь"){
            Button registerDocumentButton = new Button("             Добавить документ             ");
            registerDocumentButton.setOnAction(ActionEvent -> new RegisterDocumentWindow(controller));
            Button approachingDeadlinesButton = new Button("     Приближающиеся дедлайны     ");
            approachingDeadlinesButton.setOnAction(ActionEvent -> new DeadlinesWindow(controller, "approaching"));
            Button overdueDeadlinesButton = new Button("        Просроченные дедлайны        ");
            overdueDeadlinesButton.setOnAction(ActionEvent -> new DeadlinesWindow(controller, "overdue"));

            gridPane.add(registerDocumentButton, 0, 0);
            gridPane.add(approachingDeadlinesButton, 0, 1);
            gridPane.add(overdueDeadlinesButton, 0, 2);
        }
        primaryStage.setTitle("Действия с документами");
        primaryStage.setScene(new Scene(gridPane, 400, 300));
        primaryStage.show();
    }
}
