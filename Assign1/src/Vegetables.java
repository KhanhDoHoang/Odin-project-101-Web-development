import java.util.Scanner;

/**
 * This is read file class
 * @author HoangDo
 *
 */
public class Vegetables extends FoodItem{
	private String farmName;
	public Vegetables() {
		super();
		this.farmName = "";
	}

	public boolean addItem(Scanner scanner) {
		//System.out.print("Enter the code for the item: ");
		super.addItem(scanner);

		System.out.print("Enter the name of the farm supplier: ");
		farmName = scanner.nextLine();
		
		return true;
	}
	public String getFarmName() {
		return farmName;
	}
	public String toString() {
		return super.toString() + " farm supplier: " + this.farmName;
	}
}
