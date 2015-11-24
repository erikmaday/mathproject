package PartOne;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Erik on 11/21/2015.
 */
public class GraphE extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        ArrayList<solve_px_b> pxList = new ArrayList<solve_px_b>();
        for (int n = 2; n <= 12; n++) {
            pxList.add(new solve_px_b(n));
        }

        stage.setTitle("Pascal Matrix");
        final NumberAxis xAxis = new NumberAxis(0, 12, 1);
        final NumberAxis yAxis = new NumberAxis(0, 1, 1);
        final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);

        xAxis.setLabel("n");
        yAxis.setLabel("Error");
        sc.setTitle("Pascal Matrix Errors");

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("LU Error");
        series2.setName("PX Error");
        for (int n = 2; n <= 12; n++){
            series1.getData().add(new XYChart.Data(n, pxList.get(n - 2).getLUError()));
            series2.getData().add(new XYChart.Data(n, pxList.get(n - 2).getPXBError()));
        }

        sc.getData().addAll(series1, series2);
        VBox vBox1 = new VBox();
        Scene scene = new Scene(new Group());
        vBox1.getChildren().addAll(sc);
        ((Group)scene.getRoot()).getChildren().add(vBox1);
        stage.setScene(scene);
        stage.show();
    }

    public void show() {
    }

    public static void main(String[] args) {
        launch(args);
    }
}