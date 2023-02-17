public class Student extends Person implements Comparable<Student> {

    private String name;
    private int id;
    private final int CREDIT_LIMIT = 18;
    private int currentCredits;

    public Student() {

    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Student(String name, int id, int currentCredits) {
        this.name = name;
        this.id = id;
        this.currentCredits = currentCredits;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCREDIT_LIMIT() {
        return CREDIT_LIMIT;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public void addCredits(int creditHours) {
        this.currentCredits += creditHours;
    }

    public int compareTo(Student inputStudent){
      return (getName().compareToIgnoreCase(inputStudent.getName()));
    }

}
