package org.plugin.core.fx;

import org.or5e.core.BaseObject;
import org.or5e.core.plugin.PluginLoaderSPI;
import org.or5e.core.plugin.event.PluginEvent;
import org.or5e.plugin.manage.PluginUIManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class PluginManagerController extends BaseObject{
	private PluginUIManager manager;

	@FXML private VBox rootPanel;
	@FXML private MenuBar menuBar;
	@FXML private Menu actionMenu;
	@FXML private MenuItem settingsMenu;
	@FXML private MenuItem minimizeMenu;
	@FXML private MenuItem exitMenu;

	@FXML private Menu accountMenu;
	@FXML private MenuItem signInMenu;
	@FXML private MenuItem signOutMenu;
	@FXML private MenuItem viewAccountsMenu;

	//Close Btn Operation
	@FXML public void handleCloseMenu(ActionEvent actionEvent) {
		manager.destroy();
		Platform.exit();
	}

	public void initilizeApplication(PluginEvent event) {
		manager = PluginUIManager.getManager();
		manager.initilizePlugin(event);
	}
	@Override public String getName() {
		return "org.plugin.core.fx.PluginManagerController";
	}
}
