import java.util.*;
public class ElevatorCode {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Elevator A = new Elevator();
		System.out.println("The elevator is at floor " + A.getFloor());
		boolean activity = true;
		boolean firstPush = true;
		boolean someoneEntered = false;
		while (activity) {
			if (!someoneEntered) {
				System.out.println("What floor is the elevator being called from?");
				int callingFloor = input.nextInt();
				A.setFloor(callingFloor);
			}
			if (firstPush) {
				System.out.println("Which direction was first pushed? Up/Down? (enter true/false)");
				boolean direction = input.nextBoolean();
				A.setDirection(direction);
			}
			
			System.out.println("How many people are entering the elevator? (Max: " + Elevator.getPersonCapacity() + ")");
			int numPeople = input.nextInt();
			input.nextLine();
			for (int i = 0; i < numPeople; i++) {
				System.out.println("Enter name of person " + (i + 1));
				String name = input.nextLine();
				System.out.println("Enter their Weight");
				int weight = input.nextInt();
				input.nextLine();
				if (firstPush) {
					System.out.println("Enter the floor this person wants to go to: ");
					int desiredFloor = input.nextInt();
					input.nextLine();
					A.addPerson(new Person(weight, name, desiredFloor));
					if (A.getPeople().contains(new Person(weight, name, desiredFloor))) {
						A.addFloor(desiredFloor);
					}
				}else {
					System.out.println("Enter the floor this person wants to go to: ");
					int desiredFloor = input.nextInt();
					input.nextLine();
					if (A.getDirection() && desiredFloor > A.getFloor()) {
						A.setDirection(false);
					}else if (!A.getDirection() && desiredFloor < A.getFloor()){
						A.setDirection(true);
					}
					A.addPerson(new Person(weight, name, desiredFloor));
					if (A.getPeople().contains(new Person(weight, name, desiredFloor))) {
						A.addFloor(desiredFloor);
					}
				}
			}
			if (A.getDirection()) {
				elevatorSortUp(A);
			}
			else {
				elevatorSortDown(A);
			}
			for (int i = 0; i < A.getFloorSequence().size();) {
				for (int j = 0; j < A.getPeople().size();) {
					if (A.getPeople().get(j).getAssignedFloor() == A.getFloorSequence().get(i)) {
						A.removePerson(A.getPeople().get(j));
					}else {
						j++;
					}
				}
				A.setFloor(A.getFloorSequence().get(i));
				A.getFloorSequence().remove(A.getFloorSequence().get(i));
				System.out.println("The elevator is now at floor " + A.getFloor() + "\n" 
						+ "Is anyone else entering the elevator? Y/N");
				char response = input.next().charAt(0);
				while (response != 'Y' && response != 'N') {
					System.out.println("Invalid input. Try again. Is anyone else entering the elevator? Y/N");
					response = input.next().charAt(0);
				}
				if (response == 'Y') {
					input.nextLine();
					firstPush = false;
					someoneEntered = true;
					activity = true;
					break;
				}
				else if (response == 'N') {
					input.nextLine();
					activity = false;
				}
			}
		}
		input.close();
	}
	public static void elevatorSortUp(Elevator x) {
		ArrayList<Integer> floorList = x.getFloorSequence();
		for (int i = 0; i < floorList.size(); i++) {
			for (int j = 0; j < floorList.size() - 1 - i; j++) {
				if (floorList.get(j) > floorList.get(j + 1)) {
					Integer temp = floorList.get(j);
					floorList.set(j, floorList.get(j + 1));
					floorList.set(j + 1, temp);
				}
			}
		}
		ArrayList<Integer> underOriginFloor = new ArrayList<>();
		for (int i = 0; i < floorList.size();) {
			if (floorList.get(i) < x.getFloor()) {
				underOriginFloor.add(floorList.get(i));
				floorList.remove(floorList.get(i));
			}else {
				break;
			}
		}
		for (int i = 0; i < underOriginFloor.size(); i++) {
			for (int j = 0; j < underOriginFloor.size() - 1 - i; j++) {
				if (underOriginFloor.get(j) < underOriginFloor.get(j + 1)) {
					Integer temp = underOriginFloor.get(j);
					underOriginFloor.set(j, underOriginFloor.get(j + 1));
					underOriginFloor.set(j + 1, temp);
				}
			}
		}
		for (int i = 0; i < underOriginFloor.size(); i++) {
			floorList.add(underOriginFloor.get(i));
		}
	}
	public static void elevatorSortDown(Elevator x) {
		ArrayList<Integer> floorList = x.getFloorSequence();
		for (int i = 0; i < floorList.size(); i++) {
			for (int j = 0; j < floorList.size() - 1 - i; j++) {
				if (floorList.get(j) < floorList.get(j + 1)) {
					Integer temp = floorList.get(j);
					floorList.set(j, floorList.get(j + 1));
					floorList.set(j + 1, temp);
				}
			}
		}
		ArrayList<Integer> aboveOriginFloor = new ArrayList<>();
		for (int i = 0; i < floorList.size();) {
			if (floorList.get(i) > x.getFloor()) {
				aboveOriginFloor.add(floorList.get(i));
				floorList.remove(floorList.get(i));
			}else {
				break;
			}
		}
		for (int i = 0; i < aboveOriginFloor.size(); i++) {
			for (int j = 0; j < aboveOriginFloor.size() - 1 - i; j++) {
				if (aboveOriginFloor.get(j) > aboveOriginFloor.get(j + 1)) {
					Integer temp = aboveOriginFloor.get(j);
					aboveOriginFloor.set(j, aboveOriginFloor.get(j + 1));
					aboveOriginFloor.set(j + 1, temp);
				}
			}
		}
		for (int i = 0; i < aboveOriginFloor.size(); i++) {
			floorList.add(aboveOriginFloor.get(i));
		}
	}
}
class Person {
	private int weight;
	private String name;
	private int assignedFloor;
	Person(){
		this(100, "NoName", 0);
	}
	Person(int weight, String name, int assignedFloor){
		this.weight = weight;
		this.name = name;
		this.assignedFloor = assignedFloor;
	}
	public int getWeight() {
		return weight;
	}
	public String getName() {
		return name;
	}
	public int getAssignedFloor() {
		return assignedFloor;
	}
	public void setAssignedFloor(int assignedFloor) {
		this.assignedFloor = assignedFloor;
	}
	public boolean equals(Object obj) {
	    if (obj instanceof Person) {
	        Person x = (Person) obj;
	        return x.weight == this.weight && x.name.equals(this.name);
	    }
	    return false;
	}
	public String toString() {
		return name + " Weight: " + weight;
	}
}
class Elevator {
	private final static int personCapacity = 10;
	private final static int weightThreshold = 2000;
	private int totalWeight;
	private int floor;
	private boolean direction;
	private ArrayList<Integer> floorSequence = new ArrayList<>();
	// for elevator: if instr is up and a higher floor is given, any floor lower than the current floor is ignored until the highest floor inputted is reached. Then the instr is down.
	private ArrayList<Person> people = new ArrayList<>();
	
