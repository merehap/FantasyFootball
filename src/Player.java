
public class Player {
	private final String name;
	private final Position position;
	private final double vor;
	
	public Player(
			final String name,
			final Position position,
			final double vor) {
		this.name = name;
		this.position = position;
		this.vor = vor;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public double getVor() {
		return this.vor;
	}
	
	public static enum Position {
		QUARTERBACK,
		WIDE_RECEIVER,
		RUNNING_BACK,
		TIGHT_END,
		KICKER,
		DEFENSE;
		
		public static Position fromCode(String code) {
			switch(code) {
			case "QB": return Position.QUARTERBACK;
			case "WR": return Position.WIDE_RECEIVER;
			case "RB": return Position.RUNNING_BACK;
			case "TE": return Position.TIGHT_END;
			case "K" : return Position.KICKER;
			case "DST": return Position.DEFENSE;
			default: throw new IllegalArgumentException(
					"'" + code + "' isn't a valid player position type");
			}
		}
	}
}
