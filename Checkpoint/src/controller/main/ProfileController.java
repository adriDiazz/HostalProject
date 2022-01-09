package controller.main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.MongoHelper;

public class ProfileController {
		
	private Stage mainWindow;
	
	String currentClient;
	
	//Date checkIn = null;
	
	Date checkIn = null;
	Date checkOut = null;
	LocalDate actualDate = null;
	
	
	boolean entradas;
	
	
	
	
	@FXML
	private Label nameLbl;
	
	@FXML
	private Label dateInLbl;
	
	@FXML
	private Label dateOutLbl;
	
	@FXML
	private Label entradasLbl;
	
	
	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public LocalDate getActualDate() {
		return actualDate;
	}

	public void setActualDate(LocalDate actualDate) {
		this.actualDate = actualDate;
	}

	public boolean isEntradas() {
		return entradas;
	}

	public void setEntradas(boolean entradas) {
		this.entradas = entradas;
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
		actualDate = LocalDate.now();
		try {
			 checkIn = new SimpleDateFormat().parse(mongo.getCheckIn(currentClient));
			 checkOut = new SimpleDateFormat().parse(mongo.getCheckOut(currentClient));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//checkIn = mongo.getCheckIn(currentClient);
		//checkOut = mongo.getCheckOut(currentClient);
		entradas = mongo.getIfEntradas(currentClient);
		
		if (entradas) {
			entradasLbl.setText("Necesita Entradas");
		}else {
			entradasLbl.setText("No necesita entradas");
		}
		
		dateInLbl.setText(mongo.getCheckIn(currentClient));
		dateOutLbl.setText(mongo.getCheckOut(currentClient));
		nameLbl.setText(currentClient);
	}
	
	@FXML
	void goBack() {
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
