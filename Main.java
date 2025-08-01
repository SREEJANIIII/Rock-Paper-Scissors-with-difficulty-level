import java.util.*;

enum Moves
{
    ROCK, PAPER, SCISSORS;

    public boolean beats(Moves other) 
    {
        return (this == ROCK && other == SCISSORS)
                || (this == PAPER && other == ROCK)
                || (this == SCISSORS && other == PAPER);
    }
}

class Main
{
    public static void main(String[] args) 
    {
        System.out.println("🎮 Welcome to Rock Paper Scissors! 🎮");
        int HS_Player = 0;
        int HS_Comp = 0;
        boolean b = true;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int r=0;
        while (b) 
        {
            boolean b1 = true;
            while(true)
            {

            try 
            {
                System.out.print("ENTER THE NO. OF ROUNDS: ");
                r = sc.nextInt();
                if(r<0)
                {
                    System.out.println("Round no. must be positive. Try again!");
                    continue;
                }
                else
                break; // valid input
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("🚫Invalid choice! Enter a valid no.");
                sc.nextLine(); //Clear the junk input
            }
            }

            if (r != 0) 
            {
                int comp = 0, player = 0;
                System.out.print("Enter Difficulty level: Easy, medium, or hard: ");
                String DL = sc.next();
                while (b1) 
                {
                    if ((DL.equalsIgnoreCase("hard")) || (DL.equalsIgnoreCase("medium"))
                            || (DL.equalsIgnoreCase("easy")))
                        b1 = false;
                    else 
                    {
                        System.out.print("🚫Invalid Input. Pls enter easy, medium, or hard: ");
                        DL = sc.next();
                    }
                }

                System.out.println("THE CHOICES ARE: 🪨 Rock, 📄 Paper, ✂️ Scissors");
                for (int i = 1; i <= r; i++) 
                {
                    int rand1 = rand.nextInt(100); // For the difficulty level
                    Moves PlayerMove = null;
                    Moves CompMove = null;
                    while (true) 
                    {
                        System.out.print("SHOOT! Enter your choice for round " + i + ": ");
                        String in = sc.next();
                        try 
                        {
                            PlayerMove= Moves.valueOf(in.toUpperCase());
                            break; // valid input
                        } 
                        catch (IllegalArgumentException e) 
                        {
                            System.out.println("🚫Invalid choice! Try again!");
                            sc.nextLine();
                        }
                    }
                    while (true) {
                        if (DL.equalsIgnoreCase("hard")) {
                            if (rand1 <= 65)
                                CompMove = Win(PlayerMove);
                            else if (rand1 <= 80)
                                CompMove = Lose(PlayerMove);
                            else
                                CompMove = Tie(PlayerMove);
                            break;
                        } else if (DL.equalsIgnoreCase("easy")) {
                            if (rand1 <= 50)
                                CompMove = Lose(PlayerMove);
                            else if (rand1 <= 75)
                                CompMove = Win(PlayerMove);
                            else
                                CompMove = Tie(PlayerMove);
                            break;
                        } else if (DL.equalsIgnoreCase("medium")) {
                            int rand2 = rand.nextInt(3);
                            CompMove = Moves.values()[rand2];
                            break;
                        }
                    }

                    System.out.println("🙋Your choice: " + PlayerMove);
                    System.out.println("💻Computer's choice: " + CompMove);

                    if (PlayerMove == CompMove)
                        System.out.println("🤝This round ends in a tie!");
                    else if (PlayerMove.beats(CompMove)) {
                        System.out.println("🏆YOU WON THIS ROUND!🏆");
                        player++;
                    } else {
                        System.out.println("COMPUTER WON THIS ROUND!");
                        comp++;
                    }

                    System.out.println("Results of round " + i + ": 💻Computer= " + comp + "    🙋Player= " + player);
                    System.out.println();

                }

                System.out.println("THE POINTS ARE----- 💻Computer: " + comp + "     🙋Player: " + player);
                if (comp > player)
                    System.out.println(
                            "The computer is the winner by " + (comp - player) + " points. Better luck next time!🤝");
                else if (comp < player)
                    System.out.println(
                            "🏆👑🎉You are the winner by " + (player - comp) + " points🔥. CONGRATULATIONS!🎉");
                else
                    System.out.println("🤝THE GAME ENDS IN A TIE.");
                HS_Player += player;
                HS_Comp += comp;
            }

            System.out.println("🏆Current high scores are: 🙋Player= " + HS_Player + "   💻Computer= " + HS_Comp + "🏆");
            System.out.println("🔥🔥🔥");

            System.out.print("Wanna go for another round? Enter a yes or a no: ");
            String p = sc.next();
            while (true) {
                if (p.equalsIgnoreCase("yes")) {
                    b = true;
                    break;
                } else if (p.equalsIgnoreCase("no")) {
                    System.out.println("🙏THANK YOU! VISIT AGAIN!💖🙏");
                    b = false;
                    break;
                } else {
                    System.out.println("🚫Invalid choice. Pls enter a yes or no.");
                    p = sc.next();
                }

            }
        }
        sc.close();
    }

    public static Moves Win(Moves PlayerMove) {
        Moves result;
        switch (PlayerMove) {
            case ROCK:
                result = Moves.PAPER;
                break;
            case PAPER:
                result = Moves.SCISSORS;
                break;
            case SCISSORS:
                result = Moves.ROCK;
                break;
            default:
                throw new IllegalArgumentException("Invalid move: " + PlayerMove); // Handle unexpected cases
        }
        return result;
    }

    public static Moves Lose(Moves PlayerMove) {
        Moves result;
        switch (PlayerMove) {
            case ROCK:
                result = Moves.SCISSORS;
                break;
            case PAPER:
                result = Moves.ROCK;
                break;
            case SCISSORS:
                result = Moves.PAPER;
                break;
            default:
                throw new IllegalArgumentException("Invalid move: " + PlayerMove); // Handle unexpected cases
        }
        return result;
    }

    public static Moves Tie(Moves PlayerMove) {
        Moves result;
        switch (PlayerMove) {
            case ROCK:
                result = Moves.ROCK;
                break;
            case PAPER:
                result = Moves.PAPER;
                break;
            case SCISSORS:
                result = Moves.SCISSORS;
                break;
            default:
                throw new IllegalArgumentException("Invalid move: " + PlayerMove); // Handle unexpected cases
        }
        return result;
    }
}
