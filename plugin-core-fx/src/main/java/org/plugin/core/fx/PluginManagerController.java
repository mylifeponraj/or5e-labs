package org.plugin.core.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.or5e.core.BaseObject;
import org.or5e.core.plugin.Plugin;
import org.or5e.core.plugin.PluginEvent;
import org.or5e.plugin.manage.PluginData;
import org.or5e.plugin.manage.PluginUIManager;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class PluginManagerController extends BaseObject {
	private PluginUIManager manager;
	private MainApp app;
	@FXML
	private VBox rootPanel;
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu actionMenu;
	@FXML
	private MenuItem settingsMenu;
	@FXML
	private MenuItem minimizeMenu;
	@FXML
	private MenuItem exitMenu;

	@FXML
	private Label pluginInstalledLbl;
	@FXML
	private Label settingsLbl;
	@FXML
	private Label eventManagerLbl;

	@FXML
	private Menu accountMenu;
	@FXML
	private MenuItem signInMenu;
	@FXML
	private MenuItem signOutMenu;
	@FXML
	private MenuItem viewAccountsMenu;

	@FXML
	private TableColumn<PluginData, String> pluginName;
	@FXML
	private TableColumn<PluginData, String> pluginJar;
	@FXML
	private TableColumn<PluginData, String> pluginMain;
	@FXML
	private TableColumn<PluginData, String> pluginAction;
	@FXML
	private TableView<PluginData> pluginTable;

	public void initilizeUIComponents() {
		pluginName.setCellValueFactory(new PropertyValueFactory<>("pluginID"));
		pluginJar.setCellValueFactory(new PropertyValueFactory<>("pluginName"));
		pluginMain.setCellValueFactory(new PropertyValueFactory<>("pluginMain"));
		pluginAction.setCellValueFactory(new PropertyValueFactory<>("pluginAction"));

		Map<String, Plugin> pluginTableData = this.manager.getPluginTableData();
		List<PluginData> pluginDataList = new ArrayList<>();
		pluginTableData.forEach((K, V) -> {
			PluginData data = new PluginData();
			Plugin plugin = (Plugin) V;
			data.setPluginID(plugin.getPluginID());
			data.setPluginMain(plugin.getName());
			data.setPluginName(plugin.getPluginID());
			data.setPluginAction("");
			pluginDataList.add(data);
		} );
		
		ObservableList<PluginData> list = FXCollections.observableArrayList(pluginDataList);
		pluginTable.setItems(list);
		
	}

	// Close Btn Operation
	@FXML
	public void handleCloseMenu(ActionEvent actionEvent) {
		manager.destroy();
		this.app.cancelNotification();
		Platform.exit();
	}

	// Minimize Btn Action
	@FXML
	public void handleMinimize(ActionEvent actionEvent) {
		this.app.minimizeWindow();
	}

	// Settings Btn Action
	@FXML
	public void handleSettings(ActionEvent actionEvent) {

	}

	public void initilizeApplication(PluginEvent event) {
		manager = PluginUIManager.getManager();
		manager.initilizePlugin(event);
		this.initilizeUIComponents();
	}

	public void setApp(MainApp mainApp) {
		this.app = mainApp;
	}

	@Override
	public String getName() {
		return "org.plugin.core.fx.PluginManagerController";
	}
}
