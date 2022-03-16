package mechanics.cards;

import java.util.function.Supplier;

import mechanics.cards.attacks.*;
import mechanics.cards.passives.TakeABreak;
import mechanics.cards.singed.Guilt;
import mechanics.cards.skills.*;
import utils.English;

public enum CardTag {
	//Texts should be punctuated.
	//STARTER CARDS:
	DO_HOMEWORK("Do Homework", 1, true, DoHomework::new,
			ct("Deal D0 damage.", DoHomework.DAMAGE)),
	REVIEW_NOTES("Review Notes", 1, false, ReviewNotes::new,
			ct("Gain B0 block.", ReviewNotes.BLOCK)),
	//ATTACKS:
	PROCRASTINATE("Procrastinate", 1, true, Procrastinate::new,
			String.format("Next turn, deal %d damage. Motivation and Discipline do not apply to this card.", Procrastinate.DAMAGE)),
	GRIND("Grind", 2, true, Grind::new,
			ct(String.format("Deal D0 damage %d times.", Grind.TIMES), Grind.DAMAGE)),
	QUIZLET("Quizlet", 1, true, Quizlet::new,
			ct("Deal D0 damage. Can only be used on AP Classroom assignments.", Quizlet.DAMAGE)),
	RAGE("Rage", 1, true, Rage::new,
			ct(String.format("Deal D0 damage %d times. Immediately discard the next card you draw.", Rage.TIMES), Rage.DAMAGE)),
	ALL_NIGHTER("All-Nighter", 2, false, AllNighter::new,
			ct("Deal D0 damage to ALL enemies. End your turn. You cannot play any cards next turn.", AllNighter.DAMAGE), true),
	DEFENESTRATE("Defenestrate", 1, false, Defenestrate::new,
			ct("Discard your hand. Deal D0 damage to a random enemy for each card discarded.", Defenestrate.DAMAGE)),
	WRITE_NONSENSE("Write Nonsense", 1, true, WriteNonsense::new,
			ct(String.format("Deal D0 damage. At the end of your turn, take %d damage.", WriteNonsense.NONSENSE), WriteNonsense.DAMAGE)),
	PUSH_THROUGH("Push Through", 1, true, PushThrough::new,
			ct("Deal D0 damage. Can only be played if you have 1 energy.", PushThrough.DAMAGE)),
	CTRL_F("Ctrl+F", 1, true, CtrlF::new,
			String.format("Remove %d health from an enemy.", CtrlF.HEALTH_LOSS)),
	POMODORO("Pomodoro", 2, true, Pomodoro::new,
			ct("Deal D0 damage. The next card you play this turn cannot be an attack and must not require more than 1 energy.", Pomodoro.DAMAGE)),
	BLUFF("Bluff", 1, true, Bluff::new,
			ct("Deal D0 damage. If this does not kill the enemy, discard a random card from your hand.", Bluff.DAMAGE)),
	FREE_TIME("Free Time", 0, true, FreeTime::new,
			ct("Deal D0 damage. Gain B0 block.", FreeTime.DAMAGE, FreeTime.BLOCK)),
	DIVIDE_AND_CONQUER("Divide and Conquer", 1, false, DivideAndConquer::new,
			ct(String.format("Deal D0 damage to a random enemy %d times.", DivideAndConquer.TIMES), DivideAndConquer.DAMAGE)),
	BEFORE_MIDNIGHT("Before Midnight", 1, true, BeforeMidnight::new,
			ct(String.format("Deal D0 damage and draw %d %s. Can only be played if you have played %d or fewer cards this turn.",
			BeforeMidnight.DRAW, English.plural("card", BeforeMidnight.DRAW), BeforeMidnight.MAX_CARDS_PLAYED), BeforeMidnight.DAMAGE)),
	//SKILLS:
	MOTIVATIONAL_VIDEO("Motivational Video", 0, false, MotivationalVideo::new,
			ct("Gain W Motivation. Decrease the effectiveness of all Motivational"
			+ " Videos by 1 for the rest of this combat.", MotivationalVideo.BASE_EFFECTIVENESS)),
	DISCIPLINE("Discipline", 1, false, Discipline::new,
			ct(String.format("Attacks deal %.0f%% more damage for the rest of this combat.", 
			mechanics.modifiers.buffs.Discipline.PERCENT * 100))),
	YOGA("Yoga", 1, false, Yoga::new,
			String.format("Draw %d cards. Discard between %d and %d cards.", Yoga.DRAW, Yoga.MIN_DISCARD, Yoga.MAX_DISCARD)),
	PLANNER("Planner", 1, false, Planner::new,
			ct("At the start of next turn, gain B0 block.", Planner.BLOCK)),
	STUDY("Study", 1, false, Study::new,
			ct("Gain B0 block.", Study.BLOCK)),
	CAFFEINE("Caffeine", 0, false, Caffeine::new,
			ct(String.format("Gain %d energy.", Caffeine.ENERGY)), true),
	READ_FOR_FUN("Read for Fun", 1, false, ReadForFun::new,
			ct(String.format("Gain B0 block. Draw %d %s.", ReadForFun.DRAW, English.plural("card", ReadForFun.DRAW)),
			ReadForFun.BLOCK)),
	CRAM("Cram", 2, false, Cram::new,
			ct("Gain B0 block. Gain 1 Tired.", Cram.BLOCK)),
	EXCUSE("Excuse", 0, true, Excuse::new,
			ct("Cancel an enemy's attack. Increase the energy cost of all Excuses by 1."), true),
	MEMORIZE("Memorize", 1, false, Memorize::new,
			ct("Gain B0 block. You can no longer gain block from skills this turn.", Memorize.BLOCK)),
	RESERVES("Reserves", 0, false, Reserves::new,
			ct(String.format("Lose your remaining energy. At the start of next turn, gain this much energy plus %d.",
			Reserves.ADDITIONAL))),
	PACK_LUNCH("Pack Lunch", 1, false, PackLunch::new,
			ct("Next turn, gain 1 additional energy and draw 1 additional card.")),
	COPY("Copy", 1, false, Copy::new,
			ct("Your next card (that's not Copy) is played twice. Add a Guilt to the top of your draw pile."), true),
	FORESIGHT("Foresight", 1, false, Foresight::new,
			ct("See your draw pile in the order cards will be drawn.")),
	ORGANIZE("Organize", 1, false, Organize::new,
			ct("Return all skills (or as many as will fit) from your discard pile to your hand."), true),
	BRAG("Brag", 1, false, Brag::new,
			ct(String.format("Gain %d Motivation. Gain %d Toxic.", Brag.MOTIVATION, Brag.TOXIC))),
	//PASSIVES:
	TAKE_A_BREAK("Take A Break", 1, false, TakeABreak::new,
			ct(String.format("End your turn. At the start of next turn, gain %d Concentration.", TakeABreak.CONCENTRATION)), true),
	//SIGNED:
	GUILT("Guilt", -1, false, Guilt::new,
			ct("Unplayable. If this card is in your hand at the end of your turn, put it on top of your draw pile."));
	
