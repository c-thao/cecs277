import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TestTaskList {

	public static void main(String[] args) {
		GenHeap<Job> taskList = new GenHeap<Job>();
		boolean cont = true;
		initHeap(taskList);
		
		while (cont == true) {
			System.out.println("1. Display the list of tasks"
					+ "\n2. Display current task"
					+ "\n3. Add a new item to the task list"
					+ "\n4. Mark complete-removes task from list displays new current task"
					+ "\n5. Postpone next task-prompts user for new due date, remove and re-add to list"
					+ "\n6. Quit");
			cont = taskChoice(taskList, checkInt(0, 6));
		}
	}

	public static void initHeap(GenHeap<Job> taskList) {
		try {
			Scanner read = new Scanner(new File("taskList.txt"));
			while (read.hasNextLine()) {
				String taskDate = read.nextLine();
				//System.out.println(taskDate);
				String split[] = taskDate.split(",", 2);
				//System.out.println(split[0] + " " + split[1]);
				SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				Date date;
				try {
					taskList.add(new Node<Job>(new Job(split[0], d.parse(split[1]))));
				} catch (ParseException e) {
					System.out.println("Date conversion failed.");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File taskList is no where to be found.");
		}
	}

	public static boolean taskChoice(GenHeap<Job> taskList, int choice) {
		Scanner input = new Scanner(System.in);
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
			System.out.println("new current task: " + taskList.getAt(0).getData().toString());
			break;
		case 5:
			boolean cor = false;
			Date dat;
			Job current = taskList.getAt(0).getData();
			taskList.removeMin();
			Job next = taskList.getAt(0).getData();
			taskList.removeMin();
			System.out.println("Next task is " + next.toString());
			System.out.println("Please enter the new date and time the task is due(note time must be in military time format)");
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

	public static int checkInt(int low, int high) {
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
