public class Instructor extends Personnel implements Comparable<Instructor> {

    private String name;

    public Instructor() {

    }

    public Instructor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Instructor inputPersonnel){
      return (getName().compareToIgnoreCase(inputPersonnel.getName()));
    }
}
