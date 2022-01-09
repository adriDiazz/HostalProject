package controller.rooms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.MongoHelper;

public class ProfileController {
	
	private Stage mainWindow;
	
	String currentClient;
	
	String currentRoom;
	
	 @FXML
	 private Label nameLbl;

	 @FXML
	 private Label dateInLbl;

	 @FXML
	 private Label dateOutLbl;

	 @FXML
	 private Label cleanedLbl;


	public String getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(String currentRoom) {
		this.currentRoom = currentRoom;
	}

	public String getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(String currentClient) {
		this.currentClient = currentClient;
	}

	public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@FXML
	public void initialize() {
		MongoHelper mongo = new MongoHelper();
		boolean cleaned = mongo.getIfCleaned(currentRoom);
		nameLbl.setText("Habitacion numero "+ currentRoom + " : " + mongo.getRoomClient(currentRoom));
		dateInLbl.setText(mongo.getCheckIn(mongo.getRoomClient(currentRoom)));
		dateOutLbl.setText(mongo.getCheckOut(mongo.getRoomClient(currentRoom)));
		if (cleaned) {
			cleanedLbl.setText("Habitacion Limpia");
		}else {
			cleanedLbl.setText("Habitacion sin limpiar");
		}
	}
	
}
