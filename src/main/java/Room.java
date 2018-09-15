public class Room {
   private static final String BUILDING_PREFIX = "ENGR";   
   public static final int MIN_ROOM_NUMBER = 1000;
   public static final int MAX_ROOM_NUMBER = 5999;
   public static final int MIN_SEAT_CAPACITY = 1;
   public static final int MAX_SEAT_CAPACITY = 100;
   private int number;
   private int seatCapacity;
   private boolean isLab;
   private boolean isAvailable;
   private static int numRooms = 0;
   
   public Room() {
      ++this.numRooms;
      this.isAvailable = true;
   }
   
   public Room(boolean isLab) {
      this();
      this.isLab = isLab;
   }
   
   public boolean setNumber(int number) {
      if (number >= MIN_ROOM_NUMBER && number <= MAX_ROOM_NUMBER) {
         this.number = number;
         return true;
      }
      else {
         return false;
      }
   }

   public boolean setSeatCapacity(int seatCapacity) {
      if (seatCapacity >= MIN_SEAT_CAPACITY && seatCapacity <= MAX_SEAT_CAPACITY) {
         this.seatCapacity = seatCapacity;
         return true;
      }
      else {
         return false;
      }
   }
   
   public void setIsLab(boolean isLab) {
      this.isLab = isLab;
   }
   
   public int getNumber() {
      return this.number;
   }
   
   public String getRoomNumber() {
      return BUILDING_PREFIX + "-" + this.number;
   }
   
   public boolean reserve(boolean isLabRequested, int seatRequest) {
      if (isLabRequested == this.isLab && seatRequest <= this.seatCapacity && this.isAvailable) {
         this.isAvailable = false;
         return true;
      }
      else {
         return false;
      }
   }
   
   public static int getNumRooms() {
      return numRooms;
   }
   
   public String toString() {
      return getRoomNumber() + " (" + ((this.isLab) ? "lab" : "non-lab") + ")"
         + " is " + ((this.isAvailable) ? "OPEN" : "RESERVED");
   }
}


