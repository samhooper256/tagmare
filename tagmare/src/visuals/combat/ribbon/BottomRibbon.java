package visuals.combat.ribbon;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import visuals.*;
import visuals.fxutils.*;

public class BottomRibbon extends Pane {
	
	public static final double HEIGHT = 64, Y = GameScene.HEIGHT - HEIGHT;
	
	private final HealthBar healthBar;
	private final Buffs buffs;
	private final Debuffs debuffs;
	private final Shield shield;
	
	public BottomRibbon() {
		setLayoutY(Y);
		Nodes.setPrefAndMaxWidth(this, GameScene.WIDTH);
		setBackground(Backgrounds.of(Color.LIGHTGRAY));
		healthBar = new HealthBar();
		buffs = new Buffs();
		debuffs = new Debuffs();
		Nodes.setPrefAndMaxSize(buffs, GameScene.CENTER_X - HealthBar.WIDTH * .5, HEIGHT);
		debuffs.setLayoutX(GameScene.CENTER_X + HealthBar.WIDTH * .5);
		Nodes.setPrefAndMaxSize(debuffs, GameScene.CENTER_X - HealthBar.WIDTH * .5, HEIGHT);
		shield = new Shield();
		shield.setAnchorX(GameScene.CENTER_X - Shield.WIDTH * .5);
		getChildren().addAll(healthBar, buffs, debuffs, shield);
	}
	
	/** updates {@link #healthBar()} and {@link #shield()} and calls {@link #updateModifiers()}. */
	public void update() {
		healthBar().update();
		updateModifiers();
		shield().update();
	}
	
	public void startHNBTransition(boolean resume) {
		boolean hbc = healthBar.hasChanged(), sc = shield.hasChanged();
		if(hbc && !sc)
			healthBar.startTransition(resume);
		else if(!hbc && sc)
			shield.startTransition(resume);
		else if(hbc && sc) {
			if(healthBar.secToAnimateChange() > shield.secToAnimateChange()) {
				healthBar.startTransition(resume);
				shield.startTransition(false);
			}
			else {
				healthBar.startTransition(false);
				shield.startTransition(resume);
			}
		}
		else {
			if(resume)
				Vis.manager().checkedResumeFromAnimation();
		}
	}
	
	public void updateModifiers() {
		buffs.update();
		debuffs.update();
	}

	public HealthBar healthBar() {
		return healthBar;
	}

	public Shield shield() {
		return shield;
	}
	
	public Buffs buffs() {
		return buffs;
	}
	
	public Debuffs debuffs() {
		return debuffs;
	}
	
}