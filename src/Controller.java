import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.*;

public class Controller {
    private static final String url = "jdbc:mysql://localhost:3306/tables?serverTimezone = PST";
    private static final String user = "root";
    private static final String password = "lera.uzuzer123";

    private static Connection connection;
    private static ResultSet checkResult;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    /////////////////////////Autorization///////////////////////////////////////
    public Boolean checkPerson(String surname, String pass, String person) {
        String selectQuery = "select surname, password from employees " +
                    "where employees.position = '" + person + "' and surname = '" + surname + "' and password = '" + pass + "'";

        Boolean check = false;
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                check = true;
            }
        }
        catch (SQLException sqlEx) {
        }
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }

        if (check){
            return true;
        }
        else {
            return false;
        }
    }

    /////////////////////////Register document//////////////////////////////////
    public int countId(){
        String selectQuery = "select * from documents";
        int count = 0;

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                count++;
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return count;
    }

    public VBox getAllEmployees(){
        String query = "select id, surname, position from employees where employees.status = 'исполнитель' or employees.status = 'контроллер'";
        VBox executors = new VBox();

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(query);
            while (checkResult.next()) {
                String resultId = checkResult.getString(1);
                String resultSurname = checkResult.getString(2);
                String resultPosition = checkResult.getString(3);
                Text result = new Text("id: " + resultId + ", исполнитель: " + resultSurname + ", должность: " + resultPosition + ";");
                executors.getChildren().add(result);
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return executors;
    }

    public void registerDocument(String id, String name, String type, String taskName, String creationDate) {
        String insertQuery = "insert into documents (id, name, type, taskName, registration_date, creation_date, employee_id) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, taskName);
            preparedStatement.setString(6, creationDate);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlEx) {
        }
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
    }

    public void registerExternalCorrespondent (String correspondentId, String documentId, String correspondent) {
        String insertQuery = "insert into external_correspondent (ex_correspondent_id, document_id, correspondent_id) values (?, ?, ?)";
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, correspondentId);
            preparedStatement.setString(2, documentId);
            preparedStatement.setString(3, correspondent);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlEx) {
        }
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
    }

    public void registerInternalCorrespondent (String correspondentId, String documentId, String correspondent) {
        String insertQuery = "insert into internal_correspondent values (?, ?, ?)";
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, correspondentId);
            preparedStatement.setString(2, documentId);
            preparedStatement.setString(3, correspondent);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlEx) {
        }
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
    }

    public int countExternalCorrespondent(){
        String selectQuery = "select * from external_correspondent";
        int count = 0;

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                count++;
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return count;
    }

    public int countInternalCorrespondent(){
        String selectQuery = "select * from internal_correspondent";
        int count = 0;

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                count++;
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return count;
    }

    /////////////////////////Edit date//////////////////////////////////////
    public Boolean checkDocument(String id){
        String selectQuery = "select document_id from documents where document_id = '" + id + "'";
        Boolean check = false;
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                check = true;
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return check;
    }

    public VBox getAllDocuments(String selectQuery){
        VBox documents = new VBox();
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                String resultId = checkResult.getString(1);
                String resultName = checkResult.getString(2);
                String resultType = checkResult.getString(3);
                String resultTaskName = checkResult.getString(4);
                String resultExecutorId = checkResult.getString(5);
                String resultCreationDate = checkResult.getString(6);
                String resultRegistrationDate = checkResult.getString(7);
                Text result = new Text("id: " + resultId + ", имя документа: " + resultName +
                        ", тип документа: " + resultType + ", \nназвание задачи: " + resultTaskName +
                        ", id исполнителя: " + resultExecutorId +
                        ", \nдата создания: " + resultCreationDate + ", дедлайн: " + resultRegistrationDate + ";\n");
                documents.getChildren().add(result);
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return documents;
    }

    public VBox getAllExecutors(){
        String query = "select id, surname, position from employees where employees.status = 'исполнитель'";
        VBox executors = new VBox();

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(query);
            while (checkResult.next()) {
                String resultId = checkResult.getString(1);
                String resultSurname = checkResult.getString(2);
                String resultPosition = checkResult.getString(3);
                Text result = new Text("id: " + resultId + ", исполнитель: " + resultSurname + ", должность: " + resultPosition + ";");
                executors.getChildren().add(result);
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return executors;
    }

    public void updateExecutorSurnameAndExecutionDateSurname(String id, String newId, String newRegistrationDate){
        String updateQuery = "update documents set employee_id = ?, registration_date = ? where document_id = ?";
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newId);
            preparedStatement.setString(2, newRegistrationDate);
            preparedStatement.setString(3, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlEx) {
        }
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
    }

    ///////////////////////Notifications///////////////////////////////////////
    public Boolean checkDate(){
        String selectQuery = "select document_id from documents where datediff(registration_date, current_date()) = 2";
        Boolean check = false;

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                check = true;
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return check;
    }

    public VBox getDocumentsWithOverdueDeadline(String registrationDate){
        String selectQuery = "select document_id, document_name, document_type from documents where registration_date < '" + registrationDate + "'";
        VBox documents = new VBox();
        int count = 0;
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                count++;
                String resultId = checkResult.getString(1);
                String resultName = checkResult.getString(2);
                String resultType = checkResult.getString(3);
                Text result = new Text("id: " + resultId + ", имя документа: " + resultName + ", \nтип документа: " + resultType + ";\n");
                documents.getChildren().add(result);
            }
            if (count == 0){
                documents.getChildren().clear();
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Информация");
                message.setContentText("Документов не найдено!");
                message.showAndWait();
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return documents;
    }

    public VBox getDocumentsWithApproachingDeadline(String registrationDate){
        String selectQuery = "select document_id, document_name, task_name, surname, position from documents, employees " +
                "where documents.employee_id = employees.id " +
                "and datediff(documents.registration_date, '" + registrationDate + "') = 2";
        VBox documents = new VBox();
        documents.setAlignment(Pos.CENTER);
        int count = 0;
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            checkResult = statement.executeQuery(selectQuery);
            while (checkResult.next()) {
                count++;
                String resultId = checkResult.getString(1);
                String resultName = checkResult.getString(2);
                String resultTaskName = checkResult.getString(3);
                String resultSurname = checkResult.getString(4);
                String resultPosition = checkResult.getString(5);
                Text result = new Text("id: " + resultId + ", название документа: " + resultName + ", \nназвание задачи: " + resultTaskName +
                        ", \nисполнитель: " + resultSurname+ ", \nдолжность: " + resultPosition + ";\n");
                documents.getChildren().add(result);
            }
            if (count == 0){
                documents.getChildren().clear();
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Информация");
                message.setContentText("Документов не найдено!");
                message.showAndWait();
            }
        }
        catch (SQLException sqlEx) {}
        finally {
            try { statement.close(); } catch(SQLException se) {}
            try { checkResult.close(); } catch(SQLException se) {}
            try { connection.close(); } catch(SQLException se) {}
        }
        return documents;
    }
}
