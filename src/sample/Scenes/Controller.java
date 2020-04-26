package sample.Scenes;

import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Utility.Point;
import sample.Verfahren.Bisektion.Bisektionsverfahren;
import sample.Verfahren.Verfahren;

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
        int accuracy = Integer.parseInt(txt_Accuracy.getText());
        int endValue = Integer.parseInt(txt_EndValue.getText());
        int startValue = Integer.parseInt(txt_StartValue.getText());
        int maxEntries = Integer.parseInt(txt_MaxEntries.getText());

        Verfahren v = new Bisektionsverfahren(function, startValue, endValue, accuracy, maxEntries);
        Point[] points = v.calculate();

        for(Point p : points) {
            System.out.println("(" + p.x + "/" + p.y + ")");
        }
    }
}
