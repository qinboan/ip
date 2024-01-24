import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();

            if ("bye".equals(input)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(input)) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else if (input.startsWith("todo")) {
                addTask(new ToDo(input.substring(5)));
            } else if (input.startsWith("deadline")) {
                String[] parts = input.substring(9).split(" /by ");
                addTask(new Deadline(parts[0], parts[1]));
            } else if (input.startsWith("event")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                addTask(new Event(parts[0], parts[1], parts[2]));
            } else {
                System.out.println("Invalid task!\n");
            }
        }

        scanner.close();
    }
    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.\n");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
        System.out.println("");
    }

    private static void markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskIndex].toString() + "\n");
        } else {
            System.out.println("Task does not exist.");
        }
    }

    private static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1; // subtract 1 for array index
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskIndex].toString() + "\n");
        } else {
            System.out.println("Task does not exist.");
        }
    }
}
