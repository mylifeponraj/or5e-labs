package org.plugin.core.fx;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.or5e.core.plugin.event.PluginEvent;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainApp extends Application {
	public static final String SPLASH_IMAGE = "splashscreen.png";

	private Pane splashLayout;
	private ProgressBar loadProgress;
	private Label progressText;
	private Stage mainStage;
	private Parent rootNode;
	private static final int SPLASH_WIDTH = 676;
	private static final int SPLASH_HEIGHT = 227;
	private static final String iconImageLoc = "GameCenter-icon.png";
	private Timer notificationTimer = new Timer();
	private DateFormat timeFormat = SimpleDateFormat.getTimeInstance();
	private PluginManagerController controller;
	private java.awt.SystemTray tray;
	private java.awt.TrayIcon trayIcon;
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void init() {
		ImageView splash;
		String imagefile = "/images/" + SPLASH_IMAGE;
		System.out.println(imagefile+".");
		splash = new ImageView(new Image(getClass().getResourceAsStream(imagefile)));
		loadProgress = new ProgressBar();
		loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
		progressText = new Label("Will find friends for peanuts . . .");
		splashLayout = new VBox();
		splashLayout.getChildren().addAll(splash, loadProgress, progressText);
		progressText.setAlignment(Pos.CENTER);
		splashLayout.setStyle(
				"-fx-padding: 5; " + "-fx-background-color: cornsilk; " + "-fx-border-width:5; " + "-fx-border-color: "
						+ "linear-gradient(" + "to bottom, " + "chocolate, " + "derive(chocolate, 50%)" + ");");
		splashLayout.setEffect(new DropShadow());
	}

	@Override
	public void start(Stage stage) throws Exception {
		String fxmlFile = "/fxml/plugin-manager.fxml";
		FXMLLoader loader = new FXMLLoader();
		this.rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
		this.controller = (PluginManagerController) loader.getController();

		final Task<String> friendTask = new Task<String>() {
			@Override
			protected String call() throws InterruptedException {
				updateMessage("Loading. . .");
				controller.initilizeApplication(new PluginEvent() {
					@Override public void complete(String message, int perc) {
						updateMessage(message);
						updateProgress((double) perc, 100);
					}
				});
				updateMessage("Done");
				return "";
			}
		};

		showSplash(stage, friendTask, () -> showMainStage(friendTask.valueProperty()));
		new Thread(friendTask).start();
	}

	private void showSplash(final Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
		progressText.textProperty().bind(task.messageProperty());
		loadProgress.progressProperty().bind(task.progressProperty());
		task.stateProperty().addListener((observableValue, oldState, newState) -> {
			if (newState == Worker.State.SUCCEEDED) {
				loadProgress.progressProperty().unbind();
				loadProgress.setProgress(1);
				initStage.toFront();
				FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
				fadeSplash.setFromValue(1.0);
				fadeSplash.setToValue(0.0);
				fadeSplash.setOnFinished(actionEvent -> initStage.hide());
				fadeSplash.play();

				initCompletionHandler.complete();
			} // todo add code to gracefully handle other task states.
		});

		Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
		final Rectangle2D bounds = Screen.getPrimary().getBounds();
		initStage.setScene(splashScene);
		initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
		initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
		initStage.initStyle(StageStyle.TRANSPARENT);
		initStage.setAlwaysOnTop(true);
		initStage.show();
	}

	public interface InitCompletionHandler {
		void complete();
	}

	private void showMainStage(ReadOnlyObjectProperty<String> message) {
		mainStage = new Stage();
		Platform.setImplicitExit(false);
		javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
		Scene scene = new Scene(rootNode, 1280, 800);
		System.out.println("Adding Style...");
		scene.getStylesheets().add("/styles/styles.css");

		mainStage.setTitle("Plugin Manager");
		mainStage.setAlwaysOnTop(Boolean.TRUE);
		mainStage.initStyle(StageStyle.UNDECORATED);
		mainStage.setResizable(Boolean.FALSE);
		mainStage.setScene(scene);
		mainStage.show();
		this.controller.setApp(this);
	}

	/**
	 * @Override public void start(Stage stage) throws Exception { String fxmlFile =
	 *           "/fxml/plugin-manager.fxml"; FXMLLoader loader = new FXMLLoader();
	 *           rootNode = (Parent)
	 *           loader.load(getClass().getResourceAsStream(fxmlFile));
	 *           PluginManagerController controller = (PluginManagerController)
	 *           loader.getController();
	 * 
	 *           final Task<ObservableList<String>> friendTask = new
	 *           Task<ObservableList<String>>() {
	 * @Override protected ObservableList<String> call() throws InterruptedException
	 *           { ObservableList<String> foundFriends =
	 *           FXCollections.<String>observableArrayList(); ObservableList<String>
	 *           availableFriends = FXCollections.observableArrayList("Fili",
	 *           "Kili", "Oin", "Gloin", "Thorin", "Dwalin", "Balin", "Bifur",
	 *           "Bofur", "Bombur", "Dori", "Nori", "Ori");
	 * 
	 *           updateMessage("Finding friends . . ."); for (int i = 0; i <
	 *           availableFriends.size(); i++) { Thread.sleep(400); updateProgress(i
	 *           + 1, availableFriends.size()); String nextFriend =
	 *           availableFriends.get(i); foundFriends.add(nextFriend);
	 *           updateMessage("Finding friends . . . found " + nextFriend); }
	 *           Thread.sleep(400); updateMessage("All friends found.");
	 *           controller.initilizeApplication(); return foundFriends; } };
	 * 
	 *           showSplash(stage, friendTask, () ->
	 *           showMainStage(friendTask.valueProperty())); new
	 *           Thread(friendTask).start(); }
	 */
	
	private void addAppToTray() {
		try {
			java.awt.Toolkit.getDefaultToolkit();

			if (!java.awt.SystemTray.isSupported()) {
				System.out.println("No system tray support, application exiting.");
				Platform.exit();
			}

			this.tray = java.awt.SystemTray.getSystemTray();
			java.awt.Image image = ImageIO.read(getClass().getResourceAsStream("/images/"+iconImageLoc));
			this.trayIcon = new java.awt.TrayIcon(image);

			this.trayIcon.addActionListener(event -> Platform.runLater(this::showStage));

			java.awt.MenuItem openItem = new java.awt.MenuItem("Show IT Bot");
			openItem.addActionListener(event -> Platform.runLater(this::showStage));

			java.awt.Font defaultFont = java.awt.Font.decode(null);
			java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
			openItem.setFont(boldFont);

			java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
			exitItem.addActionListener(event -> {
				notificationTimer.cancel();
				Platform.exit();
				tray.remove(trayIcon);
			});

			final java.awt.PopupMenu popup = new java.awt.PopupMenu();
			popup.add(openItem);
			popup.addSeparator();
			popup.add(exitItem);
			trayIcon.setPopupMenu(popup);

			notificationTimer.schedule(new TimerTask() {
				@Override public void run() {
					javax.swing.SwingUtilities.invokeLater(() -> trayIcon.displayMessage("Plugin Manager Notification",
							"The time is now " + timeFormat.format(new Date()), java.awt.TrayIcon.MessageType.INFO));
				}
			}, 5_000, 60_000);

			tray.add(trayIcon);
		} catch (java.awt.AWTException | IOException e) {
			System.out.println("Unable to init system tray");
			e.printStackTrace();
		}
	}

	private void showStage() {
		if (this.mainStage != null) {
			this.mainStage.setIconified(Boolean.FALSE);
			this.mainStage.show();
			this.mainStage.toFront();
		}
	}

	public void minimizeWindow() {
		this.mainStage.setIconified(Boolean.TRUE);
		this.mainStage.hide();
	}

	public void cancelNotification() {
		notificationTimer.cancel();
		tray.remove(trayIcon);
	}
}
