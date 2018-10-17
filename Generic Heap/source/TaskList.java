import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 * a TaskList program which 
 * allows an user to inter-
 * act upon a list of task
 * inside of a heap
 * 
 * @author Chou Thao
 *
 */
public class TaskList {
	/**
	 * a heap of Job objects
	 * 
	 */
	private GenHeap<Job> taskList;
	/**
	 * a constructor which initializes
	 * taskList with a heap/list of Job
	 * objects and then runs the program
	 * 
	 * @param tasks a heap/list of Job
	 * 		  objects
	 */
	public TaskList(GenHeap<Job> tasks) {
		taskList = tasks;
		runTaskProg();
	}
	/**
	 * continuously prompts an user to
	 * select one of 6 options and
	 * passes the selected choice into
	 * another method
	 * 
	 */
	public void runTaskProg() {
		boolean cont = true;
		while (cont == true) {
			System.out.println("1. Display the list of tasks" + "\n2. Display current task"
					+ "\n3. Add a new item to the task list"
					+ "\n4. Mark complete-removes task from list displays new current task"
					+ "\n5. Postpone next task-prompts user for new due date, remove and re-add to list" + "\n6. Quit");
			cont = taskChoice(checkInt(0, 6));
		}
	}
	/**
	 * performs one of six task dependent
	 * on the selected choice noted by the
	 * argument choice
	 * 
	 * @param choice
	 * @return       true if choice is not 6
	 * 				 else false
	 */
	public boolean taskChoice(int choice) {
		Scanner input = new Scanner(System.in);
		if (choice != 3 && choice != 6 && taskList.isEmpty()) {
			System.out.println("Please add a new task as the current task list is empty");
			return true;
		}
		switch (choice) {
		case 1:
			taskList.printHeap();
			break;
		case 2:
			System.out.println("current task: " + taskList.getAt(0).getData().toString());
			break;
		case 3:
			String task = " ";
			boolean correct = false;
			Date date;
			System.out.println("Please enter a new task:");
			System.out.println("Task");
			if (input.hasNextLine()) {
				task = input.nextLine();
			}
			System.out.println("Please enter date and time the task is due(note time must be in military time format)");
			System.out.println("task MM/DD/YYYY hh:mm");
			while (correct == false && input.hasNextLine()) {
				SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				String nDate = input.nextLine();
				try {
					date = d.parse(nDate);
					correct = true;
					taskList.add(new Node<Job>(new Job(task, date)));
				} catch (ParseException e) {
					System.out.println("Please enter the associated date and time again.");
				}
			}
			break;
		case 4:
			System.out.println("completed current task: " + taskList.getAt(0).getData().toString());
			taskList.removeMin();
			if (!taskList.isEmpty()) {
				System.out.println("new current task: " + taskList.getAt(0).getData().toString());
			} else {
				System.out.println("there are no more tasks in the list");
			}
			break;
		case 5:
			boolean cor = false;
			Date dat;
			Job current = taskList.removeMin().getData();
			if (!taskList.isEmpty()) {
				Job next = taskList.removeMin().getData();
				System.out.println("Next task is " + next.toString());
				System.out.println(
						"Please enter the new date and time the task is due(note time must be in military time format)");
				System.out.println("MM/DD/YYYY hh:mm");
				while (cor == false && input.hasNextLine()) {
					SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy HH:mm");
					String nDate = input.nextLine();
					try {
						dat = d.parse(nDate);
						cor = true;
						taskList.add(new Node<Job>(new Job(next.getTask(), dat)));
						taskList.add(new Node<Job>(current));
					} catch (ParseException e) {
						System.out.println("Please enter the new due date and time again.");
					}
				}
			} else {
				String nod = " ";
				while (!nod.equalsIgnoreCase("Y") && !nod.equalsIgnoreCase("N")) {
					System.out.println(
							"Sorry there is currently only one task" + "\nDo you wish to change its due date?(y or n)");
					nod = input.next();
				}
				if (nod.equalsIgnoreCase("Y")) {
					System.out.println("Please enter the new due date and time.\nmm/dd/yyyy hh:mm");
					while (cor == false && input.hasNextLine()) {
						SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy HH:mm");
						input.nextLine();
						String nDate = input.nextLine();
						try {
							dat = d.parse(nDate);
							cor = true;
							taskList.add(new Node<Job>(new Job(current.getTask(), dat)));
						} catch (ParseException e) {
							System.out
									.println("Sorry I didn't get that can you please reenter the new due date and time"
											+ "\nmm/dd/yyyy hh:mm");
						}
					}
				}
			}
			break;
		case 6:
			System.out.println("Exitting task program");
			return false;
		default:
			System.out.println("Invalid choice");
			break;
		}
		return true;
	}
	/**
	 * prompts an user for a integer within
	 * a specified range
	 * 
	 * @param low  minimum int
	 * @param high maximum int
	 * @return     a valid int
	 */
	public int checkInt(int low, int high) {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		while (!valid) {
			if (in.hasNextInt()) {
				validNum = in.nextInt();
				if (validNum >= low && validNum <= high) {
					valid = true;
				} else {
					System.out.print("Invalid- Retry: ");
				}
			} else {
				// clear buffer of junk input
				in.next();
				System.out.print("Invalid input- Retry: ");
			}
		}
		return validNum;
	}

}
