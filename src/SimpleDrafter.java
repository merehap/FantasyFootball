
public final class SimpleDrafter extends Drafter {

	@Override
	public Player chooseBestChoice(PlayerHierarchy hierarchy) {
		
		if(super.allPositionsFilled()) {
			return hierarchy.getBestPlayer();
		}
		
		PlayerHierarchy neededPositions =
				super.removeUnneededPositions(hierarchy);
		
		Player bestPlayer = null;
		for(Player.Position position : Player.Position.values()) {
			if(!neededPositions.containsPosition(position)) {
				continue;
			}
			
			Player candidate = neededPositions.getBestPlayerAtPosition(position);
			if(bestPlayer == null || candidate.getVor() > bestPlayer.getVor()) {
				bestPlayer = candidate;
			}
		}
		
		if(bestPlayer.getVor() < hierarchy.getBestPlayer().getVor()
				&& !super.hasFlexPlayer()) {
			return hierarchy.getBestPlayer();
		}
		
		return bestPlayer;
	}

}
