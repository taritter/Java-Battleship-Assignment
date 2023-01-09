import java.io.*;
import java.util.*;
public class BattleShipDriver
{
/**This is the game battleship a new game object is created which creates the players and their boards.
This class does exception handling on the player inputting a move and also determines if a ship or the whole fleet sunk.
*/
   public static void main(String[]args)
   {
      Game game = new Game();
      Scanner sc = new Scanner(System.in);
      
      //introduction
      System.out.println("Welcome to Battleship! \n\nYou will be playing against a computer.\n");
      System.out.println("If you don't know how to play the rules are pretty simple.\n"
                        + "Both you and the computer place your ships without the other one knowing where.\n"
                        + "You will then attack different coordinates on the other player's board to try and sink their ships.\n"
                        + "If there is a large 'X' that means you hit a ship, if there is a small 'x' that means you hit nothing.\n");
      
      
      //flipping the coin
      int coin = new Random().nextInt(2);
      
      System.out.println("We'll do a coin toss to see who goes first.");
      //player's turn determines who goes next
      boolean pTurn = false;
      int playerCoin = new Random().nextInt(2);
      if(coin == playerCoin)
      {
         System.out.println("Player won the coin toss and gets to go first. Press enter to continue.\n\n");
         sc.nextLine();
         pTurn = true;
      }
      else
      {
         System.out.println("Computer won the coin toss and gets to go first. Press enter to contine.\n\n");
         sc.nextLine();
         pTurn = false;
      }
      
      //while no one has won by defeating the other's fleet
      while(!game.computerDefeated() && !game.userDefeated())
      {
         boolean valid = false;
         if(pTurn)
         {
            System.out.println("Your turn?");
            String move = "";
            //if input is not a string
            try
            {
               move = sc.nextLine();
               while(Integer.parseInt(move) % 1 == 0)
               {
                  System.out.println("Please enter a string (ex. A5): ");
                  move = sc.nextLine();
               }
            }
            catch(Exception e)
            {
               valid = true;
            }
            
            valid = false;
            char letter = 'z';
            String l = "";
            int number = 0;
            int numLetter = 0;
            
            //to make sure the first char is a letter and the second is a number
            try
            {
               l = move.substring(0).toUpperCase();
               letter = l.charAt(0);
               number = Integer.parseInt(move.substring(1));
               numLetter = (int) (letter)-65;
               if(numLetter >= 0 && numLetter <=9)
               {
                  if(number >= 0 && number <=10)
                  {
                     valid = true;
                  }
               }
            }
            catch(Exception e)
            {
               while(!valid)
               {
                  System.out.println("This is not a valid coordinate please re-enter: ");
                  move = sc.nextLine();
                  l = move.substring(0).toUpperCase();
                  letter = l.charAt(0);
                  number = Integer.parseInt(move.substring(1));
                  numLetter = (int) (letter)-65;
                  if(numLetter >= 0 && numLetter <=9)
                  {
                     if(number >= 0 && number <=10)
                     {
                        valid = true;
                     }
                  }
               }
            }
            
            //applies the move onto computer's board and determines if ship was sunk
            String sunk = game.makePlayerMove(move);
            if(sunk != null)
            {
               System.out.println("The Computer says: " + sunk);
               valid = true;
            }
            pTurn = false;
         }
         
         //if it's the computer's turn
         else if(!pTurn)
         {
            System.out.println("Computer's turn. Press enter to continue");
            sc.nextLine();
            String[] comp = game.makeComputerMove();
            System.out.println("Computer Chose: " + comp[0] + "\n");
            if(comp[1] != null)
            {
               System.out.println(comp[1]);
            }
            pTurn = true;
         }
         System.out.println(game.toString());
      }
      
      //if the game is over
      if(game.computerDefeated())
      {
         System.out.println("Computer's fleet sunk. Player won!");
      }
      else if(game.userDefeated())
      {
         System.out.println("Player's fleet sunk. Computer won!");
      }
      
   }
}