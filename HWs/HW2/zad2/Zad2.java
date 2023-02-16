package hw.zad2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Zad2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Line line;
        Group group = new Group();
        int increments = 20;
        int x = 400 / increments;
        double y = 400 / increments;

        for (int i = 0; i < increments; i++) {
            line = new Line(0, (i * y), ((i + 1) * x), 400);
            line.setStrokeWidth(1);
            line.setStroke(Color.BLUE);
            group.getChildren().add(line);
        }

        Scene scene = new Scene(group, 400, 400);

        // Set the Window title
        stage.setTitle("Draw mesh");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}