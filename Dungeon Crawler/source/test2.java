
import java.util.*;
import java.awt.*;
import java.io.*;

public class test2 {

	public static void main(String[] args) {
		Hero charac = new Hero("Jon Tron", "Tron baby", new Point(0, 0));
		
		Item hpItm = new Item("health potion", 25);
		
		//charac.pickUpItem(new Item("health potion", 25));
		charac.pickUpItem(hpItm);
		if(charac.getItems().contains(hpItm)) {
			System.out.println("health potion is in character inventory");
		}
		else {
			System.out.println("health potion is not detected in character inventory");
		}
		/*
		// saving hero
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hero.dat"));
			out.writeObject(charac);
			out.close();
			System.out.println("\nSerilized data saved correctly");
		} catch (IOException i) {
			System.out.println("\nObject not serializable");
		}
		
		Hero james = new Hero("James turn", "turn baby", new Point(0, 0));
		// loading hero
		james.display();
		try {
			FileInputStream fileIn = new FileInputStream("hero.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			james = (Hero) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("\nSerilized data loaded correctly");
		} catch (IOException e) {
			System.out.println("\nSerilizied file not found");
		} catch (ClassNotFoundException c) {
			System.out
					.println("\nNo class was found for specified serialize object");
		}
		james.display();*/
	}
}