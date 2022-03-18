package visuals;

import base.Main;
import javafx.scene.text.*;

public final class Fonts {

	private static final String UI_FONT_NAME = "Courier New";
	private static final String NUMBERS_FONT_FILENAME = "Rubik-Regular.ttf";
	private static final String NUMBERS_FONT_BOLD_FILENAME = "Rubik-Medium.ttf";
	private Fonts() {
		
	}
	
	static {
		//load the necessary fonts:
		Font.loadFont(Main.getResourceStream(NUMBERS_FONT_FILENAME), 0);
		Font.loadFont(Main.getResourceStream(NUMBERS_FONT_BOLD_FILENAME), 0);
	}
	
	public static final Font
		UI_12 = Font.font(UI_FONT_NAME, 12),
		UI_12_BOLD = Font.font(UI_FONT_NAME, FontWeight.BOLD, 12),
		UI_14 = Font.font(UI_FONT_NAME, 14),
		UI_14_BOLD = Font.font(UI_FONT_NAME, FontWeight.BOLD, 14),
		UI_18 = Font.font(UI_FONT_NAME, 18),
		UI_18_BOLD = Font.font(UI_FONT_NAME, FontWeight.BOLD, 18),
		UI_24 = Font.font(UI_FONT_NAME, 24),
		UI_24_BOLD = Font.font(UI_FONT_NAME, FontWeight.BOLD, 24),
		UI_30 = Font.font(UI_FONT_NAME, 30),
		UI_30_BOLD = Font.font(UI_FONT_NAME, FontWeight.BOLD, 30),
		NUMBERS_18 = numbers(18),
		NUMBERS_24 = numbers(24),
		NUMBERS_30 = numbers(30),
		NUMBERS_36 = numbers(36),
		NUMBERS_48 = numbers(48),
		NUMBERS_72 = numbers(72),
		NUMBERS_12_BOLD = numbersBold(12),
		NUMBERS_18_BOLD = numbersBold(18),
		NUMBERS_24_BOLD = numbersBold(24),
		NUMBERS_30_BOLD = numbersBold(30),
		GEORGIA_12 = Font.font("Georgia", 12),
		GEORGIA_12_BOLD = Font.font("Georgia", FontWeight.BOLD, 12),
		GEORGIA_14 = Font.font("Georgia", 14),
		GEORGIA_14_BOLD = Font.font("Georgia", FontWeight.BOLD, 14),
		GEORGIA_18 = Font.font("Georgia", 18),
		GEORGIA_18_BOLD = Font.font("Georgia", FontWeight.BOLD, 18),
		GEORGIA_18_ITALIC = Font.font("Georgia", FontPosture.ITALIC, 18),
		GEORGIA_24 = Font.font("Georgia", 24),
		GEORGIA_24_BOLD = Font.font("Georgia", FontWeight.BOLD, 24),
		GEORGIA_30 = Font.font("Georgia", 30),
		GEORGIA_30_BOLD = Font.font("Georgia", FontWeight.BOLD, 30),
		GEORGIA_36 = Font.font("Georgia", 36),
		GEORGIA_36_BOLD = Font.font("Georgia", FontWeight.BOLD, 36),
		GEORGIA_36_ITALIC = Font.font("Georgia", FontPosture.ITALIC, 36),
		GEORGIA_72_ITALIC = Font.font("Georgia", FontPosture.ITALIC, 72);

	private static Font numbers(double size) {
		return Font.loadFont(Main.getResourceStream(NUMBERS_FONT_FILENAME), size);
	}
	
	private static Font numbersBold(double size) {
		return Font.loadFont(Main.getResourceStream(NUMBERS_FONT_BOLD_FILENAME), size);
	}
	
}
