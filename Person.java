public class Person {
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
