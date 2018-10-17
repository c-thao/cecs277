

import java.io.*;
import java.util.*;

public class ItemGenerator {
	
	private ArrayList<Item> ItemList;
	
	public ItemGenerator() {
		ItemList = new ArrayList<Item>();
		try {
			Scanner read = new Scanner(new File("ItemList.txt"));
			while (read.hasNextLine()) {
				String line = read.nextLine();
				String[] split = line.split(",");
				//System.out.println("Current split size is " + split.length);
				//System.out.println("Current item is " + split[0]);
				//System.out.println(split[0] + ", " + Integer.valueOf(split[1]));
				int price = Integer.valueOf(split[1]);
				ItemList.add(new Item(split[0], price ));
				//System.out.println("Current itemlist size is " + ItemList.size());
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("Where did you put the file you imbecile.");
		}
	}
	
	public Item generateItem() {
		return ItemList.get(0);
		//return ItemList.get(new Random().nextInt(ItemList.size()-1));
	}
	
	
}
