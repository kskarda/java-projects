import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class HouseholdProjectTracker extends JFrame implements ActionListener{
	
	private JPanel northPanel, southPanel, westPanel, eastPanel, westPanel2, eastPanel2, 
				checkPanel, categoryPanel, descriptPanel, costPanel, locationPanel, managerPanel, 
				priorityPanel, disruptPanel, datePanel, priorGroupPanel, locGroupPanel, disGroupPanel, manGroupPanel;
	private JLabel nameLabel, locationLabel, disruptLabel, categoryLabel, descriptLabel, 
				managerLabel, priorityLabel, dateLabel, costLabel, emptyLabel, cartoonLabel;
	private JTextField nameField, costField, dateField;
	private JTextArea descriptArea;
	private JRadioButton interiorButton, exteriorButton, majorButton, minorButton, ownerButton, 
				contractButton, highButton, medButton, lowButton;
	private ButtonGroup locationGroup, disruptGroup, managerGroup, priorityGroup;
	private JButton enterButton, resetButton;
	private JCheckBox designBox, structBox, roofBox, masonBox, paintBox, plumbBox, electBox, carpBox;
	private JComboBox statusBox, durationBox;
	private ImageIcon toolCartoon;
	
	public HouseholdProjectTracker() {
		
		super("Household Project Tracker");
		
		nameLabel = new JLabel("Project Name:");
		nameField = new JTextField(20);
		//add tool ImageIcon here
		toolCartoon = new ImageIcon("/Users/katherineskarda/eclipse-workspace/FinalProject/cartoonwithtool.jpg");
		cartoonLabel = new JLabel(toolCartoon);
		northPanel = new JPanel();
		northPanel.add(cartoonLabel);
		northPanel.add(nameLabel);
		northPanel.add(nameField);
		northPanel.setBackground(Color.pink);
		add(northPanel, BorderLayout.NORTH);
		
		enterButton = new JButton("Enter");
		enterButton.addActionListener(this);
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		southPanel = new JPanel();
		southPanel.add(enterButton);
		southPanel.add(resetButton);
		southPanel.setBackground(Color.pink);
		add(southPanel, BorderLayout.SOUTH);
		
		emptyLabel = new JLabel("\n");
		categoryLabel = new JLabel("Category: (select all that apply)");
		checkPanel = new JPanel(new GridLayout(5,2));
		designBox = new JCheckBox("Design");
		structBox = new JCheckBox("Structural");
		roofBox = new JCheckBox("Roofing");
		masonBox = new JCheckBox("Masonry");
		paintBox = new JCheckBox("Painting");
		plumbBox = new JCheckBox("Plumbing");
		electBox = new JCheckBox("Electrical");
		carpBox = new JCheckBox("Carpentry");
		checkPanel.add(designBox);
		checkPanel.add(structBox);
		checkPanel.add(roofBox);
		checkPanel.add(masonBox);
		checkPanel.add(paintBox);
		checkPanel.add(plumbBox);
		checkPanel.add(electBox);
		checkPanel.add(carpBox);
		checkPanel.add(emptyLabel);
		categoryPanel = new JPanel(new BorderLayout());
		categoryPanel.add(categoryLabel, BorderLayout.NORTH);
		categoryPanel.add(checkPanel, BorderLayout.CENTER);
		
		descriptPanel = new JPanel(new BorderLayout());
		descriptLabel = new JLabel("Project Description:");
		descriptArea = new JTextArea(10,20);
		descriptPanel.add(descriptLabel, BorderLayout.NORTH);
		descriptPanel.add(descriptArea, BorderLayout.CENTER);
		
		eastPanel = new JPanel(new GridLayout(2,1));
		eastPanel.add(categoryPanel);//, BorderLayout.NORTH);
		eastPanel.add(descriptPanel);//, BorderLayout.SOUTH);
		eastPanel2 = new JPanel();
		eastPanel2.add(eastPanel);
		add(eastPanel2, BorderLayout.CENTER);

		managerLabel = new JLabel("Project Manager:");
		ownerButton = new JRadioButton("Homeowner");
		contractButton = new JRadioButton("Contractor");
		managerGroup = new ButtonGroup();
		managerGroup.add(ownerButton);
		managerGroup.add(contractButton);
		manGroupPanel = new JPanel();
		manGroupPanel.add(ownerButton);
		manGroupPanel.add(contractButton);
		managerPanel = new JPanel(new BorderLayout());
		managerPanel.add(managerLabel, BorderLayout.NORTH);
		managerPanel.add(manGroupPanel, BorderLayout.WEST);
//		managerPanel.add(ownerButton, BorderLayout.WEST);
//		managerPanel.add(contractButton, BorderLayout.CENTER);
		//managerPanel.add(emptyLabel2, BorderLayout.SOUTH);
		
		disruptLabel = new JLabel("Disruption Level:");
		minorButton = new JRadioButton("Minor");
		majorButton = new JRadioButton("Major");
		disruptGroup = new ButtonGroup();
		disruptGroup.add(minorButton);
		disruptGroup.add(majorButton);
		disGroupPanel = new JPanel();
		disGroupPanel.add(majorButton);
		disGroupPanel.add(minorButton);
		disruptPanel = new JPanel(new BorderLayout());
		disruptPanel.add(disruptLabel, BorderLayout.NORTH);
		disruptPanel.add(disGroupPanel, BorderLayout.WEST);
//		disruptPanel.add(majorButton, BorderLayout.WEST);
//		disruptPanel.add(minorButton, BorderLayout.CENTER);
		//disruptPanel.add(emptyLabel2, BorderLayout.SOUTH);
		
		locationLabel = new JLabel("Location:");
		interiorButton = new JRadioButton("Interior");
		exteriorButton = new JRadioButton("Exterior");
		locationGroup = new ButtonGroup();
		locationGroup.add(interiorButton);
		locationGroup.add(exteriorButton);
		locGroupPanel = new JPanel();
		locGroupPanel.add(interiorButton);
		locGroupPanel.add(exteriorButton);
		locationPanel = new JPanel(new BorderLayout());
		locationPanel.add(locationLabel, BorderLayout.NORTH);
		locationPanel.add(locGroupPanel, BorderLayout.WEST);
//		locationPanel.add(interiorButton, BorderLayout.WEST);
//		locationPanel.add(exteriorButton, BorderLayout.CENTER);
		//locationPanel.add(emptyLabel2, BorderLayout.SOUTH);

		
		priorityLabel = new JLabel("Priority Level:");
		highButton = new JRadioButton("Critical");
		medButton = new JRadioButton("Moderate");
		lowButton = new JRadioButton("Discretionary");
		priorityGroup = new ButtonGroup();
		priorityGroup.add(highButton);
		priorityGroup.add(medButton);
		priorityGroup.add(lowButton);
		priorGroupPanel = new JPanel();
		priorGroupPanel.add(highButton);
		priorGroupPanel.add(medButton);
		priorGroupPanel.add(lowButton);
		priorityPanel = new JPanel(new BorderLayout());
		priorityPanel.add(priorityLabel, BorderLayout.NORTH);
		priorityPanel.add(priorGroupPanel, BorderLayout.WEST);
		//priorityPanel.add(emptyLabel2, BorderLayout.SOUTH);

		costLabel = new JLabel("Cost Estimate: $");
		costField = new JTextField(10);
		costPanel = new JPanel();
		costPanel.add(costLabel);//, BorderLayout.NORTH);
		costPanel.add(costField);//, BorderLayout.SOUTH);
		
		String statusChoices [] = {"~ Project Status ~", "To Do", "In Progress", "Complete"};
		statusBox = new JComboBox(statusChoices);
		
		String durationChoices [] = {"~ Duration ~", "Day", "Week", "Month", "Month+"};
		durationBox = new JComboBox(durationChoices);
		
		dateLabel = new JLabel("Start Date: (mm/dd/yy)");
		dateField = new JTextField(7);
		datePanel = new JPanel();
		datePanel.add(dateLabel);
		datePanel.add(dateField);
		
		westPanel = new JPanel(new GridLayout(8,1));
		westPanel.add(managerPanel);
		westPanel.add(priorityPanel);
		westPanel.add(disruptPanel);
		westPanel.add(locationPanel);
		westPanel.add(statusBox);
		westPanel.add(costPanel);
		westPanel.add(datePanel);
		westPanel.add(durationBox);
		westPanel2 = new JPanel();
		westPanel2.add(westPanel);
		add(westPanel2, BorderLayout.WEST);
		
		setVisible(true);
		setSize(800, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == enterButton) {
			String name = nameField.getText();
			String manager = "";
			if (ownerButton.isSelected()) 
				manager = "Homeowner";
			else if (contractButton.isSelected())
				manager = "Contractor";
			String priority = "";
			if (highButton.isSelected())
				priority = "Critical";
			else if (medButton.isSelected())	
				priority = "Moderate";
			else if (lowButton.isSelected())
				priority = "Discretionary";
			String disruption ="";
			if (majorButton.isSelected())
				disruption = "Major";
			else if (minorButton.isSelected())
				disruption = "Minor";
			String location = "";
			if (interiorButton.isSelected())
				location = "Interior";
			else if (exteriorButton.isSelected())
				location = "Exterior";
			String status = statusBox.getSelectedItem().toString();
			String cost = costField.getText();
			String startDate = dateField.getText();
			String duration = durationBox.getSelectedItem().toString();
			String category = "";
			if (designBox.isSelected())
				category += "\tDesign\n";
			if (structBox.isSelected())
				category += "\tStructural\n";
			if (roofBox.isSelected())
				category += "\tRoofing\n";
			if (masonBox.isSelected())
				category += "\tMasonry\n";
			if (paintBox.isSelected())
				category += "\tPainting\n";
			if (plumbBox.isSelected())
				category += "\tPlumbing\n";
			if (electBox.isSelected())
				category += "\tElectrical\n";
			if (carpBox.isSelected())
				category += "\tCarpentry\n";
			String description = descriptArea.getText();
			
			String output = "New Project\n\nProject Name: " + name + "\nProject Manager: " +
					manager + "\nPriority Level: " + priority + "\nDisruption Level: " +
					disruption + "\nLocation: " + location + "\nProject Status: " + status +
					"\nCost Estimate: $" + cost + "\nStart Date: " + startDate + "\nDuration: " +
					duration + "\nCategory:\n" + category + "Project Description: "+ description;
			JOptionPane.showMessageDialog(null, output);
		}
		else if (event.getSource() == resetButton) {
			nameField.setText("");
			managerGroup.clearSelection();
			priorityGroup.clearSelection();
			disruptGroup.clearSelection();
			locationGroup.clearSelection();
			statusBox.setSelectedIndex(0);
			costField.setText("");
			dateField.setText("");
			durationBox.setSelectedIndex(0);
			descriptArea.setText("");
			designBox.setSelected(false);
			structBox.setSelected(false);
			roofBox.setSelected(false);
			masonBox.setSelected(false);
			paintBox.setSelected(false);
			plumbBox.setSelected(false);
			electBox.setSelected(false);
			carpBox.setSelected(false);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HouseholdProjectTracker app = new HouseholdProjectTracker();

	}
	
}
