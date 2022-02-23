import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Car {
	//Car Class: used for initializing and modifying Car objects

	private int carNo;
	private static ArrayList<CarPart> carParts = new ArrayList<CarPart>();
	private static Map<String, ImageIcon> manufacturerList = new HashMap<>();
	private String manufacturer;
	private int horsepower, brakingDistance;
	private double maxSpeed, accelerationTime, fuelConsumption, tireAgeDuration;
	private JLayeredPane displayPanel;
	private double cost;
	//	private double totalPrice, tirePrice, enginePrice, brakePrice;
	//	private Map<String, String> componentNames = new HashMap<>();
	//	private Image icon;
	//	private static ArrayList<String> manufacturerList = new ArrayList<String>(Arrays.asList("BMW", "Honda", "Toyota", 
	//			"Ford", "Mitsubishi", "Audi", "Chevrolet"));
	//	private static ArrayList<String> modelList = new ArrayList<String>(), tireList = new ArrayList<String>(), 
	//			engineList = new ArrayList<String>(), brakeList = new ArrayList<String>(),
	//			paintColourList = new ArrayList<String>();

	//manufacturer, model, tire, engine, brake, paintColour, (interior?), (suspension?), (transmission?)

	public Car(int index, JLayeredPane displayPanelInput, CarPart body, CarPart engine, CarPart tires, CarPart brakes) {
		//Purpose: constructor for Car object
		carParts.add(body);
		carParts.add(engine);
		carParts.add(tires);
		carParts.add(brakes);
		setDisplayPanel(displayPanelInput);
		carNo = index;
		manufacturer = body.getManufacturer();
		horsepower = Math.round((long)(body.getHorsepowerPercentIncrease()*engine.getHorsepower()));
		brakingDistance = Math.round((long)(body.getBrakingDistancePercentIncrease()*tires.getBrakingDistancePercentIncrease()*brakes.getBrakingDistance()));
		accelerationTime = Math.round((body.getAccelerationPercentIncrease()*tires.getAccelerationPercentIncrease()*engine.getAccelerationTime())*100)/100.00;
		maxSpeed = body.getMaxSpeedPercentIncrease()*tires.getMaxSpeedPercentIncrease()*engine.getMaxSpeed();
		for (int count = 0; count < carParts.size(); count++)
			setCost(getCost() + carParts.get(count).getCost());
	}
	
	public Car(Car origCar) {
		//Purpose: constructor for Car object
		carParts = new ArrayList<CarPart>(origCar.carParts);
		manufacturer = origCar.manufacturer;
//		horsepower = Math.round((long)(body.getHorsepowerPercentIncrease()*engine.getHorsepower()));
//		brakingDistance = Math.round((long)(body.getBrakingDistancePercentIncrease()*tires.getBrakingDistancePercentIncrease()*brakes.getBrakingDistance()));
//		accelerationTime = Math.round((body.getAccelerationPercentIncrease()*tires.getAccelerationPercentIncrease()*engine.getAccelerationTime())*100)/100.00;
//		maxSpeed = body.getMaxSpeedPercentIncrease()*tires.getMaxSpeedPercentIncrease()*engine.getMaxSpeed();
//		for (int count = 0; count < carParts.size(); count++)
//			setCost(getCost() + carParts.get(count).getCost());
	}
	
	public Car(CarPart body, CarPart engine, CarPart tires, CarPart brakes) {
		//Purpose: constructor for Car object
		manufacturer = body.getManufacturer();
		horsepower = Math.round((long)(body.getHorsepowerPercentIncrease()*engine.getHorsepower()));
		brakingDistance = Math.round((long)(body.getBrakingDistancePercentIncrease()*tires.getBrakingDistancePercentIncrease()*brakes.getBrakingDistance()));
		accelerationTime = Math.round((body.getAccelerationPercentIncrease()*tires.getAccelerationPercentIncrease()*engine.getAccelerationTime())*100)/100.00;
		maxSpeed = body.getMaxSpeedPercentIncrease()*tires.getMaxSpeedPercentIncrease()*engine.getMaxSpeed();
		for (int count = 0; count < carParts.size(); count++)
			setCost(getCost() + carParts.get(count).getCost());
	}

	
	//GETTER AND SETTER METHODS
	public static Map<String, ImageIcon> getManufacturerList() {
		//		Collections.sort(manufacturerList);
		return manufacturerList;
	}

	public static void setManufacturerList(Map<String, ImageIcon> manufacturerListInput) {
		manufacturerList = manufacturerListInput;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public double getAccelerationTime() {
		return accelerationTime;
	}

	public int getHorsepower() {
		return horsepower;
	}

	public double getFuelConsumption() {
		return fuelConsumption;
	}

	public int getBrakingDistance() {
		return brakingDistance;
	}

	public CarPart getPart(String name) {
		if (name.equalsIgnoreCase("body"))
			return carParts.get(0);
		else if (name.equalsIgnoreCase("engine"))
			return carParts.get(1);
		else if (name.equalsIgnoreCase("tires"))
			return carParts.get(2);
		else if (name.equalsIgnoreCase("brakes"))
			return carParts.get(3);
		else
			return new CarPart("", 0, new ImageIcon());
	}

	public void setPart(String name, int scaleValueInput) {
		if (name.equalsIgnoreCase("body")) {
		}else if (name.equalsIgnoreCase("engine")) {
			carParts.get(1).analyzeCarPart(scaleValueInput, null);
		}else if (name.equalsIgnoreCase("tires")) {
			carParts.get(2).analyzeCarPart(scaleValueInput, null);
		}else if (name.equalsIgnoreCase("brakes")) {
			carParts.get(3).analyzeCarPart(scaleValueInput, null);
		}
		
	}
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		carParts.get(0).setManufacturer(manufacturer);	
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void refreshStats() {
		horsepower = Math.round((long)(carParts.get(0).getHorsepowerPercentIncrease()*carParts.get(1).getHorsepower()));
		brakingDistance = Math.round((long)(carParts.get(0).getBrakingDistancePercentIncrease()*carParts.get(2).getBrakingDistancePercentIncrease()*carParts.get(3).getBrakingDistance()));
		accelerationTime = Math.round((carParts.get(0).getAccelerationPercentIncrease()*carParts.get(2).getAccelerationPercentIncrease()*carParts.get(1).getAccelerationTime())*100)/100.00;
		maxSpeed = carParts.get(0).getMaxSpeedPercentIncrease()*carParts.get(2).getMaxSpeedPercentIncrease()*carParts.get(1).getMaxSpeed();
		cost = 0;
		for (int count = 0; count < carParts.size(); count++)
			cost+= carParts.get(count).getCost();
		
//		MainClass.formattedText = MainClass.statsText.
	}

	public JLayeredPane getDisplayPanel() {
		return displayPanel;
	}

	public void setDisplayPanel(JLayeredPane displayPanel) {
		this.displayPanel = displayPanel;
	}
}
