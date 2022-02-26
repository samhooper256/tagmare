package mechanics.actions;

import mechanics.Hub;

public class GainBlock extends AbstractAction {

	private int block;
	
	public GainBlock(int block, ActionSource source) {
		super(source);
		this.block = block;
	}
	
	public int block() {
		return block;
	}
	
	public void setBlock(int block) {
		this.block = block;
	}
	
	@Override
	public void execute() {
		Hub.block().gain(block());
	}
	
}
