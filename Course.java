import java.util.Collections;
import java.util.ArrayList;

public class Course implements Comparable<Course> {
    private String className;
    private int creditHours;
    private Student[] studentsInCourse;
    private int studentCapacity;
    private int studentEnrollmentCounter;
    private Instructor[] instructorsInCourse;
    private int instructorCapacity;
    private int instructorEnrollmentCounter;
    private Teaching_Assistant[] teachingAssistantsInCourse;
    private int teachingAssistantCapacity;
    private int teachingAssistantEnrollmentCounter;
    private Lab_Technician[] labTechniciansInCourse;
    private int labTechnicianCapacity;
    private int labTechnicianEnrollmentCounter;
    private Staff_Member[] staffMembersInCourse;
    private int staffMemberCapacity;
    private int staffMemberEnrollmentCounter;

    public Course() {
        this.className = "default";
        this.creditHours = 0;
        studentEnrollmentCounter = 0;
        instructorEnrollmentCounter = 0;
        teachingAssistantEnrollmentCounter = 0;
        labTechnicianEnrollmentCounter = 0;
        staffMemberEnrollmentCounter = 0;
        studentCapacity = 30;
        instructorCapacity = 3;
        teachingAssistantCapacity = 3;
        labTechnicianCapacity = 3;
        staffMemberCapacity = 3;
        studentsInCourse = new Student[studentCapacity];
        instructorsInCourse = new Instructor[instructorCapacity];
        teachingAssistantsInCourse = new Teaching_Assistant[teachingAssistantCapacity];
        labTechniciansInCourse = new Lab_Technician[labTechnicianCapacity];
        staffMembersInCourse = new Staff_Member[staffMemberCapacity];
    }


    public Course(String className, int creditHours) {
        this.className = className;
        this.creditHours = creditHours;
        studentEnrollmentCounter = 0;
        instructorEnrollmentCounter = 0;
        teachingAssistantEnrollmentCounter = 0;
        labTechnicianEnrollmentCounter = 0;
        staffMemberEnrollmentCounter = 0;
        studentCapacity = 30;
        instructorCapacity = 3;
        teachingAssistantCapacity = 3;
        labTechnicianCapacity = 3;
        staffMemberCapacity = 3;
        studentsInCourse = new Student[studentCapacity];
        instructorsInCourse = new Instructor[instructorCapacity];
        teachingAssistantsInCourse = new Teaching_Assistant[teachingAssistantCapacity];
        labTechniciansInCourse = new Lab_Technician[labTechnicianCapacity];
        staffMembersInCourse = new Staff_Member[staffMemberCapacity];
    }

    public Course(String className,
                  int creditHours,
                  Student[] studentsInCourse,
                  int studentCapacity,
                  int studentEnrollmentCounter,
                  Instructor[] instructorsInCourse,
                  int instructorCapacity,
                  int instructorEnrollmentCounter,
                  Teaching_Assistant[] teachingAssistantsInCourse,
                  int teachingAssistantCapacity,
                  int teachingAssistantEnrollmentCounter,
                  Lab_Technician[] labTechniciansInCourse,
                  int labTechnicianCapacity,
                  int labTechnicianEnrollmentCounter,
                  Staff_Member[] staffMembersInCourse,
                  int staffMemberCapacity,
                  int staffMemberEnrollmentCounter
    ) {
        this.className = className;
        this.creditHours = creditHours;
        this.studentEnrollmentCounter = studentEnrollmentCounter;
        this.instructorEnrollmentCounter = instructorEnrollmentCounter;
        this.teachingAssistantEnrollmentCounter = teachingAssistantEnrollmentCounter;
        this.labTechnicianEnrollmentCounter = labTechnicianEnrollmentCounter;
        this.staffMemberEnrollmentCounter = staffMemberEnrollmentCounter;
        this.studentCapacity = studentCapacity;
        this.instructorCapacity = instructorCapacity;
        this.teachingAssistantCapacity = teachingAssistantCapacity;
        this.labTechnicianCapacity = labTechnicianCapacity;
        this.staffMemberCapacity = staffMemberCapacity;
        this.studentsInCourse = studentsInCourse;
        this.instructorsInCourse = instructorsInCourse;
        this.teachingAssistantsInCourse = teachingAssistantsInCourse;
        this.labTechniciansInCourse = labTechniciansInCourse;
        this.staffMembersInCourse = staffMembersInCourse;

    }

