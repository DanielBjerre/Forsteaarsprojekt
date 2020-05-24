package presentation;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class customComboBox extends ComboBox<customComboBox.DisplayValue>{
    ArrayList<DisplayValue> listValues = new ArrayList<>();
    DisplayValue dv = new DisplayValue();

    public customComboBox()
    {
        refresh();
    }

    public void refresh()
    {
        ObservableList<DisplayValue> valuesOL = FXCollections.observableList(listValues);
        this.setItems(valuesOL);
    }

    public void add(String Display, String Value)
    {
        listValues.add(dv.newDisplayValue(Display, Value));
    }

    public String getSelectedItemValue()
    {
        String sReturn;
        if (this.getSelectionModel().getSelectedIndex() == -1) {
            throw new ArithmeticException("'Selected Index is -1'");
        } else {
            sReturn = this.getSelectionModel().getSelectedItem().getValue();
        }
        return sReturn;
    }

    class DisplayValue {
        private String display;
        private String value;

        public String getDisplay() {
            return display;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public DisplayValue newDisplayValue(String Display, String Value)
        {
            DisplayValue dv = new DisplayValue();
            dv.setDisplay(Display);
            dv.setValue(Value);
            return dv;
        }

        @Override
        public String toString()
        {
            return this.getDisplay();
        }
    }
}