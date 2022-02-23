/*
 * MAANIK GOGNA
 * Final Project - Car Dealership Management System
 * Jun 14 2021
 * Original Plan: Would allow user to customize cars and 'sell them' while keeping track of the cars previously sold and allowing for the user to buy replacement parts.
 * Actual Project was unable to meet many aspects of the initial plan(see ReadMe).
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.*;
//import java.awt.List;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.util.stream.Collectors;
//import javax.swing.LayoutStyle.ComponentPlacement;
//import javax.swing.plaf.basic.BasicArrowButton;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.TableCellEditor;
//import javax.swing.table.TableCellRenderer;

public class MainClass extends JLayeredPane implements ActionListener{

	//Variable initialization
	int currentFocusedCar = 0;
	static ArrayList <Car> cars = new ArrayList<Car>();
	static ArrayList <CarPart> carPartsInventory = new ArrayList<CarPart>();
	static JTextPane displayText = new JTextPane(), totalBalanceText = new JTextPane();
	static StyledDocument formattedText = displayText.getStyledDocument();
	static JTextPane writtenPieceText = new JTextPane();
	static MainClass mainPanel = new MainClass();
	static JFrame mainFrame = new JFrame("Car Dealership Program");
	static double currentBalance = 0;
	JPanel mainScreenButtonPanel = new JPanel();//, subPanel = new JPanel();
	JLayeredPane recentCarPane = new JLayeredPane();
	SimpleAttributeSet textStyle = new SimpleAttributeSet();
	static SimpleAttributeSet title, subText;
	static JPanel budgetPanel, sellCarPanel, garagePanel, shopPanel, inventoryPanel, specSheetPanel;
	static ArrayList<Integer> carBodiesInventory, enginesInventory, tiresInventory, brakesInventory;

	ImageIcon specSheetIcon = new ImageIcon("specSheet.png");
	ImageIcon budgetIcon = new ImageIcon("budget.png");
	ImageIcon inventoryIcon = new ImageIcon("inventory.png");
	ImageIcon shopIcon = new ImageIcon("shop.png");
	ImageIcon garageIcon = new ImageIcon("garage.png");
	ImageIcon sellCarIcon = new ImageIcon("sellCar.png");
	ImageIcon previousCarIcon = new ImageIcon("previousCar.png");
	ImageIcon nextCarIcon = new ImageIcon("nextCar.png");
	ImageIcon returnIcon = new ImageIcon("return.png");
	ImageIcon proceedIcon = new ImageIcon("proceed.png");
	//make static
	JButton specSheet = new JButton("Spec Sheet"), shop = new JButton("Shop"), 
			inventory = new JButton("Inventory"), budget = new JButton("Budget"),
			garage = new JButton("Garage"), sellCar = new JButton("Sell A Car"),
			previousCar = new JButton("Previous Car"), nextCar = new JButton("Next Car"),
			returnButton = new JButton("Return");//, proceedButton = new JButton("Proceed");
	JButton returnButtonSpecSheet, returnButtonShop, returnButtonInventory, returnButtonBudget, returnButtonGarage, returnButtonSellCar;
	JButton proceedButtonSellCar, proceedButtonShop = new JButton("Proceed");
	//	JButton[] jButtonArray = new JButton[]{returnButtonSpecSheet, returnButtonShop, returnButtonInventory, returnButtonBudget, returnButtonGarage, returnButtonSellCar};
	//	static JButton proceedButton;

	//	static ArrayList<String> fileNames = new ArrayList<String>();
	//	static JComboBox<String> fileOptions = new JComboBox<String>();
	//	static JPanel subPanel = new JPanel(), buttonPanel = new JPanel();



	//Budget Panel
	JTextPane totalRevenueText = new JTextPane(), totalCostText = new JTextPane(), revenueTitle = new JTextPane(), costTitle = new JTextPane(),
			balanceTitle = new JTextPane(), balanceText = new JTextPane(), sortingText = new JTextPane();
	static JScrollPane revenueScroll, costScroll;


	//Sell New Panel
	static int sellCarManufacturerIndex, tireLabelSize = 0, brakeLabelSize = 0, currentTireValue = 0, currentBrakeValue = 0;
	JTextPane statsText = new JTextPane(), carCost = new JTextPane(), manufacturerTitle = new JTextPane(), manufacturerList = new JTextPane();
	static JLabel sellCarLabel, sellCarTireOneLabel, sellCarTireTwoLabel, sellCarBrakeLabel;
	static JPanel manufacturerPanel, orderScreen;
	static JScrollPane manufacturerScroll;
	ArrayList<JButton> manufacturerButtons = new ArrayList<JButton>(), engineButtons, tireButtons, brakeButtons;
	static Map<String, ImageIcon> manufacturerMap;
	static boolean sellCarManufacturerSelected;
	static String sellCarManufacturer;
	static JLayeredPane sellCarDisplayPanel;
	static ImageIcon BMWBrandIcon, mitsubishiBrandIcon, audiBrandIcon, toyotaBrandIcon, hondaBrandIcon, fordBrandIcon, chevroletBrandIcon, volkswagenBrandIcon, miscBrandIcon,
	BMWWhiteIcon, audiWhiteIcon, toyotaWhiteIcon, hondaWhiteIcon, fordWhiteIcon, volkswagenWhiteIcon, chevroletWhiteIcon,
	tireOneIcon, tireTwoIcon, tireThreeIcon, tireFourIcon, tireFiveIcon,
	brakeOneIcon, brakeTwoIcon, brakeThreeIcon, brakeFourIcon, brakeFiveIcon,
	engineOneIcon, engineTwoIcon, engineThreeIcon, engineFourIcon, engineFiveIcon,
	carDisplayTireOneIcon, carDisplayTireTwoIcon, carDisplayTireThreeIcon, carDisplayTireFourIcon, carDisplayTireFiveIcon,
	carDisplayBrakeOneIcon, carDisplayBrakeTwoIcon, carDisplayBrakeThreeIcon, carDisplayBrakeFourIcon, carDisplayBrakeFiveIcon,
	carDisplayEngineOneIcon, carDisplayEngineTwoIcon, carDisplayEngineThreeIcon, carDisplayEngineFourIcon, carDisplayEngineFiveIcon;
	static ArrayList<ImageIcon> whiteCars = new ArrayList<>();
	static ArrayList<ImageIcon> tireIcons = new ArrayList<>();
	static ArrayList<ImageIcon> brakeIcons = new ArrayList<>();
	static ArrayList<ImageIcon> engineIcons = new ArrayList<>();
	//	static int currButtonIndex;
	//Garage Panel
	static JRadioButton graphicalViewButton, listViewButton;
	static JPanel garagePanelGraphicalViewPanel, garagePanelListViewPanel;
	//Shop panel
	static double runningCost = 0, origBalance = 0, newBalance = 0; 

	//Spec SHeet panel 
	ArrayList<JLabel> carBodiesLabelsSpecSheet = new ArrayList<>(), carEnginesLabelsSpecSheet = new ArrayList<>(),
			carTiresLabelsSpecSheet = new ArrayList<>(), carBrakesLabelsSpecSheet = new ArrayList<>();
	//	JLabel tempLabel;
	//	ImageIcon tempIcon;

	public MainClass() {
		//MainClass(frame) object constructor
		setLayout(null);
		setPreferredSize(new Dimension(1200, 700));
		title = new SimpleAttributeSet();
		subText= new SimpleAttributeSet();
		StyleConstants.setFontSize(title, 60);
		StyleConstants.setFontFamily(title, Font.SERIF);
		StyleConstants.setAlignment(title, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontSize(subText, 40);
		StyleConstants.setFontFamily(subText, "TimesRoman");
		StyleConstants.setAlignment(subText, StyleConstants.ALIGN_LEFT);
		StyleConstants.setItalic(subText, true);

		createMainScreenPanel();
		createSellCarPanel();
		createSpecSheetPanel();
		createInventoryPanel();
		createShopPanel();
		createBudgetPanel();
		createGaragePanel();

		//		subPanel.setVisible(false);
		mainScreenButtonPanel.setVisible(true);
		sellCarPanel.setVisible(false);
		budgetPanel.setVisible(false);
		garagePanel.setVisible(false);
		shopPanel.setVisible(false);
		inventoryPanel.setVisible(false);
		setVisible(true);
	}


	public void createMainScreenPanel() {
		//Purpose: Creates main screen panel and adds relevant components to it
		//Params: None
		//Returns: void
		add(mainScreenButtonPanel);
		mainScreenButtonPanel.add(recentCarPane);
		mainScreenButtonPanel.setBounds(new Rectangle(0,0,1200,700));

		//		displayText.setSize(new Dimension(300, 50));
		//		displayText.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 50));
		//		displayText.setText("Placeholder");
		//		displayText.setLocation(450, 300);
		//		displayText.setEditable(false);
		//		add(subPanel);
		//		subPanel.setBounds(new Rectangle(0,0,1200,700));
		//		subPanel.setLayout(null);
		//		totalBalanceText.setSize(new Dimension(500, 30));
		//		totalBalanceText.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 30));
		//		totalBalanceText.setText("Current Balance:");
		//		totalBalanceText.setLocation(subPanel.getWidth()/2-totalBalanceText.getWidth()/2, 5);
		//		totalBalanceText.setBackground(this.getBackground());
		//		totalBalanceText.setEditable(false);
		//		subPanel.add(displayText);
		//		subPanel.add(totalBalanceText);
		//		subPanel.setVisible(true);
		//		setLayer(subPanel, 1);
		//		StyleConstants.setAlignment(textStyle, StyleConstants.ALIGN_CENTER);
		//		formattedText.setParagraphAttributes(0, formattedText.getLength(), textStyle, false);
		//		formattedText = totalBalanceText.getStyledDocument();
		//		formattedText.setParagraphAttributes(0, formattedText.getLength(), textStyle, false);

		//Adding all buttons
		proceedButtonShop.setSize(new Dimension(300, 150));
		proceedButtonShop.setLocation(890,545);
		returnButton.setSize(new Dimension(300,150));
		returnButton.setLocation(7,545);

		mainScreenButtonPanel.add(specSheet);
		mainScreenButtonPanel.add(shop);
		mainScreenButtonPanel.add(inventory);
		mainScreenButtonPanel.add(previousCar);
		mainScreenButtonPanel.add(nextCar);
		mainScreenButtonPanel.add(budget);
		mainScreenButtonPanel.add(garage);
		mainScreenButtonPanel.add(sellCar);

		specSheet.setVisible(true);
		shop.setVisible(true);
		inventory.setVisible(true);
		previousCar.setVisible(true);
		nextCar.setVisible(true);
		budget.setVisible(true);
		garage.setVisible(true);
		sellCar.setVisible(true);

		specSheet.addActionListener(this);
		specSheet.setActionCommand("spec sheet");
		shop.addActionListener(this);
		shop.setActionCommand("shop");
		inventory.addActionListener(this);
		inventory.setActionCommand("inventory");
		previousCar.addActionListener(this);
		previousCar.setActionCommand("previous car");
		nextCar.addActionListener(this);
		nextCar.setActionCommand("next car");
		budget.addActionListener(this);
		budget.setActionCommand("budget");
		garage.addActionListener(this);
		garage.setActionCommand("garage");
		sellCar.addActionListener(this);
		sellCar.setActionCommand("sell New");
		returnButton.addActionListener(this);
		returnButton.setActionCommand("return");
		proceedButtonShop.addActionListener(this);
		proceedButtonShop.setActionCommand("proceed");

		//Setting panel layout
		GroupLayout layout = new GroupLayout(mainScreenButtonPanel);
		mainScreenButtonPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		specSheet.setPreferredSize(new Dimension(300, 150));
		shop.setPreferredSize(new Dimension(300, 120));
		previousCar.setPreferredSize(new Dimension(200, 100));

		layout.linkSize(SwingConstants.HORIZONTAL, previousCar, nextCar);
		layout.linkSize(SwingConstants.VERTICAL, previousCar, nextCar);
		layout.linkSize(SwingConstants.HORIZONTAL, specSheet, inventory, budget, garage, sellCar);
		layout.linkSize(SwingConstants.VERTICAL, specSheet, inventory, budget, garage, sellCar);

		returnIcon = new ImageIcon(returnIcon.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
		returnButton.setIcon(returnIcon);
		returnButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		returnButton.setHorizontalTextPosition(SwingConstants.CENTER);
		proceedIcon = new ImageIcon(proceedIcon.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
		proceedButtonShop.setIcon(proceedIcon);
		proceedButtonShop.setVerticalTextPosition(SwingConstants.BOTTOM);
		proceedButtonShop.setHorizontalTextPosition(SwingConstants.CENTER);

		specSheetIcon = new ImageIcon("specSheet.png");
		specSheetIcon = new ImageIcon(specSheetIcon.getImage().getScaledInstance(111, 100, Image.SCALE_FAST));
		specSheet.setIcon(specSheetIcon);
		specSheet.setHorizontalTextPosition(SwingConstants.CENTER);
		specSheet.setVerticalTextPosition(SwingConstants.BOTTOM);

		shopIcon = new ImageIcon(shopIcon.getImage().getScaledInstance(144, 90, Image.SCALE_FAST));
		shop.setIcon(shopIcon);
		shop.setIconTextGap(15);
		shop.setHorizontalTextPosition(SwingConstants.RIGHT);
		shop.setVerticalTextPosition(SwingConstants.CENTER);

		inventoryIcon = new ImageIcon(inventoryIcon.getImage().getScaledInstance(100, 100, Image.SCALE_FAST));
		inventory.setIcon(inventoryIcon);
		inventory.setHorizontalTextPosition(SwingConstants.CENTER);
		inventory.setVerticalTextPosition(SwingConstants.BOTTOM);

		previousCarIcon = new ImageIcon(previousCarIcon.getImage().getScaledInstance(200, 100, Image.SCALE_FAST));
		previousCar.setIcon(previousCarIcon);
		previousCar.setHorizontalTextPosition(SwingConstants.CENTER);
		previousCar.setVerticalTextPosition(SwingConstants.CENTER);
		previousCar.setBorder(BorderFactory.createEmptyBorder());
		previousCar.setContentAreaFilled(false);

		nextCarIcon = new ImageIcon(nextCarIcon.getImage().getScaledInstance(200, 100, Image.SCALE_FAST));
		nextCar.setIcon(nextCarIcon);
		nextCar.setHorizontalTextPosition(SwingConstants.CENTER);
		nextCar.setVerticalTextPosition(SwingConstants.CENTER);
		nextCar.setBorder(BorderFactory.createEmptyBorder());
		nextCar.setContentAreaFilled(false);

		budgetIcon = new ImageIcon(budgetIcon.getImage().getScaledInstance(147, 100, Image.SCALE_FAST));
		budget.setIcon(budgetIcon);
		budget.setVerticalTextPosition(SwingConstants.TOP);
		budget.setHorizontalTextPosition(SwingConstants.CENTER);

		garage.setVerticalTextPosition(SwingConstants.TOP);
		garage.setHorizontalTextPosition(SwingConstants.CENTER);

		sellCarIcon = new ImageIcon(sellCarIcon.getImage().getScaledInstance(220, 100, Image.SCALE_FAST));
		sellCar.setIcon(sellCarIcon);
		sellCar.setVerticalTextPosition(SwingConstants.TOP);
		sellCar.setHorizontalTextPosition(SwingConstants.CENTER);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(specSheet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(previousCar)
						.addComponent(budget))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(shop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)
						//						.addGap(GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						//						.addComponent(filler)
						.addComponent(garage))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(inventory)
						.addComponent(nextCar)
						.addComponent(sellCar))
				//				.addComponent(specSheet)
				//				.addComponent(shop)
				//				.addComponent(inventory)
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(specSheet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(shop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(inventory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						)
				.addGap(GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(previousCar)
						//						.addComponent(filler)
						//						.addComponent(filler, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nextCar)
						)
				.addGap(GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(budget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(garage)
						.addComponent(sellCar)
						)
				);

		cloneButton();
		mainScreenButtonPanel.setVisible(true);
		setLayer(mainScreenButtonPanel, 2);
	}

	public void createSpecSheetPanel() {
		//Purpose: Creates main screen panel and adds relevant components to it
		//Params: None
		//Returns: void
		specSheetPanel = new JPanel();
		specSheetPanel.setBounds(new Rectangle(0,0,1200,700));
		specSheetPanel.setLayout(null);
		specSheetPanel.setVisible(false);
		add(specSheetPanel);

		JTextPane specSheetTitle = new JTextPane(), specSheetSpecs = new JTextPane();
		JTabbedPane tabsPane = new JTabbedPane();
		JPanel bodySpecSheetPanel = new JPanel(), engineSpecSheetPanel = new JPanel(), tireSpecSheetPanel = new JPanel(), brakeSpecSheetPanel = new JPanel();
		JPanel[] specSheetPanels = new JPanel[4];
		SimpleAttributeSet title = new SimpleAttributeSet(), mainText = new SimpleAttributeSet();
		StyleConstants.setFontSize(title, 30);
		StyleConstants.setFontFamily(title, Font.SERIF);
		StyleConstants.setAlignment(title, StyleConstants.ALIGN_CENTER);

		StyleConstants.setFontSize(mainText, 13);
		StyleConstants.setFontFamily(mainText, Font.SANS_SERIF);
		StyleConstants.setAlignment(mainText, StyleConstants.ALIGN_LEFT);

		specSheetSpecs.setSize(returnButtonSpecSheet.getSize());
		specSheetSpecs.setLocation(specSheetPanel.getWidth()-returnButtonSpecSheet.getX()-returnButtonSpecSheet.getWidth(), returnButtonSpecSheet.getY());
		specSheetSpecs.setEditable(false);
		specSheetPanels[0] = bodySpecSheetPanel;
		specSheetPanels[1] = engineSpecSheetPanel;
		specSheetPanels[2] = tireSpecSheetPanel;
		specSheetPanels[3] = brakeSpecSheetPanel;

		//Creating title
		specSheetTitle.setSize(300,60);
		specSheetTitle.setLocation(returnButtonSpecSheet.getX()+returnButtonSpecSheet.getWidth()+10,returnButtonSpecSheet.getY()+5);
		specSheetTitle.setEditable(false);
		specSheetTitle.setBackground(getBackground());
		try {
			StyleConstants.setAlignment(title, StyleConstants.ALIGN_LEFT);
			formattedText = specSheetTitle.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
			formattedText.insertString(formattedText.getLength(), "Spec Sheet", title);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		//Creating tabbed pane
		tabsPane.setLocation(returnButtonSpecSheet.getX()+(returnButtonSpecSheet.getWidth()/2), 
				10);
		tabsPane.setSize(new Dimension(specSheetPanel.getWidth()-returnButtonSpecSheet.getWidth(), 
				specSheetPanel.getHeight()-returnButtonSpecSheet.getHeight()-15));

		tabsPane.addTab("Car Bodies", bodySpecSheetPanel);
		tabsPane.addTab("Engines", engineSpecSheetPanel);
		tabsPane.addTab("Tires", tireSpecSheetPanel);
		tabsPane.addTab("Brakes", brakeSpecSheetPanel);

		specSheetPanels[0].setBackground(Color.pink);
		specSheetPanels[1].setBackground(Color.pink);
		specSheetPanels[2].setBackground(Color.pink);
		specSheetPanels[3].setBackground(Color.pink);

		specSheetPanels[0].setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

		formattedText = specSheetSpecs.getStyledDocument();

		ImageIcon tempIcon = null;
		int currWidth = 0, currHeight = 0;
		Iterator<String> manuMapIterator = manufacturerMap.keySet().iterator();
		//Filling car body pane
		for (int count = 0; count < manufacturerMap.size(); count++) {
			tempIcon = whiteCars.get(count);
			currWidth = tempIcon.getIconWidth();
			currHeight = tempIcon.getIconHeight();
			tempIcon = new ImageIcon(tempIcon.getImage().getScaledInstance(350, (int)((double)currHeight/(double)currWidth*350), Image.SCALE_FAST));
			String manufacturer = manuMapIterator.next();

			carBodiesLabelsSpecSheet.add(new JLabel());
			carBodiesLabelsSpecSheet.get(carBodiesLabelsSpecSheet.size()-1).setSize(tempIcon.getIconWidth(), tempIcon.getIconHeight());
			carBodiesLabelsSpecSheet.get(carBodiesLabelsSpecSheet.size()-1).setBackground(getBackground());
			carBodiesLabelsSpecSheet.get(carBodiesLabelsSpecSheet.size()-1).setIcon(tempIcon);

			//MouseListener for car bodies
			carBodiesLabelsSpecSheet.get(carBodiesLabelsSpecSheet.size()-1).addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent e) {
					try {
						formattedText = specSheetSpecs.getStyledDocument();
						formattedText.remove(0, formattedText.getLength());
						formattedText.insertString(0, String.format("Specs(%s):\n", manufacturer), title);
						int offset = String.format("specs(%s):\n", manufacturer).length();
						String outputStr = "";
						formattedText.setParagraphAttributes(offset, formattedText.getLength(), mainText, false);
						if (new CarPart("body", manufacturer, null).getHorsepowerPercentIncrease()>=1)
							outputStr += String.format("%d%% more horsepower than the default car\n", (int)(100*(new CarPart("body", manufacturer, null).getHorsepowerPercentIncrease()-1)));
						else
							outputStr += String.format("%d%% less horsepower than the default car\n", (int)(100*(1- new CarPart("body", manufacturer, null).getHorsepowerPercentIncrease())));
						if (new CarPart("body", manufacturer, null).getMaxSpeedPercentIncrease()>=1)
							outputStr += String.format("%d%% higher max speed than the default car\n", (int)(100*(new CarPart("body", manufacturer, null).getMaxSpeedPercentIncrease()-1)));
						else
							outputStr+= String.format("%d%% lower max speed than the default car\n", (int)(100*(1- new CarPart("body", manufacturer, null).getMaxSpeedPercentIncrease())));
						if (new CarPart("body", manufacturer, null).getAccelerationPercentIncrease()>=1)
							outputStr += String.format("%d%% lesser acceleration time than the default car\n", -(int)(100*(1- new CarPart("body", manufacturer, null).getAccelerationPercentIncrease())));
						else
							outputStr += String.format("%d%% faster acceleration time than the default car\n", -(int)(100*(new CarPart("body", manufacturer, null).getAccelerationPercentIncrease()-1)));
						if (new CarPart("body", manufacturer, null).getBrakingDistancePercentIncrease()>=1) 
							outputStr += String.format("%d%% farther braking distance than the default car\n", (int)(100*(new CarPart("body", manufacturer, null).getBrakingDistancePercentIncrease()-1)));
						else
							outputStr += String.format("%d%% lesser braking distance than the default car\n", (int)(100*(1- new CarPart("body", manufacturer, null).getBrakingDistancePercentIncrease())));
						formattedText.insertString(offset, outputStr, mainText);
					} catch (BadLocationException e2) {
						e2.printStackTrace();
					}
				}
				public void mouseExited(MouseEvent e) {
					try {
						formattedText = specSheetSpecs.getStyledDocument();
						formattedText.remove(0, formattedText.getLength());
						formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
						formattedText.insertString(0, "Specs:\n", title);
						formattedText.setParagraphAttributes(6, formattedText.getLength(), mainText, true);
					} catch (BadLocationException e2) {
						e2.printStackTrace();
					}
				}
			});
			specSheetPanels[0].add(carBodiesLabelsSpecSheet.get(carBodiesLabelsSpecSheet.size()-1));
		}

		//Filling all other car parts panes
		for (int count = 0; count < 5; count++) {
			for (int tabCount = 1; tabCount < specSheetPanels.length; tabCount++) {
				specSheetPanels[tabCount].setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
				carEnginesLabelsSpecSheet.add(new JLabel());
				carTiresLabelsSpecSheet.add(new JLabel());
				carBrakesLabelsSpecSheet.add(new JLabel());
				if (tabCount == 1) {
					carEnginesLabelsSpecSheet.get(carEnginesLabelsSpecSheet.size()-1).setIcon(new ImageIcon(engineIcons.get(count).getImage().getScaledInstance(200, 200, Image.SCALE_FAST)));
					carEnginesLabelsSpecSheet.get(carEnginesLabelsSpecSheet.size()-1).setSize(carEnginesLabelsSpecSheet.get((carEnginesLabelsSpecSheet.size()-1)).getIcon().getIconWidth(), 
							carEnginesLabelsSpecSheet.get((carEnginesLabelsSpecSheet.size()-1)).getIcon().getIconHeight());
					carEnginesLabelsSpecSheet.get(carEnginesLabelsSpecSheet.size()-1).setBackground(getBackground());
					specSheetPanels[tabCount].add(carEnginesLabelsSpecSheet.get(carEnginesLabelsSpecSheet.size()-1));
				}else if (tabCount == 2) {
					carTiresLabelsSpecSheet.get(carTiresLabelsSpecSheet.size()-1).setIcon(new ImageIcon(tireIcons.get(count).getImage().getScaledInstance(200, 200, Image.SCALE_FAST)));
					carTiresLabelsSpecSheet.get(carTiresLabelsSpecSheet.size()-1).setSize(carTiresLabelsSpecSheet.get((carTiresLabelsSpecSheet.size()-1)).getIcon().getIconWidth(), 
							carTiresLabelsSpecSheet.get((carTiresLabelsSpecSheet.size()-1)).getIcon().getIconHeight());
					carTiresLabelsSpecSheet.get(carTiresLabelsSpecSheet.size()-1).setBackground(getBackground());

					specSheetPanels[tabCount].add(carTiresLabelsSpecSheet.get(carTiresLabelsSpecSheet.size()-1));
				}else if (tabCount == 3) {
					carBrakesLabelsSpecSheet.get(carBrakesLabelsSpecSheet.size()-1).setIcon(new ImageIcon(brakeIcons.get(count).getImage().getScaledInstance(200, 200, Image.SCALE_FAST)));
					carBrakesLabelsSpecSheet.get(carBrakesLabelsSpecSheet.size()-1).setSize(carBrakesLabelsSpecSheet.get((carBrakesLabelsSpecSheet.size()-1)).getIcon().getIconWidth(), 
							carBrakesLabelsSpecSheet.get((carBrakesLabelsSpecSheet.size()-1)).getIcon().getIconHeight());
					carBrakesLabelsSpecSheet.get(carBrakesLabelsSpecSheet.size()-1).setBackground(getBackground());
					specSheetPanels[tabCount].add(carBrakesLabelsSpecSheet.get(carBrakesLabelsSpecSheet.size()-1));
				}
				int currIndex = count+1;

				//MouseListener for car engines
				carEnginesLabelsSpecSheet.get(carEnginesLabelsSpecSheet.size()-1).addMouseListener(new MouseAdapter() { 
					public void mouseEntered(MouseEvent e) {
						try {
							formattedText = specSheetSpecs.getStyledDocument();
							formattedText.remove(0, formattedText.getLength());
							formattedText.insertString(0, String.format("Specs(Engine %d):\n", currIndex), title);
							int offset = String.format("Specs(Engine %d):\n", currIndex).length();
							int index = currIndex;
							String outputStr = "";
							formattedText.setParagraphAttributes(offset, formattedText.getLength(), mainText, false);
							if (index == 1) {
								outputStr += String.format("Horsepower: %.2f HP\n", new CarPart("engine", index, null).getHorsepower());
								outputStr += String.format("Max Speed: %.2f kph\n", new CarPart("engine", index, null).getMaxSpeed());
								outputStr += String.format("Acceleration Time: %.2f s\n", new CarPart("engine", index, null).getAccelerationTime());
							}else {
								outputStr += String.format("%d%% more horsepower than engine 1\n", (int)(100*(new CarPart("engine", index, null).getHorsepowerPercentIncrease()-1)));
								outputStr += String.format("%d%% higher max speed than engine 1\n", (int)(100*(new CarPart("engine", index, null).getMaxSpeedPercentIncrease()-1)));
								outputStr += String.format("%d%% lesser acceleration time than engine 1\n", (int)(100*(1- new CarPart("engine", index, null).getAccelerationPercentIncrease())));
							}
							formattedText.insertString(offset, outputStr, mainText);
						} catch (BadLocationException e2) {
							e2.printStackTrace();
						}
					}
					public void mouseExited(MouseEvent e) {
						try {
							formattedText = specSheetSpecs.getStyledDocument();
							formattedText.remove(0, formattedText.getLength());
							formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
							formattedText.insertString(0, "Specs:\n", title);
							formattedText.setParagraphAttributes(6, formattedText.getLength(), mainText, true);
						} catch (BadLocationException e2) {
							e2.printStackTrace();
						}
					}
				});

				//MouseListener for tires
				carTiresLabelsSpecSheet.get(carTiresLabelsSpecSheet.size()-1).addMouseListener(new MouseAdapter() { 
					public void mouseEntered(MouseEvent e) {
						try {
							formattedText = specSheetSpecs.getStyledDocument();
							formattedText.remove(0, formattedText.getLength());
							formattedText.insertString(0, String.format("Specs(Tires %d):\n", currIndex), title);
							int offset = String.format("Specs(Tires %d):\n", currIndex).length();
							int index = currIndex;
							String outputStr = "";
							formattedText.setParagraphAttributes(offset, formattedText.getLength(), mainText, false);
							if (currIndex == 1) {
								outputStr += "No modifications on performance";
							}else {
								outputStr += String.format("%d%% higher max speed modification\n", (int)(100*(new CarPart("tires", index, null).getMaxSpeedPercentIncrease()-1)));
								outputStr += String.format("%d%% faster acceleration modification\n", -(int)(100*(new CarPart("tires", index, null).getAccelerationPercentIncrease()-1)));
								outputStr += String.format("%d%% lesser braking distance modification\n", -(int)(100*(new CarPart("tires", index, null).getBrakingDistancePercentIncrease()-1)));
							}
							formattedText.insertString(offset, outputStr, mainText);
						} catch (BadLocationException e2) {
							e2.printStackTrace();
						}
					}
					public void mouseExited(MouseEvent e) {
						try {
							formattedText = specSheetSpecs.getStyledDocument();
							formattedText.remove(0, formattedText.getLength());
							formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
							formattedText.insertString(0, "Specs:\n", title);
							formattedText.setParagraphAttributes(6, formattedText.getLength(), mainText, true);
						} catch (BadLocationException e2) {
							e2.printStackTrace();
						}
					}
				});

				//MouseListener for brakes
				carBrakesLabelsSpecSheet.get(carBrakesLabelsSpecSheet.size()-1).addMouseListener(new MouseAdapter() { 
					public void mouseEntered(MouseEvent e) {
						try {
							formattedText = specSheetSpecs.getStyledDocument();
							formattedText.remove(0, formattedText.getLength());
							formattedText.insertString(0, String.format("Specs(Brakes %d):\n", currIndex), title);
							int offset = String.format("Specs(Brakes %d):\n", currIndex).length();
							int index = currIndex;
							String outputStr = "";
							formattedText.setParagraphAttributes(offset, formattedText.getLength(), mainText, false);
							if (currIndex == 1) {
								outputStr += String.format("Braking distance: %.2f m\n", (new CarPart("brakes", index, null).getBrakingDistance()));
								outputStr += "No other modifications on performance";
							}else 
								outputStr += String.format("%d%% lesser braking distance than brakes 1\n", -(int)(100*(new CarPart("brakes", index, null).getBrakingDistancePercentIncrease()-1)));
							formattedText.insertString(offset, outputStr, mainText);
						} catch (BadLocationException e2) {
							e2.printStackTrace();
						}
					}
					public void mouseExited(MouseEvent e) {
						try {
							formattedText = specSheetSpecs.getStyledDocument();
							formattedText.remove(0, formattedText.getLength());
							formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
							formattedText.insertString(0, "Specs:\n", title);
							formattedText.setParagraphAttributes(6, formattedText.getLength(), mainText, true);
						} catch (BadLocationException e2) {
							e2.printStackTrace();
						}
					}
				});
			}
		}

		specSheetPanel.add(specSheetTitle);
		specSheetPanel.add(specSheetSpecs);
		specSheetPanel.add(returnButtonSpecSheet);
		specSheetPanel.add(tabsPane);
	}

	public void createShopPanel() {
		//Purporse: creates shop screen panel and adds relevant components
		//Params: None
		//Returns: void
		shopPanel = new JPanel();
		shopPanel.setBounds(new Rectangle(0,0,1200,700));
		shopPanel.setLayout(null);
		shopPanel.setVisible(false);
		add(shopPanel);

		JTextPane shopTitle = new JTextPane(), originalBalanceText = new JTextPane(), costText = new JTextPane(), newBalanceText = new JTextPane(),
				originalBalanceValue = new JTextPane(), costValue = new JTextPane(), newBalanceValue = new JTextPane();
		JTabbedPane tabsPane = new JTabbedPane(); 
		DefaultTableModel tableModel = new DefaultTableModel(7,6);
		JTable[] shopTables = new JTable[4];
		JTable tireShopTable = new JTable(6, 3), brakeShopTable = new JTable(6,3), engineShopTable = new JTable(6,3), 
				bodyShopTable = new JTable(Car.getManufacturerList().size()+1,3), orderTable = new JTable(tableModel);
		shopTables[0] = bodyShopTable;
		shopTables[1] = engineShopTable;
		shopTables[2] = tireShopTable;
		shopTables[3] = brakeShopTable;

		//Adding text boxes
		shopTitle.setSize(130,60);
		shopTitle.setLocation(returnButtonShop.getX()+5,10);
		shopTitle.setEditable(false);
		shopTitle.setBackground(getBackground());

		originalBalanceText.setSize(400, 40);
		originalBalanceText.setLocation(returnButtonShop.getX()+returnButtonShop.getWidth()+10,returnButtonShop.getY()+5);
		originalBalanceText.setEditable(false);
		originalBalanceText.setBackground(getBackground());

		costText.setSize(originalBalanceText.getSize());
		costText.setLocation(originalBalanceText.getX(),originalBalanceText.getY()+originalBalanceText.getHeight()+10);
		costText.setEditable(false);
		costText.setBackground(getBackground());

		newBalanceText.setSize(originalBalanceText.getSize());
		newBalanceText.setLocation(costText.getX(),costText.getY()+costText.getHeight()+10);
		newBalanceText.setEditable(false);
		newBalanceText.setBackground(getBackground());

		originalBalanceValue.setSize(300,40);
		originalBalanceValue.setLocation(originalBalanceText.getX()+originalBalanceText.getWidth(), originalBalanceText.getY());
		originalBalanceValue.setEditable(false);
		originalBalanceValue.setBackground(getBackground());

		costValue.setSize(originalBalanceValue.getSize());
		costValue.setLocation(originalBalanceValue.getX(), costText.getY());
		costValue.setEditable(false);
		costValue.setBackground(getBackground());

		newBalanceValue.setSize(originalBalanceValue.getSize());
		newBalanceValue.setLocation(originalBalanceValue.getX(), newBalanceText.getY());
		newBalanceValue.setEditable(false);
		newBalanceValue.setBackground(getBackground());

		try {
			formattedText = shopTitle.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
			formattedText.insertString(formattedText.getLength(), "Shop", title);
			formattedText = originalBalanceText.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), subText, true);
			formattedText.insertString(formattedText.getLength(), "Original Balance:", subText);
			formattedText = costText.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), subText, true);
			formattedText.insertString(formattedText.getLength(), "Running Cost:", subText);
			formattedText = newBalanceText.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), subText, true);
			formattedText.insertString(formattedText.getLength(), "New Balance:", subText);

			formattedText = originalBalanceValue.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), subText, true);
			formattedText.insertString(formattedText.getLength(), "$" + origBalance, subText);

			formattedText = costValue.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), subText, true);
			formattedText.insertString(formattedText.getLength(), "$" + runningCost, subText);

			formattedText = newBalanceValue.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), subText, true);
			formattedText.insertString(formattedText.getLength(), "$" + newBalance, subText);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		//Adding tabbed pane
		tabsPane.setLocation(returnButtonShop.getX()+(returnButtonShop.getWidth()*2/3), 
				shopTitle.getY());
		tabsPane.setSize(new Dimension(shopPanel.getWidth()-(returnButtonShop.getWidth()*4/3), //
				shopPanel.getHeight()-returnButtonShop.getHeight() - shopTitle.getY()-5));
		tabsPane.setTabPlacement(JTabbedPane.TOP);

		tabsPane.addTab("Car Bodies", bodyShopTable);
		tabsPane.addTab("Engines", engineShopTable);
		tabsPane.addTab("Tires", tireShopTable);
		tabsPane.addTab("Brakes", brakeShopTable);

		for (int count = 0; count < shopTables.length; count++) {
			shopTables[count].setSize(tabsPane.getSize());
			shopTables[count].setDefaultEditor(Object.class, null);
			shopTables[count].setGridColor(Color.black);
			shopTables[count].setBorder(BorderFactory.createLineBorder(Color.black));
		}

		bodyShopTable.setRowHeight(0, 30);
		for (int count = 1; count < bodyShopTable.getRowCount(); count++) 
			bodyShopTable.setRowHeight(count, (bodyShopTable.getHeight()-bodyShopTable.getRowHeight(0))/(bodyShopTable.getRowCount()-1+bodyShopTable.getRowMargin()));

		engineShopTable.setRowHeight(0, 30);
		for (int count = 1; count < engineShopTable.getRowCount(); count++)
			engineShopTable.setRowHeight(count, (engineShopTable.getHeight()-engineShopTable.getRowHeight(0))/(engineShopTable.getRowCount()-1+engineShopTable.getRowMargin()));

		tireShopTable.setRowHeight(0, 30);
		for (int count = 1; count < tireShopTable.getRowCount(); count++)
			tireShopTable.setRowHeight(count, (tireShopTable.getHeight()-tireShopTable.getRowHeight(0))/(tireShopTable.getRowCount()-1+tireShopTable.getRowMargin()));

		brakeShopTable.setRowHeight(0, 30);
		for (int count = 1; count < brakeShopTable.getRowCount(); count++)
			brakeShopTable.setRowHeight(count, (brakeShopTable.getHeight()-brakeShopTable.getRowHeight(0))/(brakeShopTable.getRowCount()-1+brakeShopTable.getRowMargin()));

		orderTable.setRowHeight(50);
		orderTable.setRowHeight(0, 45);

		bodyShopTable.setBackground(Color.pink);
		engineShopTable.setBackground(Color.red);
		tireShopTable.setBackground(Color.yellow);
		brakeShopTable.setBackground(Color.blue);
		orderTable.setBackground(Color.blue.brighter());

		for (int count = 0; count < shopTables.length; count++) {
			shopTables[count].setDefaultRenderer(Object.class, new TableRenderer());
			if (count == 0) 
				shopTables[count].setValueAt("Manufacturer", 0, 0);
			else 
				shopTables[count].setValueAt("Scale Value", 0, 0);
			shopTables[count].setValueAt("Image", 0, 1);
			shopTables[count].setValueAt("Market Cost", 0, 2);
			shopTables[count].setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}

		ImageIcon tempIcon = null;
		int iconWidth = 0, iconHeight = 0;
		//Filling car bodies table
		Iterator<String> manufacturerIterator = manufacturerMap.keySet().iterator();
		for (int rowCount = 1; rowCount < shopTables[0].getRowCount(); rowCount++) {
			for (int columnCount = 0; columnCount < shopTables[0].getColumnCount(); columnCount++) {
				if (columnCount == 0)
					shopTables[0].setValueAt(manufacturerIterator.next(), rowCount, columnCount);
				else if (columnCount == 1) {
					tempIcon = whiteCars.get(rowCount-1);
					iconWidth = tempIcon.getIconWidth();
					iconHeight = tempIcon.getIconHeight();
					int tempNum = 0;

					if (iconWidth>iconHeight) {
						tempNum = iconWidth;
						iconWidth = shopTables[0].getColumnModel().getColumn(columnCount).getWidth();
						iconHeight = (int)((double)iconHeight/(double)tempNum*shopTables[0].getColumnModel().getColumn(columnCount).getWidth());
					}else {
						tempNum = iconHeight;
						iconHeight = shopTables[0].getRowHeight(rowCount);
						iconWidth = (int)((double)iconWidth/(double)tempNum*shopTables[0].getRowHeight(rowCount));
					}
					shopTables[0].setValueAt(tempIcon.getImage().getScaledInstance(tempIcon.getIconWidth()/
							tempIcon.getIconHeight()*shopTables[0].getRowHeight(rowCount), shopTables[0].getRowHeight(rowCount), Image.SCALE_SMOOTH), rowCount, columnCount);
				}
				else if (columnCount == 2) {
					shopTables[0].setValueAt(String.format("%.2f", new CarPart("body", (String)shopTables[0].getValueAt(rowCount, 0), new JLabel()).getCost()), rowCount, columnCount);
				}else if (columnCount == 3) {
					JButton tempButton = new JButton("BUY");
					shopTables[0].setValueAt(tempButton, rowCount, columnCount);
				}
			}
		}

		//Filling all other car parts table
		for (int count = 1; count < 4; count++)
			for (int rowCount = 1; rowCount < shopTables[1].getRowCount(); rowCount++) {
				for (int columnCount = 0; columnCount < shopTables[1].getColumnCount(); columnCount++) {
					if (columnCount == 0) {
						shopTables[count].setValueAt(rowCount, rowCount, columnCount);
					}else if (columnCount == 1) {
						iconHeight = shopTables[count].getRowHeight(rowCount);
						if (count == 1) 
							tempIcon = new ImageIcon(engineIcons.get(rowCount-1).getImage().getScaledInstance(iconHeight, iconHeight, Image.SCALE_AREA_AVERAGING));
						else if (count == 2) 
							tempIcon = new ImageIcon(tireIcons.get(rowCount-1).getImage().getScaledInstance(iconHeight, iconHeight, Image.SCALE_AREA_AVERAGING));
						else if (count == 3) 
							tempIcon = new ImageIcon(brakeIcons.get(rowCount-1).getImage().getScaledInstance(iconHeight, iconHeight, Image.SCALE_AREA_AVERAGING));
						shopTables[count].setValueAt(tempIcon.getImage(), rowCount, columnCount);
					}else if (columnCount == 2) {
						String partName = "";
						if (count == 1)
							partName = "engine";
						else if (count == 2)
							partName = "tires";
						else if (count == 3)
							partName = "brakes";
						shopTables[count].setValueAt(String.format("%.2f", new CarPart(partName, rowCount, new ImageIcon()).getCost()), rowCount, columnCount);
					}else if (columnCount == 3) {
						shopTables[count].setValueAt(new JButton("BUY"), rowCount, columnCount);
					}
				}
			}

		//Orders Table
		//		String[] filler = {"Scale Value/\n", "Manufacturer"};
		//		JList<String> thirdColHeader = new JList<String>();
		//		DefaultListCellRenderer listCellRenderer = (DefaultListCellRenderer)thirdColHeader.getCellRenderer();
		//		listCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		//		thirdColHeader.setBackground(null);
		//		thirdColHeader.setFont(new Font("TimesRoman", Font.ITALIC, 20));
		//		thirdColHeader.setListData(filler);
		//
		//		orderTable.setDefaultRenderer(Object.class, new TableRenderer());
		//		orderTable.setValueAt("Remove Item", 0, 0);
		//		orderTable.setValueAt("Item", 0, 1);
		//		orderTable.setValueAt(thirdColHeader, 0, 2);
		//		orderTable.setValueAt("Unit Cost", 0, 3);
		//		orderTable.setValueAt("Quantity", 0, 4);
		//		orderTable.setValueAt("Total Cost", 0, 5);
		//		//		orderTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		//		orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JButton shopButton = new JButton("SHOP");
		shopButton.setSize(new Dimension(returnButtonShop.getWidth()/2, returnButtonShop.getHeight()));
		shopButton.setLocation(shopPanel.getWidth()-returnButtonShop.getX()-returnButtonShop.getWidth()/2, returnButtonShop.getY());

		shopButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String[] possibilities = {"Brakes", "Tires", "Engines", "Car Bodies"};
				int initialSelection = JOptionPane.showOptionDialog(shopPanel, "What would you like to buy?", "Shop", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibilities, null);
				if (initialSelection == 0) {

				}else if (initialSelection == 1) {

				}else if (initialSelection == 2) {

				}else if (initialSelection == 3) {

				}
			}
		});

		shopPanel.add(shopTitle);
		shopPanel.add(originalBalanceText);
		shopPanel.add(costText);
		shopPanel.add(newBalanceText);
		shopPanel.add(returnButtonShop);
		shopPanel.add(tabsPane);
		shopPanel.add(originalBalanceValue);
		shopPanel.add(costValue);
		shopPanel.add(newBalanceValue);
		shopPanel.add(shopButton);
	}


	public void createInventoryPanel() {
		//Purporse: creates inventory screen panel and adds relevant components
		//Params: None
		//Returns: void
		inventoryPanel = new JPanel();
		inventoryPanel.setBounds(new Rectangle(0,0,1200,700));
		inventoryPanel.setLayout(null);
		inventoryPanel.setVisible(false);
		add(inventoryPanel);

		JTextPane inventoryTitle = new JTextPane();//, balanceText = new JTextPane();
		JTabbedPane tabsPane = new JTabbedPane(); 
		DefaultTableModel tireInventoryModel = new DefaultTableModel(6,4), brakeInventoryModel = new DefaultTableModel(6,4),
				engineInventoryModel = new DefaultTableModel(6,4), bodyInventoryModel = new DefaultTableModel(manufacturerMap.size()+1,4);//change body inventory rows(actually, all row counts)
		JTable[] inventoryTables = new JTable[4];
		JTable tireInventoryTable = new JTable(tireInventoryModel), brakeInventoryTable = new JTable(brakeInventoryModel), 
				engineInventoryTable = new JTable(engineInventoryModel), bodyInventoryTable = new JTable(bodyInventoryModel);

		inventoryTables[0] = bodyInventoryTable;
		inventoryTables[1] = engineInventoryTable;
		inventoryTables[2] = tireInventoryTable;
		inventoryTables[3] = brakeInventoryTable;

		//Adding text boxes
		inventoryTitle.setSize(300,60);
		inventoryTitle.setLocation(returnButtonShop.getX()+returnButtonShop.getWidth()+10,returnButtonShop.getY()+5);
		inventoryTitle.setEditable(false);
		inventoryTitle.setBackground(getBackground());

		//		balanceText.setSize(400, 40);
		//		balanceText.setLocation(inventoryTitle.getX(),inventoryTitle.getY()+inventoryTitle.getHeight()+10);
		//		balanceText.setEditable(false);
		//		balanceText.setBackground(getBackground());
		//		String balanceStr = "Current Balance: $" + currentBalance;
		try {
			StyleConstants.setAlignment(title, StyleConstants.ALIGN_LEFT);
			formattedText = inventoryTitle.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
			formattedText.insertString(formattedText.getLength(), "Inventory", title);

			//			StyleConstants.setAlignment(title, StyleConstants.ALIGN_RIGHT);
			//			formattedText = balanceText.getStyledDocument();
			//			formattedText.setParagraphAttributes(0, formattedText.getLength(), subText, true);
			//			formattedText.insertString(formattedText.getLength(), balanceStr, subText);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		//Adding tabbed pane
		tabsPane.setLocation(returnButtonInventory.getX()+(returnButtonInventory.getWidth()/2), 
				10);
		tabsPane.setSize(new Dimension(inventoryPanel.getWidth()-returnButtonInventory.getWidth(), 
				inventoryPanel.getHeight()-returnButtonInventory.getHeight()-15));

		tabsPane.addTab("Car Bodies", bodyInventoryTable);
		tabsPane.addTab("Engines", engineInventoryTable);
		tabsPane.addTab("Tires", tireInventoryTable);
		tabsPane.addTab("Brakes", brakeInventoryTable);

		bodyInventoryTable.setPreferredSize(tabsPane.getSize());

		bodyInventoryTable.setRowHeight(0, 37);
		//		Font tableFont = new Font("Helvetica", Font.BOLD, 12);

		//Filling car body table
		Iterator<String> manufacturerIterator = manufacturerMap.keySet().iterator();
		int numInventory = 0;
		for (int rowCount = 1; rowCount < bodyInventoryTable.getRowCount(); rowCount++) {
			bodyInventoryTable.setRowHeight(rowCount, (tabsPane.getHeight()-30)/(bodyInventoryTable.getRowCount()));
			for (int columnCount = 0; columnCount < bodyInventoryTable.getColumnCount(); columnCount++) {
				if (columnCount == 0)
					bodyInventoryTable.setValueAt(manufacturerIterator.next(), rowCount, columnCount);
				else if (columnCount == 1) {
					int iconWidth = whiteCars.get(rowCount-1).getIconWidth();
					int iconHeight = whiteCars.get(rowCount-1).getIconHeight();
					int tempNum = 0;

					if (iconWidth>iconHeight) {
						tempNum = iconWidth;
						iconWidth = bodyInventoryTable.getColumnModel().getColumn(columnCount).getWidth();
						iconHeight = (int)((double)iconHeight/(double)tempNum*bodyInventoryTable.getColumnModel().getColumn(columnCount).getWidth());
					}else {
						tempNum = iconHeight;
						iconHeight = bodyInventoryTable.getRowHeight(rowCount);
						iconWidth = (int)((double)iconWidth/(double)tempNum*bodyInventoryTable.getRowHeight(rowCount));
					}
					bodyInventoryTable.setValueAt(whiteCars.get(rowCount-1).getImage().getScaledInstance(whiteCars.get(rowCount-1).getIconWidth()/
							whiteCars.get(rowCount-1).getIconHeight()*bodyInventoryTable.getRowHeight(rowCount), bodyInventoryTable.getRowHeight(rowCount), Image.SCALE_SMOOTH), rowCount, columnCount);
				}else if (columnCount == 2)
					bodyInventoryTable.setValueAt(String.format("%.2f", new CarPart("body", (String)bodyInventoryTable.getValueAt(rowCount, 0), new JLabel()).getCost()), rowCount, columnCount);
				else if (columnCount == 3) {
					numInventory = findInventory("car body", (String)bodyInventoryTable.getValueAt(rowCount, 0), 0);
					bodyInventoryTable.setValueAt(""+ numInventory, rowCount, columnCount);
				}
			}
		}

		engineInventoryTable.setRowHeight(0, 30);
		tireInventoryTable.setRowHeight(0, 30);
		brakeInventoryTable.setRowHeight(0, 30);

		//Filling all other car parts table
		for (int rowCount = 1; rowCount < engineInventoryTable.getRowCount(); rowCount++) {
			engineInventoryTable.setRowHeight(rowCount, 80);
			tireInventoryTable.setRowHeight(rowCount, 80);
			brakeInventoryTable.setRowHeight(rowCount, 80);
			for (int columnCount = 0; columnCount < engineInventoryTable.getColumnCount(); columnCount++) {
				if (columnCount == 0) {
					engineInventoryTable.setValueAt(rowCount, rowCount, columnCount);
					tireInventoryTable.setValueAt(rowCount, rowCount, columnCount);
					brakeInventoryTable.setValueAt(rowCount, rowCount, columnCount);
				}else if (columnCount == 1) {
					engineInventoryTable.setValueAt(engineIcons.get(rowCount-1).getImage().getScaledInstance(engineInventoryTable.getRowHeight(rowCount), engineInventoryTable.getRowHeight(rowCount), Image.SCALE_AREA_AVERAGING), rowCount, columnCount);
					tireInventoryTable.setValueAt(tireIcons.get(rowCount-1).getImage().getScaledInstance(tireInventoryTable.getRowHeight(rowCount), tireInventoryTable.getRowHeight(rowCount), Image.SCALE_AREA_AVERAGING), rowCount, columnCount);
					brakeInventoryTable.setValueAt(brakeIcons.get(rowCount-1).getImage().getScaledInstance(brakeInventoryTable.getRowHeight(rowCount), brakeInventoryTable.getRowHeight(rowCount), Image.SCALE_AREA_AVERAGING), rowCount, columnCount);
				}
				else if (columnCount == 2) {
					engineInventoryTable.setValueAt(String.format("%.2f", new CarPart("engine", rowCount, new ImageIcon()).getCost()), rowCount, columnCount);
					tireInventoryTable.setValueAt(String.format("%.2f", new CarPart("tires", rowCount, new ImageIcon()).getCost()), rowCount, columnCount);
					brakeInventoryTable.setValueAt(String.format("%.2f", new CarPart("brakes", rowCount, new ImageIcon()).getCost()), rowCount, columnCount);
				}
				else if (columnCount == 3) {
					numInventory = findInventory("engine", "", rowCount);
					engineInventoryTable.setValueAt(""+ numInventory, rowCount, columnCount);
					numInventory = findInventory("tire", "", rowCount);
					tireInventoryTable.setValueAt(""+ numInventory, rowCount, columnCount);
					numInventory = findInventory("brake", "", rowCount);
					brakeInventoryTable.setValueAt(""+ numInventory, rowCount, columnCount);
				}
			}
		}

		//Filling all table headers
		for (int count = 0; count < inventoryTables.length; count++) {
			inventoryTables[count].setSize(tabsPane.getSize());
			inventoryTables[count].setDefaultEditor(Object.class, null);
			inventoryTables[count].setGridColor(Color.black);
			inventoryTables[count].setBorder(BorderFactory.createLineBorder(Color.black));
			inventoryTables[count].setBackground(Color.pink);

			inventoryTables[count].setDefaultRenderer(Object.class, new TableRenderer());
			if (count == 0)
				inventoryTables[count].setValueAt("Manufacturer", 0, 0);
			else
				inventoryTables[count].setValueAt("Scale Value", 0, 0);

			inventoryTables[count].setValueAt("Image", 0, 1);
			inventoryTables[count].setValueAt("Market Cost", 0, 2);
			inventoryTables[count].setValueAt("Quantity", 0, 3);
			inventoryTables[count].setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}

		inventoryPanel.add(inventoryTitle);
		inventoryPanel.add(balanceText);
		inventoryPanel.add(returnButtonInventory);
		inventoryPanel.add(tabsPane);
	}

	public void createBudgetPanel() {
		//Purporse: creates inventory screen panel and adds relevant components
		//Params: None
		//Returns: void
		budgetPanel = new JPanel();
		budgetPanel.setBounds(new Rectangle(0,0,1200,700));
		GroupLayout layout = new GroupLayout(budgetPanel);
		budgetPanel.setLayout(layout);
		budgetPanel.setVisible(false);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		add(budgetPanel);	

		JTable sortingCriteria = new JTable(1, 4) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		}, revenueTable = new JTable(1,4) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		}, costTable = new JTable(1,4) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};

		sortingCriteria.setRowSelectionAllowed(false);

		budgetPanel.add(sortingCriteria);
		budgetPanel.add(revenueTitle);
		budgetPanel.add(revenueTable);
		budgetPanel.add(costTitle);
		budgetPanel.add(costTable);
		budgetPanel.add(balanceTitle);
		budgetPanel.add(balanceText);
		budgetPanel.add(returnButtonBudget);

		revenueTitle.setBackground(getBackground());
		costTitle.setBackground(getBackground());
		balanceTitle.setBackground(getBackground());

		revenueTitle.setPreferredSize(new Dimension(300, 40));
		revenueTitle.setBackground(this.getBackground());

		sortingCriteria.setPreferredSize(new Dimension(875, 60));
		sortingCriteria.setBorder(BorderFactory.createLineBorder(Color.black));
		sortingCriteria.setBackground(this.getBackground());
		sortingCriteria.setRowHeight(60);

		//Adding tables
		revenueTable.setPreferredSize(new Dimension(875, 200));
		revenueTable.setBorder(BorderFactory.createLineBorder(Color.black));
		revenueTable.setBackground(this.getBackground());
		revenueTable.setRowHeight(60);
		costTable.setBorder(BorderFactory.createLineBorder(Color.black));
		costTable.setBackground(this.getBackground());
		costTable.setRowHeight(60);

		layout.linkSize(SwingConstants.HORIZONTAL, revenueTitle, costTitle, balanceTitle);
		layout.linkSize(SwingConstants.VERTICAL, revenueTitle, costTitle, balanceTitle, balanceText, sortingCriteria);
		layout.linkSize(SwingConstants.HORIZONTAL, revenueTable, costTable, balanceText, sortingCriteria);
		layout.linkSize(SwingConstants.VERTICAL, revenueTable, costTable);

		//Setting fonts for text boxes
		SimpleAttributeSet title = new SimpleAttributeSet(), mainText = new SimpleAttributeSet();
		StyleConstants.setFontSize(title, 30);
		StyleConstants.setFontFamily(title, Font.SERIF);
		StyleConstants.setAlignment(title, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontSize(mainText, 15);
		StyleConstants.setFontFamily(mainText, Font.SANS_SERIF);
		StyleConstants.setAlignment(mainText, StyleConstants.ALIGN_LEFT);

		try {
			formattedText = revenueTitle.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
			formattedText.insertString(formattedText.getLength(), "Revenue:", title);

			formattedText = costTitle.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
			formattedText.insertString(formattedText.getLength(), "Cost:", title);

			formattedText = balanceTitle.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
			formattedText.insertString(formattedText.getLength(), "Total:", title);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		Font font = new Font("Helvetica", Font.BOLD, 30); 
		sortingCriteria.setFont(font);
		sortingCriteria.setValueAt("Item", 0, 0);
		sortingCriteria.setValueAt("Unit Cost", 0, 1);
		sortingCriteria.setValueAt("# of Units", 0, 2);
		sortingCriteria.setValueAt("Total Cost", 0, 3);

		revenueTable.setValueAt("Item", 0, 0);
		revenueTable.setValueAt("Unit Cost", 0, 1);
		revenueTable.setValueAt("# of Units", 0, 2);
		revenueTable.setValueAt("Total Cost", 0, 3);

		costTable.setValueAt("Item", 0, 0);
		costTable.setValueAt("Unit Cost", 0, 1);
		costTable.setValueAt("# of Units", 0, 2);
		costTable.setValueAt("Total Cost", 0, 3);

		//Setting panel layout
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(revenueTitle)
						.addComponent(costTitle)
						.addComponent(balanceTitle))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(sortingCriteria)
						.addComponent(revenueTable)
						.addComponent(costTable)
						.addComponent(balanceText))
				);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(sortingCriteria)
				.addGroup(layout.createParallelGroup()
						.addComponent(revenueTitle)
						.addComponent(revenueTable)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(costTitle)
						.addComponent(costTable)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(balanceTitle)
						.addComponent(balanceText)
						)
				.addGap(GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
				);
		budgetPanel.setVisible(false);
	}


	public void createGaragePanel() {
		//Purporse: creates inventory screen panel and adds relevant components
		//Params: None
		//Returns: void
		garagePanel = new JPanel();
		garagePanel.setBounds(new Rectangle(0,0,1200,700));
		GroupLayout layout = new GroupLayout(garagePanel);
		garagePanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		garagePanel.setVisible(false);
		add(garagePanel);	

		JTextPane garageTitle = new JTextPane();
		graphicalViewButton = new JRadioButton("Graphical View");
		listViewButton = new JRadioButton("List View");
		ButtonGroup buttonGroup = new ButtonGroup();
		garagePanelGraphicalViewPanel = new JPanel();
		garagePanelListViewPanel = new JPanel();

		graphicalViewButton.setSelected(true);
		graphicalViewButton.setActionCommand("Graphical View Garage");
		listViewButton.setActionCommand("List View Garage");
		graphicalViewButton.addActionListener(this);
		listViewButton.addActionListener(this);
		buttonGroup.add(graphicalViewButton);
		buttonGroup.add(listViewButton);

		Font radioButtonSelected = new Font("Helvetica", Font.BOLD+Font.ITALIC, 30), radioButtonUnselected = new Font("Helvetica", Font.ITALIC, 30);
		graphicalViewButton.setFont(radioButtonSelected);
		listViewButton.setFont(radioButtonUnselected);

		graphicalViewButton.putClientProperty("JComponent.sizeVariant", "large");
		listViewButton.putClientProperty("JComponent.sizeVariant", "large");

		garagePanel.add(garageTitle);
		garagePanel.add(graphicalViewButton);
		garagePanel.add(listViewButton);
		garagePanel.add(garagePanelListViewPanel);
		garagePanel.add(garagePanelGraphicalViewPanel);
		garagePanel.add(returnButtonGarage);

		SimpleAttributeSet title = new SimpleAttributeSet();
		StyleConstants.setFontSize(title, 60);
		StyleConstants.setFontFamily(title, Font.SERIF);
		StyleConstants.setAlignment(title, StyleConstants.ALIGN_RIGHT);

		garageTitle.setSize(175,60);
		garageTitle.setLocation(returnButtonGarage.getX()+returnButtonGarage.getWidth()-garageTitle.getWidth(),25);
		garageTitle.setEditable(false);
		garageTitle.setBackground(getBackground());

		try {
			formattedText = garageTitle.getStyledDocument();
			formattedText.setParagraphAttributes(0, formattedText.getLength(), title, true);
			formattedText.insertString(formattedText.getLength(), "Garage", title);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		graphicalViewButton.setLocation(325, 25);
		graphicalViewButton.setSize(300, 30);
		listViewButton.setLocation(325, 50);
		listViewButton.setSize(300, 30);

		garagePanelListViewPanel.setLocation(garageTitle.getX()-70, garageTitle.getY()+garageTitle.getHeight()+10);
		garagePanelListViewPanel.setSize(1200-2*(returnButtonGarage.getX()+returnButtonGarage.getWidth()-garageTitle.getWidth()-70), 
				700-returnButtonGarage.getHeight()-garageTitle.getY()-garageTitle.getHeight()-30);
		garagePanelGraphicalViewPanel.setLocation(garageTitle.getX()-70, garageTitle.getY()+garageTitle.getHeight()+10);
		garagePanelGraphicalViewPanel.setSize(1200-2*(returnButtonGarage.getX()+returnButtonGarage.getWidth()-garageTitle.getWidth()-70), 
				700-returnButtonGarage.getHeight()-garageTitle.getY()-garageTitle.getHeight()-30);

		//LIST VIEW:
		garagePanelListViewPanel.setLayout(new BorderLayout());
		JTable listViewTable = new JTable(5,7) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		String[] columnNames = {"Order", "Car", "Price", "Manufacturer", "Horsepower", "Top Speed", "Acceleration"};
		JScrollPane listTableScroll = new JScrollPane(listViewTable);

		listViewTable.setBackground(Color.red);
		listViewTable.setLocation(0,0);
		listViewTable.setSize(garagePanelListViewPanel.getSize());
		listViewTable.setRowHeight(200);
		listViewTable.getTableHeader().setReorderingAllowed(false);
		listViewTable.setRowSelectionAllowed(false);
		listViewTable.setColumnSelectionAllowed(false);
		listViewTable.setCellSelectionEnabled(false);
		listViewTable.setDefaultEditor(Object.class, null);

//		listViewTable.setValueAt("Hi", 0, 0);
//		listViewTable.setValueAt("Hi", 0, 1);
//		listViewTable.setValueAt("Hi", 0, 2);
//		listViewTable.setValueAt("Hi", 0, 3);

		listTableScroll.setBounds(0, 0, listViewTable.getWidth(), listViewTable.getHeight());

		listTableScroll.setVisible(true);
		listTableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listTableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listTableScroll.setBorder(null);
		listTableScroll.setBackground(getBackground());
		listViewTable.setPreferredScrollableViewportSize(new Dimension(listViewTable.getWidth(), listViewTable.getHeight()));

		JTableHeader header = listViewTable.getTableHeader();
		TableColumnModel columnModel = header.getColumnModel();
		for (int count = 0; count < columnNames.length; count++) {
			TableColumn tableColumn = columnModel.getColumn(count);
			tableColumn.setHeaderValue(columnNames[count]);
		}

		int[] columnWidths = {100, 170, 130, 170, 170, 150};
		for (int count = 0; count < columnWidths.length; count++) {
			columnModel.getColumn(count).setMinWidth(columnWidths[count]);
			columnModel.getColumn(count).setPreferredWidth(columnWidths[count]);
			columnModel.getColumn(count).setMaxWidth(columnWidths[count]);
		}

		header.setPreferredSize(new Dimension(listViewTable.getWidth(), 50));
		header.setBackground(Color.orange);
		header.setFont(new Font("TimesRoman", Font.ITALIC, 23));


		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = header.columnAtPoint(e.getPoint());
			}
		});

		//Graphical View
		garagePanelGraphicalViewPanel.setBackground(Color.pink);

		//		garagePanel.add(graphicalScroll);
		//		garagePanelGraphicalViewPanel.add(graphicalScroll);
		garagePanelListViewPanel.add(listTableScroll);
		garagePanelGraphicalViewPanel.setVisible(true);
		garagePanelListViewPanel.setVisible(false);
		garagePanel.setVisible(false);
	}


	public void createSellCarPanel(){
		//Purporse: creates selling car screen panel and adds relevant components
		//Params: None
		//Returns: void
		initializeCarIcons();

		sellCarPanel = new JPanel();
		sellCarPanel.setBounds(new Rectangle(0,0,1200,700));
		GroupLayout layout = new GroupLayout(sellCarPanel);
		sellCarPanel.setLayout(layout);
		sellCarPanel.setVisible(false);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		add(sellCarPanel);
		sellCarPanel.add(manufacturerTitle);
		manufacturerTitle.setSize(new Dimension(220, 80));
		manufacturerTitle.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 30));
		manufacturerTitle.setText("Select a Manufacturer:");
		manufacturerTitle.setLocation(30, 80);
		manufacturerTitle.setBackground(this.getBackground());
		manufacturerTitle.setEditable(false);

		formattedText = manufacturerTitle.getStyledDocument();
		StyleConstants.setAlignment(textStyle, StyleConstants.ALIGN_RIGHT);
		formattedText.setParagraphAttributes(0, formattedText.getLength(), textStyle, false);

		manufacturerPanel = new JPanel();
		manufacturerPanel.setSize(new Dimension(800, 120));
		manufacturerPanel.setLocation(270, 60);
		manufacturerPanel.setBackground(this.getBackground());
		manufacturerPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		manufacturerMap = new TreeMap<String, ImageIcon>();
		manufacturerMap.put("Audi", audiBrandIcon);
		manufacturerMap.put("BMW", BMWBrandIcon);
		manufacturerMap.put("Chevrolet", chevroletBrandIcon);
		manufacturerMap.put("Honda", hondaBrandIcon);
		manufacturerMap.put("Toyota", toyotaBrandIcon);
		manufacturerMap.put("Volkswagen", volkswagenBrandIcon);
		//		manufacturerMap.put("Ford", fordBrandIcon);
		//		manufacturerMap.put("Mitsubishi", mitsubishiBrandIcon);


		statsText = new JTextPane();
		statsText.setEditable(false);
		statsText.setLocation(25, 200);
		statsText.setSize(new Dimension(275, 325));

		SimpleAttributeSet title = new SimpleAttributeSet(), mainText = new SimpleAttributeSet();
		StyleConstants.setFontSize(title, 30);
		StyleConstants.setFontFamily(title, Font.SERIF);
		StyleConstants.setAlignment(title, StyleConstants.ALIGN_CENTER);

		StyleConstants.setFontSize(mainText, 15);
		StyleConstants.setFontFamily(mainText, Font.SANS_SERIF);
		StyleConstants.setAlignment(mainText, StyleConstants.ALIGN_LEFT);

		formattedText = statsText.getStyledDocument();
		try {
			formattedText.setParagraphAttributes(0, formattedText.getLength(), title, false);
			formattedText.insertString(formattedText.getLength(), "Stats:\n", title);
			formattedText.setParagraphAttributes(7, formattedText.getLength(), mainText, false);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		//Adding manufacturer buttons
		Iterator<Map.Entry<String, ImageIcon>> manufacturerIterator = manufacturerMap.entrySet().iterator();
		Font buttonFont = new Font("TimesRoman", Font.ITALIC, 20);
		while (manufacturerIterator.hasNext()) {
			Map.Entry<String, ImageIcon> currElement = manufacturerIterator.next();
			ImageIcon currManufacturerIcon = currElement.getValue();
			String currManufacturer = currElement.getKey();
			sellCarManufacturerSelected = false;
			sellCarManufacturerIndex = 0;

			manufacturerButtons.add(new JButton(currManufacturerIcon));
			int currIndex = manufacturerButtons.size()-1;

			manufacturerButtons.get(currIndex).setFont(buttonFont);
			manufacturerButtons.get(currIndex).setOpaque(true);
			manufacturerButtons.get(currIndex).addActionListener(this);
			manufacturerButtons.get(currIndex).setActionCommand(currIndex + currManufacturer+  "Select Manufacturer");
			manufacturerButtons.get(currIndex).addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if ((!sellCarManufacturerSelected)||sellCarManufacturerIndex!=currIndex) {
						manufacturerButtons.get(currIndex).setIcon(null);
						manufacturerButtons.get(currIndex).setText(currManufacturer);

						Car tempCar = new Car(cars.get(cars.size()-1));
						tempCar.setManufacturer(currManufacturer);
						tempCar.refreshStats();

						formattedText = statsText.getStyledDocument();
						try {
							formattedText.remove(7, formattedText.getLength()-7);
							formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d --> %d%n", cars.get(cars.size()-1).getHorsepower(), tempCar.getHorsepower()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f --> %.2f%n", cars.get(cars.size()-1).getMaxSpeed(), tempCar.getMaxSpeed()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f --> %.2f%n", cars.get(cars.size()-1).getAccelerationTime(), tempCar.getAccelerationTime()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d --> %d%n", cars.get(cars.size()-1).getBrakingDistance(), tempCar.getBrakingDistance()), mainText);
						} catch (BadLocationException e2) {}
					}
				}
				public void mouseExited(MouseEvent e) {
					if ((!sellCarManufacturerSelected)||sellCarManufacturerIndex!=currIndex) {
						manufacturerButtons.get(currIndex).setIcon(currManufacturerIcon);
						manufacturerButtons.get(currIndex).setText(null);

						formattedText = statsText.getStyledDocument();
						try {
							formattedText.remove(7, formattedText.getLength()-7);
							formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", cars.get(cars.size()-1).getBrakingDistance()), mainText);
						} catch (BadLocationException e2) {}
					}
				}
			});
			manufacturerPanel.add(manufacturerButtons.get(currIndex));
		}

		manufacturerPanel.setLayout(new GridLayout(0,4));
		sellCarPanel.add(returnButtonSellCar);
		sellCarPanel.add(proceedButtonShop);
		Car.setManufacturerList(manufacturerMap);

		orderScreen = new JPanel();
		orderScreen.setLocation(895,200);
		orderScreen.setSize(new Dimension(returnButtonSellCar.getWidth(), 325));
		orderScreen.setLayout(null);

		JTextPane engineText = new JTextPane(), tireText = new JTextPane(), brakeText = new JTextPane();
		Font orderScreenFont = new Font("TimesRoman", Font.PLAIN, 35);
		JTextPane[] orderScreenTexts = {engineText, tireText, brakeText}; 

		//Adding car part buttons
		for (int count = 0; count < orderScreenTexts.length; count++) {
			if (count == 0)
				orderScreenTexts[count].setText("Engine:");
			else if (count == 1)
				orderScreenTexts[count].setText("Tires:");
			else if (count == 2)
				orderScreenTexts[count].setText("Brakes:");
			orderScreenTexts[count].setFont(orderScreenFont);
			orderScreenTexts[count].setSize(200, 35);
			orderScreenTexts[count].setBackground(getBackground());
			orderScreenTexts[count].setLocation(engineText.getX(), engineText.getY()+(count*110));
			orderScreen.add(orderScreenTexts[count]);
		}

		JButton engineOneButton = new JButton(), engineTwoButton = new JButton(), engineThreeButton = new JButton(), 
				engineFourButton = new JButton(), engineFiveButton = new JButton(),
				tireOneButton = new JButton(), tireTwoButton = new JButton(), tireThreeButton = new JButton(), 
				tireFourButton = new JButton(), tireFiveButton = new JButton(),
				brakeOneButton = new JButton(), brakeTwoButton = new JButton(), brakeThreeButton = new JButton(),
				brakeFourButton = new JButton(), brakeFiveButton = new JButton();

		engineButtons = new ArrayList<JButton>();
		tireButtons = new ArrayList<JButton>();
		brakeButtons = new ArrayList<JButton>();

		engineButtons.add(engineOneButton);
		engineButtons.add(engineTwoButton);
		engineButtons.add(engineThreeButton);
		engineButtons.add(engineFourButton);
		engineButtons.add(engineFiveButton);

		tireButtons.add(tireOneButton);
		tireButtons.add(tireTwoButton);
		tireButtons.add(tireThreeButton);
		tireButtons.add(tireFourButton);
		tireButtons.add(tireFiveButton);

		brakeButtons.add(brakeOneButton);
		brakeButtons.add(brakeTwoButton);
		brakeButtons.add(brakeThreeButton);
		brakeButtons.add(brakeFourButton);
		brakeButtons.add(brakeFiveButton);

		engineOneButton.setIcon(engineOneIcon);
		engineTwoButton.setIcon(engineTwoIcon);
		engineThreeButton.setIcon(engineThreeIcon);
		engineFourButton.setIcon(engineFourIcon);
		engineFiveButton.setIcon(engineFiveIcon);

		tireOneButton.setIcon(tireOneIcon);
		tireTwoButton.setIcon(tireTwoIcon);
		tireThreeButton.setIcon(tireThreeIcon);
		tireFourButton.setIcon(tireFourIcon);
		tireFiveButton.setIcon(tireFiveIcon);

		brakeOneButton.setIcon(brakeOneIcon);
		brakeTwoButton.setIcon(brakeTwoIcon);
		brakeThreeButton.setIcon(brakeThreeIcon);
		brakeFourButton.setIcon(brakeFourIcon);
		brakeFiveButton.setIcon(brakeFiveIcon);

		for (int count = 0; count < engineButtons.size(); count++) {
			engineButtons.get(count).setBackground(getBackground());
			tireButtons.get(count).setBackground(getBackground());
			brakeButtons.get(count).setBackground(getBackground());

			engineButtons.get(count).addActionListener(this);
			tireButtons.get(count).addActionListener(this);
			brakeButtons.get(count).addActionListener(this);
			engineButtons.get(count).setActionCommand("Selected engine"+count);
			tireButtons.get(count).setActionCommand("Selected tire"+count);
			brakeButtons.get(count).setActionCommand("Selected brake"+count);

			engineButtons.get(count).setSize(60,60);
			engineButtons.get(count).setOpaque(true);
			engineButtons.get(count).setLocation(count*(50+7), engineText.getY()+engineText.getHeight()+5);
			orderScreen.add(engineButtons.get(count));

			tireButtons.get(count).setSize(60,60);
			tireButtons.get(count).setOpaque(true);
			tireButtons.get(count).setLocation(count*(50+7), tireText.getY()+tireText.getHeight()+5);
			orderScreen.add(tireButtons.get(count));

			brakeButtons.get(count).setSize(60,60);
			brakeButtons.get(count).setOpaque(true);
			brakeButtons.get(count).setLocation(count*(50+7), brakeText.getY()+brakeText.getHeight()+5);
			orderScreen.add(brakeButtons.get(count));


			int currButtonIndex = count;

			//Adding engine buttons mouse listener
			engineButtons.get(count).addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (engineButtons.get(currButtonIndex).getBackground()!=Color.black) {
						Car tempCar = new Car(cars.get(cars.size()-1));
						tempCar.setPart("engine", currButtonIndex+1);
						tempCar.refreshStats();

						try {
							formattedText.remove(7, formattedText.getLength()-7);
							formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d --> %d%n", cars.get(cars.size()-1).getHorsepower(), tempCar.getHorsepower()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f --> %.2f%n", cars.get(cars.size()-1).getMaxSpeed(), tempCar.getMaxSpeed()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f --> %.2f%n", cars.get(cars.size()-1).getAccelerationTime(), tempCar.getAccelerationTime()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d --> %d%n", cars.get(cars.size()-1).getBrakingDistance(), tempCar.getBrakingDistance()), mainText);
						} catch (BadLocationException e2) {}
					}
				}
				public void mouseExited(MouseEvent e) {
					if (engineButtons.get(currButtonIndex).getBackground()!=Color.black) {
						try {
							formattedText.remove(7, formattedText.getLength()-7);
							formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", cars.get(cars.size()-1).getBrakingDistance()), mainText);
						} catch (BadLocationException e2) {}
					}
				}
			});

			//Adding tire buttons mouse listeners			
			tireButtons.get(count).addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (tireButtons.get(currButtonIndex).getBackground()!=Color.black) {
						Car tempCar = new Car(cars.get(cars.size()-1));
						tempCar.setPart("tires", currButtonIndex+1);
						tempCar.refreshStats();
						try {
							formattedText.remove(7, formattedText.getLength()-7);
							formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d --> %d%n", cars.get(cars.size()-1).getHorsepower(), tempCar.getHorsepower()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f --> %.2f%n", cars.get(cars.size()-1).getMaxSpeed(), tempCar.getMaxSpeed()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f --> %.2f%n", cars.get(cars.size()-1).getAccelerationTime(), tempCar.getAccelerationTime()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d --> %d%n", cars.get(cars.size()-1).getBrakingDistance(), tempCar.getBrakingDistance()), mainText);
						} catch (BadLocationException e2) {}
					}
				}
				public void mouseExited(MouseEvent e) {
					if (tireButtons.get(currButtonIndex).getBackground()!=Color.black) {
						try {
							formattedText.remove(7, formattedText.getLength()-7);
							formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", cars.get(cars.size()-1).getBrakingDistance()), mainText);
						} catch (BadLocationException e2) {}
					}
				}
			});

			//Adding brake buttons mouse listeners	
			brakeButtons.get(count).addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (brakeButtons.get(currButtonIndex).getBackground()!=Color.black) {
						Car tempCar = new Car(cars.get(cars.size()-1));
						tempCar.setPart("brakes", currButtonIndex+1);
						tempCar.refreshStats();
						try {
							formattedText.remove(7, formattedText.getLength()-7);
							formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d --> %d%n", cars.get(cars.size()-1).getBrakingDistance(), tempCar.getBrakingDistance()), mainText);
						} catch (BadLocationException e2) {}
					}
				}
				public void mouseExited(MouseEvent e) {
					if (brakeButtons.get(currButtonIndex).getBackground()!=Color.black) {
						try {
							formattedText.remove(7, formattedText.getLength()-7);
							formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
							formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", cars.get(cars.size()-1).getBrakingDistance()), mainText);
						} catch (BadLocationException e2) {}
					}
				}
			});
		}

		sellCarDisplayPanel = new JLayeredPane();
		sellCarDisplayPanel.setLayout(null);
		sellCarDisplayPanel.setLocation(statsText.getX()+statsText.getWidth()+10, statsText.getY());
		sellCarDisplayPanel.setSize(new Dimension(orderScreen.getX()-(statsText.getX()+statsText.getWidth())-20,statsText.getHeight()));
		sellCarDisplayPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		recentCarPane.setLayout(null);
		recentCarPane.setLocation(sellCarDisplayPanel.getLocation());
		recentCarPane.setSize(sellCarDisplayPanel.getSize());
		recentCarPane.setBorder(BorderFactory.createLineBorder(Color.black));

		sellCarLabel = new JLabel();
		sellCarBrakeLabel = new JLabel();
		sellCarTireOneLabel = new JLabel();
		sellCarTireTwoLabel = new JLabel();

		sellCarDisplayPanel.setLayout(null);

		sellCarTireOneLabel.setIcon(tireOneIcon);
		sellCarTireTwoLabel.setIcon(tireOneIcon);
		sellCarBrakeLabel.setIcon(brakeOneIcon);
		manufacturerButtons.get(0).doClick();
		engineButtons.get(0).doClick();
		brakeButtons.get(0).doClick();
		tireButtons.get(0).doClick();

		sellCarDisplayPanel.add(sellCarLabel);
		sellCarDisplayPanel.add(sellCarTireOneLabel);
		sellCarDisplayPanel.add(sellCarTireTwoLabel);
		sellCarDisplayPanel.add(sellCarBrakeLabel);

		sellCarDisplayPanel.setLayer(sellCarLabel, 1);
		sellCarDisplayPanel.setLayer(sellCarBrakeLabel, 2);
		sellCarDisplayPanel.setLayer(sellCarTireOneLabel, 3);
		sellCarDisplayPanel.setLayer(sellCarTireTwoLabel, 4);

		sellCarPanel.add(statsText);
		sellCarPanel.add(orderScreen);
		sellCarPanel.add(manufacturerPanel);
		sellCarPanel.add(sellCarDisplayPanel);
		sellCarPanel.setVisible(false);
	}


	public static void main(String[] args) {
		//Purporse: adds main panel to frame
		//Params: None
		//Returns: void

		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);

		mainFrame.setFocusable(true);
		mainFrame.requestFocusInWindow();
	}

	public void actionPerformed(ActionEvent event) {
		//Purpose: Executes events according to which button has been clicked
				//Params: the ActionEvent created when a button is clicked
				//Returns: void

		SimpleAttributeSet mainText = new SimpleAttributeSet();
		StyleConstants.setFontSize(mainText, 15);
		StyleConstants.setFontFamily(mainText, Font.SANS_SERIF);
		StyleConstants.setAlignment(mainText, StyleConstants.ALIGN_LEFT);
		if (event.getActionCommand().equalsIgnoreCase("Spec Sheet")) {
			//If spec sheet button is clicked
			mainScreenButtonPanel.setVisible(false);
			specSheetPanel.setVisible(true);
		}else if (event.getActionCommand().equalsIgnoreCase("Shop")) {
			//If shop button is clicked
			mainScreenButtonPanel.setVisible(false);
			shopPanel.setVisible(true);
		}else if (event.getActionCommand().equalsIgnoreCase("Inventory")) {
			//If inventory button is clicked
			mainScreenButtonPanel.setVisible(false);
			inventoryPanel.setVisible(true);
		}else if (event.getActionCommand().equalsIgnoreCase("Previous Car")) {
			//If previous car button is clicked
//			currentFocusedCar--;
//			recentCarPane.removeAll();
//			if (currentFocusedCar < 1)
//				currentFocusedCar = 1;
//			recentCarPane.add(cars.get(currentFocusedCar).getDisplayPanel());
		}else if (event.getActionCommand().equalsIgnoreCase("Next Car")) {
			//If next car button is clicked
//			currentFocusedCar++;
//			if (currentFocusedCar+1>cars.size())
//				currentFocusedCar = cars.size()-1;
//			recentCarPane.add(cars.get(currentFocusedCar).getDisplayPanel());
		}else if (event.getActionCommand().equalsIgnoreCase("Budget")) {
			//If budget button is clicked
			mainScreenButtonPanel.setVisible(false);
			budgetPanel.setVisible(true);
		}else if (event.getActionCommand().equalsIgnoreCase("Garage")) {
			//If garage button is clicked
			mainScreenButtonPanel.setVisible(false);
			garagePanel.setVisible(true);
		}else if (event.getActionCommand().equalsIgnoreCase("Sell New")) {
			//If sell new car button is clicked
			sellCarTireOneLabel.setIcon(tireOneIcon);
			sellCarTireTwoLabel.setIcon(tireOneIcon);

			manufacturerButtons.get(0).doClick();
			engineButtons.get(0).doClick();
			brakeButtons.get(0).doClick();
			tireButtons.get(0).doClick();
			mainScreenButtonPanel.setVisible(false);
			sellCarPanel.setVisible(true);

			brakeIcons.add(carDisplayBrakeOneIcon);
			brakeIcons.add(carDisplayBrakeTwoIcon);
			brakeIcons.add(carDisplayBrakeThreeIcon);
			brakeIcons.add(carDisplayBrakeFourIcon);
			brakeIcons.add(carDisplayBrakeFiveIcon);

			engineIcons.add(carDisplayEngineOneIcon);
			engineIcons.add(carDisplayEngineTwoIcon);
			engineIcons.add(carDisplayEngineThreeIcon);
			engineIcons.add(carDisplayEngineFourIcon);
			engineIcons.add(carDisplayEngineFiveIcon);

			String manufacturer = manufacturerButtons.get(0).getActionCommand().substring(1, manufacturerButtons.get(0).getActionCommand().indexOf("Select Manufacturer"));
			cars.add(new Car(cars.size()+1, sellCarDisplayPanel, new CarPart("body", manufacturer, sellCarLabel), new CarPart("engine", 1, engineIcons.get(0)), 
					new CarPart("tires", 1, sellCarTireOneLabel, sellCarTireTwoLabel), new CarPart("brakes", 1, brakeIcons.get(0))));
		}else if (event.getActionCommand().equalsIgnoreCase("Graphical View Garage") || event.getActionCommand().equalsIgnoreCase("List View Garage")) {
			//If graphical view/list view radio button is clicked
			Font radioButtonUnselected = new Font("Helvetica", Font.ITALIC, 30), radioButtonSelected = new Font("Helvetica", Font.BOLD+Font.ITALIC, 30);
			if (event.getActionCommand().equalsIgnoreCase("Graphical View Garage")) {
				garagePanelListViewPanel.setVisible(false);
				garagePanelGraphicalViewPanel.setVisible(true);
				graphicalViewButton.setFont(radioButtonSelected);
				listViewButton.setFont(radioButtonUnselected);
			}else {
				garagePanelGraphicalViewPanel.setVisible(false);
				garagePanelListViewPanel.setVisible(true);
				listViewButton.setFont(radioButtonSelected);
				graphicalViewButton.setFont(radioButtonUnselected);
			}
		}else if (event.getActionCommand().equalsIgnoreCase("return")) {
			//If return button is clicked
			mainScreenButtonPanel.setVisible(true);

			if (sellCarPanel.isVisible()) 
				cars.remove(cars.size()-1);
			budgetPanel.setVisible(false);
			sellCarPanel.setVisible(false);
			garagePanel.setVisible(false);
			shopPanel.setVisible(false);
			inventoryPanel.setVisible(false);
			specSheetPanel.setVisible(false);
		}else if (event.getActionCommand().indexOf("Select Manufacturer")>=0) {
			//If select manufacturer button from sell car panel is clicked
			Font selectedButtonFont = new Font("TimesRoman", Font.BOLD+Font.ITALIC, 20), unselectedButtonFont =  new Font("TimesRoman", Font.ITALIC, 20);
			Iterator<Map.Entry<String, ImageIcon>> manufacturerIterator = manufacturerMap.entrySet().iterator();
			String currManufacturer = event.getActionCommand().substring(1, event.getActionCommand().indexOf("Select Manufacturer"));
			sellCarManufacturerSelected = true;
			sellCarManufacturerIndex = Integer.parseInt("" + event.getActionCommand().charAt(0));

			int counter = 0;
			while (manufacturerIterator.hasNext()) {
				Map.Entry<String, ImageIcon> currElement = manufacturerIterator.next();
				ImageIcon currManufacturerIcon = currElement.getValue();
				manufacturerButtons.get(counter).setText(null);
				manufacturerButtons.get(counter).setIcon(currManufacturerIcon);
				manufacturerButtons.get(counter).setFont(unselectedButtonFont);
				counter++;
			}

			manufacturerButtons.get(sellCarManufacturerIndex).setText(currManufacturer);
			manufacturerButtons.get(sellCarManufacturerIndex).setIcon(null);
			manufacturerButtons.get(sellCarManufacturerIndex).setFont(selectedButtonFont);

			whiteCars.add(audiWhiteIcon);
			whiteCars.add(BMWWhiteIcon);
			whiteCars.add(chevroletWhiteIcon);
			whiteCars.add(hondaWhiteIcon);
			whiteCars.add(toyotaWhiteIcon);
			whiteCars.add(volkswagenWhiteIcon);

			sellCarLabel.setIcon(whiteCars.get(sellCarManufacturerIndex));

			//Setting graphic component sizes according to car size
			if (currManufacturer.equals("Audi")) {
				tireLabelSize = 80;
				brakeLabelSize = 57;
				sellCarTireOneLabel.setLocation(66,168);
				sellCarTireTwoLabel.setLocation(412,168);
				sellCarBrakeLabel.setLocation(76,182);
			}else if (currManufacturer.equals("BMW")) {
				tireLabelSize = 87;
				brakeLabelSize = 64;
				sellCarTireOneLabel.setLocation(44,164);
				sellCarTireTwoLabel.setLocation(404,168);
				sellCarBrakeLabel.setLocation(54,176);
			}else if (currManufacturer.equals("Chevrolet")) {
				tireLabelSize = 97;
				brakeLabelSize = 64;
				sellCarTireOneLabel.setLocation(56,198);
				sellCarTireTwoLabel.setLocation(448,198);
				sellCarBrakeLabel.setLocation(72,210);
			}else if (currManufacturer.equals("Honda")) {
				tireLabelSize = 84;
				brakeLabelSize = 60;
				sellCarTireOneLabel.setLocation(60,166);
				sellCarTireTwoLabel.setLocation(406,166);
				sellCarBrakeLabel.setLocation(72,180);
			}else if (currManufacturer.equals("Toyota")) {
				tireLabelSize = 86;
				brakeLabelSize = 61;
				sellCarTireOneLabel.setLocation(64,174);
				sellCarTireTwoLabel.setLocation(412,170);
				sellCarBrakeLabel.setLocation(76,190);
			}else if (currManufacturer.equals("Volkswagen")) {
				tireLabelSize = 87;
				brakeLabelSize = 62;
				sellCarTireOneLabel.setLocation(56,168);
				sellCarTireTwoLabel.setLocation(398,168);
				sellCarBrakeLabel.setLocation(70,180);
			}

			sellCarTireOneLabel.setSize(tireLabelSize, tireLabelSize);
			sellCarTireTwoLabel.setSize(tireLabelSize, tireLabelSize);
			sellCarBrakeLabel.setSize(brakeLabelSize, brakeLabelSize);

			//Quality deteoriates due to sizing
			sellCarTireOneLabel.setIcon(new ImageIcon(((ImageIcon)sellCarTireOneLabel.getIcon()).getImage().getScaledInstance(tireLabelSize, tireLabelSize, Image.SCALE_AREA_AVERAGING)));
			sellCarTireTwoLabel.setIcon(new ImageIcon(((ImageIcon)sellCarTireTwoLabel.getIcon()).getImage().getScaledInstance(tireLabelSize, tireLabelSize, Image.SCALE_AREA_AVERAGING)));
			sellCarBrakeLabel.setIcon(new ImageIcon(((ImageIcon)sellCarBrakeLabel.getIcon()).getImage().getScaledInstance(brakeLabelSize, brakeLabelSize, Image.SCALE_AREA_AVERAGING)));
			sellCarLabel.setSize(sellCarLabel.getIcon().getIconWidth(), sellCarLabel.getIcon().getIconHeight());
			sellCarLabel.setLocation(0,sellCarDisplayPanel.getHeight()/2-sellCarLabel.getHeight()/2);
			formattedText = statsText.getStyledDocument();
			if (cars.size()>0) {
				
				//Attempted to display completed cars in center of main menu screen; didn't work out
				//				tempBodyLabel.setIcon(sellCarLabel.getIcon());
				//				tempBodyLabel.setLocation(sellCarLabel.getLocation());
				//				tempBodyLabel.setSize(sellCarLabel.getSize());
				//				
				//				tempTireOneLabel.setIcon(sellCarTireOneLabel.getIcon());
				//				tempTireOneLabel.setLocation(sellCarTireOneLabel.getLocation());
				//				tempTireOneLabel.setSize(sellCarTireOneLabel.getSize());
				//				
				//				tempTireTwoLabel.setIcon(sellCarTireTwoLabel.getIcon());
				//				tempTireTwoLabel.setLocation(sellCarTireTwoLabel.getLocation());
				//				tempTireTwoLabel.setSize(sellCarTireTwoLabel.getSize());
				//				
				//				tempBrakeLabel.setIcon(sellCarBrakeLabel.getIcon());
				//				tempBrakeLabel.setLocation(sellCarBrakeLabel.getLocation());
				//				tempBrakeLabel.setSize(sellCarBrakeLabel.getSize());
				//				cars.get(cars.size()-1).setManufacturer(currManufacturer);
				//				cars.get(cars.size()-1).getPart("body").setPartLabelOne(tempBodyLabel);
				//				cars.get(cars.size()-1).getPart("tires").setPartLabelOne(tempTireOneLabel);
				//				cars.get(cars.size()-1).getPart("tires").setPartLabelOne(tempTireTwoLabel);
				//				cars.get(cars.size()-1).getPart("brakes").setPartLabelOne(tempBrakeLabel);
				cars.get(cars.size()-1).refreshStats();

				try {
					formattedText.remove(7, formattedText.getLength()-7);
					formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
					formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
					formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
					formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", cars.get(cars.size()-1).getBrakingDistance()), mainText);
				} catch (BadLocationException e) {}
			}else {
				try {
					formattedText.remove(7, formattedText.getLength()-7);
					Car tempCar = new Car(new CarPart("body", currManufacturer, new JLabel()), new CarPart("engine", 1, new ImageIcon()), new CarPart("tires", 1, new JLabel(), new JLabel()),
							new CarPart("brakes", 1, new ImageIcon()));
					formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", tempCar.getHorsepower()), mainText);
					formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", tempCar.getMaxSpeed()), mainText);
					formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", tempCar.getAccelerationTime()), mainText);
					formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", tempCar.getBrakingDistance()), mainText);
				} catch (BadLocationException e) {}
			}
		}else if (event.getActionCommand().indexOf("Selected")>=0) {
			//If an engine/tire/brake button is clicked
			int index = Integer.parseInt("" + event.getActionCommand().charAt(event.getActionCommand().length()-1));
			if (event.getActionCommand().indexOf("engine")>=0) {
				//If an engine button is clicked, adjust car stats 
				for (int count = 0; count < engineButtons.size(); count++) 
					engineButtons.get(count).setBackground(getBackground());
				engineButtons.get(index).setBackground(Color.black);
				if (cars.size()>0) {
					cars.get(cars.size()-1).getPart("engine").analyzeCarPart(index+1, "");
					cars.get(cars.size()-1).refreshStats();

					try {
						formattedText.remove(7, formattedText.getLength()-7);
						formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", cars.get(cars.size()-1).getBrakingDistance()), mainText);
					} catch (BadLocationException e) {}
				}
			}else if(event.getActionCommand().indexOf("tire")>=0) {
				//If a tire button is clicked, adjust car stats
				tireIcons.add(carDisplayTireOneIcon);
				tireIcons.add(carDisplayTireTwoIcon);
				tireIcons.add(carDisplayTireThreeIcon);
				tireIcons.add(carDisplayTireFourIcon);
				tireIcons.add(carDisplayTireFiveIcon);

				for (int count = 0; count < tireButtons.size(); count++) 
					tireButtons.get(count).setBackground(getBackground());
				tireButtons.get(index).setBackground(Color.black);
				sellCarTireOneLabel.setIcon(new ImageIcon(tireIcons.get(index).getImage().getScaledInstance(tireLabelSize, tireLabelSize, Image.SCALE_AREA_AVERAGING)));
				sellCarTireTwoLabel.setIcon(new ImageIcon(tireIcons.get(index).getImage().getScaledInstance(tireLabelSize, tireLabelSize, Image.SCALE_AREA_AVERAGING)));
				currentTireValue = index;

				if (cars.size()>0) {
					cars.get(cars.size()-1).getPart("tires").analyzeCarPart(index+1, "");
					//					tempTireOneLabel.setIcon(sellCarTireOneLabel.getIcon());
					//					tempTireOneLabel.setLocation(sellCarTireOneLabel.getLocation());
					//					tempTireOneLabel.setSize(sellCarTireOneLabel.getSize());
					//					
					//					tempTireTwoLabel.setIcon(sellCarTireTwoLabel.getIcon());
					//					tempTireTwoLabel.setLocation(sellCarTireTwoLabel.getLocation());
					//					tempTireTwoLabel.setSize(sellCarTireTwoLabel.getSize());
					//					
					//					cars.get(cars.size()-1).getPart("tires").setPartLabelOne(tempTireOneLabel);
					//					cars.get(cars.size()-1).getPart("tires").setPartLabelOne(tempTireTwoLabel);

					cars.get(cars.size()-1).refreshStats();

					try {
						formattedText.remove(7, formattedText.getLength()-7);
						formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", cars.get(cars.size()-1).getBrakingDistance()), mainText);
					} catch (BadLocationException e) {}
				}
			}else if(event.getActionCommand().indexOf("brake")>=0) {
				//If a brake button is clicked, adjust car stats
				brakeIcons.add(carDisplayBrakeOneIcon);
				brakeIcons.add(carDisplayBrakeTwoIcon);
				brakeIcons.add(carDisplayBrakeThreeIcon);
				brakeIcons.add(carDisplayBrakeFourIcon);
				brakeIcons.add(carDisplayBrakeFiveIcon);

				for (int count = 0; count < brakeButtons.size(); count++) 
					brakeButtons.get(count).setBackground(getBackground());
				brakeButtons.get(index).setBackground(Color.black);
				sellCarBrakeLabel.setIcon(new ImageIcon(brakeIcons.get(index).getImage().getScaledInstance(brakeLabelSize, brakeLabelSize, Image.SCALE_AREA_AVERAGING)));
				currentBrakeValue = index;
				if (cars.size()>0) {
					cars.get(cars.size()-1).getPart("brakes").analyzeCarPart(index+1, "");
					cars.get(cars.size()-1).refreshStats();

					try {
						formattedText.remove(7, formattedText.getLength()-7);
						formattedText.insertString(formattedText.getLength(), String.format("Horsepower: %d%n", cars.get(cars.size()-1).getHorsepower()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Max Speed: %.2f%n", cars.get(cars.size()-1).getMaxSpeed()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Acceleration Time: %.2f%n", cars.get(cars.size()-1).getAccelerationTime()), mainText);
						formattedText.insertString(formattedText.getLength(), String.format("Braking Distance: %d%n", cars.get(cars.size()-1).getBrakingDistance()), mainText);
					} catch (BadLocationException e) {}
				}
			}
		}else if (event.getActionCommand().equalsIgnoreCase("proceed")) {
			//If 'proceed' button is clicked from sell car panel
			budgetPanel.setVisible(false);
			sellCarPanel.setVisible(false);
			garagePanel.setVisible(false);
			shopPanel.setVisible(false);
			inventoryPanel.setVisible(false);
			specSheetPanel.setVisible(false);
			mainScreenButtonPanel.setVisible(true);
//			cars.get(cars.size()-1).getDisplayPanel().setLocation(0,0);
//			cars.get(cars.size()-1).getDisplayPanel().setSize(recentCarPane.getSize());
			//			recentCarPane.add(cars.get(cars.size()-1).getDisplayPanel());
		}
	}


	public void cloneButton() {
		//Purpose: Clones return button for each panel
		//Params: None
		//Returns: void
		returnButtonSpecSheet = new JButton("Return");
		returnButtonSpecSheet.setLocation(returnButton.getLocation());
		returnButtonSpecSheet.setSize(returnButton.getSize());
		returnButtonSpecSheet.addActionListener(this);
		returnButtonSpecSheet.setActionCommand("return");
		returnButtonSpecSheet.setIcon(returnButton.getIcon());
		returnButtonSpecSheet.setVerticalTextPosition(SwingConstants.BOTTOM);
		returnButtonSpecSheet.setHorizontalTextPosition(SwingConstants.CENTER);

		returnButtonShop = new JButton("Return");
		returnButtonShop.setLocation(returnButton.getLocation());
		returnButtonShop.setSize(returnButton.getSize());
		returnButtonShop.addActionListener(this);
		returnButtonShop.setActionCommand("return");
		returnButtonShop.setIcon(returnButton.getIcon());
		returnButtonShop.setVerticalTextPosition(SwingConstants.BOTTOM);
		returnButtonShop.setHorizontalTextPosition(SwingConstants.CENTER);

		returnButtonInventory = new JButton("Return");
		returnButtonInventory.setLocation(returnButton.getLocation());
		returnButtonInventory.setSize(returnButton.getSize());
		returnButtonInventory.addActionListener(this);
		returnButtonInventory.setActionCommand("return");
		returnButtonInventory.setIcon(returnButton.getIcon());
		returnButtonInventory.setVerticalTextPosition(SwingConstants.BOTTOM);
		returnButtonInventory.setHorizontalTextPosition(SwingConstants.CENTER);

		returnButtonBudget = new JButton("Return");
		returnButtonBudget.setLocation(returnButton.getLocation());
		returnButtonBudget.setSize(returnButton.getSize());
		returnButtonBudget.addActionListener(this);
		returnButtonBudget.setActionCommand("return");
		returnButtonBudget.setIcon(returnButton.getIcon());
		returnButtonBudget.setVerticalTextPosition(SwingConstants.BOTTOM);
		returnButtonBudget.setHorizontalTextPosition(SwingConstants.CENTER);

		returnButtonGarage = new JButton("Return");
		returnButtonGarage.setLocation(returnButton.getLocation());
		returnButtonGarage.setSize(returnButton.getSize());
		returnButtonGarage.addActionListener(this);
		returnButtonGarage.setActionCommand("return");
		returnButtonGarage.setIcon(returnButton.getIcon());
		returnButtonGarage.setVerticalTextPosition(SwingConstants.BOTTOM);
		returnButtonGarage.setHorizontalTextPosition(SwingConstants.CENTER);

		returnButtonSellCar = new JButton("Return");
		returnButtonSellCar.setLocation(returnButton.getLocation());
		returnButtonSellCar.setSize(returnButton.getSize());
		returnButtonSellCar.addActionListener(this);
		returnButtonSellCar.setActionCommand("return");
		returnButtonSellCar.setIcon(returnButton.getIcon());
		returnButtonSellCar.setVerticalTextPosition(SwingConstants.BOTTOM);
		returnButtonSellCar.setHorizontalTextPosition(SwingConstants.CENTER);

		proceedButtonSellCar = new JButton("Proceed");
		proceedButtonSellCar.setLocation(proceedButtonShop.getLocation());
		proceedButtonSellCar.setSize(proceedButtonShop.getSize());
		proceedButtonSellCar.addActionListener(this);
		proceedButtonSellCar.setActionCommand("proceed");
		proceedButtonSellCar.setIcon(proceedButtonShop.getIcon());
		proceedButtonSellCar.setVerticalTextPosition(SwingConstants.BOTTOM);
		proceedButtonSellCar.setHorizontalTextPosition(SwingConstants.CENTER);
	}


	public void initializeCarIcons() {
		//Purpose: Initializes car icons
		//Params: None
		//Returns: void
		BMWBrandIcon = new ImageIcon(new ImageIcon("BMW.png").getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		mitsubishiBrandIcon = new ImageIcon(new ImageIcon("mitsubishi.png").getImage().getScaledInstance(47, 40, Image.SCALE_FAST));//
		audiBrandIcon = new ImageIcon(new ImageIcon("audi.png").getImage().getScaledInstance(103, 40, Image.SCALE_FAST));
		toyotaBrandIcon = new ImageIcon(new ImageIcon("toyota.png").getImage().getScaledInstance(64, 40, Image.SCALE_FAST));
		hondaBrandIcon = new ImageIcon(new ImageIcon("honda.png").getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		fordBrandIcon = new ImageIcon(new ImageIcon("ford.png").getImage().getScaledInstance(105, 40, Image.SCALE_FAST));//
		chevroletBrandIcon = new ImageIcon(new ImageIcon("chevrolet.png").getImage().getScaledInstance(144, 40, Image.SCALE_FAST));
		volkswagenBrandIcon = new ImageIcon(new ImageIcon("volkswagen.png").getImage().getScaledInstance(40, 40, Image.SCALE_FAST));
		miscBrandIcon = new ImageIcon("miscBrand.png");//NEED TO DO

		//575 width
		audiWhiteIcon = new ImageIcon(new ImageIcon("audiWhite.png").getImage().getScaledInstance(575, 169, Image.SCALE_FAST));
		BMWWhiteIcon = new ImageIcon(new ImageIcon("BMWWhite.png").getImage().getScaledInstance(575, 185, Image.SCALE_FAST));
		chevroletWhiteIcon = new ImageIcon(new ImageIcon("chevroletWhite.png").getImage().getScaledInstance(575, 267, Image.SCALE_FAST));
		hondaWhiteIcon = new ImageIcon(new ImageIcon("hondaWhite.png").getImage().getScaledInstance(575, 176, Image.SCALE_FAST));
		toyotaWhiteIcon = new ImageIcon(new ImageIcon("toyotaWhite.png").getImage().getScaledInstance(575, 190, Image.SCALE_FAST));
		volkswagenWhiteIcon = new ImageIcon(new ImageIcon("volkswagenWhite.png").getImage().getScaledInstance(575, 185, Image.SCALE_FAST));

		whiteCars = new ArrayList<>();
		whiteCars.add(audiWhiteIcon);
		whiteCars.add(BMWWhiteIcon);
		whiteCars.add(chevroletWhiteIcon);
		whiteCars.add(hondaWhiteIcon);
		whiteCars.add(toyotaWhiteIcon);
		whiteCars.add(volkswagenWhiteIcon);

		carDisplayTireOneIcon = new ImageIcon("tireOne.png");
		carDisplayTireTwoIcon = new ImageIcon("tireTwo.png");
		carDisplayTireThreeIcon = new ImageIcon("tireThree.png");
		carDisplayTireFourIcon = new ImageIcon("tireFour.png");
		carDisplayTireFiveIcon = new ImageIcon("tireFive.png");

		carDisplayBrakeOneIcon = new ImageIcon("brakeOne.png");
		carDisplayBrakeTwoIcon = new ImageIcon("brakeTwo.png");
		carDisplayBrakeThreeIcon = new ImageIcon("brakeThree.png");
		carDisplayBrakeFourIcon = new ImageIcon("brakeFour.png");
		carDisplayBrakeFiveIcon = new ImageIcon("brakeFive.png");

		carDisplayEngineOneIcon = new ImageIcon("engineOne.png");
		carDisplayEngineTwoIcon = new ImageIcon("engineTwo.png");
		carDisplayEngineThreeIcon = new ImageIcon("engineThree.png");
		carDisplayEngineFourIcon = new ImageIcon("engineFour.png");
		carDisplayEngineFiveIcon = new ImageIcon("engineFive.png");

		tireIcons = new ArrayList<>();
		tireIcons.add(carDisplayTireOneIcon);
		tireIcons.add(carDisplayTireTwoIcon);
		tireIcons.add(carDisplayTireThreeIcon);
		tireIcons.add(carDisplayTireFourIcon);
		tireIcons.add(carDisplayTireFiveIcon);

		brakeIcons = new ArrayList<>();
		brakeIcons.add(carDisplayBrakeOneIcon);
		brakeIcons.add(carDisplayBrakeTwoIcon);
		brakeIcons.add(carDisplayBrakeThreeIcon);
		brakeIcons.add(carDisplayBrakeFourIcon);
		brakeIcons.add(carDisplayBrakeFiveIcon);

		engineIcons = new ArrayList<>();
		engineIcons.add(carDisplayEngineOneIcon);
		engineIcons.add(carDisplayEngineTwoIcon);
		engineIcons.add(carDisplayEngineThreeIcon);
		engineIcons.add(carDisplayEngineFourIcon);
		engineIcons.add(carDisplayEngineFiveIcon);

		tireOneIcon = new ImageIcon(carDisplayTireOneIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		tireTwoIcon = new ImageIcon(carDisplayTireTwoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		tireThreeIcon = new ImageIcon(carDisplayTireThreeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		tireFourIcon = new ImageIcon(carDisplayTireFourIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		tireFiveIcon = new ImageIcon(carDisplayTireFiveIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));

		brakeOneIcon = new ImageIcon(carDisplayBrakeOneIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		brakeTwoIcon = new ImageIcon(carDisplayBrakeTwoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		brakeThreeIcon = new ImageIcon(carDisplayBrakeThreeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		brakeFourIcon = new ImageIcon(carDisplayBrakeFourIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		brakeFiveIcon = new ImageIcon(carDisplayBrakeFiveIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));

		engineOneIcon = new ImageIcon(carDisplayEngineOneIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		engineTwoIcon = new ImageIcon(carDisplayEngineTwoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		engineThreeIcon = new ImageIcon(carDisplayEngineThreeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		engineFourIcon = new ImageIcon(carDisplayEngineFourIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));
		engineFiveIcon = new ImageIcon(carDisplayEngineFiveIcon.getImage().getScaledInstance(50, 50, Image.SCALE_FAST));

		//		mitsubishiBrandIcon = new ImageIcon(new ImageIcon("mitsubishiWhite.png").getImage().getScaledInstance(47, 40, Image.SCALE_FAST));//
		//		fordBrandIcon = new ImageIcon(new ImageIcon("fordWhite.png").getImage().getScaledInstance(105, 40, Image.SCALE_FAST));//
	}



	public static int findInventory(String partType, String manufacturer, int scaleVal) {
		//Purpose: Finds the number of a particular part in inventory(not actually used)
		//Params: a string indicating a part type, a string representing the manufacturer, an int representing the scale value
		//Returns: int
		return 0;
	}

}