    public String getClassName() {
        return className;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public int getStudentEnrollmentCounter() {
        return studentEnrollmentCounter;
    }

    public int getInstructorEnrollmentCounter() {
        return instructorEnrollmentCounter;
    }

    public int getTeachingAssistantEnrollmentCounter() {
        return teachingAssistantEnrollmentCounter;
    }

    public int getLabTechnicianEnrollmentCounter() {
        return labTechnicianEnrollmentCounter;
    }

    public int getStaffMemberEnrollmentCounter() {
        return staffMemberEnrollmentCounter;
    }

    public int getStudentCapacity() {
        return studentCapacity;
    }

    public int getInstructorCapacity() {
        return instructorCapacity;
    }

    public int getTeachingAssistantCapacity() {
        return teachingAssistantCapacity;
    }

    public int getLabTechnicianCapacity() {
        return labTechnicianCapacity;
    }

    public int getStaffMemberCapacity() {
        return staffMemberCapacity;
    }

    public Student[] getStudentsInCourse() {
        return studentsInCourse;
    }

    public Instructor[] getInstructorsInCourse() {
        return instructorsInCourse;
    }

    public Teaching_Assistant[] getTeachingAssistantsInCourse() {
        return teachingAssistantsInCourse;
    }

    public Lab_Technician[] getLabTechniciansInCourse() {
        return labTechniciansInCourse;
    }

    public Staff_Member[] getStaffMembersInCourse() {
        return staffMembersInCourse;
    }


    public boolean hasStudentSpace() {
        if (studentEnrollmentCounter < studentCapacity) {
            return true;
        } else {
            return false;
        }
    }

    public void addStudentToCourse(Student student) {
        this.studentsInCourse[studentEnrollmentCounter] = student;
        studentEnrollmentCounter++;
    }

    public boolean hasInstructorSpace() {
        if (instructorEnrollmentCounter < instructorCapacity) {
            return true;
        } else {
            return false;
        }
    }

    public void addInstructorToCourse(Instructor instructor) {
        this.instructorsInCourse[instructorEnrollmentCounter] = instructor;
        instructorEnrollmentCounter++;
    }

    public boolean hasTeachingAssistantSpace() {
        if (teachingAssistantEnrollmentCounter < teachingAssistantCapacity) {
            return true;
        } else {
            return false;
        }
    }

    public void addTeachingAssistantToCourse(Teaching_Assistant teachingAssistant) {
        this.teachingAssistantsInCourse[teachingAssistantEnrollmentCounter] = teachingAssistant;
        teachingAssistantEnrollmentCounter++;
    }

    public boolean hasLabTechnicianSpace() {
        if (labTechnicianEnrollmentCounter < labTechnicianCapacity) {
            return true;
        } else {
            return false;
        }
    }

    public void addLabTechnicianToCourse(Lab_Technician labTechnician) {
        this.labTechniciansInCourse[labTechnicianEnrollmentCounter] = labTechnician;
        labTechnicianEnrollmentCounter++;
    }

    public boolean hasStaffMemberSpace() {
        if (staffMemberEnrollmentCounter < staffMemberCapacity) {
            return true;
        } else {
            return false;
        }
    }

    public void addStaffMemberToCourse(Staff_Member staffMember) {
        this.staffMembersInCourse[staffMemberEnrollmentCounter] = staffMember;
        staffMemberEnrollmentCounter++;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setStudentsInCourse(Student[] studentsInCourse) {
        this.studentsInCourse = studentsInCourse;
    }

    public void setStudentCapacity(int studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public void setStudentEnrollmentCounter(int studentEnrollmentCounter) {
        this.studentEnrollmentCounter = studentEnrollmentCounter;
    }

    public void setInstructorsInCourse(Instructor[] instructorsInCourse) {
        this.instructorsInCourse = instructorsInCourse;
    }

    public void setInstructorCapacity(int instructorCapacity) {
        this.instructorCapacity = instructorCapacity;
    }

    public void setInstructorEnrollmentCounter(int instructorEnrollmentCounter) {
        this.instructorEnrollmentCounter = instructorEnrollmentCounter;
    }

    public void setTeachingAssistantsInCourse(Teaching_Assistant[] teachingAssistantsInCourse) {
        this.teachingAssistantsInCourse = teachingAssistantsInCourse;
    }

    public void setTeachingAssistantCapacity(int teachingAssistantCapacity) {
        this.teachingAssistantCapacity = teachingAssistantCapacity;
    }

    public void setTeachingAssistantEnrollmentCounter(int teachingAssistantEnrollmentCounter) {
        this.teachingAssistantEnrollmentCounter = teachingAssistantEnrollmentCounter;
    }

    public void setLabTechniciansInCourse(Lab_Technician[] labTechniciansInCourse) {
        this.labTechniciansInCourse = labTechniciansInCourse;
    }

    public void setLabTechnicianCapacity(int labTechnicianCapacity) {
        this.labTechnicianCapacity = labTechnicianCapacity;
    }

    public void setLabTechnicianEnrollmentCounter(int labTechnicianEnrollmentCounter) {
        this.labTechnicianEnrollmentCounter = labTechnicianEnrollmentCounter;
    }

    public void setStaffMembersInCourse(Staff_Member[] staffMembersInCourse) {
        this.staffMembersInCourse = staffMembersInCourse;
    }

    public void setStaffMemberCapacity(int staffMemberCapacity) {
        this.staffMemberCapacity = staffMemberCapacity;
    }

    public void setStaffMemberEnrollmentCounter(int staffMemberEnrollmentCounter) {
        this.staffMemberEnrollmentCounter = staffMemberEnrollmentCounter;
    }

    public int compareTo(Course inputCourse){
      return (getClassName().compareToIgnoreCase(inputCourse.getClassName()));
    }


    public void sortPersonArrays(Student[] studentsInCourse, Instructor[] instructorsInCourse, Teaching_Assistant[] teachingAssistantsInCourse, Lab_Technician[] labTechniciansInCourse, Staff_Member[] staffMembersInCourse){
      sortStudentArray(studentsInCourse);
      sortInstructorArray(instructorsInCourse);
      sortTeachingAssistantArray(teachingAssistantsInCourse);
      sortLabTechnicianArray(labTechniciansInCourse);
      sortStaffMemberArray(staffMembersInCourse);
    }

    public void listStudentsInCourse(Student[] studentsInCourse){
      if (studentsInCourse.length > 0){
        for (int i = 0; i < studentsInCourse.length; i++){
          if (studentsInCourse[i] != null){
            System.out.println(studentsInCourse[i].getName());
          }
        }
      }
    }

    public void sortStudentArray(Student[] studentsInCourse){

      if (studentsInCourse.length > 0){
        if (studentsInCourse[0] != null){

          System.out.println("\nBefore sort:");
          listStudentsInCourse(studentsInCourse);

          ArrayList<Student> tempStudentArrayList = new ArrayList<Student>();
          for (int i = 0; i < studentsInCourse.length; i++){
            if (studentsInCourse[i] != null){
              tempStudentArrayList.add(studentsInCourse[i]);
            }
          }
          Collections.sort(tempStudentArrayList);

          //sets the original array equal to the new sorted arrayList
          for (int i = 0; i < tempStudentArrayList.size(); i++){
            studentsInCourse[i] = tempStudentArrayList.get(i);
          }

          System.out.println("\nStudent list sorted.");
          listStudentsInCourse(studentsInCourse);
        }
      }
    }


    public void listInstructorsInCourse(Instructor[] personnelInCourse){
      if (personnelInCourse.length > 0){
        for (int i = 0; i < personnelInCourse.length; i++){
          if (personnelInCourse[i] != null){
            System.out.println(personnelInCourse[i].getName());
          }
        }
      }
    }

    public void sortInstructorArray(Instructor[] instructorsInCourse){

      if (instructorsInCourse.length > 0){
        if (instructorsInCourse[0] != null){

          //System.out.println("\nBefore sort:");
          //listInstructorsInCourse(instructorsInCourse);

          ArrayList<Instructor> tempInstructorArrayList = new ArrayList<Instructor>();

          for (int i = 0; i < instructorsInCourse.length; i++){
            if (instructorsInCourse[i] != null){
              tempInstructorArrayList.add(instructorsInCourse[i]);
            }
          }
          Collections.sort(tempInstructorArrayList);

          for (int i = 0; i < tempInstructorArrayList.size(); i++){
            instructorsInCourse[i] = tempInstructorArrayList.get(i);
          }

          //System.out.println("\nInstructor list sorted.");
          //listInstructorsInCourse(instructorsInCourse);
        }
      }
    }

    public Instructor binarySearchInstructors(Instructor[] instructorsInCourse, Instructor searchInstructor){
      sortInstructorArray(instructorsInCourse);

      if (instructorsInCourse.length > 0){
        if (instructorsInCourse[0] != null){

          ArrayList<Instructor> tempInstructorArrayList = new ArrayList<Instructor>();

          for (int i = 0; i < instructorsInCourse.length; i++){
            if (instructorsInCourse[i] != null){
              tempInstructorArrayList.add(instructorsInCourse[i]);
            }
          }

          int instructorSearchIndex = Collections.binarySearch(tempInstructorArrayList, searchInstructor);

          if (instructorSearchIndex < 0){
            System.out.println("Instructor not found");
            return null;
          } else {
            return tempInstructorArrayList.get(instructorSearchIndex);
          }
        }
      }
      return null;
    }



    public void listTeachingAssistantsInCourse(Teaching_Assistant[] personnelInCourse){
      if (personnelInCourse.length > 0){
        for (int i = 0; i < personnelInCourse.length; i++){
          if (personnelInCourse[i] != null){
            System.out.println(personnelInCourse[i].getName());
          }
        }
      }
    }

    public void sortTeachingAssistantArray(Teaching_Assistant[] teachingAssistantsInCourse){

      if (teachingAssistantsInCourse.length > 0){
        if (teachingAssistantsInCourse[0] != null){

          //System.out.println("\nBefore sort:");
          //listTeachingAssistantsInCourse(teachingAssistantsInCourse);

          ArrayList<Teaching_Assistant> tempTeachingAssistantArrayList = new ArrayList<Teaching_Assistant>();

          for (int i = 0; i < teachingAssistantsInCourse.length; i++){
            if (teachingAssistantsInCourse[i] != null){
              tempTeachingAssistantArrayList.add(teachingAssistantsInCourse[i]);
            }
          }
          Collections.sort(tempTeachingAssistantArrayList);

          for (int i = 0; i < tempTeachingAssistantArrayList.size(); i++){
            teachingAssistantsInCourse[i] = tempTeachingAssistantArrayList.get(i);
          }

          //System.out.println("\nTeaching Assistant list sorted.");
          //listTeachingAssistantsInCourse(teachingAssistantsInCourse);
        }
      }
    }

    public Teaching_Assistant binarySearchTeachingAssistants(Teaching_Assistant[] personnelInCourse, Teaching_Assistant searchPersonnel){
      sortTeachingAssistantArray(personnelInCourse);

      if (personnelInCourse.length > 0){
        if (personnelInCourse[0] != null){

          ArrayList<Teaching_Assistant> tempPersonnelArrayList = new ArrayList<Teaching_Assistant>();

          for (int i = 0; i < personnelInCourse.length; i++){
            if (personnelInCourse[i] != null){
              tempPersonnelArrayList.add(personnelInCourse[i]);
            }
          }

          int personnelSearchIndex = Collections.binarySearch(tempPersonnelArrayList, searchPersonnel);

          if (personnelSearchIndex < 0){
            System.out.println("Teaching Assistant not found");
            return null;
          } else {
            return tempPersonnelArrayList.get(personnelSearchIndex);
          }
        }
      }
      return null;
    }



    public void listLabTechniciansInCourse(Lab_Technician[] personnelInCourse){
      if (personnelInCourse.length > 0){
        for (int i = 0; i < personnelInCourse.length; i++){
          if (personnelInCourse[i] != null){
            System.out.println(personnelInCourse[i].getName());
          }
        }
      }
    }

    public void sortLabTechnicianArray(Lab_Technician[] labTechniciansInCourse){

      if (labTechniciansInCourse.length > 0){
        if (labTechniciansInCourse[0] != null){

          //System.out.println("\nBefore sort:");
          //listLabTechniciansInCourse(labTechniciansInCourse);

          ArrayList<Lab_Technician> tempLabTechnicianArrayList = new ArrayList<Lab_Technician>();

          for (int i = 0; i < labTechniciansInCourse.length; i++){
            if (labTechniciansInCourse[i] != null){
              tempLabTechnicianArrayList.add(labTechniciansInCourse[i]);
            }
          }
          Collections.sort(tempLabTechnicianArrayList);

          for (int i = 0; i < tempLabTechnicianArrayList.size(); i++){
            labTechniciansInCourse[i] = tempLabTechnicianArrayList.get(i);
          }

          //System.out.println("\nLab Technician list sorted.");
          //listLabTechniciansInCourse(labTechniciansInCourse);
        }
      }
    }

    public Lab_Technician binarySearchLabTechnicians(Lab_Technician[] personnelInCourse, Lab_Technician searchPersonnel){
      sortLabTechnicianArray(personnelInCourse);

      if (personnelInCourse.length > 0){
        if (personnelInCourse[0] != null){

          ArrayList<Lab_Technician> tempPersonnelArrayList = new ArrayList<Lab_Technician>();

          for (int i = 0; i < personnelInCourse.length; i++){
            if (personnelInCourse[i] != null){
              tempPersonnelArrayList.add(personnelInCourse[i]);
            }
          }

          int personnelSearchIndex = Collections.binarySearch(tempPersonnelArrayList, searchPersonnel);

          if (personnelSearchIndex < 0){
            System.out.println("Lab Technician not found");
            return null;
          } else {
            return tempPersonnelArrayList.get(personnelSearchIndex);
          }
        }
      }
      return null;
    }



    public void listStaffMembersInCourse(Staff_Member[] personnelInCourse){
      if (personnelInCourse.length > 0){
        for (int i = 0; i < personnelInCourse.length; i++){
          if (personnelInCourse[i] != null){
            System.out.println(personnelInCourse[i].getName());
          }
        }
      }
    }

    public void sortStaffMemberArray(Staff_Member[] staffMembersInCourse){

      if (staffMembersInCourse.length > 0){
        if (staffMembersInCourse[0] != null){
          //System.out.println("\nBefore sort:");
          //listStaffMembersInCourse(staffMembersInCourse);


          ArrayList<Staff_Member> tempStaffMemberArrayList = new ArrayList<Staff_Member>();

          for (int i = 0; i < staffMembersInCourse.length; i++){
            if (staffMembersInCourse[i] != null){
              tempStaffMemberArrayList.add(staffMembersInCourse[i]);
            }
          }
          Collections.sort(tempStaffMemberArrayList);

          for (int i = 0; i < tempStaffMemberArrayList.size(); i++){
            staffMembersInCourse[i] = tempStaffMemberArrayList.get(i);
          }

          //System.out.println("\nStaff member list sorted.");
          //listStaffMembersInCourse(staffMembersInCourse);
        }
      }
    }

    public Staff_Member binarySearchStaffMembers(Staff_Member[] personnelInCourse, Staff_Member searchPersonnel){
      sortStaffMemberArray(personnelInCourse);

      if (personnelInCourse.length > 0){
        if (personnelInCourse[0] != null){

          ArrayList<Staff_Member> tempPersonnelArrayList = new ArrayList<Staff_Member>();

          for (int i = 0; i < personnelInCourse.length; i++){
            if (personnelInCourse[i] != null){
              tempPersonnelArrayList.add(personnelInCourse[i]);
            }
          }

          int personnelSearchIndex = Collections.binarySearch(tempPersonnelArrayList, searchPersonnel);

          if (personnelSearchIndex < 0){
            System.out.println("Staff Member not found");
            return null;
          } else {
            return tempPersonnelArrayList.get(personnelSearchIndex);
          }
        }
      }
      return null;
    }

}
