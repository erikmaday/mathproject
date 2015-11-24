package PartTwo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author Erik Maday, CG Carson, Quinton Johnson
 * @version 1.0
 */
public class GraphPlot extends Application {

    @Override
    public void start(Stage stage) {
        matrix_thousand grouping = new matrix_thousand(1000);
        Group root = new Group();
        ArrayList<PowerMatrix> matrices = grouping.getMatrices();
        ArrayList<Color> c = generateColors();

        stage.setTitle("Graphs");
        final NumberAxis xAxis = new NumberAxis(-10, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-10, 10, 1);
        final NumberAxis xAxis2 = new NumberAxis(-10, 10, 1);
        final NumberAxis yAxis2 = new NumberAxis(-10, 10, 1);
        final ScatterChart<Number, Number> sc = new
                ScatterChart<Number, Number>(xAxis, yAxis);

        final ScatterChart<Number, Number> sc2 = new
                ScatterChart<Number, Number>(xAxis2, yAxis2);
        xAxis.setLabel("Determinant");
        yAxis.setLabel("Trace");
        sc.setTitle("Matrix Data");

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("A");
        series2.setName("A Inverse");
        for (PowerMatrix m : matrices) {
            Rectangle rect1 = new Rectangle(5,5);
            Rectangle rect2 = new Rectangle(5,5);
            rect1.setFill(c.get(m.getLargestIterations()));
            rect2.setFill(c.get(m.getSmallestIterations()));
            XYChart.Data d1 = new XYChart.Data(m.getDeterminant(), m.getTrace());
            d1.setNode(rect1);
            series1.getData().add(d1);
            XYChart.Data d2 = new XYChart.Data(m.getInverseDeterminant(), m.getInverseTrace());
            d2.setNode(rect2);
            series2.getData().add(d2);
        }

        sc.getData().addAll(series1);
        sc2.getData().addAll(series2);
        VBox vBox1 = new VBox();
        Scene scene = new Scene(new Group());
        vBox1.getChildren().addAll(sc, sc2);
        ((Group)scene.getRoot()).getChildren().add(vBox1);
        stage.setScene(scene);
        stage.show();
    }

    public void show() {

    }

    private static ArrayList<Color> generateColors() {
        ArrayList<Color> colors = new ArrayList<Color>();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            Color randomColor = Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
            colors.add(randomColor);
        }
        return colors;
    }
    public static void main(String[] args) {
        launch(args);
    }
}