package org.plugin.core.fx;

import org.or5e.core.BaseObject;
import org.or5e.core.plugin.event.PluginEvent;
import org.or5e.plugin.manage.PluginUIManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class PluginManagerController extends BaseObject{
	private PluginUIManager manager;
	private MainApp app;
	@FXML private VBox rootPanel;
	@FXML private MenuBar menuBar;
	@FXML private Menu actionMenu;
	@FXML private MenuItem settingsMenu;
	@FXML private MenuItem minimizeMenu;
	@FXML private MenuItem exitMenu;

	@FXML private Label pluginInstalledLbl;
	@FXML private Label settingsLbl;
	@FXML private Label eventManagerLbl;
	
	@FXML private Menu accountMenu;
	@FXML private MenuItem signInMenu;
	@FXML private MenuItem signOutMenu;
	@FXML private MenuItem viewAccountsMenu;

	//Close Btn Operation
	@FXML public void handleCloseMenu(ActionEvent actionEvent) {
		manager.destroy();
		this.app.cancelNotification();
		Platform.exit();
	}
	//Minimize Btn Action
	@FXML public void handleMinimize(ActionEvent actionEvent) {
		this.app.minimizeWindow();
	}
	//Settings Btn Action
	@FXML public void handleSettings(ActionEvent actionEvent) {
		
	}
	public void initilizeApplication(PluginEvent event) {
		manager = PluginUIManager.getManager();
		manager.initilizePlugin(event);
	}
	public void setApp(MainApp mainApp) {
		this.app = mainApp;
	}
	@Override public String getName() {
		return "org.plugin.core.fx.PluginManagerController";
	}
}
