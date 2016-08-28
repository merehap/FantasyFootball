import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Drafter {
	
	private List<Player> starters;
	protected List<Player> bench;
	
	public Drafter() {
		this.starters = new ArrayList<>();
		this.bench = new ArrayList<>();
	}
	
	/*
	 * 1. Determine position types that need spots.
	 * 2. Determine best player of each type.
	 * 3. Compare best player of each type against
	 * 		the expected player of that expected player
	 * 		of that type if we don't pick him, plus
	 *      the best player of the next best type.
	 * 4. If flex is empty and the best player of
	 *      all types is not in the remaining types,
	 *      draft him as the flex player.
	 */
	public Player draftBestChoice(PlayerHierarchy hierarchy) {
		Player chosenPlayer = chooseBestChoice(hierarchy);
		Player bestChoice =
				hierarchy.removeBestPlayerAtPosition(chosenPlayer.getPosition());

		this.starters.add(bestChoice);
		
		return bestChoice;
	}
	
	protected abstract Player chooseBestChoice(PlayerHierarchy hierarchy);
	
	protected PlayerHierarchy removeUnneededPositions(
			PlayerHierarchy hierarchy) {
		
		Set<Player.Position> filledPositions = this.getFilledPositions(hierarchy);
		
		return hierarchy.removePositions(filledPositions);
	}
	
	private Set<Player.Position> getFilledPositions(PlayerHierarchy hierarchy) {
		Set<Player.Position> positions = new HashSet<>();
		
		addPositionTypeIfFilled(
				positions, Player.Position.QUARTERBACK, 1);
		addPositionTypeIfFilled(
				positions, Player.Position.RUNNING_BACK, 2);
		addPositionTypeIfFilled(
				positions, Player.Position.WIDE_RECEIVER, 2);
		addPositionTypeIfFilled(
				positions, Player.Position.TIGHT_END, 1);
		addPositionTypeIfFilled(
				positions, Player.Position.KICKER, 1);
		addPositionTypeIfFilled(
				positions, Player.Position.DEFENSE, 1);
		
		return positions;
	}
	
	protected boolean hasFlexPlayer() {
		return this.positionCount(Player.Position.QUARTERBACK) > 1
				|| this.positionCount(Player.Position.RUNNING_BACK) > 2
				|| this.positionCount(Player.Position.WIDE_RECEIVER) > 2
				|| this.positionCount(Player.Position.TIGHT_END) > 1
				|| this.positionCount(Player.Position.KICKER) > 1
				|| this.positionCount(Player.Position.DEFENSE) > 1;
	}
	
	protected boolean allPositionsFilled() {
		return this.positionCount(Player.Position.QUARTERBACK) >= 1
				&& this.positionCount(Player.Position.RUNNING_BACK) >= 2
				&& this.positionCount(Player.Position.WIDE_RECEIVER) >= 2
				&& this.positionCount(Player.Position.TIGHT_END) >= 1
				&& this.positionCount(Player.Position.KICKER) >= 1
				&& this.positionCount(Player.Position.DEFENSE) >= 1;
	}
	
	private void addPositionTypeIfFilled(
			Set<Player.Position> positions,
			Player.Position position,
			int count) {
		
		if(this.positionCount(position) >= count) {
			positions.add(position);
		}
	}
	
	private int positionCount(Player.Position position) {
		int count = 0;
		for(Player player : this.starters) {
			if(player.getPosition().equals(position)) {
				count++;
			}
		}
		
		return count;
	}
}
