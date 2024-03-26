import java.util.Scanner;

public class Main {
    public static void bookTicket(Passenger p) {
        Ticket ticket = new Ticket();
        if (ticket.a_waiting == 0) {
            System.out.println("No Tickets are available");
            return;
        }
        if ((p.berthPreference.equals("L") && Ticket.a_lowerberth > 0) ||
                (p.berthPreference.equals("M") && Ticket.a_middleberth > 0) ||
                (p.berthPreference.equals("U") && Ticket.a_upperberth > 0)) {

            if (p.berthPreference.equals("L")) {
                System.out.println("Allotted with the Lower Berth.");
                ticket.BookTicket(p, (Ticket.lowerberthPositions.get(0)), "L");
                Ticket.lowerberthPositions.remove(0);
                Ticket.a_lowerberth--;
            } else if (p.berthPreference.equals("M")) {
                System.out.println("Allotted with the Middle Berth.");
                ticket.BookTicket(p, (Ticket.middleberthPositions.get(0)), "M");
                Ticket.middleberthPositions.remove(0);
                Ticket.a_middleberth--;
            } else if (p.berthPreference.equals("U")) {
                System.out.println("Allotted with the Upper Berth.");
                ticket.BookTicket(p, (Ticket.upperberthPositions.get(0)), "U");
                Ticket.upperberthPositions.remove(0);
                Ticket.a_upperberth--;
            }
        } else if (Ticket.a_lowerberth > 0) {
            System.out.println("Lower Berth is Available");
            System.out.println("Booked with Lower Berth");
            ticket.BookTicket(p, (Ticket.lowerberthPositions.get(0)), "L");
            Ticket.lowerberthPositions.remove(0);
            Ticket.a_lowerberth--;
        } else if (Ticket.a_middleberth > 0) {
            System.out.println("Middle Berth is Availabe");
            System.out.println("Allotted with Middle Berth");
            ticket.BookTicket(p, (Ticket.middleberthPositions.get(0)), "M");
            Ticket.middleberthPositions.remove(0);
            Ticket.a_middleberth--;
        } else if (Ticket.a_upperberth > 0) {
            System.out.println("Upper Berth is Available");
            System.out.println("Allotted with Upper Berth");
            ticket.BookTicket(p, (Ticket.upperberthPositions.get(0)), "U");
            Ticket.upperberthPositions.remove(0);
            Ticket.a_upperberth--;
        } else if (Ticket.a_rac > 0) {
            System.out.println("RAC Ticket is Availabe");
            System.out.println("Allocated in RAC List");
            ticket.BookRAC(p, (Ticket.RACPositions.get(0)), "RAC Ticket");

        } else if (Ticket.a_waiting > 0) {
            System.out.println("Waiting List is Available");
            System.out.println("Added to Waiting List");
            ticket.BookWaiting(p, (Ticket.WaitingListPositions.get(0)), "Waiting List");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("Enter the Choice: ");
            System.out.println(
                    " 1. Book Ticket \n 2. Check the available Tickets \n 3. Cancel the Ticket \n 4. Check the booked Tickets \n 5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    sc.nextLine();
                    System.out.println("Enter the name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter the age: ");
                    int age = sc.nextInt();
                    System.out.println("Enter the berth preference (L, M, H):");
                    String berth = sc.next();
                    Passenger p = new Passenger(name, age, berth);
                    bookTicket(p);
                    break;
                }
                case 2: {
                    Ticket ticket = new Ticket();
                    ticket.checkAvailableTickets();
                    break;
                }
                case 3: {
                    System.out.println("Enter the Passenger id to remove: ");
                    int id = sc.nextInt();
                    Ticket ticket = new Ticket();
                    ticket.removePassenger(id);
                    break;
                }

                case 4: {
                    Ticket ticket = new Ticket();
                    ticket.printBookedTickets();
                    break;
                }
                case 5: {
                    loop = false;
                    break;
                }
                default: {
                    loop = false;
                }

            }
        }
    }

}
