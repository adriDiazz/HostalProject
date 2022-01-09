package controller.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.Vector;

import org.omg.CORBA.INITIALIZE;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSpinner;

import controller.rooms.RoomsController;
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

public class MainController {

	private Stage mainWindow;
	
	Date checkIn = null;
	Date checkOut = null;
	LocalDate actualDate = null;
	
	Vector<String> clients = null;
	
	String currentClient;
	
    @FXML
    private JFXSpinner spOcupancy;
	
	@FXML
	private JFXListView<Label> listView;

	public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void getOcupancy() {
		final Float numeroDeHabitaciones = (float) 15;
		Float ocupancy = (float) ((clients.size())/(numeroDeHabitaciones));
		spOcupancy.setProgress(ocupancy);
		
	}
	
	@FXML
	public void initialize() {
		MongoHelper mongo = new MongoHelper();
		clients = mongo.getClientsName();
		Date dat = new Date();
		actualDate = LocalDate.now();
		
		for (int i = 0;i < clients.size();i++) {	
			try {
				checkIn = new SimpleDateFormat("yyyy-MM-dd").parse(mongo.getCheckIn(clients.elementAt(i)));
				checkOut = new SimpleDateFormat("yyyy-MM-dd").parse(mongo.getCheckOut(clients.elementAt(i)));
				

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			if(dat.after(checkOut)) {
				clients.remove(i);
			}
		}
			
		for (String cliente:clients) {	
			try {
				Label lbl = new Label(cliente);
				lbl.setGraphic(new ImageView(new Image(new FileInputStream("src/img/icon.png"))));
				lbl.setGraphicTextGap(30);
				
				listView.getItems().add(lbl);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Label>() {

			@Override
			public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
				System.out.println(listView.getSelectionModel().getSelectedItem().getText().toString());
				currentClient = listView.getSelectionModel().getSelectedItem().getText().toString();
				 try {
						FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/principal/ProfileView.fxml"));
						ProfileController controller =  new ProfileController();
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
				
				
			}});
		getOcupancy();
		
	}
			
	 @FXML
	 void añadir(ActionEvent event) {
		 try {
				FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/principal/AñadirView.fxml"));
				AñadirController controller =  new AñadirController();
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
	 
	 @FXML
	 void habitaciones(ActionEvent event) {
		 try {
				FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/rooms/RoomsView.fxml"));
				RoomsController controller =  new RoomsController();
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
}
