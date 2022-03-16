package base;

import javafx.application.Platform;
import mechanics.Hub;
import mechanics.actions.DealDamage;
import visuals.GameScene;

public final class DebugConsole {

	private static final DebugConsole INSTANCE = new DebugConsole();
	
	public static DebugConsole get() {
		return INSTANCE;
	}
	
	public static void acceptInput(String line) {
		Platform.runLater(() -> get().accept(line));
	}
	
	public static void debugPrint() {
		GameScene.get().debugPrint();
		if(Hub.combat() != null)
			Hub.combat().debugPrint();
		else
			System.out.printf("=== There is currently no combat ===%n");
	}
	
	private void accept(String line) {
		System.out.printf("accepting line: %s%n", line);
		if(Hub.combat() != null && Hub.combat().isRunning())
			return;
		if(Hub.combat() != null) {
			String[] split = line.trim().split(" +");
			if(line.startsWith("deal ")) {
				if(split.length != 4 || !split[2].equals("to"))
					return;
				int damage = Integer.parseInt(split[1]), enemyIndex = Integer.parseInt(split[3]);
				Hub.stack().push(new DealDamage(damage, null, Hub.enemies().get(enemyIndex)));
				Hub.combat().resume();
			}
			else if(line.startsWith("applyp ")) {
//				//TODO apply modifiers to player?
			}
		}
		else {
			debugPrint();
		}
	}
	
}
