package problem3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class JavaFXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group(); // the group where all shapes are added
        int x = 600 / 2; // the x coordinate of the initial point
        int y = 600 / 2; // the y coordinate of the initial point

        for (int i = 0; i < 5; i++) {

            int t = i * 40; // to increase the length of the line

            Line l1 = new Line(x + t, y + t, x + t, y + 40 + t); // line down
            Line l2 = new Line(x + t, y + 40 + t, x - 40 - t , y + 40 + t); // line left
            Line l3 = new Line(x - 40 - t, y + 40 + t, x - 40 - t, y - 40 - t); // line up
            Line l4 = new Line(x - 40 - t, y - 40 - t, x + 40 + t, y - 40 - t); // line right
            Line l5 = new Line(x  + 40 + t, y - 40 - t, x + 40 + t, y + 40 + t); // line down

            // setting the color of the lines
            l1.setStroke(Color.RED);
            l2.setStroke(Color.RED);
            l3.setStroke(Color.RED);
            l4.setStroke(Color.RED);
            l5.setStroke(Color.RED);

            // when the loop reaches the final iteration it gets only the first four lines
            if (i == 4) {
                root.getChildren().addAll(l1, l2, l3, l4); // adding the lines to the root
            } else {
                root.getChildren().addAll(l1, l2, l3, l4, l5); // adding the lines to the root
            }
        }

        Scene scene = new Scene(root, 600, 600);

        // Set the Window title
        stage.setTitle("Square-shaped spiral");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}