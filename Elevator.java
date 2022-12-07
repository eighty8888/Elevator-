import java.util.*;
public class Elevator {
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