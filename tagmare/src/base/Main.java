package base;

import base.temp.TempScene;
import javafx.application.Application;
import javafx.stage.Stage;
import mechanics.Hub;
import visuals.GameScene;

public class Main extends Application {

	public static final boolean USE_TEMP = false;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(USE_TEMP ? TempScene.get() : GameScene.get());
		primaryStage.show();
		primaryStage.setMaximized(true);
		Hub.combat().start();
		System.out.println(Hub.deck());
		Updater.startTimer();
	}
	
}
