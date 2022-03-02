package base;

import visuals.*;

final class VisualManagerHolder {

	public static final VisualManager INSTANCE = Main.USE_TEMP ? new TempVisualManager() : new VisualManagerImpl();
	
}
