import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CarPart {
	//CarPart Class: used for initializing and modifying CarPart objects
	private String name, manufacturer;
	private double unitCost, totalCost;
	private int scaleValue, assignedValue, quantity;
	private ImageIcon partIcon;
	private JLabel partLabelOne, partLabelTwo;
	private double horsepowerPercentIncrease, maxSpeedPercentIncrease, accelerationPercentIncrease, pricePercentIncrease, brakingDistancePercentIncrease;
	private double maxSpeed, accelerationTime, brakingDistance;
	private int horsepower;
	private ProfitCostMargin margin;

	public CarPart(String nameInput, int scaleValueInput, ImageIcon partIconInput) {
		//Purpose: constructor for CarPart object
		name = nameInput;
		partIcon = partIconInput;
		analyzeCarPart(scaleValueInput, "");
	}

	public CarPart(String nameInput, int scaleValueInput, JLabel partLabelInput, JLabel partLabelTwoInput) {
		//Purpose: constructor for CarPart object
		name = nameInput;
		setPartLabelOne(partLabelInput);
		partLabelTwo = partLabelTwoInput;
		analyzeCarPart(scaleValueInput, "");
	}

	public CarPart(String nameInput, String manufacturerInput, JLabel partLabelInput) {
		//Purpose: constructor for CarPart object
		name = nameInput;
		setPartLabelOne(partLabelInput);
		analyzeCarPart(0, manufacturerInput);
	}

	public void analyzeCarPart(int scaleValueInput, String manufacturerInput) {
		//Purpose: Modifies stats of car depending on car part
		//Params: int representing scale value of car part, String representing manufacturer
		//Returns void
		if (name.equalsIgnoreCase("body")) {
			//If the car part is a car body
			manufacturer = manufacturerInput;
			if (getManufacturer().equalsIgnoreCase("Audi")) {
				unitCost = 30500;
				setHorsepowerPercentIncrease(1.2);
				setMaxSpeedPercentIncrease(1.15);
				setBrakingDistancePercentIncrease(1.05);
				setAccelerationPercentIncrease(0.9);
			}else if (getManufacturer().equalsIgnoreCase("BMW")) {
				unitCost = 41500;
				setHorsepowerPercentIncrease(1.15);
				setMaxSpeedPercentIncrease(1.1);
				setBrakingDistancePercentIncrease(1.09);
				setAccelerationPercentIncrease(0.93);
			}else if (getManufacturer().equalsIgnoreCase("Chevrolet")) {
				unitCost = 36000;
				setBrakingDistancePercentIncrease(0.9);
				setAccelerationPercentIncrease(1.1);
				setHorsepowerPercentIncrease(1.1);
				setMaxSpeedPercentIncrease(0.90);
			}else if (getManufacturer().equalsIgnoreCase("Honda")) {
				unitCost = 24750;
				setBrakingDistancePercentIncrease(0.95);
				setHorsepowerPercentIncrease(0.95);
				setMaxSpeedPercentIncrease(0.98);
				setAccelerationPercentIncrease(0.98);
			}else if (getManufacturer().equalsIgnoreCase("Toyota")) {
				unitCost = 16050;
				setBrakingDistancePercentIncrease(1.05);
				setAccelerationPercentIncrease(0.97);
				setHorsepowerPercentIncrease(1.05);
				setMaxSpeedPercentIncrease(1.05);
			}else if (getManufacturer().equalsIgnoreCase("Volkswagen")) {
				unitCost = 28050;
				setBrakingDistancePercentIncrease(0.95);
				setAccelerationPercentIncrease(0.95);
				setHorsepowerPercentIncrease(1);
				setMaxSpeedPercentIncrease(1.02);
			}
		}else {
			//If the car part is any other car part
			scaleValue = scaleValueInput;
			if (name.equalsIgnoreCase("engine")) {
				//If the car part is an engine
				if (scaleValue == 1) {
					unitCost = 4500;
					horsepower = 190; 
					maxSpeed = 215;
					setAccelerationTime(6.15); 

					pricePercentIncrease = 1;
					setHorsepowerPercentIncrease(1);
					setMaxSpeedPercentIncrease(1);
					setAccelerationPercentIncrease(1);
				}else {
					if (scaleValue == 2) {
						pricePercentIncrease = 1.5;
						setHorsepowerPercentIncrease(1.3);
						setMaxSpeedPercentIncrease(1.25);
						setAccelerationPercentIncrease(0.85);
					}else if (scaleValue == 3) {
						pricePercentIncrease = 1.9;
						setHorsepowerPercentIncrease(1.65);
						setMaxSpeedPercentIncrease(1.5);
						setAccelerationPercentIncrease(0.75);
					}else if (scaleValue == 4) {
						pricePercentIncrease = 2.3;
						setHorsepowerPercentIncrease(1.85);
						setMaxSpeedPercentIncrease(1.8);
						setAccelerationPercentIncrease(0.60);
					}else if (scaleValue == 5) {
						pricePercentIncrease = 2.5;
						setHorsepowerPercentIncrease(2.1);
						setMaxSpeedPercentIncrease(2);
						setAccelerationPercentIncrease(0.5);
					}
					unitCost = pricePercentIncrease * new CarPart("engine", 1, new ImageIcon()).getCost();
					horsepower = Math.round((long)(getHorsepowerPercentIncrease() * new CarPart("engine", 1, new ImageIcon()).getHorsepower()));
					maxSpeed = getMaxSpeedPercentIncrease() * new CarPart("engine", 1, new ImageIcon()).getMaxSpeed();
					setAccelerationTime(getAccelerationPercentIncrease() * new CarPart("engine", 1, new ImageIcon()).getAcceleration());
				}
				quantity = 1;
			}else if (name.equalsIgnoreCase("tires")) {
				//If the car part selected is a tire
				if (scaleValue == 1) {
					unitCost = 95.5; 
					setMaxSpeedPercentIncrease(1);
					setAccelerationPercentIncrease(1);
					setBrakingDistancePercentIncrease(1);
					pricePercentIncrease = 1;
				}else {
					if (scaleValue == 2) {
						pricePercentIncrease = 1.4;
						setMaxSpeedPercentIncrease(1.05);
						setAccelerationPercentIncrease(0.9);
						setBrakingDistancePercentIncrease(0.95);
					}else if (scaleValue == 3) {
						pricePercentIncrease = 1.8;
						setMaxSpeedPercentIncrease(1.13);
						setAccelerationPercentIncrease(0.85);
						setBrakingDistancePercentIncrease(0.9);
					}else if (scaleValue == 4) {
						pricePercentIncrease = 2.2;
						setMaxSpeedPercentIncrease(1.2);
						setAccelerationPercentIncrease(0.8);
						setBrakingDistancePercentIncrease(0.85);
					}else if (scaleValue == 5) {
						pricePercentIncrease = 2.5;
						setMaxSpeedPercentIncrease(1.28);
						setAccelerationPercentIncrease(0.7);
						setBrakingDistancePercentIncrease(0.8);
					}
					unitCost = pricePercentIncrease * new CarPart("tires", 1, new ImageIcon()).getCost();
				}
				quantity = 4;
			}else if (name.equalsIgnoreCase("brakes")) {
				//If the car part selected is a brake
				if (scaleValue == 1) {
					unitCost = 170; 
					brakingDistance = 40;
					setBrakingDistancePercentIncrease(1);
					pricePercentIncrease = 1;
				}else {
					if (scaleValue == 2) {
						pricePercentIncrease = 1.4;
						setBrakingDistancePercentIncrease(0.85);
					}else if (scaleValue == 3) {
						pricePercentIncrease = 1.7;
						setBrakingDistancePercentIncrease(0.77);
					}else if (scaleValue == 4) {
						pricePercentIncrease = 2;
						setBrakingDistancePercentIncrease(0.7);
					}else if (scaleValue == 5) {
						pricePercentIncrease = 2.3;
						setBrakingDistancePercentIncrease(0.65);
					}
					unitCost = pricePercentIncrease * new CarPart("brakes", 1, new ImageIcon()).getCost();
					brakingDistance = getBrakingDistancePercentIncrease() * new CarPart("brakes", 1, new ImageIcon()).getBrakingDistance();
				}
				quantity = 2;
			}
		}
		margin = new ProfitCostMargin(false, true, name, scaleValueInput, unitCost, quantity);
	}

	//GETTER AND SETTER METHODS:
	public double getCost() {
		return unitCost;
	}

	public double getHorsepower() {
		return horsepower;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public double getAcceleration() {
		return getAccelerationTime();
	}

	public double getBrakingDistance() {
		return brakingDistance;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		analyzeCarPart(0, manufacturer);
	}

	public double getHorsepowerPercentIncrease() {
		return horsepowerPercentIncrease;
	}

	public void setHorsepowerPercentIncrease(double horsepowerPercentIncrease) {
		this.horsepowerPercentIncrease = horsepowerPercentIncrease;
	}

	public double getBrakingDistancePercentIncrease() {
		return brakingDistancePercentIncrease;
	}

	public void setBrakingDistancePercentIncrease(double brakingDistancePercentIncrease) {
		this.brakingDistancePercentIncrease = brakingDistancePercentIncrease;
	}

	public double getAccelerationPercentIncrease() {
		return accelerationPercentIncrease;
	}

	public void setAccelerationPercentIncrease(double accelerationPercentIncrease) {
		this.accelerationPercentIncrease = accelerationPercentIncrease;
	}

	public double getAccelerationTime() {
		return accelerationTime;
	}

	public void setAccelerationTime(double accelerationTime) {
		this.accelerationTime = accelerationTime;
	}

	public JLabel getPartLabelOne() {
		return partLabelOne;
	}

	public void setPartLabelOne(JLabel partLabelOne) {
		this.partLabelOne = partLabelOne;
	}

	public void setPartLabelTwo(JLabel partLabelTwo) {
		 this.partLabelTwo = partLabelTwo;
	}

	public double getMaxSpeedPercentIncrease() {
		return maxSpeedPercentIncrease;
	}
	
	public void setMaxSpeedPercentIncrease(double maxSpeedPercentIncrease) {
		this.maxSpeedPercentIncrease = maxSpeedPercentIncrease;
	}
}
