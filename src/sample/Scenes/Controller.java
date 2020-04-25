package sample.Scenes;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import sample.Utility.Point;
import sample.Verfahren.Newton.NewtonVerfahren;
import sample.Verfahren.Verfahren;

import java.text.DecimalFormat;

public class Controller {
    public TextField txt_Function;
    public TextField txt_Accuracy;
    public TextField txt_EndValue;
    public TextField txt_StartValue;
    public TextField txt_MaxEntries;

    public void btn_Calculate_Click() {
        String function = txt_Function.getText();
        int accuracy = Integer.parseInt(txt_Accuracy.getText());
        int endValue = Integer.parseInt(txt_EndValue.getText());
        int startValue = Integer.parseInt(txt_StartValue.getText());
        int maxEntries = Integer.parseInt(txt_MaxEntries.getText());

        // Replace NewtonVerfahren to test your Verfahren
        Verfahren v = new NewtonVerfahren(function, startValue, endValue, accuracy, maxEntries);
        Point[] points = v.calculate();

        for(Point p : points) {
            System.out.println("(" + p.x + "/" + p.y + ")");
        }
    }
}
