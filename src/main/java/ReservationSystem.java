import javax.swing.JOptionPane;
public class ReservationSystem {
   public static void main(String[] args) {
      final int MAX_NUM_ROOMS = 1000;
      Room[] building = build(MAX_NUM_ROOMS);
      reserveRooms(building);
      printReservations(building);
   }
   
   private static Room[] build(int maxNumRooms) {
      Room[] rooms = new Room[maxNumRooms];
      JOptionPane.showMessageDialog(null, "Begin Room Building");
      do {
         rooms[Room.getNumRooms()] = new Room(JOptionPane.showConfirmDialog(null,
            "Is the room a lab?") == JOptionPane.YES_OPTION);
         
         int roomNumber;
         boolean roomBuilt;
         do {
            try {
               roomNumber = Integer.parseInt(JOptionPane.showInputDialog(
                  "Enter the room number"));
            }
            catch (NumberFormatException e) {
               roomNumber = Room.MIN_ROOM_NUMBER - 1;
            }
            
            roomBuilt = isRoomBuilt(rooms, roomNumber);
            
            if (roomBuilt) {
               JOptionPane.showMessageDialog(null,
                  "Room has already been built. Please try again.");
            }
            
            else if (!rooms[Room.getNumRooms() - 1].setNumber(roomNumber)) {
               JOptionPane.showMessageDialog(null,
                  "Invalid room number. Please try again.");
            }
         }
         while (roomBuilt || !rooms[Room.getNumRooms() - 1].setNumber(roomNumber));

         int seatsAvailable;
         do {
            try {
               seatsAvailable = Integer.parseInt(JOptionPane.showInputDialog(
                  "Enter the number of seats available: "));
            }
            catch (NumberFormatException e) {
               seatsAvailable = Room.MIN_SEAT_CAPACITY - 1;
            }
            if (!rooms[Room.getNumRooms() - 1].setSeatCapacity(seatsAvailable)) {
               JOptionPane.showMessageDialog(null,
                  "Invalid seat capacity. Please try again.");
            }
         }
         while (!rooms[Room.getNumRooms() - 1].setSeatCapacity(seatsAvailable));         
                  
      } while (JOptionPane.showConfirmDialog(null,
                  "Build another room?") == JOptionPane.YES_OPTION);
      return rooms;
   }

   private static void reserveRooms(Room[] building) {
      JOptionPane.showMessageDialog(null, "Begin Room Reservations");   
      
      do {
         boolean isRoomScheduled = false;;
         boolean isLabNeeded = JOptionPane.showConfirmDialog(null,
            "Do you need a lab for your reservation?") == JOptionPane.YES_OPTION;
         int seatsNeeded;

         do {
            try {
               seatsNeeded = Integer.parseInt(JOptionPane.showInputDialog(null,
                  "How many seats in the room are needed?"));
            }
            catch (NumberFormatException e) {
               seatsNeeded = Room.MIN_SEAT_CAPACITY - 1;
               JOptionPane.showMessageDialog(null,
                  "Invalid seat capacity. Please try again.");
            }
         } while (seatsNeeded < Room.MIN_SEAT_CAPACITY);

         int index = 0;         
         while (index < Room.getNumRooms() && !isRoomScheduled) {
            if (building[index].reserve(isLabNeeded, seatsNeeded)) {
               JOptionPane.showMessageDialog(null, building[index].getRoomNumber() + " is reserved");
               isRoomScheduled = true;
            }
            ++index;
         }
         
         if (!isRoomScheduled) {
            JOptionPane.showMessageDialog(null, "No rooms available for this request");
         }
         
      } while (JOptionPane.showConfirmDialog(null, "Schedule another room?")
         == JOptionPane.YES_OPTION);   
   }

   private static boolean isRoomBuilt(Room[] building, int roomNumber) {
      boolean roomBuilt = false;
      
      for (int x = 0; x < Room.getNumRooms(); x++) {
         if (building[x].getNumber() == roomNumber) {
            roomBuilt = true;
            break;
         }
      }
      
      return roomBuilt;
   }

   private static void printReservations(Room[] building) {
      String reservationList = "Rooms for Orientation: \n";
      for (int x = 0; x < Room.getNumRooms(); x++) {
         reservationList += building[x] + "\n";
      }
      JOptionPane.showMessageDialog(null, reservationList);
   }
   

}