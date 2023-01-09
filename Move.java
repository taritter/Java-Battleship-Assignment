import java.io.*;
import java.util.*;
/**This class creates a move object that contains the coordinates used to attack
from either a string or 2 numbers. It then returns the move as a string.
*/
public class Move
{
   private int row;
   private int col;
   
   /**this constructor gets row and col as numbers
   */
   public Move(int x, int y)
   {
      row = x;
      col = y+1;
   }
   /**this constructor gets row and col as a string
   */
   public Move(String m)
   {
      String ch = m.substring(0).toUpperCase();
      char c = ch.charAt(0);
      row = (int) (c - 65);
      col = Integer.parseInt(String.valueOf(m.substring(1)));
   }
   /**method to get row
   @returns row
   */
   public int row()
   {
      return row;
   }
   /**method to get col
   @returns col
   */
   public int col()
   {
      return col;
   }
   /**this method prints out the move
   @returns the correct string display of move
   */
   @Override
   public String toString()
   {
      String chRow = Character.toString(row+65);
      String stCol = Integer.toString(col);
      String ts = chRow + stCol;
      return ts;
   }
}