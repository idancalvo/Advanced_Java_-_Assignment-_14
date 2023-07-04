/**A person with name, id and year of birth
*
*@author Idan Calvo
*@version 1.0
*/
public class Person implements Comparable<Person> {

//#Fields	
	private String name;
	private int id;
	private int yearBirth;

//#Constructor
	public Person(String name, int id, int yearBirth) {
		this.name = name;
		this.id = id;
		this.yearBirth = yearBirth;
	}

//#Methods
	@Override
	public int compareTo(Person other) {
		if (other == null) {
			return 1;
		} else {
			return other.getYearBirth() - this.yearBirth;
		}
	}
	
	@Override
	public String toString() {
		return "(name:"+this.name +",id:"+this.id + ",yearBirth:"+this.yearBirth+")";
		
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

	private int getYearBirth() {
		return this.yearBirth;
	}

}
