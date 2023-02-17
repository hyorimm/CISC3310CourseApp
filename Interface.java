// Classify the occupations of each person, such as student, Lab attendant, and Teaching Assistant.
interface intership {
  // String person = "a person";

  void becomeStudentTutor();
  void becomeLabAttendant();
  void becomeTeachingAssistant();
}

public class Interface implements intership{

  // public static void main(String[] args){
  //   System.out.println(person);
  //
  //   Interface ex = new Interface();
  //   ex.becomeStudentTutor();
  //   ex.becomeLabAttendant();
  //   ex.becomeTeachingAssistant();
  // }

  public void becomeStudentTutor(){
    System.out.println("It is the proven as a student tutor.");
  }

  public void becomeLabAttendant(){
    System.out.println("It is the proven as a lab attendant.");
  }

  public void becomeTeachingAssistant(){
    System.out.println("It is the proven as a teaching assistant.");
  }
}
