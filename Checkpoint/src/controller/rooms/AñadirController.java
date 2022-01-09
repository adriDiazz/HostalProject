package controller.rooms;

import java.util.Vector;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Room;
import util.MongoHelper;

public class AñadirController {
	
	private Stage mainWindow;
	
	String currentClient;
	
	
	
    @FXML
    private JFXComboBox<String> tipo;
    
	@FXML
	private JFXTextField nameTF;

    @FXML
    private JFXCheckBox cbL;

    @FXML
    private JFXCheckBox cbLi;

    @FXML
    private JFXTextField numeroTF;


	public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}

	public String getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(String currentClient) {
		this.currentClient = currentClient;
	}
	
	
	@FXML
	public void initialize() {
		tipo.getItems().add("Individual");
		tipo.getItems().add("Doble");
		tipo.getItems().add("Triple");
		tipo.getItems().add("Cuadruple");
		
	}
	 @FXML
	 void saveRoom(ActionEvent event) {
		 Room room = new Room();
		 MongoHelper mongo = new MongoHelper();
		 room.setNumber(Integer.parseInt(numeroTF.getText().toString()));
		 room.setClient(nameTF.getText().toString());
		 room.setType(tipo.getValue().toString());
		 room.setFree(cbLi.selectedProperty().getValue());
		 room.setFree(cbL.selectedProperty().getValue());
		 System.out.println(tipo.getValue().toString());
		 
		 mongo.insertRoom(room);
		 
		 
		 
	 }


}
