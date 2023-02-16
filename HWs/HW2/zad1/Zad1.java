package hw.zad1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Zad1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Line l;
        Group group = new Group();
        int increments = 16;
        double x = 500.0;
        double y = 0.0;

        for (int i = 0; i < increments; i++) {
            l = new Line(0, 0, x, y);
            l.setStrokeWidth(5);
            l.setStroke(Color.BLUE);
            group.getChildren().add(l);
            x -= 34;
            y += 34;
        }

        Scene scene = new Scene(group, 500, 500);

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