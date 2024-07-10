import java.util.Scanner;

public class App {
  public static void main(String[] args) throws Exception {
    int gumballs = 5;
    Machine machine = new Machine(gumballs);

    Scanner scanner = new Scanner(System.in);

    while (true) {

      if (machine.getState() == State.NO_QUARTER) {
        System.out.println("\nMighty Gumball, Inc.");
        System.out.println("Java-enabled Standing Gumball Model #2004");
        System.out.println("Inventory: " + machine.getGumballs() + " gumballs");
        System.out.println("Machine is waiting for the quarter\n");
      }

      int input = scanner.nextInt();

      switch (input) {
        case 1:
          machine.insertQuarter();
          break;
        case 2:
          machine.turnsCrank();
          break;
        case 3:
          machine.ejectQuarter();
          break;
        case 0:
          System.out.println("Machine is sold out");
          scanner.close();
          return;

        default:
          System.out.println("Machine is sold out");
          scanner.close();
          return;
      }
    }
  }
}

enum State {
  NO_QUARTER,
  HAS_QUARTER,
  GUMBALL_SOLD,
  OUT_OF_GUMBALLS
}

class Machine {

  private State machine_state = State.NO_QUARTER;
  private int gumballs;
  private boolean quarter;

  public Machine(int gumballs) {
    this.gumballs = gumballs;
    this.quarter = false;
  }

  public int getGumballs() {
    return this.gumballs;
  }

  public State getState() {
    return this.machine_state;
  }

  public void insertQuarter() {
    if (this.machine_state == State.NO_QUARTER) {
      System.out.println("You inserted a quarter");
      this.machine_state = State.HAS_QUARTER;
      this.quarter = true;
    } else if (this.machine_state == State.OUT_OF_GUMBALLS) {
      System.out.println("You can't insert a quarter, the machine is sold out");
    } else {
      System.out.println("You can't insert another quarter");
    }
  }

  public void ejectQuarter() {
    if (this.machine_state == State.HAS_QUARTER) {
      System.out.println("Quarter returned");
      this.machine_state = State.NO_QUARTER;
      this.quarter = false;
    } else if (this.machine_state == State.NO_QUARTER) {
      System.out.println("You haven't inserted a quarter");
    }
  }

  public void turnsCrank() {
    if (this.machine_state == State.HAS_QUARTER) {
      System.out.println("You turned...");

      this.machine_state = State.GUMBALL_SOLD;
      System.out.println("A gumball comes rolling out the slot");

      this.dispenseGumball();
    } else if (this.machine_state == State.NO_QUARTER) {
      System.out.println("You turned but there's no quarter");
    } else {
      System.out.println("You turned but there are no gumballs");
    }

  }

  public void dispenseGumball() {
    if (this.machine_state == State.GUMBALL_SOLD) {

      this.gumballs -= 1;

      if (this.gumballs > 0) {
        this.quarter = false;
        this.machine_state = State.NO_QUARTER;
      } else {
        this.machine_state = State.OUT_OF_GUMBALLS;
        System.out.println("Oops, out of gumballs!");
      }
    }
  }
}
