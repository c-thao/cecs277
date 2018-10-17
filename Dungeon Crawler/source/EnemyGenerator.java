

import java.io.*;
import java.util.*;

public class EnemyGenerator {
	
	private ArrayList<Enemy> enemyList;
	
	public EnemyGenerator() {
		enemyList = new ArrayList<Enemy>();
		try {
			Scanner read = new Scanner(new File("EnemyList.txt"));
			while(read.hasNextLine()) {
				String[] split = read.nextLine().split(",");
				//System.out.println("Current split size is " + split.length);
				//System.out.println("Current enemy is " + split[0]);
				//System.out.println("Current enemy quote is " + split[1]);
				//System.out.println("Current enemy hp is " + split[2]);
				enemyList.add(new Enemy(split[0],split[1],Integer.parseInt(split[2]),1,
						25,new ItemGenerator().generateItem()));
			}
			read.close();
		} catch(FileNotFoundException e) {
			System.out.println("Look no further because you are clearly lost.");
		}
	}
	
	public Enemy generateEnemy(int level) {
		Enemy enmy = enemyList.get(new Random().nextInt(enemyList.size()-1));
		enmy = new Enemy(enmy.getName(),enmy.getQuip(),enmy.getHp()+level,level,enmy.getGold(),enmy.getItem());
		return enmy;
	}
	
	
}
