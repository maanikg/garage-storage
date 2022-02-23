import java.util.Random;
import java.util.ArrayList;

public class ProfitCostMargin {
	//ProfitCostMargin Class: used for initializing and modifying ProfitCostMargin objects(wasn't able to be implemented into program)

	double randNum;
	double originalPrice, outputPrice, marketPrice;
	int numUsed, numInventory, numCarsSold, quantity;
	boolean buyingTrue, partTrue;
	private ArrayList<Double> historicalPricing = new ArrayList<Double>();

	public ProfitCostMargin(boolean buyingTrueInput, boolean partTrueInput, String nameInput, int scaleValueInput, double originalPriceInput, int quantityInput) {
		//Purpose: constructor for ProfitCostMargin object
		if (!buyingTrueInput) {
			if (partTrueInput) {
				numCarsSold = MainClass.cars.size()-1;//
				quantity = quantityInput;
				numInventory = MainClass.findInventory(nameInput, "", scaleValueInput);
//				numUsed = MainClass.findNumUsed(nameInput, "", scaleValueInput);
				randNum = (Math.random()*15)+15;
			}else {
				
			}
		}else {	
		}
	}
}
