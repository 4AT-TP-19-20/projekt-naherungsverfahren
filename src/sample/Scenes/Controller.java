package sample.Scenes;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import sample.Utility.Point;
import sample.Verfahren.Bisektion.Bisektionsverfahren;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class Controller {
    public TextField txt_Function;
    public TextField txt_Accuracy;
    public TextField txt_EndValue;
    public TextField txt_StartValue;
    public TextField txt_MaxEntries;

    public LineChart<Double, Double> lineChart;

    public ComboBox<String> cb_Verfahren1;
    public ComboBox<String> cb_Verfahren2;
    public ComboBox<String> cb_Verfahren3;

    public Label lbl_Verfahren1;
    public Label lbl_Verfahren2;
    public Label lbl_Verfahren3;

    public ListView<String> lv_container1;
    public ListView<String> lv_container2;
    public ListView<String> lv_container3;

    public void btn_Calculate_Click() {
        String function = txt_Function.getText();
        int accuracy = 0, endValue = 0, startValue = 0, maxEntries = 0;
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(txt_Accuracy);
        textFields.add(txt_StartValue);
        textFields.add(txt_EndValue);
        textFields.add(txt_MaxEntries);
        textFields.add(txt_Function);
        boolean notEmpty = false;
        int temp = 0;
        for (int i = 0; i < 5; i++) {
            if (textFields.get(i).getText().isEmpty()) {
                textFields.get(i).setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                textFields.get(i).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                temp++;
                if(temp == 5){
                    notEmpty = true;
                }
            }
        }
        if(notEmpty){
            accuracy = Integer.parseInt(txt_Accuracy.getText());
            endValue = Integer.parseInt(txt_EndValue.getText());
            startValue = Integer.parseInt(txt_StartValue.getText());
            maxEntries = Integer.parseInt(txt_MaxEntries.getText());
            Verfahren v = new Bisektionsverfahren(function, startValue, endValue, accuracy, maxEntries);
            Point[] points = v.calculate();

            for (Point p : points) {
                System.out.println("(" + p.x + "/" + p.y + ")");
            }
        }

    }
}
