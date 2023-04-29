import java.util.Scanner;

public class InvestmentBot {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hi! I'm your investment bot. What's your name?");
        String name = scanner.nextLine();
        
        System.out.println("Nice to meet you, " + name + "! How much money are you looking to invest?");
        int investmentAmount = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        
        System.out.println("Are you looking for short-term or long-term investments?");
        String investmentHorizon = scanner.nextLine();
        
        System.out.println("Are you comfortable with high-risk or low-risk investments?");
        String riskLevel = scanner.nextLine();
        
        // suggest investment options based on the customer's needs
        if (investmentHorizon.equals("short-term") && riskLevel.equals("high-risk")) {
            System.out.println("Based on your needs, I suggest investing in cryptocurrency or stocks with high volatility.");
        } else if (investmentHorizon.equals("short-term") && riskLevel.equals("low-risk")) {
            System.out.println("Based on your needs, I suggest investing in a high-yield savings account or a money market fund.");
        } else if (investmentHorizon.equals("long-term") && riskLevel.equals("high-risk")) {
            System.out.println("Based on your needs, I suggest investing in growth stocks or an index fund with high returns.");
        } else if (investmentHorizon.equals("long-term") && riskLevel.equals("low-risk")) {
            System.out.println("Based on your needs, I suggest investing in a diversified portfolio of low-cost index funds or bonds.");
        } else {
            System.out.println("Sorry, I don't have any investment suggestions that match your needs.");
        }
        
        System.out.println("Thank you for using our investment bot!");
    }
    
}
