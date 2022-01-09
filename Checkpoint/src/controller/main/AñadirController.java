package controller.main;

import java.io.IOException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cliente;
import util.MongoHelper;

public class AñadirController {
	
	private Stage mainWindow;
	
	@FXML
	private JFXTextField nameTF;
	
	 @FXML
	 private JFXTextField numTF;


   @FXML
	private JFXDatePicker inDate;
	
	@FXML
	private JFXDatePicker outDate;
	
    @FXML
    private JFXCheckBox cbR;

    @FXML
    private JFXCheckBox cbE;

	public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}
	

    @FXML
    void guardar(ActionEvent event) {
    	Cliente client = new Cliente();
    	MongoHelper mongo = new MongoHelper();
    	client.setName(nameTF.getText().toString());
    	client.setCheckIn(inDate.getValue().toString());
    	client.setCheckOut(outDate.getValue().toString());
    	client.setRoomNum(Integer.valueOf(numTF.getText()));
    	client.setactivo(cbR.selectedProperty().getValue());
    	client.setEntradas(cbE.selectedProperty().getValue());

    	mongo.insertClient(client);
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
