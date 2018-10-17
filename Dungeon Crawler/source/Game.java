
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	public Game(Level dung, Hero mainChar) {
		mainChar = headInto(dung, mainChar);
		if (mainChar.getHp() > 0) {
			saveToAFile(mainChar);
		}
	}
	
	public Hero headInto(Level dung, Hero mainChar) {
		char obs = 'n';
		ItemGenerator items = new ItemGenerator();
		EnemyGenerator enmy = new EnemyGenerator();
		boolean cont = true;
		while (cont == true && mainChar.getHp() > 0) {
			int dirChoice = 0;
			dung.displayMap(mainChar.getLocation());
			System.out.println("\n" + mainChar.getName()
					+ "'s current location is " + mainChar.getLocation().getX()
					+ ", " + mainChar.getLocation().getY());
			
			/*ArrayList<Item> pack = mainChar.getItems();
			if (pack.size() > 0) {
				for (Item i : pack) {
					System.out.print("\n" + i.getName() + " sells for "
							+ i.getValue());
				}
			}*/
			
			System.out.println("\nChose a Direction you wish to go.");
			System.out.println("\n1. North\n2. South\n3. East\n4. West\n");

			while (dirChoice < 1 || dirChoice > 5) {
				dirChoice = checkInt();
			}

			if (dirChoice == 1) {
				obs = mainChar.goNorth(dung);
			} else if (dirChoice == 2) {
				obs = mainChar.goSouth(dung);
			} else if (dirChoice == 3) {
				obs = mainChar.goEast(dung);
			} else {
				obs = mainChar.goWest(dung);
			}

			if (obs == 'n') {
				System.out.println("You lump that's not a valid direction.");
			} else {
				if (obs == 'm') {
					Enemy monster = enmy.generateEnemy(mainChar.getLevel());
					System.out.println(mainChar.getName()
							+ " has encountered a monster.");
					while (mainChar.getHp() > 0 && monster.getHp() > 0) {
						mainChar.display();
						monster.display();
						int fightChoice = 0;
						int choices = 2;
						int index = 0;
						
						ArrayList<Item> pack = mainChar.getItems();
						if (pack.size() > 0) {
							for (Item i : pack) {
								if (i.getName().equals("Health Potion")) {
									//System.out.println("User has health potion");
									choices = 3;
									index = pack.indexOf(i);
								}
							}
						}
						
						if (choices == 2) {
							System.out
									.println("Do you wish to:\n1. fight\n2. run");
							
						} else {
							System.out
									.println("Do you wish to:\n1. fight\n2. run\n3. heal");
						}

						while (fightChoice < 1 || fightChoice > choices) {
							fightChoice = checkInt();
						}
						if (fightChoice == 1) {
							mainChar.attack(monster);
							if (monster.getHp() > 0) {
								monster.attack(mainChar);
							}
						} else if (fightChoice == 2) {
							runAway(mainChar, dung);
						} else {
							mainChar.heal();
							mainChar.removeItem(pack.get(index));
						}
					}
					if (monster.getHp() == 0) {
						dung.clearRoom(mainChar.getLocation());
					}

				} else if (obs == 'i') {
					Item loot = items.generateItem();
					System.out.println(mainChar.getName()
							+ " has stumble upon " + loot.getName());
					int itmChoice = 0;
					while (itmChoice < 1 || itmChoice > 2) {
						System.out
								.println("1. Pick up item\n2. Swap with a current item\n3. Sell it");
						itmChoice = checkInt();
					}
					if (itmChoice == 1) {
						mainChar.pickUpItem(loot);
					} else if (itmChoice == 2) {
						System.out
								.println("Which item do you wish to swap it with?");
						int bagNum = 1;
						ArrayList<Item> bag = mainChar.getItems();
						if (bag.size() > 0) {
							for (Item i : bag) {
								System.out.print("\n" + (bagNum++) + ". "
										+ i.getName());
							}
							System.out.println();
							int swapNum = 0;
							while (swapNum < 1 || swapNum >= bagNum) {
								swapNum = checkInt();
							}
							mainChar.removeItem(bag.get(swapNum));
							mainChar.pickUpItem(loot);
						} else {
							System.out
									.println("Check to see if there is a hole in your bag traveler for you have no items upon which to swap.");
						}
					} else {
						mainChar.collectGold(loot.getValue());
						System.out.println(loot.getName() + " sold for "
								+ loot.getValue());
					}
					dung.clearRoom(mainChar.getLocation());
				} else if (obs == 's') {
					System.out.println("Which item do you wish to sell?");
					int bagNum = 1;
					ArrayList<Item> bag = mainChar.getItems();
					if (bag.size() > 0) {
						for (Item i : bag) {
							System.out.print("\n" + (bagNum++) + ". "
									+ i.getName() + " sells for "
									+ i.getValue());
						}
						System.out.println("\n" + bagNum + ". sell nothing");
						int sellNum = 0;
						while (sellNum < 1 || sellNum > bagNum + 1) {
							sellNum = checkInt();
						}
						if (sellNum < bagNum) {
							mainChar.collectGold(bag.get(sellNum - 1)
									.getValue());
							System.out.println(bag.get(sellNum - 1).getName()
									+ " sold");
							mainChar.removeItem(bag.get(sellNum - 1));
						}
					} else {
						System.out
								.println("Seems like you don't have anything to sell besides your freedom traveler.");
					}
				} else if (obs == 'f') {
					System.out
							.println("Dungeon complete. You have leveled up.");
					mainChar.increaseLevel();
					cont = false;
				} else {
					System.out.println("This room is clear traveler why don't you get a move on.");
				}
			}
		}
		if (mainChar.getHp() == 0) {
			System.out
					.println("Another foolish mortal has perished. Maybe the next one would be more capable of retrieving my treasure.");
		}
		return mainChar;

	}

	public void runAway(Hero chara, Level dung) {
		boolean check = false;
		int dir = new Random().nextInt(3) + 1;
		System.out.println(dir);
		while (check == false) {
			char path = 'n';
			if (dir == 1) {
				path = chara.goNorth(dung);
			} else if (dir == 2) {
				path = chara.goSouth(dung);
			} else if (dir == 3) {
				path = chara.goEast(dung);
			} else  if (dir == 4){
				path = chara.goWest(dung);
			}
			if (path != 'n') {
				check = true;
			}
			dir = new Random().nextInt(3) + 1;
		}
		System.out.println("\n" + chara.getName() + "has run to location "
				+ chara.getLocation().getX() + ", "
				+ chara.getLocation().getY());

	}
	

	public static void saveToAFile(Hero charac) {
		// saving a hero to a file
		Hero ch = charac;
		try {
			FileOutputStream fileOut = new FileOutputStream("hero.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(ch);
			out.close();
			fileOut.close();
			System.out.println("\nSerilized data saved correctly");
		} catch (IOException i) {
			System.out.println("\nObject not serializable");
		}
	}

	public int checkInt() {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;

		while (!valid) {
			if (in.hasNextInt()) {
				validNum = in.nextInt();
				valid = true;
			} else {
				in.next();
				System.out.print("Invalue input- Retry:");
			}
		}
		return validNum;
	}

}
