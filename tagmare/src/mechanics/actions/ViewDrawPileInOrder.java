package mechanics.actions;

/** {@link #execute()} does nothing; this is an entirely visual action that has no effect on the mechanics. */
public class ViewDrawPileInOrder extends AbstractAction {

	public ViewDrawPileInOrder(ActionSource source) {
		super(source);
	}

	@Override
	public void execute() {
		//nothing
	}

}
