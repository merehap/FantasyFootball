import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Main {
	public static void main(String[] args) throws IOException {

		JFrame f = new MyFrame();
		f.setVisible(true);
	}
	
	public static class MyFrame extends JFrame {
		private static final long serialVersionUID = 1L;

		public MyFrame() throws IOException {
			
			List<Player> players = new ArrayList<>();
			Reader in = new FileReader("/home/sean/Downloads/FFA-CustomRankings.csv");
			
			List<CSVRecord> records = CSVFormat.DEFAULT.parse(in).getRecords();
			for (int index = 1; index < records.size(); index++) {
				CSVRecord record = records.get(index);
				players.add(new Player(
						record.get(2),
						Player.Position.fromCode(record.get(3)),
						Double.parseDouble(record.get(7))));
			}
			
			PlayerHierarchy hierarchy = new PlayerHierarchy(players);
			setTitle("Fantasy Football Drafter");
			setSize(600,600);
			setLocation(10,200);
			
			Container contentPane = this.getContentPane();
			
			final List<List<String>> drafterInfos = new ArrayList<>();
			drafterInfos.add(new ArrayList<String>());
			drafterInfos.add(new ArrayList<String>());
			
			DraftPanel draftPanel =
					new DraftPanel(players, drafterInfos, hierarchy);
			draftPanel.setVisible(false);

			contentPane.add(
					new SetupPanel(draftPanel, drafterInfos),
					BorderLayout.NORTH);
			contentPane.add(draftPanel);

			// Window Listeners
			addWindowListener(new WindowAdapter() {
			  	public void windowClosing(WindowEvent e) {
				   System.exit(0);
			  	}
			} );
		}
	}
}
