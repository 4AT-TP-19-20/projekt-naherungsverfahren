package sample.Scenes;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Controller {
    public TextField txt_Function;
    public TextField txt_Accuracy;
    public TextField txt_EndValue;
    public TextField txt_StartValue;
    public TextField txt_MaxEntries;

    public void btn_Calculate_Click(ActionEvent actionEvent) {
        String function = txt_Function.getText();
        int accuracy = Integer.parseInt(txt_Accuracy.getText());
        int endValue = Integer.parseInt(txt_EndValue.getText());
        int startValue = Integer.parseInt(txt_StartValue.getText());
        int maxEntries = Integer.parseInt(txt_MaxEntries.getText());

        // ToDo Implement Functions
    }
}
