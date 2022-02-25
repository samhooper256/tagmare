package base;

import base.temp.GameScene;
import javafx.application.Application;
import javafx.stage.Stage;
import mechanics.Hub;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(GameScene.INSTANCE);
		primaryStage.show();
		Hub.combat().start();
		System.out.println(Hub.deck());
	}
	
}
