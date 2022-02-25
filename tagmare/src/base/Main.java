package base;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameScene gs = new GameScene();
		gs.deckDisplay.update();
		primaryStage.setScene(gs);
		primaryStage.show();
	}
	
}
