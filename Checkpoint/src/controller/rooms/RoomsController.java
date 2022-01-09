package controller.rooms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import com.jfoenix.controls.JFXListView;

import controller.main.MainController;
import controller.rooms.ProfileController;
import controller.rooms.AñadirController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.MongoHelper;

public class RoomsController {
	
	private Stage mainWindow;
	
	String currentClient;
	
	String currentRoom;
	
	Vector<String> roomNumbers;
	
	 @FXML
	 private JFXListView<Label> roomsLV;

	   
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
		roomNumbers = mongo.getRoomsNumbers();
		
		for (String room : roomNumbers) {
			
			try {
				Label lbl = new Label(room);
				lbl.setGraphic(new ImageView(new Image(new FileInputStream("src/img/bedIcon.png"))));
				lbl.setGraphicTextGap(30);
				
				roomsLV.getItems().add(lbl);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		roomsLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Label>() {

			@Override
			public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
				System.out.println(roomsLV.getSelectionModel().getSelectedItem().getText().toString());
				currentRoom = roomsLV.getSelectionModel().getSelectedItem().getText().toString();
				 try {
						FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/rooms/ProfilView.fxml"));
						ProfileController controller =  new ProfileController();
						controller.setMainWindow(mainWindow);
						controller.setCurrentClient(currentClient);
						controller.setCurrentRoom(currentRoom);
						loader.setController(controller);
						Parent node;
						node = loader.load();
						Scene scene = new Scene(node, mainWindow.getScene().getWidth(), mainWindow.getScene().getHeight());
						mainWindow.setScene(scene);
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				
			}});
		
	}
	
	 @FXML
	 void añadirHabitacion(ActionEvent event) {
		 try {
				FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/rooms/AñadirRoomView.fxml"));
				AñadirController controller =  new AñadirController();
				controller.setMainWindow(mainWindow);
				controller.setCurrentClient(currentClient);
				loader.setController(controller);
				Parent node;
				node = loader.load();
				Scene scene = new Scene(node, mainWindow.getScene().getWidth(), mainWindow.getScene().getHeight());
				mainWindow.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
	 }
	 
	 @FXML
	 void goIncio(ActionEvent event) {
		 try {
				FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/principal/MainView.fxml"));
				MainController controller =  new MainController();
				controller.setMainWindow(mainWindow);
				loader.setController(controller);
				Parent node;
				node = loader.load();
				Scene scene = new Scene(node, mainWindow.getScene().getWidth(), mainWindow.getScene().getHeight());
				mainWindow.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
	 }


}