	Elevator() {
		this.totalWeight = 0;
		this.floor = 0;
	}
	Elevator(ArrayList<Person> people){
		for (int i = 0; i < people.size(); i++) {
			this.people.add(people.get(i));
		}
		for (Person x : this.people) {
			this.totalWeight += x.getWeight();
		}
		this.floor = 0;
	}
	public int getTotalWeight() {
		return totalWeight;
	}
	public ArrayList<Person> getPeople() {
		return people;
	}
	public int getFloor() {
		return floor;
	}
	public ArrayList<Integer> getFloorSequence(){
		return floorSequence;
	}
	public static int getPersonCapacity() {
		return personCapacity;
	}
	public static int getWeightThreshold() {
		return weightThreshold;
	}
	public boolean getDirection() {
		return direction;
	}
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public void addFloor(int floor) {
		floorSequence.add(floor);
	}
	public void addPerson(Person p) {
		if (people.size() < personCapacity) {
			if (p.getWeight() + totalWeight <= weightThreshold) {
				people.add(p);
				totalWeight += p.getWeight();
			}else {
				System.out.println("Adding " + p.getName() + " to the elevator will cause it to crash.");
			}
		}else {
			System.out.println("The elevator is full.");
		}
	}
	public void removePerson(Person p) {
		if (people.size() > 0) {
			if (people.indexOf(p) >= 0) {
				people.remove(p);
				totalWeight -= p.getWeight();
			}
		}else {
			System.out.println("The elevator is already empty");
		}
	}
	
}
