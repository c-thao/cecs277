import java.util.*;

/**
 * 
 * @author Chou Thao
 *
 */
public class DefeatTheSithLord {
	/**
	 * Constructs a DefeatTheSithLord game and allows
	 * user to name and give an quote to a main character.
	 * This game loops until either the main character or an
	 * opposing main character is defeated(inactive).
	 * 
	 * @param rebels    an array of rebellion forces
	 * @param empire    an array of imperial forces
	 */
	public DefeatTheSithLord(ArrayList<Entity> rebels, ArrayList<Entity> empire) {
		Scanner input = new Scanner(System.in);
		genChars(rebels, empire, input);
		Random ran = new Random();

		for (Entity goodGuy : rebels) {
			System.out.println(goodGuy.getName() + " currently has "
					+ goodGuy.getHp() + " hp.");
		}
		for (Entity badGuy : empire) {
			System.out.println(badGuy.getName() + " currently has "
					+ badGuy.getHp() + " hp.");
		}

		while (rebels.get(0).getActive() == true
				&& empire.get(0).getActive() == true) {
			System.out.println("\nPlease select an option below:\n"
					+ "1.Use Force\n2.Attack\n3.Heal");
			int choice = 0;
			if (input.hasNextInt()) {
				choice = input.nextInt();
			}
			boolean cont;
			if (choice == 3) {
				cont = decision(choice, rebels.get(0), rebels, input);
			} else {
				cont = decision(choice, rebels.get(0), empire, input);
			}

			if (cont == true) {
				if (empire.get(0).getActive() == true) {
					for (int i = 1; i < rebels.size(); i++) {
						if (i >= 6) {
							choice = 4;
						} else {
							choice = ran.nextInt(1) + 2;
						}
						if (rebels.get(i).getActive() == true) {
							decision(choice, rebels.get(i), empire, input);
						}
					}
					for (int i = 0; i < empire.size(); i++) {
						if (i == 0) {
							choice = ran.nextInt(1) + 1;
						} else {
							choice = 2;
						}
						if (empire.get(i).getActive() == true) {
							decision(choice, empire.get(i), rebels, input);
						}
					}
				}
			} else if (cont == false) {
				System.out
						.println("If a commander such as yourself cannot fathom to give commands "
								+ " then empire has already won... Please input a valid number.");
			}
			System.out.println("\nRebels:");
			for (Entity goodGuy : rebels) {
				System.out.println(goodGuy.getName() + " currently has "
						+ goodGuy.getHp() + " hp.");
			}

			System.out.println("\nEmpire:");
			for (Entity badGuy : empire) {
				System.out.println(badGuy.getName() + " currently has "
						+ badGuy.getHp() + " hp.");
			}
		}
		if (rebels.get(0).getActive() != true) {
			System.out.println("Jedi " + rebels.get(0).getName()
					+ " has fallen "
					+ " and the empire's reign of terror contiunues.");
		} else {
			System.out
					.println("SithLord "
							+ empire.get(0).getName()
							+ " has fallen,"
							+ " the galaxy is now safe from the evil of the empire once more.");
		}

	}
	
	/**
	 * Generates two array of rebels and empire
	 * 
	 * @param rebels    an array of rebellion forces
	 * @param empire    an array of imperial force
	 * @param input     an input from user
	 */
	public static void genChars(ArrayList<Entity> rebels,
			ArrayList<Entity> empire, Scanner input) {
		System.out.println("Please create a Name for your Jedi.");
		String name = input.nextLine();
		System.out.println("Now please create a quote.");
		String quote = input.nextLine();
		rebels.add(new Jedi(name, quote, "blue"));

		rebels.add(new Rebel("Kevin", "The empire shall fall!"));
		rebels.add(new Rebel("Keith", "The empire shall fall!"));
		rebels.add(new Rebel("Khor", "The empire shall fall!"));
		rebels.add(new Rebel("Konor", "The empire shall fall!"));
		rebels.add(new Rebel("Khaos", "The empire shall fall!"));

		rebels.add(new Medical("Lily", 5));

		empire.add(new SithLord("Darth Chocolate",
				"Any fool willing stand before me shall fall before me!", "red"));
		empire.add(new Stormtropper("James", "Rebel scum!"));
		empire.add(new Stormtropper("Gary", "Rebel scum!"));
		empire.add(new Stormtropper("Larry", "Rebel scum!"));
		empire.add(new Stormtropper("Tom", "Rebel scum!"));
		empire.add(new Stormtropper("Ben", "Rebel scum!"));

	}
	
	/**
	 * Performs one of three task dependent on a
	 * integer choice which can perform an attack,
	 * use force, or heal some exclusion to varying
	 * types of Entities
	 * 
	 * @param choice     an integer choice
	 * @param first      the current Entity to perform a task
	 * @param enemies    an Array of targets to choose from
	 * @param input      an user's input
	 * @return           whether operation was successful or not
	 */
	public static boolean decision(int choice, Entity first,
			ArrayList<Entity> enemies, Scanner input) {
		Entity sec;
		Random rand = new Random();

		if (choice == 1 || choice == 2) {
			if (choice == 2) {
				first.setTask("attack");
			} else if (choice == 1) {
				first.setTask("use force");
			}

			ArrayList<Entity> atkable = new ArrayList<Entity>();
			for (Entity emy : enemies) {
				if (emy.getActive()) {
					atkable.add(emy);
				}
			}

			if (Jedi.class.isInstance(first)) {
				System.out.println("Jedi " + first.getName()
						+ " please choose an enemy below you wish to attack.");

				for (Entity emy : atkable) {
					System.out.print((atkable.indexOf(emy) + 1) + "."
							+ emy.getName() + "\n");
				}
				int trgt = input.nextInt();
				sec = atkable.get(trgt - 1);
			} else {
				sec = atkable.get(rand.nextInt(atkable.size() - 1));
			}
			first.doTask(sec);
			return true;
		} else if (choice == 3) {
			if (enemies.get(6).getActive()) {
				first.setTask("heal");
				first.doTask(enemies.get(6));
				return true;
			} else {
				System.out.println("Medical droid is not available to heal.");
				return false;
			}
		} else {
			return false;
		}
	}

}
