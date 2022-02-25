package mechanics.actions;

abstract class AbstractAction implements Action {

	private final ActionSource source;
	
	protected AbstractAction(ActionSource source) {
		this.source = source;
	}
	
	@Override
	public final ActionSource source() {
		return source;
	}
	
}
