
public final class SimpleDrafter extends Drafter {

	@Override
	public Player.Position chooseBestChoice(PlayerHierarchy hierarchy) {
		
		PlayerHierarchy neededPositions =
				super.removeUnneededPositions(hierarchy);
		
		Player bestPlayer = null;
		Player.Position bestPosition = null;
		for(Player.Position position : Player.Position.values()) {
			if(!neededPositions.containsPosition(position)) {
				continue;
			}
			
			Player candidate = neededPositions.getBestPlayerAtPosition(position);
			if(bestPlayer == null || candidate.getVor() > bestPlayer.getVor()) {
				bestPlayer = candidate;
				bestPosition = position;
			}
		}
		
		return bestPosition;
	}

}
