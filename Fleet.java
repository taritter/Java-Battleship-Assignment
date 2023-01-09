import java.io.*;
import java.util.*;

/**This class is the fleet class and contains all the ships each player has, 
it also updates the ship if it's been hit and determines if a fleet has been sunk
*/

public class Fleet
{
   private Ship battleShip;
   private Ship aircraftCarrier;
   private Ship cruiser;
   private Ship sub;
   private Ship destroyer;
   
   /**this constructor instantiates all the ships in a fleet
   */
   public Fleet()
   {
      battleShip = new Battleship();
      aircraftCarrier = new AircraftCarrier();
      cruiser = new Cruiser();
      sub = new Sub();
      destroyer = new Destroyer();
   }
   
   /**this method updates the fleet based on the ship type and calls the ships hit method to tell the ship it's been hit
   @param type is ShipType
   @returns true if the ship has not sunk, false if it has sunk
   */
   public boolean updateFleet(ShipType type)
   {
      //if hit() returns false that means ship has not sunk
      if(type == ShipType.ST_AIRCRAFT_CARRIER)
      {
         if(aircraftCarrier.hit())
         {
            return true;
         }
         return false;
      }
      if(type == ShipType.ST_BATTLESHIP)
      {
         if(battleShip.hit())
         {
            return true;
         }
         return false;
      }
      if(type == ShipType.ST_CRUISER)
      {
         if(cruiser.hit())
         {
            return true;
         }
         return false;
      }
      if(type == ShipType.ST_DESTROYER)
      {
         if(destroyer.hit())
         {
            return true;
         }
         return false;
      }
      if(type == ShipType.ST_SUB)
      {
         if(sub.hit())
         {
            return true;
         }
         return false;
      }
      return false;
   }
   
   /**this method determines if the whole fleet has been sunk
   @returns true if all ships have been sunk, returns false if not all ships have been sunk
   */
   public boolean gameOver()
   {
      if(battleShip.getSunk() && aircraftCarrier.getSunk() && cruiser.getSunk() && sub.getSunk() && destroyer.getSunk())
         return true;
      return false;
   }
}