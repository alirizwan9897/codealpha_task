import java.util.Scanner;

public class SimpleChatBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("Bot: Hello! Ask me something.");
        while (true) {
            System.out.print("You: ");
            input = sc.nextLine().toLowerCase();
            if (input.contains("hello")) {
                System.out.println("Bot: Hi Rizwan!");
            } else if (input.contains("how are you")) {
                System.out.println("Bot: I am fine. How about you?");
            } else if (input.contains("bye")) {
                System.out.println("Bot: Goodbye!");
                break;
            } else {
                System.out.println("Bot: I don't understand.");
            }
        }
        sc.close();
    }
}