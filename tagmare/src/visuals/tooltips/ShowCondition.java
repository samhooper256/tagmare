package visuals.tooltips;

import java.util.function.BooleanSupplier;

/** A condition (represented as a {@link BooleanSupplier}) that, iff {@code false}, prevents a {@link TooltipColumn}
 * from showing when the mouse enters the {@link TooltipManager#region() region}. */
public interface ShowCondition extends BooleanSupplier {

}
