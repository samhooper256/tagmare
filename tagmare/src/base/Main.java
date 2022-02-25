package base;

import javafx.application.Application;
import javafx.stage.Stage;
import mechanics.Hub;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public static GameScene gs;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		gs = new GameScene();
		gs.deckDisplay.update();
		primaryStage.setScene(gs);
		primaryStage.show();
		Hub.combat().start();
		System.out.println(Hub.deck());
	}
	
}
