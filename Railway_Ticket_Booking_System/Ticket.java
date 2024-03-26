import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Ticket {
    static int a_lowerberth = 1;
    static int a_middleberth = 1;
    static int a_upperberth = 1;
    static int a_rac = 1;
    static int a_waiting = 1;

    static Queue<Integer> raclist = new LinkedList<>();
    static Queue<Integer> waitinglist = new LinkedList<>();
    static List<Integer> Booked = new ArrayList<>();
    static List<Integer> lowerberthPositions = new ArrayList<>(Arrays.asList(1, 2));
    static List<Integer> upperberthPositions = new ArrayList<>(Arrays.asList(1, 2));
    static List<Integer> middleberthPositions = new ArrayList<>(Arrays.asList(1, 2));
    static List<Integer> WaitingListPositions = new ArrayList<>(Arrays.asList(1, 2));
    static List<Integer> RACPositions = new ArrayList<>(Arrays.asList(1, 2));
    static Map<Integer, Passenger> passengers = new HashMap<>();

    public void BookTicket(Passenger p, int berthNo, String allocatedBerth) {
        p.number = berthNo;
        p.allotted = allocatedBerth;
        Booked.add(p.passengerid);
        passengers.put(p.passengerid, p);
        System.out.println("The Passenger Id is: " + p.passengerid);
        System.out.println("Booked Successfully ");
    }

    public void removePassenger(int id) {
        if (!Booked.contains(id)) {
            System.out.println("No Passenger Exists");
            return;
        }
        Passenger p = passengers.get(id);
        passengers.remove(Integer.valueOf(id));
        Booked.remove(Integer.valueOf(id));
        System.out.println("Removed the Passenger Successfully");
        int BookedPosition = p.number;
        if (p.allotted.equals("L")) {
            a_lowerberth++;
            lowerberthPositions.add(BookedPosition);
        } else if (p.allotted.equals("M")) {
            a_middleberth++;
            middleberthPositions.add(BookedPosition);
        } else if (p.allotted.equals("U")) {
            a_upperberth++;
            upperberthPositions.add(BookedPosition);
        } else if (p.allotted.equals("RAC")) {
            a_rac++;
            RACPositions.add(BookedPosition);
        }
        if (raclist.size() > 0) {
            Passenger rac_passenger = passengers.get(raclist.poll());
            int rac_position = rac_passenger.number;
            RACPositions.add(rac_position);
            raclist.remove(Integer.valueOf(rac_passenger.passengerid));
            a_rac++;

            if (waitinglist.size() > 0) {
                Passenger waitinglist_passenger = passengers.get(waitinglist.poll());
                int waitinglist_position = waitinglist_passenger.number;
                WaitingListPositions.add(waitinglist_position);
                waitinglist.remove(Integer.valueOf(waitinglist_passenger.passengerid));

                waitinglist_passenger.number = RACPositions.get(0);
                waitinglist_passenger.allotted = "RAC";
                raclist.add(waitinglist_passenger.id);
                a_waiting++;
                a_rac--;
            }
            Main.bookTicket(rac_passenger);
        }

    }

    public void printBookedTickets() {
        System.out.println("Printing the booked tickets ");
        if (passengers.size() == 0) {
            System.out.println("No Passengers Found");
            return;
        }
        for (Passenger val : passengers.values()) {
            System.out.println("Passenger Name: " + val.name);
            System.out.println("Passenger Age: " + val.age);
            System.out.println("Passenger Id: " + val.passengerid);
            System.out.println("Passenger Berth: " + val.allotted);
            System.out.println();
        }
    }

    public void BookRAC(Passenger p, int rac, String berth) {
        p.number = rac;
        p.allotted = berth;
        raclist.add(p.passengerid);
        RACPositions.remove(0);
        a_rac--;

        passengers.put(p.passengerid, p);
        System.out.println("The Passenger Id is: " + p.passengerid);
    }

    public void BookWaiting(Passenger p, int waiting, String berth) {
        p.number = waiting;
        p.allotted = berth;
        waitinglist.add(p.passengerid);
        WaitingListPositions.remove(0);
        a_waiting--;
        passengers.put(p.passengerid, p);
        System.out.println("The Passenger Id is:" + p.passengerid);
    }

    public void checkAvailableTickets() {
        System.out.println("The available Tickets are: \n");
        System.out.println("Available Lower Berth: " + a_lowerberth);
        System.out.println("Available Middle Berth: " + a_middleberth);
        System.out.println("Available Upper Berth: " + a_upperberth);
        System.out.println("Available RAC List: " + a_rac);
        System.out.println("Available Waiting List: " + a_waiting);
    }

}