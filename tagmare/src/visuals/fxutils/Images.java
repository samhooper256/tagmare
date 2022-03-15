package visuals.fxutils;

import java.util.*;

import base.Main;
import javafx.scene.image.*;
import mechanics.cards.*;
import mechanics.enemies.*;

/**
 * Utility class for creating {@link javafx.scene.image.Image} objects from resource files and working with
 * {@link ImageView ImageViews}.
 * @author Sam Hooper
 */
public final class Images {
	
	public static final Image
		//calendar:
		CALENDAR = get("calendar.png"),
		CALENDAR_TOP_RIBBON = get("calendar_top_ribbon.png"),
		CALENDAR_BOTTOM_RIBBON = get("calendar_bottom_ribbon.png"),
		DESK = get("desk.png"),
		//enemies:
		TEST_ENEMY = get("test_enemy.png"),
		//intents:
		SWORD_INTENT = get("sword_intent.png"),
		SHIELD_INTENT = get("shield_intent.png"),
		SHIELD_AND_SWORD_INTENT = get("shield_and_sword_intent.png"),
		DO_NOTHING_INTENT = get("do_nothing_intent.png"),
		ENEMY_SHIELD = get("enemy_shield.png"),
		//cards/combat:
		CARD_BACK = get("card_back.png"),
		ATTACK_BASE = get("attack_base.png"),
		PASSIVE_BASE = get("passive_base.png"),
		SINGED_BASE = get("singed_base.png"),
		SKILL_BASE = get("skill_base.png"),
		CARD_BORDER = get("card_border.png"),
		CARD_ENERGY_TAB = get("card_energy_tab.png"),
		CARD_IMAGE_BORDER = get("card_image_border.png"),
		TEST_IMAGE = get("test_image.png"),
		CARD_BACKLIGHT = get("card_backlight.png"),
		DISCIPLINE_CARD = get("discipline_card.png"),
		PLAYER_SHIELD = get("player_shield.png"),
		SKIP_ARROW = get("skip_arrow.png"),
		SKIP_ARROW_HOVERED = get("skip_arrow_hovered.png");
		
	
	private static final Map<CardTag, Image> CARD_IMAGE_MAP = new HashMap<>();
	private static final Map<EnemyTag, Image> ENEMY_IMAGE_MAP = new HashMap<>();
	
	static {
		//Cards:
		CARD_IMAGE_MAP.put(CardTag.DISCIPLINE, DISCIPLINE_CARD);
		
		//Enemies:
		// (none yet)
	}
	
	private Images() {}
	
	public static Image forCard(Card c) {
		Image image = CARD_IMAGE_MAP.get(c.tag());
		return image == null ? TEST_IMAGE : image;
	}
	
	public static Image forEnemy(Enemy e) {
		Image image = ENEMY_IMAGE_MAP.get(e.tag());
		return image == null ? TEST_ENEMY : image;
	}
	
	/**
	 * Returns the image given by {@code filename} by invoking {@link Image#Image(java.io.InputStream)} with
	 * the appropriate {@link InputStream}. The file indicated by {@code filename} must be in the "resources"
	 * folder.
	 * @return the image given by {@code filename}
	 */
	public static Image get(String filename) {
		return new Image(Main.getResourceStream(filename));
	}
	
	/**
	 * Returns the image given by {@code filename} with the given properties. The file indicated by
	 * {@code filename} must be in the "resources" folder.
	 * See {@link Image#Image(String, double, double, boolean, boolean) for details on the arguments.
	 * @return the {@link Image} described by the given filename with the given properties.
	 */
	public static Image get(	String filename,
								double requestedWidth,
								double requestedHeight,
								boolean preserveRatio,
								boolean smooth) {
		return new Image(Main.getResourceStream(filename), requestedWidth, requestedHeight, preserveRatio, smooth);
	}
	
	/**
	 * Equivalent to {@code setFitSize(view, fitSize, fitSize)}.
	 * */
	public static void setFitSize(ImageView view, double fitSize) {
		setFitSize(view, fitSize, fitSize);
	}
	
	public static void setFitSize(ImageView view, double fitWidth, double fitHeight) {
		view.setFitWidth(fitWidth);
		view.setFitHeight(fitHeight);
	}
	
}