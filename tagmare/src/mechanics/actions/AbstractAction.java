package mechanics.actions;

abstract class AbstractAction implements Action {

	private final ActionSource source;
	
	protected AbstractAction(ActionSource source) {
		this.source = source;
	}
	
	/** {@inheritDoc} <em>This method should only be overridden to provide a more specific return type.</em> */
	@Override
	public ActionSource source() {
		return source;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
}
