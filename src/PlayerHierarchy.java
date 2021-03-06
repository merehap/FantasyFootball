import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PlayerHierarchy {
	private final Map<Player.Position, List<Player>> map;
	
	public PlayerHierarchy(List<Player> players) {
		this.map = new HashMap<>();
		for(Player player : players) {
			if(this.map.containsKey(player.getPosition())) {
				this.map.get(player.getPosition()).add(player);
			} else {
				List<Player> tempPlayers = new ArrayList<>();
				tempPlayers.add(player);
				this.map.put(player.getPosition(), tempPlayers);
			}
		}
	}
	
	private PlayerHierarchy(Map<Player.Position, List<Player>> map) {
		this.map = new HashMap<>(map);
	}
	
	public boolean containsPosition(Player.Position position) {
		return this.map.containsKey(position);
	}
	
	public Player getBestPlayerAtPosition(Player.Position position) {
		if(!this.map.containsKey(position)) {
			throw new IllegalArgumentException(
					"No players found at position " + position);
		}
		
		return this.map.get(position).get(0);
	}
	
	public Player getBestPlayer() {
		
		Player bestPlayer = null;
		for(Player.Position position : Player.Position.values()) {
			Player candidate = this.getBestPlayerAtPosition(position);
			if(bestPlayer == null || candidate.getVor() > bestPlayer.getVor()) {
				bestPlayer = candidate;
			}
		}
		
		return bestPlayer;
	}
	
	public Player removeBestPlayerAtPosition(Player.Position position) {
		return this.map.get(position).remove(0);
	}
	
	public Player removePlayerByName(String name) {
		for(List<Player> players : this.map.values()) {
			for(int index = 0; index < players.size(); index++) {
				if(players.get(index).getName().equals(name)) {
					return players.remove(index);
				}
			}
		}
		
		return null;
	}
	
	public PlayerHierarchy removePositions(Set<Player.Position> positions) {
		Map<Player.Position, List<Player>> map = new HashMap<>(this.map);
		for(Player.Position position : positions) {
			map.remove(position);
		}
		
		return new PlayerHierarchy(map);
	}
}
