package sample.Scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import sample.Verfahren.Sekanten.Sekantenverfahren;
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

    public String[] cb_initalize = {"Bisektion", "Regula-Falsi", "Newton-Verfahren", "Steffensen", "Euler-Tschebyschow", "Sekanten"};
    public String[] temp = new String[3];

    @FXML
    public void initialize() {
        cb_Verfahren1.setValue(cb_initalize[0]);
        cb_Verfahren2.setValue(cb_initalize[1]);
        cb_Verfahren3.setValue(cb_initalize[2]);

        temp[0] = cb_Verfahren1.getValue();
        temp[1] = cb_Verfahren2.getValue();
        temp[2] = cb_Verfahren3.getValue();

        for (int i = 0; i < 6; i++) {
            cb_Verfahren1.getItems().add(cb_initalize[i]);
            cb_Verfahren2.getItems().add(cb_initalize[i]);
            cb_Verfahren3.getItems().add(cb_initalize[i]);
        }
    }

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
                if (temp == 5) {
                    notEmpty = true;
                }
            }
        }
        if (notEmpty) {
            accuracy = Integer.parseInt(txt_Accuracy.getText());
            endValue = Integer.parseInt(txt_EndValue.getText());
            startValue = Integer.parseInt(txt_StartValue.getText());
            maxEntries = Integer.parseInt(txt_MaxEntries.getText());
            Verfahren v = new Sekantenverfahren("x^2 - 2", 0, 3, 5, 20);
            Point[] points = v.calculate();

            for (Point p : points) {
                System.out.println("(" + p.x + "/" + p.y + ")");
            }
        }

    }

    public void cmb_selection_Method(ActionEvent actionEvent) throws Exception {
        boolean next;
        //not good programmed

        if (cb_Verfahren2.getValue().equals(cb_Verfahren1.getValue())) {
            cb_Verfahren1.setValue(temp[1]);
        } else if (cb_Verfahren1.getValue().equals(cb_Verfahren2.getValue())) {
            cb_Verfahren2.setValue(temp[0]);
        } else if (cb_Verfahren2.getValue().equals(cb_Verfahren3.getValue())) {
            cb_Verfahren3.setValue(temp[1]);
        } else if (cb_Verfahren1.getValue().equals(cb_Verfahren3.getValue())) {
            cb_Verfahren3.setValue(temp[0]);
        } else if (cb_Verfahren3.getValue().equals(cb_Verfahren1.getValue())) {
            cb_Verfahren1.setValue(temp[2]);
        } else if (cb_Verfahren3.getValue().equals(cb_Verfahren2.getValue())) {
            cb_Verfahren2.setValue(temp[2]);
        }
        temp[0] = cb_Verfahren1.getValue();
        temp[1] = cb_Verfahren2.getValue();
        temp[2] = cb_Verfahren3.getValue();
    }
}
