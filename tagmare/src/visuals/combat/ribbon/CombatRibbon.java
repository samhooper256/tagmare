package visuals.combat.ribbon;

import javafx.scene.layout.Pane;
import utils.Strings;
import visuals.*;
import visuals.fxutils.*;

public class CombatRibbon extends Pane {
	
	public static final double HEIGHT = 64, Y = GameScene.HEIGHT - HEIGHT, MODIFIER_ARROW_WIDTH = 144;
	
	private final Sprite background;
	private final HealthBar healthBar;
	private final Buffs buffs;
	private final Debuffs debuffs;
	private final Shield shield;
	
	public CombatRibbon() {
		setLayoutY(Y);
		Nodes.setPrefAndMaxWidth(this, GameScene.WIDTH);
		background = new Sprite(Images.COMBAT_RIBBON);
		healthBar = new HealthBar();
		buffs = new Buffs();
		debuffs = new Debuffs();
		Nodes.setPrefAndMaxSize(buffs, GameScene.CENTER_X - .5 * HealthBar.WIDTH - MODIFIER_ARROW_WIDTH, HEIGHT);
		Nodes.setPrefAndMaxSize(debuffs, GameScene.WIDTH - Debuffs.X, HEIGHT);
		shield = new Shield();
		shield.setAnchorX(GameScene.CENTER_X - Shield.WIDTH * .5);
		getChildren().addAll(background, healthBar, buffs, debuffs, shield);
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
	
	public void debugPrint(int indent) {
		System.out.printf("%s%s:%n", getClass().getSimpleName(), Strings.repeat("\t", indent));
		buffs.debugPrint(indent + 1);
	}
	
}