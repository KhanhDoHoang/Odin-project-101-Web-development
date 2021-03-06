import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This is read file class
 * @author HoangDo
 *
 */
public class FoodItem {

	private int itemCode;
	private String itemName;
	private float itemPrice;
	private int itemQuantityInStock;
	private float itemCost;

	public FoodItem() {
		this.itemCode = 0;
		this.itemName = "";
		this.itemPrice = 0;
		this.itemQuantityInStock = 0;
		this.itemCost = 0;
	}

	/**
	 * Update a quantity field
	 * @param int
	 * @return boolean
	 */
	public boolean updateItem(int amount) {
		//Update a quantity field, could be positive or negative
		int tempQuantity = itemQuantityInStock;
		
		tempQuantity += amount;				//inStock: 12 == sell: -8
														//inStock: 12 == buy : 8
		
		if(tempQuantity < 0) {
			System.out.println("Insufficient stock in inventory...");
			System.out.println("Error...could not sell item");
			return false;
		}
		itemQuantityInStock = tempQuantity;
		
		return true;
	}

	public boolean inputCode(Scanner scanner) {
		//---------------Check for itemCode-----------------------------
		//Must be an integer			
		while (true) { 
			try { 
				//
				itemCode = scanner.nextInt();
				scanner.nextLine();
				break; 
			} 
			catch (InputMismatchException e) { 
				System.out.println("Invalid entry");
				System.out.println("Enter a valid code:");
				scanner.nextLine();
				continue; 
			}
		}
		
		return true;
	}

	/**
	 * Check for valid item for buying or selling
	 * @param FoodItem
	 * @return boolean
	 */
	public boolean isEqual(FoodItem item) {
		//check through inventory
		if(this.itemCode == item.itemCode) {
			return true;
		}else {
			return false;
		}

	}

	/**
	 * Check for valid item from Fruit, Preserve or Vegetable
	 * @param Scanner
	 * @return boolean
	 */
	public boolean addItem(Scanner scanner) {
		//---------------Check for itemCode-----------------------------
		//Must be an integer		
		//inputCode(scanner);
		
		//------------------Check for itemName------------------------------------
		System.out.print("Enter the name for the item: "); //Can be any input
		this.itemName = scanner.nextLine();
		scanner.nextLine();
		//------------------Check for quantity------------------------------------
		//Must be positive
		while (true) { 
			try { 
				System.out.print("Enter the quantity for the item: ");
				itemQuantityInStock = scanner.nextInt();
				scanner.nextLine();
				while(itemQuantityInStock < 0) {
					System.out.println("Invalid entry");
					System.out.print("Enter the quantity for the item: ");
					itemQuantityInStock = scanner.nextInt();
					scanner.nextLine();
					continue;
				}
				break; 
			} 
			catch (InputMismatchException e) { 
				System.out.println("Invalid entry");
				scanner.next(); 
				continue; 
			}
		}

		//-----------Check for cost----------------------------------
		while (true) { 
			try { 
				System.out.print("Enter the cost of the item: ");
				itemCost = scanner.nextFloat();
				scanner.nextLine();
				while(itemCost < 0) {
					System.out.println("Invalid entry");
					System.out.print("Enter the cost of the item: ");
					itemCost = scanner.nextFloat();
					scanner.nextLine();
					continue;
				}
				break; 
			} 
			catch (InputMismatchException e) { 
				System.out.println("Invalid entry");
				scanner.next(); 
				continue; 
			}
		}

		//-----------Check for price---------------------------------
		while (true) { 
			try { 
				System.out.print("Enter the price of the item: ");
				itemPrice = scanner.nextFloat();
				scanner.nextLine();
				while(itemPrice < 0) {
					System.out.println("Invalid entry");
					System.out.print("Enter the price of the item: ");
					itemPrice = scanner.nextFloat();
					scanner.nextLine();
					continue;
				}
				break; 
			} 
			catch (InputMismatchException e) { 
				System.out.println("Invalid entry");
				scanner.next(); 
				continue;
			}
		}
		return true;
	}

	/**
	 * Display data members
	 * @return toString
	 */
	public String toString() {		

		return "Item code: " + this.itemCode + " Name: " + this.itemName + " In stock: " 
				+ this.itemQuantityInStock + " price: " + this.itemPrice
				+ " cost: " + this.itemCost + " ";
		//How to add fruit, vegie or preserve to this toString
	}

	public int getItemCode() {
		return itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public int getItemQuantityInStock() {
		return itemQuantityInStock;
	}
	public float getItemCost() {
		return itemCost;
	}
}

//public boolean addItemFruit(Scanner scanner) {
//Fruit fruit = new Fruit();
//if(fruit.addItem(scanner)) {
//	fruit.addItem(scanner);
//	return true;
//}
//return false;
//}
//
//public boolean addItemVegetable(Scanner scanner) {
//Vegetables vegie = new Vegetables();
//if(vegie.addItem(scanner)) {
//	vegie.addItem(scanner);
//	return true;
//}
//return false;
//}
//
//public boolean addItemPreserve(Scanner scanner) {
//Preserve preserve = new Preserve();
//if(preserve.addItem(scanner)) {
//	preserve.addItem(scanner);
//	return true;
//}
//return false;
//}


//public String toString2() {		
//
//	return "Item: " + this.itemCode + " " + this.itemName + " " 
//			+ this.itemQuantityInStock + " price: " + this.itemPrice
//			+ " cost: " + this.itemCost + " ";
//	//How to add fruit, vegie or preserve to this toString
//}
//
//public String toString3() {		
//
//	return "Item: " + this.itemCode + " " + this.itemName + " " 
//			+ this.itemQuantityInStock + " price: " + this.itemPrice
//			+ " cost: " + this.itemCost + " ";
//	//How to add fruit, vegie or preserve to this toString
//}
