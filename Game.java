import java.io.*;
import java.util.*;

/**This class is the game class and starts the game, it allows the player
   to make a player and computer player and make moves against eachother.
   It also determines if one player was defeated*/
public class Game
{
   //create the players
   private ComputerBoard computer;
   private UserBoard player;
   
   /**This constructor creates a player and computer player, it does 
   exception handling on the file
   */
   public Game()
   {
      try
      {
         computer = new ComputerBoard("compFleet.txt");
      }
      catch (IOException e)
      {
         System.out.println(".txt file could not be found");
      }
      try
      {
         player = new UserBoard("userFleet.txt");
      }
      catch (IOException e)
      {
         System.out.println(".txt file could not be found");
      }
   }
   /**This method allows the board to make a move against the player
   @returns an array of two strings, the first is the move, the second is either null or the ship was sunk
   */
   public String[] makeComputerMove()
   {
      String[] againstBoard = player.makeComputerMove();
      return againstBoard;
   }
   /**this method allows the player to make a move against the board
   @param s is the move selected by the player
   @returns if computer's ship sunk or not
   */
   public String makePlayerMove(String s)
   {
      Move againstComp = new Move(s);
      return computer.makePlayerMove(againstComp);
   }
   /**this method determines if computer's fleet was sunk
   @returns true if the fleet was sunk and false if the fleet wasn't sunk
   */
   public boolean computerDefeated()
   {
      if(computer.gameOver())
      {
         return true;
      }
      return false;
   }
   /**this method determines if the player's fleet was sunk
   @returns true if the fleet was sunk and false if the fleet wasn't sunk
   */
   public boolean userDefeated()
   {
      if(player.gameOver())
      {
         return true;
      }
      return false;
   }
   /**this is the toString method
   @returns a the computer's toString method and player's toString method which is both their boards
   */
   @Override
   public String toString()
   {
      String ts = "\nCOMPUTER'S BOARD:\n" + computer.toString();
      ts += "\nPLAYER'S BOARD:\n" + player.toString();
      return ts;
   }
}