import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Main {
	public static void main(String[] args) throws IOException {

		int drafterPosition = 4;
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
			Drafter drafter = new SimpleDrafter();
			drafter.draftBestChoice(hierarchy);
			drafter.draftBestChoice(hierarchy);
			setTitle(drafter.draftBestChoice(hierarchy).getName());
			setSize(600,200); // default size is 0,0
			setLocation(10,200); // default is 0,0 (top left corner)
			
			Container contentPane = this.getContentPane();
			
			TableModel dataModel = new AbstractTableModel() {

				private static final long serialVersionUID = 1L;
				
				public int getColumnCount() {
					return 1;
				}

				public int getRowCount() {
					return 10;
				}

				public Object getValueAt(int row, int col) {
					return new Integer(row * col);
				}
			};
			
			JTable table = new JTable(dataModel);
			
			JScrollPane drafterPane = new JScrollPane(table);

			drafterPane.setVisible(false);
			
			contentPane.add(new SetupPanel(drafterPane), BorderLayout.NORTH);
			
			contentPane.add(drafterPane);

			// Window Listeners
			addWindowListener(new WindowAdapter() {
			  	public void windowClosing(WindowEvent e) {
				   System.exit(0);
			  	}
			} );
		}
	}
}
