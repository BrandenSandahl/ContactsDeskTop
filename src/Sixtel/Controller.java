package Sixtel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    //special kind of list that links into listeners
    ObservableList<Contact> contactList = FXCollections.observableArrayList();

    //need to get links to objects in GUI
    @FXML
    TextField nameField, phoneField, emailField;
    @FXML
    TableView<Contact> displayTable = new TableView<>();  //using a table for output instead of a list.
    @FXML
    Label statusLabel;   //just a little label to show success or failure.

   @FXML
   TableColumn<Contact, String> nameColumn, phoneColumn, emailColumn;

    public void addContact() {
        if (nameField.getText().equals("") || phoneField.getText().equals("") || emailField.getText().equals("")) {
            statusLabel.setText("Failure");

        } else {
            contactList.add(new Contact(nameField.getText(), phoneField.getText(), emailField.getText())); //add to the array
            statusLabel.setText("Added");
        }

        //clear out entry fields
        nameField.clear();
        phoneField.clear();
        emailField.clear();
    }

    public void removeContact() {
        Contact remove = displayTable.getSelectionModel().getSelectedItem(); //took awhile to get to the correct method here.
        contactList.remove(remove);
    }

    public void saveFile() throws IOException {
        saveToJson(contactList);
    }

    public void saveToJson(ObservableList<Contact> contactList) throws IOException {
        File f = new File("contacts.json");
        FileWriter fw = new FileWriter(f);
        JsonSerializer serializer = new JsonSerializer();

        String serialized = serializer.deep(true).serialize(contactList);
        fw.write(serialized);
        fw.close();

    }

    /** What's happening here is that I can't pass a value to a button
     * So, I am having to create a method that runs when i click the button
     * that goes ahead and calls the proper method with the passed value!
     */
    @FXML
    protected void handleSaveToJasonButton() throws IOException {
        saveToJson(contactList);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /** Ok so reference this: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html
         * To learn what is going on here.
         * I am basically just using a table instead of a listView, works the same way but has columns.
         * I don't fully get it all the syntax, but it seems to function as intended.
         */
        displayTable.setItems(contactList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        displayTable.getColumns().setAll(nameColumn, phoneColumn, emailColumn);

    }
}
