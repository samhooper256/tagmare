package mechanics.actions;

import mechanics.Hub;

public class GainBlock extends AbstractAction implements HasBlock {

	private int block;
	
	public GainBlock(int block, ActionSource source) {
		super(source);
		this.block = block;
	}
	
	@Override
	public int block() {
		return block;
	}
	
	@Override
	public void setBlock(int block) {
		this.block = block;
	}
	
	@Override
	public void execute() {
		Hub.player().block().gain(block());
	}
	
}
