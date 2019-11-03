package gui;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import readindata.ReadInData;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Gui {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	
	public String showHeaders() 
	{
		return "Catalog\tNumber\tRA\tDec\tMag\tMisc\tDescription\n";
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{	
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		ReadInData r = new ReadInData();
		r.openFile();
		r.readFile();
		r.closeFile();
		initialize(r);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ReadInData r) {
		frame = new JFrame();
		frame.setBounds(50, 1, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(382, 0, 812, 572);
		frame.getContentPane().add(scrollPane);
		
		final JTextArea textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		
		
		JButton btnShowAllData = new JButton("Show All Data");
		btnShowAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				textArea_1.setText("Showing all data\n");
				textArea_1.append(showHeaders());
				
				ArrayList<String> allDataArrayList = r.showAllData();
				
				for (int x = 0; x < allDataArrayList.size(); x++) 
				{
					textArea_1.append(allDataArrayList.get(x) + "\n");
				}
			}
		});
		btnShowAllData.setBounds(6, 35, 117, 70);
		frame.getContentPane().add(btnShowAllData);
		
		JButton btnShowNorth = new JButton("Show North");
		btnShowNorth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("Showing all objects in northern hemisphere\n");
				textArea_1.append(showHeaders());

				ArrayList<String> NorSresultArrayList = r.searchHemisphere("N");
				
				for (int x = 0; x < NorSresultArrayList.size(); x++) 
				{
					textArea_1.append(NorSresultArrayList.get(x) + "\n");
				}
			}
		});
		btnShowNorth.setBounds(6, 117, 117, 70);
		frame.getContentPane().add(btnShowNorth);
		
		JButton btnShowSouth = new JButton("Show South");
		btnShowSouth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("Showing all objects in southern hemisphere\n");
				textArea_1.append(showHeaders());

				ArrayList<String> NorSresultArrayList = r.searchHemisphere("S");
				
				for (int x = 0; x < NorSresultArrayList.size(); x++) 
				{
					textArea_1.append(NorSresultArrayList.get(x) + "\n");
				}
			}
		});
		btnShowSouth.setBounds(253, 117, 117, 70);
		frame.getContentPane().add(btnShowSouth);
		
		JButton btnShowStats = new JButton("Show Stats");
		btnShowStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				textArea_1.setText("Showing statistics for brightness of objects\n");
				textArea_1.append(showHeaders());

				Object[] calcResult = r.mathCalcStats();
				textArea_1.append("Mean: \t"+ calcResult[0] + "\n");
				textArea_1.append("Dimmest (Max): "+ calcResult[2] + "\t" + calcResult[1] + "\n");
				textArea_1.append("Brightest (Min): "+ calcResult[4] + "\t" + calcResult[3] + "\n");
				textArea_1.append("Median: \t"+ calcResult[5] + "\n");
			}
		});
		btnShowStats.setBounds(253, 35, 117, 70);
		frame.getContentPane().add(btnShowStats);
		
		textField = new JTextField();
		textField.setBounds(200, 243, 170, 70);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnFindObject = new JButton("Find Object");
		btnFindObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String numberSearch = textField.getText();
				String result = r.findNumber(numberSearch);
				textArea_1.setText("Showing information for object number " + numberSearch + "\n");
				textArea_1.append(showHeaders());
				textArea_1.append(result);
			}
		});
		btnFindObject.setBounds(6, 244, 117, 70);
		frame.getContentPane().add(btnFindObject);
		
		JLabel lblEnterObjectNumber = new JLabel("Enter object number");
		lblEnterObjectNumber.setBounds(210, 229, 160, 16);
		frame.getContentPane().add(lblEnterObjectNumber);
		
		JRadioButton rdbtnNumber = new JRadioButton("Number");
		rdbtnNumber.setBounds(229, 359, 141, 23);
		frame.getContentPane().add(rdbtnNumber);
		rdbtnNumber.setSelected(true);
		
		JRadioButton rdbtnDescription = new JRadioButton("Description");
		rdbtnDescription.setBounds(229, 383, 141, 23);
		frame.getContentPane().add(rdbtnDescription);
		
		JRadioButton rdbtnMisc = new JRadioButton("Misc");
		rdbtnMisc.setBounds(229, 407, 141, 23);
		frame.getContentPane().add(rdbtnMisc);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnNumber);
		radioGroup.add(rdbtnDescription);
		radioGroup.add(rdbtnMisc);
		
		JButton btnSearchOnHeading = new JButton("Search on:");
		btnSearchOnHeading.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int field = 0;
				 if (rdbtnNumber.isSelected() == true) field = 1;
				 if (rdbtnDescription.isSelected() == true) field = 2;
				 if (rdbtnMisc.isSelected() == true) field = 6;
				 
				 String descriptionSearch = textField_1.getText();
					
					ArrayList<String> foundDescriptionArrayList = r.sortDescription(descriptionSearch, field);

					textArea_1.setText("Showing information for " + descriptionSearch + "\n");
					if (foundDescriptionArrayList.size()==0) textArea_1.append("Nothing found\n");
					else textArea_1.append(showHeaders());

					for (String s:foundDescriptionArrayList) 
					{
						textArea_1.append(s + "\n");
					}
			}
		});
		btnSearchOnHeading.setBounds(6, 359, 117, 70);
		frame.getContentPane().add(btnSearchOnHeading);
		
		textField_1 = new JTextField();
		textField_1.setBounds(210, 459, 160, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblChooseHeadingTo = new JLabel("Choose heading to look under");
		lblChooseHeadingTo.setBounds(180, 343, 190, 16);
		frame.getContentPane().add(lblChooseHeadingTo);
		
		JLabel lblEnterWhatTo = new JLabel("Enter what to look for");
		lblEnterWhatTo.setBounds(220, 442, 150, 16);
		frame.getContentPane().add(lblEnterWhatTo);
		
		JLabel lblAstro = new JLabel("Astronomic Object Detection");
		lblAstro.setBounds(99, 2, 197, 16);
		frame.getContentPane().add(lblAstro);
		
		

		
	}
}
