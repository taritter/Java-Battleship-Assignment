import java.io.*;
import java.util.*;

/**This class is a board class it uses the board to create the 2d array, however,
it also contains methods to make a move against itself. This will be the board the User will see.
*/
public class ComputerBoard extends Board
{
   /**this is the constructor method that takes in a file name and calls the board constructor
   @param s is the filename
   */
   public ComputerBoard(String s)throws IOException
   {
      super(s);
   }
   
   /**this method makes the player attack this board and if it hit a ship
   @param m is the move object that the player chose
   @returns a string saying "you sank my battleship" if the the computer's fleet is sunk return null
   */
   public String makePlayerMove(Move m)
   {
      ArrayList<ArrayList<CellStatus>> computerLayout = super.getLayout();
      if(computerLayout.get(m.row()).get(m.col()-1) == CellStatus.AIRCRAFT_CARRIER)
      {
         //get fleet update fleet update layout
         computerLayout = super.applyMoveToLayout(m);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
         if(sunk)
         {
            return "You Sank My Aircraft Carrier!";
         }
         else
         {
            return null;
         }
      }
      if(computerLayout.get(m.row()).get(m.col()-1) == CellStatus.BATTLESHIP)
      {
         //get fleet update fleet update layout
         super.getFleet();
         computerLayout = super.applyMoveToLayout(m);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_BATTLESHIP);
         if(sunk)
         {
            return "You Sank My Battleship!";
         }
         else
         {
            return null;
         }
      }
      if(computerLayout.get(m.row()).get(m.col()-1) == CellStatus.CRUISER)
      {
         //get fleet update fleet update layout
         super.getFleet();
         computerLayout = super.applyMoveToLayout(m);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_CRUISER);
         if(sunk)
         {
            return "You Sank My Cruiser!";
         }
         else
         {
            return null;
         }
      }
      if(computerLayout.get(m.row()).get(m.col()-1) == CellStatus.DESTROYER)
      {
         //get fleet update fleet update layout
         super.getFleet();
         computerLayout = super.applyMoveToLayout(m);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_DESTROYER);
         if(sunk)
         {
            return "You Sank My Destroyer!";
         }
         else
         {
            return null;
         }
      }
      if(computerLayout.get(m.row()).get(m.col()-1) == CellStatus.SUB)
      {
         //get fleet update fleet update layout
         super.getFleet();
         computerLayout = super.applyMoveToLayout(m);
         boolean sunk = super.getFleet().updateFleet(ShipType.ST_SUB);
         if(sunk)
         {
            return "You Sank My Sub!";
         }
         else
         {
            return null;
         }
      }
      if(computerLayout.get(m.row()).get(m.col()-1) == CellStatus.NOTHING)
      {
         super.getFleet();
         computerLayout = super.applyMoveToLayout(m);
      }
      return null;
   }
   /**this is the toString method
   @returns a formatted string
   */
   @Override
   public String toString()
   {
      String[] letter = {"A","B","C","D","E","F","G","H","I","J"};
      ArrayList<ArrayList<CellStatus>> computerLayout = super.getLayout();
      String ts = "   1 2 3 4 5 6 7 8 9 10 \n";
      for(int r = 0; r < 10; r++)
      {
         ts += letter[r] + "  ";
         for(int c = 0; c < 10; c++)
         {
            ts += computerLayout.get(r).get(c).toString().substring(0,1) + " ";
         }
         ts += "\n";
      }
      return ts;
   }
}