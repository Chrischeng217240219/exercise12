import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

// 自定义异常类
class MissingExtensionException extends Exception {
    public MissingExtensionException(String message) {
        super(message);
    }
}

public class ExceptionDemo {
    private double divisor;
    private double dividend;
    private double result;
    private Scanner input = new Scanner(System.in);

    public void divide() {
        try {
            System.out.print("Enter divisor: ");
            divisor = input.nextDouble();
            System.out.print("Enter dividend: ");
            dividend = input.nextDouble();
            input.nextLine();
            
            if (dividend == 0) throw new ArithmeticException("Division by zero");
            result = divisor / dividend;
            System.out.println("Result: " + result);
            
        } catch (InputMismatchException e) {
            input.nextLine();
            throw new InputMismatchException("Invalid input type");
        }
    }

    public void goToDivideMethod() {
        divide();
    }

    public void readAFile() throws IOException, MissingExtensionException {
        System.out.print("Enter file path: ");
        String path = input.next();
        input.nextLine();
        
        if (!path.contains(".")) {
            throw new MissingExtensionException("Missing file extension");
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            System.out.println("File content: " + reader.readLine());
        }
    }

    public void displayChoices() {
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n1. Divide\n2. Read from file\n3. Exit");
                System.out.print("Choose: ");
                int choice = input.nextInt();
                input.nextLine();
                
                switch (choice) {
                    case 1:
                        try {
                            goToDivideMethod();
                        } catch (ArithmeticException | InputMismatchException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            readAFile();
                        } catch (IOException | MissingExtensionException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid menu input");
                input.nextLine();
            } finally {
                if (!running) {
                    input.close();
                    System.out.println("Scanner closed.");
                }
            }
        }
    }
}