package visuals;

import javafx.scene.text.*;

public final class Fonts {

	private static final String UI_FONT_NAME = "Courier New";
	
	private Fonts() {
		
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

}
