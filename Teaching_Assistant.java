public class Teaching_Assistant extends Personnel implements Comparable<Teaching_Assistant> {
	private String name;

	public Teaching_Assistant(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public int compareTo(Teaching_Assistant inputPersonnel){
		return (getName().compareToIgnoreCase(inputPersonnel.getName()));
	}

}
