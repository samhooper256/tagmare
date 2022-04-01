package visuals.fxutils;

import java.util.*;

import base.Main;
import javafx.scene.image.*;
import mechanics.cards.*;
import mechanics.enemies.*;
import mechanics.enemies.intents.*;
import mechanics.modifiers.*;

/**
 * Utility class for creating {@link javafx.scene.image.Image} objects from resource files and working with
 * {@link ImageView ImageViews}.
 * @author Sam Hooper
 */
public final class Images {
	
	public static final double MODIFIER_ICON_HEIGHT = 32;
	
	public static final Image
		//calendar:
		CALENDAR = get("calendar.png"),
		CALENDAR_TOP_RIBBON = get("calendar_top_ribbon.png"),
		CALENDAR_BOTTOM_RIBBON = get("calendar_bottom_ribbon.png"),
		DESK = get("desk.png"),
		//enemies:
		TEST_ENEMY = get("test_enemy.png"),
		//modifiers:
		AP_CLASSROOM_MODIFIER = get("ap_classroom_modifier.png"),
		PROCRASTINATED_MODIFIER = get("procrastinated_modifier.png"),
		TEST_MODIFIER = get("test_modifier.png"),
		PACKED_MODIFIER = get("packed_modifier.png"),
		MOTIVATION_MODIFIER = get("motivation_modifier.png"),
		DISCIPLINE_MODIFIER = get("discipline_modifier.png"),
		MENTAL_EXPANSION_MODIFIER = get("mental_expansion_modifier.png"),
		ENLIGHTENED_MODIFIER = get("enlightened_modifier.png"),
		TIRED_MODIFIER = get("tired_modifier.png"),
		KNOCKED_OUT_MODIFIER = get("knocked_out_modifier.png"),
		SUGAR_CRASH_MODIFIER = get("sugar_crash_modifier.png"),
		DEFENESTRATING_MODIFIER = get("defenestrating_modifier.png"),
		NONSENSE_MODIFIER = get("nonsense_modifier.png"),
		TOMATOED_MODIFIER = get("tomatoed_modifier.png"),
		CHEATING_MODIFIER = get("cheating_modifier.png"),
		ON_LEAVE_MODIFIER = get("on_leave_modifier.png"),
		CONCENTRATION_MODIFIER = get("concentration_modifier.png"),
		PLANNING_AHEAD_MODIFIER = get("planning_ahead_modifier.png"),
		MEMORIZING_MODIFIER = get("memorizing_modifier.png"),
		CLOCKED_MODIFIER = get("clocked_modifier.png"),
		TOXIC_MODIFIER = get("toxic_modifier.png"),
		//intents:
		SWORD_INTENT = get("sword_intent.png"),
		SHIELD_INTENT = get("shield_intent.png"),
		BUFF_INTENT = get("buff_intent.png"),
		DEBUFF_INTENT = get("debuff_intent.png"),
		ENEMY_SHIELD = get("enemy_shield.png"),
		//cards/combat:
		COMBAT_RIBBON = get("combat_ribbon.png"),
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
	private static final Map<ModifierTag, Image> MODIFIER_IMAGE_MAP = new HashMap<>();
	private static final Map<IntentPartTag, Image> INTENT_PART_IMAGE_MAP = new HashMap<>();
	
	static {
		//Cards:
		CARD_IMAGE_MAP.put(CardTag.DISCIPLINE, DISCIPLINE_CARD);
		
		//Enemies:
		// (none yet)
		
		//Modifiers:
		MODIFIER_IMAGE_MAP.put(ModifierTag.AP_CLASSROOM, AP_CLASSROOM_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.PROCRASTINATED, PROCRASTINATED_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.PACKED, PACKED_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.MOTIVATION, MOTIVATION_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.DISCIPLINE, DISCIPLINE_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.MENTAL_EXPANSION, MENTAL_EXPANSION_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.ENLIGHTENED, ENLIGHTENED_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.TIRED, TIRED_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.KNOCKED_OUT, KNOCKED_OUT_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.SUGAR_CRASH, SUGAR_CRASH_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.DEFENESTRATING, DEFENESTRATING_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.NONSENSE, NONSENSE_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.TOMATOED, TOMATOED_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.CHEATING, CHEATING_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.ON_LEAVE, ON_LEAVE_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.CONCENTRATION, CONCENTRATION_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.PLANNING_AHEAD, PLANNING_AHEAD_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.MEMORIZING, MEMORIZING_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.CLOCKED, CLOCKED_MODIFIER);
		MODIFIER_IMAGE_MAP.put(ModifierTag.TOXIC, TOXIC_MODIFIER);
		
		//IntentParts:
		INTENT_PART_IMAGE_MAP.put(IntentPartTag.ATTACK, SWORD_INTENT);
		INTENT_PART_IMAGE_MAP.put(IntentPartTag.BLOCK, SHIELD_INTENT);
		INTENT_PART_IMAGE_MAP.put(IntentPartTag.BUFF, BUFF_INTENT);
		INTENT_PART_IMAGE_MAP.put(IntentPartTag.DEBUFF, DEBUFF_INTENT);
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
	
	public static Image forModifier(Modifier m) {
		return forModifier(m.tag());
	}
	
	public static Image forModifier(ModifierTag tag) {
		Image image = MODIFIER_IMAGE_MAP.get(tag);
		return image == null ? TEST_MODIFIER : image;
	}

	public static Image forIntentPart(IntentPart part) {
		return forIntentPart(part.tag());
	}
	
	public static Image forIntentPart(IntentPartTag tag) {
		Image image = INTENT_PART_IMAGE_MAP.get(tag);
		return image == null ? SHIELD_INTENT : image;
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