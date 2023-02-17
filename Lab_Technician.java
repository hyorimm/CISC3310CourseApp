public class Lab_Technician extends Personnel implements Comparable<Lab_Technician> {
	private String name;

	public Lab_Technician(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int compareTo(Lab_Technician inputPersonnel){
		return (getName().compareToIgnoreCase(inputPersonnel.getName()));
	}

}
