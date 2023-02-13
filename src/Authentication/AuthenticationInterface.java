package Authentication;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Set;

public class AuthenticationInterface extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label welcome = new Label("Welcome");
        Label userField = new Label("User Name:");
        Label passwordField = new Label("Password:");
        TextField userText = new TextField();
        PasswordField passwordText = new PasswordField();
        Button signIn = new Button("Sign In");
        Label massage = new Label();

        welcome.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 18));


        GridPane gp = new GridPane();

        gp.getRowConstraints().add(new RowConstraints(30));
        gp.getRowConstraints().add(new RowConstraints(30));
        gp.getRowConstraints().add(new RowConstraints(30));
        gp.getRowConstraints().add(new RowConstraints(30));
        gp.getRowConstraints().add(new RowConstraints(30));
        gp.getColumnConstraints().add(new ColumnConstraints(100));
        gp.getColumnConstraints().add(new ColumnConstraints(200));

        gp.setGridLinesVisible(false);
        gp.setColumnIndex(welcome,1);
        gp.setColumnIndex(userField,0);
        gp.setColumnIndex(userText, 1);
        gp.setRowIndex(welcome,1);
        gp.setRowIndex(userField,2);
        gp.setRowIndex(userText,2);
        gp.setColumnIndex(passwordField,0);
        gp.setColumnIndex(passwordText, 1);
        gp.setRowIndex(passwordField,3);
        gp.setRowIndex(passwordText,3);
        gp.setColumnIndex(massage,1);
        gp.setRowIndex(massage,5);

        gp.setAlignment(Pos.CENTER);

        HBox hb = new HBox(10,signIn);
        hb.setAlignment(Pos.CENTER_RIGHT);
        gp.setColumnIndex(hb, 1);
        gp.setRowIndex(hb,4);

        gp.getChildren().addAll(welcome, userField,userText, passwordField, passwordText, hb, massage);

        signIn.setOnAction(e -> {
            Set<Users> users = new Repository().findUser();
            for (Users user : users) {
                if(userText.getText().equals(user.getName()) && passwordText.getText().equals(user.getPassword())) {
                    massage.setText("Авторизация пройдена");
                } else {
                    massage.setText("Пользователь не найден");
                }
            }

        });

        Scene scene = new Scene(gp,400,300);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();

    }
}