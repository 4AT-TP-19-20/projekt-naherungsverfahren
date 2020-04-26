package sample.Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import sample.Utility.Point;
import sample.Verfahren.Bisektion.Bisektionsverfahren;
import sample.Verfahren.EulerTschebyschow.EulerTschebyschowVerfahren;
import sample.Verfahren.Newton.NewtonVerfahren;
import sample.Verfahren.RegulaFalsi.RegulaFalsiVerfahren;
import sample.Verfahren.Sekanten.Sekantenverfahren;
import sample.Verfahren.Steffensen.SteffensenVerfahren;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class Controller {
    public TextField txt_Function;
    public TextField txt_Accuracy;
    public TextField txt_EndValue;
    public TextField txt_StartValue;
    public TextField txt_MaxEntries;

    public LineChart<Double, Double> lineChart;

    public ComboBox<Verfahren> cb_Verfahren1;
    public ComboBox<Verfahren> cb_Verfahren2;
    public ComboBox<Verfahren> cb_Verfahren3;

    public Label lbl_lbl_Verfahren1;
    public Label lbl_lbl_Verfahren2;
    public Label lbl_lbl_Verfahren3;

    public Label lbl_Verfahren1;
    public Label lbl_Verfahren2;
    public Label lbl_Verfahren3;

    public ListView<String> lv_container1;
    public ListView<String> lv_container2;
    public ListView<String> lv_container3;

    public Button btn_Copy1;
    public Button btn_Copy2;
    public Button btn_Copy3;

    public Button btn_Info1;
    public Button btn_Info2;
    public Button btn_Info3;

    private ArrayList<TextField> textFields = new ArrayList<>();
    private ArrayList<ComboBox<Verfahren>> comboBoxes = new ArrayList<>();
    private ArrayList<ListView<String>> listViews = new ArrayList<>();
    private ArrayList<Label> labels = new ArrayList<>();
    private ArrayList<Label> lbls = new ArrayList<>();
    private ArrayList<Button> copyButtons = new ArrayList<>();
    private ArrayList<Button> infoButtons = new ArrayList<>();

    @FXML
    public void initialize() {
        comboBoxes.add(cb_Verfahren1);
        comboBoxes.add(cb_Verfahren2);
        comboBoxes.add(cb_Verfahren3);

        labels.add(lbl_lbl_Verfahren1);
        labels.add(lbl_lbl_Verfahren2);
        labels.add(lbl_lbl_Verfahren3);

        lbls.add(lbl_Verfahren1);
        lbls.add(lbl_Verfahren2);
        lbls.add(lbl_Verfahren3);

        textFields.add(txt_Accuracy);
        textFields.add(txt_StartValue);
        textFields.add(txt_EndValue);
        textFields.add(txt_MaxEntries);
        textFields.add(txt_Function);

        listViews.add(lv_container1);
        listViews.add(lv_container2);
        listViews.add(lv_container3);

        copyButtons.add(btn_Copy1);
        copyButtons.add(btn_Copy2);
        copyButtons.add(btn_Copy3);

        infoButtons.add(btn_Info1);
        infoButtons.add(btn_Info2);
        infoButtons.add(btn_Info3);


        ObservableList<Verfahren> cb_initalize = FXCollections.observableArrayList(
                new Bisektionsverfahren(),
                new EulerTschebyschowVerfahren(),
                new NewtonVerfahren(),
                new RegulaFalsiVerfahren(),
                new Sekantenverfahren(),
                new SteffensenVerfahren());

        for(int i = 0; i < comboBoxes.size(); i++) {
            ComboBox<Verfahren> cb = comboBoxes.get(i);
            Button btn_Info = infoButtons.get(i);
            Button btn_Copy = copyButtons.get(i);
            Label lbl = labels.get(i);
            Label label = lbls.get(i);

            cb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> lbl.setText(newValue.toString() + ":"));
            cb.setItems(cb_initalize);
            label.textProperty().addListener((observable, oldValue, newValue) -> {
                btn_Info.setVisible(!newValue.isEmpty());
                btn_Copy.setVisible(!newValue.isEmpty());
            });

            btn_Copy.setOnAction((e) -> {
                ClipboardContent content = new ClipboardContent();
                content.putString(label.getText());
                Clipboard.getSystemClipboard().setContent(content);
            });

        }
    }

    public void btn_Calculate_Click() {
        boolean notEmpty = true;

        for (int i = 0; i < 5; i++) {
            if (textFields.get(i).getText().isEmpty()) {
                textFields.get(i).getStyleClass().add("missing-argument");
                notEmpty = false;
            }
        }

        if (notEmpty) {
            try {
                String func = txt_Function.getText();
                int accuracy = Integer.parseInt(txt_Accuracy.getText());
                int endValue = Integer.parseInt(txt_EndValue.getText());
                int startValue = Integer.parseInt(txt_StartValue.getText());
                int maxEntries = Integer.parseInt(txt_MaxEntries.getText());

                for(int i = 0; i < comboBoxes.size(); i++) {
                    ComboBox<Verfahren> cb = comboBoxes.get(i);
                    ListView<String> lv = listViews.get(i);
                    Label lbl = lbls.get(i);

                    Verfahren v = cb.getSelectionModel().getSelectedItem();
                    if(v != null) {
                        v.setStartValue(startValue);
                        v.setMax(maxEntries);
                        v.setEndValue(endValue);
                        v.setAccuracy(accuracy);
                        v.setFunction(func);

                        if((v.f(startValue) > 0 && v.f(endValue) > 0) || (v.f(startValue) < 0 && v.f(endValue) < 0)) {
                            System.out.println("Error! Gleiches Vorzeichen!");
                            return;
                        }

                        Point[] points;
                        try {
                            points = v.calculate();
                        } catch (ArithmeticException e) {
                            System.out.println("Fehler! Division durch Null!");
                            break;
                        }

                        lv.getItems().clear();
                        for (int j = 0; j < points.length; j++) {
                            Point p = points[j];

                            String x = String.format("%." + v.getAccuracy() + "f", p.x);
                            String y = String.format("%." + v.getAccuracy() + "f", p.y);

                            lv.getItems().add((j + 1) + ". (" + x + "|" + y + ")");
                        }

                        lbl.setText(points[points.length - 1].x + "");
                    }
                }
            } catch(NumberFormatException e) {
                System.out.println("Fehler! Keine Zahl");
            }
        }
    }

    public void txt_Text_Changed(KeyEvent actionEvent) {
        if(actionEvent.getTarget() instanceof TextField) {
            TextField txt = (TextField) actionEvent.getTarget();
            txt.getStyleClass().remove("missing-argument");
        }
    }
}
