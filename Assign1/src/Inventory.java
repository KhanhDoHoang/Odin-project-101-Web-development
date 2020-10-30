import java.util.InputMismatchException;
import java.util.Scanner;

public class Inventory {
	private FoodItem[] inventory;
	private int count;

	public Inventory() {
		this.count = 0;
		this.inventory = new FoodItem[0];
	}
	public String toString() {
		//Display all data member
		String str = "";		
		for(int i = 0 ; i < this.inventory.length; i++) {
			System.out.println(this.inventory[i].toString());
		}
		return str;
	}

	public int alreadyExist(FoodItem item) {
		//---Return the index of a FoodItem in the inventory array
		for(int i = 0; i < inventory.length-1; i++) {
			if (inventory[i].isEqual(item) == true) {
				return i;				//existed
			} 
		}
		return -1;						//not existed
	}

	public boolean addItem(Scanner scanner) {
		//--expand--/	

		FoodItem[] tempArray = new FoodItem[count+1];								//count = 0  ---- tempArray[2] are 0 1
		for(int i = 0; i < count; i++) {											//count = 0  ---- i = null (1st time) 
			tempArray[i] = inventory[i];											//tempArray[0] = inventory[0]
		}
		tempArray[count] = new FoodItem();											//tempArray[1] = new Food()	
		inventory = tempArray;														//inventory12] ======> count == 0 /////// count still == 0

		//----Adds an item to the inventory array 
		scanner = new Scanner(System.in);		
		String userOption;
		while (true) { 
			try { 
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
				userOption = scanner.nextLine();
				break; 
			} 
			catch (InputMismatchException e) { 
				System.out.println("Invalid entry");
				scanner.next(); 
				continue; 
			}
		}		

		while(userOption.equals("") || userOption.equals("\t") || !userOption.equals("f") && !userOption.equals("p") && !userOption.equals("v")) {
			System.out.print("Invalid! Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
			userOption = scanner.nextLine();
		}

		//Assigning values for specific classes

		if(userOption.equals("f")) {
			inventory[count] = new Fruit();
			System.out.print("Enter the code for the item: ");
			inventory[count].inputCode(scanner);
			if(this.alreadyExist(inventory[count]) != -1) {
				System.out.println("The code already existed!");
				return false;
			}
			inventory[count].addItem(scanner);
		} else if (userOption.equals("v")) {
			inventory[count] = new Vegetables();
			System.out.print("Enter the code for the item: ");
			inventory[count].inputCode(scanner);
			if(this.alreadyExist(inventory[count]) != -1) {
				System.out.println("The code already existed!");
				return false;
			}
			inventory[count].addItem(scanner);
		} else {
			inventory[count] = new Preserve();
			System.out.print("Enter the code for the item: ");
			inventory[count].inputCode(scanner);
			if(this.alreadyExist(inventory[count]) != -1) {
				System.out.println("The code already existed!");
				return false;
			}
			inventory[count].addItem(scanner);
		}

		//bug
		//		if(this.alreadyExist(inventory[count]) == -1) {
		//			System.out.println("The code already existed!");
		//			return false;
		//		}

		//---------------------------------------------------------------------------------------------------------------//
		count += 1;
		return true;
	}

	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		//Temporary increase array to check code
		FoodItem[] tempArray = new FoodItem[count+1];								//count = 0  ---- tempArray[2] are 0 1
		for(int i = 0; i < count; i++) {											//count = 0  ---- i = null (1st time) 
			tempArray[i] = inventory[i];											//tempArray[0] = inventory[0]
		}
		tempArray[count] = new FoodItem();											//tempArray[1] = new Food()	
		inventory = tempArray;	

		if(buyOrSell == false) {	//false for sell
			
			
			
			System.out.print("Enter valid item code:");
			inventory[count].inputCode(scanner);
			if(this.alreadyExist(inventory[count]) == -1) {			//not existed
				System.out.println("Code not found in inventory...");
				System.out.println("Error...could not buy item");
				//check delete
				FoodItem[] tempArray1 = new FoodItem[count];							
				for(int i = 0; i < count; i++) {									
					tempArray1[i] = inventory[i];											
				}
				inventory = tempArray1;	
				//-------
				return false;
			} else {				
				while (true) { 
					try { 
						System.out.println("Enter valid quantity to sell");
						int amount = scanner.nextInt();
						amount = 0 - amount;
						inventory[this.alreadyExist(inventory[count])].updateItem(amount);

						FoodItem[] tempArray1 = new FoodItem[count];							
						for(int i = 0; i < count; i++) {									
							tempArray1[i] = inventory[i];											
						}
						inventory = tempArray1;	

						return true;
					} 
					catch (InputMismatchException e) { 
						System.out.println("Invalid quantity...");
						System.out.println("Error...could not sell item");
						scanner.next(); 

						FoodItem[] tempArray1 = new FoodItem[count];							
						for(int i = 0; i < count; i++) {									
							tempArray1[i] = inventory[i];											
						}
						inventory = tempArray1;	

						continue; 
					}
				}		
			}
		} else {					//true for buy
			System.out.print("Enter valid item code:");
			inventory[count].inputCode(scanner);
			if(this.alreadyExist(inventory[count]) == -1) {
				System.out.println("Code not found in inventory...");
				System.out.println("Error...could not buy item");

				FoodItem[] tempArray1 = new FoodItem[count];							
				for(int i = 0; i < count; i++) {									
					tempArray1[i] = inventory[i];											
				}
				inventory = tempArray1;	

				return false;
			} else {
				while (true) { 
					try { 
						System.out.println("Enter valid quantity to buy");
						int amount = scanner.nextInt();
						inventory[this.alreadyExist(inventory[count])].updateItem(amount);

						FoodItem[] tempArray1 = new FoodItem[count];							
						for(int i = 0; i < count; i++) {									
							tempArray1[i] = inventory[i];											
						}
						inventory = tempArray1;	

						return true;
					} 
					catch (InputMismatchException e) { 
						System.out.println("Invalid quantity...");
						System.out.println("Error...could not buy item");
						scanner.next(); 

						FoodItem[] tempArray1 = new FoodItem[count];							
						for(int i = 0; i < count; i++) {									
							tempArray1[i] = inventory[i];											
						}
						inventory = tempArray1;	

						continue; 
					}
				}		

			}
		}
		//Read in an itemCode to update and quantity by the input in the inventory array
		//The boolean parameter is used to denote
	}
}

//int current = inventory.length;
//for(int i = inventory.length; i >= 0 ; i--) {
//	if(inventory[current].getItemCode() == inventory[i].getItemCode()) {
//		return i;
//	}
//}	
//return -1;}
