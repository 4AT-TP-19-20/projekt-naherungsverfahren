package sample.Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Utility.Messages;
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

    private final ArrayList<TextField> textFields = new ArrayList<>();
    private final ArrayList<ComboBox<Verfahren>> comboBoxes = new ArrayList<>();
    private final ArrayList<ListView<String>> listViews = new ArrayList<>();
    private final ArrayList<Label> labels = new ArrayList<>();
    private final ArrayList<Label> lbls = new ArrayList<>();
    private final ArrayList<Button> copyButtons = new ArrayList<>();

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

        txt_Function.textProperty().addListener((c, o, newVal) -> initializeChart(newVal));

        ObservableList<Verfahren> cb_initalize = FXCollections.observableArrayList(
                new Bisektionsverfahren(),
                new EulerTschebyschowVerfahren(),
                new NewtonVerfahren(),
                new RegulaFalsiVerfahren(),
                new Sekantenverfahren(),
                new SteffensenVerfahren());

        for(int i = 0; i < comboBoxes.size(); i++) {
            ComboBox<Verfahren> cb = comboBoxes.get(i);
            Button btn_Copy = copyButtons.get(i);
            Label lbl = labels.get(i);
            Label label = lbls.get(i);

            cb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> lbl.setText(newValue.toString() + ":"));
            cb.setItems(cb_initalize);
            label.textProperty().addListener((observable, oldValue, newValue) -> btn_Copy.setVisible(!newValue.isEmpty()));

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
                double endValue = Double.parseDouble(txt_EndValue.getText().replace(",", "."));
                double startValue = Double.parseDouble(txt_StartValue.getText().replace(",", "."));
                int maxEntries = Integer.parseInt(txt_MaxEntries.getText());

                if(accuracy > 8) {
                    Messages.showWarning("Warnung!", "Die Anzahl der Kommastellen darf nicht größer als 8 sein!");
                    return;
                } else if(accuracy < 2) {
                    Messages.showWarning("Warnung!", "Die Anzahl der Kommastellen darf nicht kleiner als 2 sein!");
                    return;
                }

                if(maxEntries > 300) {
                    Messages.showWarning("Warnung!", "Die Anzahl der Einträge darf nicht größer als 300 sein!");
                    return;
                } else if(maxEntries < 10) {
                    Messages.showWarning("Warnung!", "Die Anzahl der Einträge darf nicht kleiner als 10 sein!");
                    return;
                }

                notEmpty = false;
                for(int i = 0; i < comboBoxes.size(); i++) {
                    ComboBox<Verfahren> cb = comboBoxes.get(i);
                    ListView<String> lv = listViews.get(i);
                    Label lbl = lbls.get(i);

                    Verfahren v = cb.getSelectionModel().getSelectedItem();
                    if(v != null) {
                        notEmpty = true;
                        v.setStartValue(startValue);
                        v.setMax(maxEntries);
                        v.setEndValue(endValue);
                        v.setAccuracy(accuracy);
                        v.setFunction(func);

                        if((v.f(startValue) > 0 && v.f(endValue) > 0) || (v.f(startValue) < 0 && v.f(endValue) < 0)) {
                            Messages.showWarning("Warnung", "f(Start-Wert) und f(End-Wert) müssen verschiedene Vorzeichen haben!");
                            return;
                        }

                        Point[] points;
                        try {
                            points = v.calculate();
                        } catch (ArithmeticException e) {
                            Messages.showError("Fehler!", "Es trat ein Rechenfehler auf!");
                            continue;
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

                if(!notEmpty) {
                    Messages.showWarning("Warnung!", "Es muss mindestens ein Verfahren ausgewählt werden!");
                }
            } catch(NumberFormatException e) {
                Messages.showError("Fehler!", "Es wurde bei einem Zahlenfeld, keine Zahl angegeben!");
            }
        }
    }

    public void txt_Text_Changed(KeyEvent actionEvent) {
        if(actionEvent.getTarget() instanceof TextField) {
            TextField txt = (TextField) actionEvent.getTarget();
            txt.getStyleClass().remove("missing-argument");
        }
    }

    private void initializeChart(String func) {
        try {
            Expression f = new ExpressionBuilder(func).variable("x").build();
            if(f != null) {
                XYChart.Series<Double, Double> data = new XYChart.Series<>();

                for(int i = -5000; i <= 5000; i+=5) {
                    try {
                        double x = (i / 100.0);
                        double y = f.setVariable("x", x).evaluate();

                        if(y != Double.POSITIVE_INFINITY && y != Double.NEGATIVE_INFINITY) {
                            data.getData().add(new XYChart.Data<>(x, y));
                        }
                    } catch (ArithmeticException ignore) {}
                }
                System.out.println();

                lineChart.getData().clear();
                lineChart.setCreateSymbols(false);
                lineChart.setLegendVisible(false);
                lineChart.getData().add(data);
            }
        } catch (Exception e) {
            lineChart.getData().clear();
        }
    }

    public void moveLineChart(ScrollEvent scrollEvent) {
        if(lineChart.getYAxis() instanceof ValueAxis) {
            ValueAxis<Double> axis = (ValueAxis<Double>)lineChart.getYAxis();
            double bound = axis.getUpperBound();
            double val = 1;

            if(scrollEvent.getDeltaY() < 0) {
                val *= -1;
            }

            if(bound - val <= 0 || bound - val > 50) {
                return;
            }

            axis.setUpperBound(bound - val);
            axis.setLowerBound(-bound + val);
        }
    }
}