	private static CardText ct(String formattedString, int... defaultValuesOfVariables) {
		return new CardText(formattedString, defaultValuesOfVariables);
	}
	
	private final String displayName;
	private final boolean targetted, oneTime;
	private final int energyCost;
	private final Supplier<Card> supplier;
	private final CardText text;
	
	/** Not {@link #isOneTime() one-time}. */
	CardTag(String displayName, int energyCost, boolean targetted, Supplier<Card> supplier, String text) {
		this(displayName, energyCost, targetted, supplier, new CardText(text));
	}
	
	/** Not {@link #isOneTime() one-time}. */
	CardTag(String displayName, int energyCost, boolean targetted, Supplier<Card> supplier, CardText text) {
		this(displayName, energyCost, targetted, supplier, text, false);
	}
	
	CardTag(String displayName, int energyCost, boolean targetted, Supplier<Card> supplier, CardText text,
			boolean oneTime) {
		this.displayName = displayName;
		this.energyCost = energyCost;
		this.targetted = targetted;
		this.supplier = supplier;
		this.text = text;
		this.oneTime = oneTime;
	}
	
	public String displayName() {
		return displayName;
	}
	
	public int energyCost() {
		return energyCost;
	}
	
	public boolean isTargetted() {
		return targetted;
	}
	
	public boolean isOneTime() {
		return oneTime;
	}
	
	public CardText text() {
		return text;
	}
	
	/** Generates a new {@link Card} every time. */
	public Card generate() {
		return supplier.get();
	}
	
}
