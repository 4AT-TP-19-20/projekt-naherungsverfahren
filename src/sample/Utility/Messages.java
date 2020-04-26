package sample.Utility;

import javafx.scene.control.Alert;

public class Messages {

    public static void showWarning(String title, String content) {
        showAlert(title, content, Alert.AlertType.WARNING);
    }

    public static void showError(String title, String content) {
        showAlert(title, content, Alert.AlertType.ERROR);
    }

    private static void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
