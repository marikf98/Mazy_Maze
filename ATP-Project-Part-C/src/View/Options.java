package View;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class Options {
    public ToggleButton toggleButton1;
    public ToggleButton toggleButton2;
    public ToggleButton toggleButton3;
    @FXML
    private ToggleGroup group1;

    @FXML
    private ToggleGroup group2;

    @FXML
    private void handleOptionSelected() {
        RadioButton selectedOption1 = (RadioButton) group1.getSelectedToggle();
        RadioButton selectedOption2 = (RadioButton) group2.getSelectedToggle();

        if (selectedOption1 != null) {
            String option1 = selectedOption1.getText();
            System.out.println("Selected Option from Group 1: " + option1);
        } else {
            System.out.println("No option selected from Group 1.");
        }

        if (selectedOption2 != null) {
            String option2 = selectedOption2.getText();
            System.out.println("Selected Option from Group 2: " + option2);
        } else {
            System.out.println("No option selected from Group 2.");
        }
    }
}
