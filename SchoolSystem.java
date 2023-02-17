import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SchoolSystem {

    //private static final String PATH = "..\\proj3eduapp-team_4\\data_files\\"; //Windows version
    //I commented out the above code because my system requires /'s for files -- i don't get why Mac and Windows file structures have to be different like that
    private static final String PATH = "../data_files/"; //Mac version

    private static List<Course> courses = new ArrayList<>();
    private static List<Course> faceToFaceCourses = new ArrayList<>();
    private static List<Course> syncOnlineCourses = new ArrayList<>();
    private static List<Course> asyncOnlineCourses = new ArrayList<>();
    private static List<Course> lectureOnlyCourses = new ArrayList<>();
    private static List<Course> laboratoryOnlyCourses = new ArrayList<>();
    private static List<Course> hybridCourses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static List<Instructor> instructors = new ArrayList<>();
    private static List<Teaching_Assistant> teachingAssistants = new ArrayList<>();
    private static List<Lab_Technician> labTechnicians = new ArrayList<>();
    private static List<Staff_Member> staffMembers = new ArrayList<>();

    private static PrintWriter pw = null;

    public static void backup_all() throws IOException {
        backup(PATH, "student");
        backup(PATH, "instructor");
        backup(PATH, "teaching_assistant");
        backup(PATH, "lab_technician");
        backup(PATH, "staff_member");
        backup(PATH, "async_class_online");
        backup(PATH, "face_to_face_class");
        backup(PATH, "hybrid_class");
        backup(PATH, "laboratory_only_class");
        backup(PATH, "lecture_only_class");
        backup(PATH, "sync_class_online");
    }

    public static void backup(String path, String inputFilename) throws IOException {
        File txt = new File(path + inputFilename + ".txt");
        File bu1 = new File(path + inputFilename + ".bu.1");
        File bu2 = new File(path + inputFilename + ".bu.2");
        File bu3 = new File(path + inputFilename + ".bu.3");

        Scanner sc3 = null;

        if (txt.exists()) {
            sc3 = new Scanner(txt);
            File txt2 = new File(PATH + inputFilename + "2.txt");
            pw = new PrintWriter(txt2);
            while (sc3.hasNextLine()) {
                String curr = sc3.nextLine();
                pw.println(curr);
            }

            if (bu1.exists()) {
                if (bu2.exists()) {
                    if (bu3.exists()) {
                        bu3.delete();
                        pw.close();
                        if (bu2.renameTo(new File(PATH + inputFilename + ".bu.3"))) {
                            //System.out.println("renamed");
                        }
                        txt2.renameTo(new File(PATH + inputFilename + ".bu.1"));
                        //txt2.delete();
                    } else {
                        pw.close();
                        if(bu2.renameTo(new File(PATH + inputFilename + ".bu.3"))){
                            //System.out.println("renamed");
                        }
                    }
                    //bu2.delete();
                    pw.close();
                    if (bu1.renameTo(new File(PATH + inputFilename + ".bu.2"))) {
                        //System.out.println("renamed");
                    }
                    txt2.renameTo(new File(PATH + inputFilename + ".bu.1"));
                    //txt2.delete();
                } else {
                    pw.close();
                    if(bu1.renameTo(new File(PATH + inputFilename + ".bu.2"))){
                        //System.out.println("renamed");
                    }
                }
                //txt2.delete();
                pw.close();
                if (txt2.renameTo(new File(PATH + inputFilename + ".bu.1"))) {
                    //System.out.println("renamed");
                }
                //txt2.delete();
            } else {
                pw.close();
                if (txt2.renameTo(new File(PATH + inputFilename + ".bu.1"))) {
                    //System.out.println("renamed");
                }
                txt2.delete();
            }
        }
        pw.close();
        sc3.close();
    }

    public static void read_all() {
        readData("student");
        readData("instructor");
        readData("teaching_assistant");
        readData("lab_technician");
        readData("staff_member");
        readData_course("async_class_online");
        readData_course("face_to_face_class");
        readData_course("hybrid_class");
        readData_course("laboratory_only_class");
        readData_course("lecture_only_class");
        readData_course("sync_class_online");
    }

    public static void readData(String inputFilename) {
        File inputFile = new File(PATH + inputFilename + ".txt");
        Scanner sc = null;
        String[] tokens = null;

        try {
            sc = new Scanner(inputFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                tokens = line.split(",");

                switch (inputFilename) {
                    case "student":
                        students.add(new Student(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                        break;
                    case "instructor":
                        instructors.add(new Instructor(tokens[0]));
                        break;
                    case "teaching_assistant":
                        teachingAssistants.add(new Teaching_Assistant(tokens[0]));
                        break;
                    case "lab_technician":
                        labTechnicians.add(new Lab_Technician(tokens[0]));
                        break;
                    case "staff_member":
                        staffMembers.add(new Staff_Member(tokens[0]));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + inputFile + " was not found.");
        } finally {
            //System.out.println("Closing file");
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void readData_course(String inputFilename) {
        //System.out.println(inputFilename);
        File inputFile = new File(PATH + inputFilename + ".txt");
        Scanner sc = null;
        String[] tokens = null;

        try {
            sc = new Scanner(inputFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                tokens = line.split(",");
                //System.out.println(line);

                //__________________________________________________________________________________________________


                String[] student_ids = tokens[2].split(";");
                int student_length = student_ids.length;

                Student[] input_student_array = new Student[student_length];

                for (int i = 0; i < student_length; i++) {
                    for (int j = 0; j < students.size(); j++) {
                        if (student_ids[i].equals("NONE")) {
                            break;
                        }
                        //System.out.println(students.size());
                        //System.out.println(student_ids.length);

                        if (Integer.parseInt(student_ids[i]) == students.get(j).getId()) {
                            input_student_array[i] = students.get(j);
                        }
                    }
                }

                //__________________________________________________________________________________________________


                String[] instructor_name = tokens[5].split(";");
                int instructor_length = instructor_name.length;
                //System.out.println(Arrays.toString(instructor_name));

                Instructor[] input_instructor_array = new Instructor[instructor_length];

                for (int i = 0; i < instructor_length; i++) {
                    for (int j = 0; j < instructors.size(); j++) {
                        if (instructor_name[i].equals("NONE")) {
                            break;
                        }

                        //System.out.println(instructor_name[i]);
                        //System.out.println(Arrays.toString(input_instructor_array));
                        if (instructor_name[i].equals(instructors.get(j).getName())) {
                            input_instructor_array[i] = instructors.get(j);
                        }
                    }
                    //System.out.println(Arrays.toString(input_instructor_array));
                }


                //__________________________________________________________________________________________________

                String[] teaching_assistant_name = tokens[8].split(";");
                int teaching_assistant_length = teaching_assistant_name.length;

                Teaching_Assistant[] input_teaching_assistant_array = new Teaching_Assistant[teaching_assistant_length];

                for (int i = 0; i < instructor_length; i++) {
                    for (int j = 0; j < teachingAssistants.size(); j++) {
                        if (teaching_assistant_name[i].equals("NONE")) {
                            break;
                        }
                        if (teaching_assistant_name[i].equals(teachingAssistants.get(j).getName())) {
                            input_teaching_assistant_array[i] = teachingAssistants.get(j);
                        }
                    }
                }

                //__________________________________________________________________________________________________

                String[] lab_technician_name = tokens[11].split(";");
                int lab_technician_length = lab_technician_name.length;

                Lab_Technician[] input_lab_technician_array = new Lab_Technician[lab_technician_length];

                for (int i = 0; i < lab_technician_length; i++) {
                    for (int j = 0; j < labTechnicians.size(); j++) {
                        if (lab_technician_name[i].equals("NONE")) {
                            break;
                        }
                        if (lab_technician_name[i].equals(labTechnicians.get(j).getName())) {
                            input_lab_technician_array[i] = labTechnicians.get(j);
                        }
                    }
                }

                //__________________________________________________________________________________________________

                String[] staff_member_name = tokens[14].split(";");
                int staff_member_length = staff_member_name.length;

                Staff_Member[] input_staff_member_array = new Staff_Member[staff_member_length];

                for (int i = 0; i < staff_member_length; i++) {
                    for (int j = 0; j < staffMembers.size(); j++) {
                        if (staff_member_name[i].equals("NONE")) {
                            break;
                        }
                        if (staff_member_name[i].equals(staffMembers.get(j).getName())) {
                            input_staff_member_array[i] = staffMembers.get(j);
                        }
                    }
                }

                switch (inputFilename) {
                    case "async_class_online":
                        asyncOnlineCourses.add(new Async_Class_Online(
                                tokens[0], Integer.parseInt(tokens[1]), input_student_array, Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                                input_instructor_array, Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), input_teaching_assistant_array,
                                Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), input_lab_technician_array,
                                Integer.parseInt(tokens[12]), Integer.parseInt(tokens[13]), input_staff_member_array, Integer.parseInt(tokens[15]),
                                Integer.parseInt(tokens[16]), tokens[17]
                        ));
                        break;
                    case "face_to_face_class":
                        faceToFaceCourses.add(new Face_to_Face_Class(
                                tokens[0], Integer.parseInt(tokens[1]), input_student_array, Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                                input_instructor_array, Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), input_teaching_assistant_array,
                                Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), input_lab_technician_array,
                                Integer.parseInt(tokens[12]), Integer.parseInt(tokens[13]), input_staff_member_array, Integer.parseInt(tokens[15]),
                                Integer.parseInt(tokens[16]), tokens[17], tokens[18]
                        ));
                        break;
                    case "hybrid_class":
                        hybridCourses.add(new Hybrid_Class(
                                tokens[0], Integer.parseInt(tokens[1]), input_student_array, Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                                input_instructor_array, Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), input_teaching_assistant_array,
                                Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), input_lab_technician_array,
                                Integer.parseInt(tokens[12]), Integer.parseInt(tokens[13]), input_staff_member_array, Integer.parseInt(tokens[15]),
                                Integer.parseInt(tokens[16]), tokens[17], tokens[18], tokens[19], tokens[20]
                        ));
                        break;
                    case "laboratory_only_class":
                        laboratoryOnlyCourses.add(new Laboratory_Only_Class(
                                tokens[0], Integer.parseInt(tokens[1]), input_student_array, Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                                input_instructor_array, Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), input_teaching_assistant_array,
                                Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), input_lab_technician_array,
                                Integer.parseInt(tokens[12]), Integer.parseInt(tokens[13]), input_staff_member_array, Integer.parseInt(tokens[15]),
                                Integer.parseInt(tokens[16]), tokens[17], tokens[18]
                        ));
                        break;
                    case "lecture_only_class":
                        lectureOnlyCourses.add(new Lecture_Only_Class(
                                tokens[0], Integer.parseInt(tokens[1]), input_student_array, Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                                input_instructor_array, Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), input_teaching_assistant_array,
                                Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), input_lab_technician_array,
                                Integer.parseInt(tokens[12]), Integer.parseInt(tokens[13]), input_staff_member_array, Integer.parseInt(tokens[15]),
                                Integer.parseInt(tokens[16]), tokens[17], tokens[18]
                        ));
                        break;
                    case "sync_class_online":
                        syncOnlineCourses.add(new Sync_Class_Online(
                                tokens[0], Integer.parseInt(tokens[1]), input_student_array, Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                                input_instructor_array, Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), input_teaching_assistant_array,
                                Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), input_lab_technician_array,
                                Integer.parseInt(tokens[12]), Integer.parseInt(tokens[13]), input_staff_member_array, Integer.parseInt(tokens[15]),
                                Integer.parseInt(tokens[16]), tokens[17]
                        ));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + inputFile + " was not found.");
        } finally {
            //System.out.println("Closing file");
            if (sc != null) {
                sc.close();
            }
        }
        add_all_courses(asyncOnlineCourses);
        add_all_courses(faceToFaceCourses);
        add_all_courses(hybridCourses);
        add_all_courses(laboratoryOnlyCourses);
        add_all_courses(lectureOnlyCourses);
        add_all_courses(syncOnlineCourses);

    }

    public static void add_all_courses(List<Course> list) {
        for (Course c : list) {
            courses.add(c);
        }
    }


    public static void sortListAlphabetically(List<Course> courses, List<Course> faceToFaceCourses, List<Course> syncOnlineCourses, List<Course> asyncOnlineCourses, List<Course> lectureOnlyCourses, List<Course> laboratoryOnlyCourses, List<Course> hybridCourses, List<Student> students, List<Instructor> instructors, List<Teaching_Assistant> teachingAssistants, List<Lab_Technician> labTechnicians, List<Staff_Member> staffMembers){
      Scanner sc = new Scanner(System.in);
      int choice = 0;

      System.out.println("-------------------------------------------");
      System.out.println(" 1. Sort all course lists");
      System.out.println(" 2. Sort all person lists");
      System.out.println(" 3. Sort all lists");
      System.out.println(" 4. Sort people in all course lists");
      System.out.println(" 0. Go back");
      System.out.println("What would you like to do:");
      System.out.println("-------------------------------------------");

      choice = sc.nextInt();

      if (choice == 1 || choice == 3){
        System.out.println("\nCourses list");
        sortCourseListAlphabetically(courses);

        System.out.println("\nFace to Face courses list");
        sortCourseListAlphabetically(faceToFaceCourses);

        System.out.println("\nSynchronous courses list");
        sortCourseListAlphabetically(syncOnlineCourses);

        System.out.println("\nAsynchronous courses list");
        sortCourseListAlphabetically(asyncOnlineCourses);

        System.out.println("\nLecture-Only courses");
        sortCourseListAlphabetically(lectureOnlyCourses);

        System.out.println("\nLaboratory-Only courses");
        sortCourseListAlphabetically(laboratoryOnlyCourses);

        System.out.println("\nHybrid courses");
        sortCourseListAlphabetically(hybridCourses);
      }

      if (choice == 2 || choice == 3){
        System.out.println("\nStudent list");
        sortStudentListAlphabetically(students);

        System.out.println("\nInstructor list");
        sortInstructorListAlphabetically(instructors);

        System.out.println("\nTeaching Assistant list");
        sortTeachingAssistantListAlphabetically(teachingAssistants);

        System.out.println("\nLab Technician list");
        sortLabTechnicianListAlphabetically(labTechnicians);

        System.out.println("\nStaff Member list");
        sortStaffMemberListAlphabetically(staffMembers);
      }

      if (choice == 4){
        //testing with face to face courses
        sortPeopleInAllCoursesAlphabetically(faceToFaceCourses);
      }

    }



    //sorts a given list of courses alphabetically by their name
    public static void sortCourseListAlphabetically(List<Course> inputCourseList){

      if (inputCourseList.size() > 0){
        if (inputCourseList.get(0) instanceof Course){

          //prints out a list of the courses before being sorted. For testing purposes.
          System.out.println("Unsorted names:");
          for (int i = 0; i < inputCourseList.size(); i++){
            System.out.println(inputCourseList.get(i).getClassName());
          }

          Collections.sort(inputCourseList);

          //prints out a litst of the courses after being sorted. For testing purposes.
          System.out.println("\n" + "Sorted names:");
          for (int i = 0; i < inputCourseList.size(); i++){
            System.out.println(inputCourseList.get(i).getClassName());
          }
        }
      }
    }

    public static void sortPeopleInAllCoursesAlphabetically(List<Course> inputCourseList){
      if (inputCourseList.size() > 0){
        if (inputCourseList.get(0) instanceof Course){
          for (int i = 0; i < inputCourseList.size(); i++){
            inputCourseList.get(i).sortPersonArrays(inputCourseList.get(i).getStudentsInCourse(), inputCourseList.get(i).getInstructorsInCourse(), inputCourseList.get(i).getTeachingAssistantsInCourse(), inputCourseList.get(i).getLabTechniciansInCourse(), inputCourseList.get(i).getStaffMembersInCourse());
          }
        }
      }
    }


    //sorts a given list of students alphabetically by their name
    //okay, so, the courses should work fine, and I can probably use a menu to choose which one is sorted, but I guess I'll need to make individual sorting methods for each of the person subclasses, as the lists all have differnt specifications(?)
    public static void sortStudentListAlphabetically(List<Student> inputStudentList){
      if (inputStudentList.size() > 0){
        if (inputStudentList.get(0) instanceof Student){

          //listing the names
          System.out.println("Unsorted names:");
          for (int i = 0; i < inputStudentList.size(); i++){
            System.out.println(inputStudentList.get(i).getName());
          }

          Collections.sort(inputStudentList);

          //prints out a list of the students after being sorted. For testing purposes.
          System.out.println("\n" + "Sorted names:");
          for (int i = 0; i < inputStudentList.size(); i++){
            System.out.println(inputStudentList.get(i).getName());
          }
        }
      }
    }

    public static void sortInstructorListAlphabetically(List<Instructor> inputPersonnelList){
      if (inputPersonnelList.size() > 0){
        if (inputPersonnelList.get(0) instanceof Instructor){

          //listing the names
          System.out.println("Unsorted names:");
          for (int i = 0; i < inputPersonnelList.size(); i++){
            System.out.println(inputPersonnelList.get(i).getName());
          }

          Collections.sort(inputPersonnelList);

          //prints out a list of the personnel after being sorted. For testing purposes.
          System.out.println("\n" + "Sorted names:");
          for (int i = 0; i < inputPersonnelList.size(); i++){
            System.out.println(inputPersonnelList.get(i).getName());
          }
        }
      }
    }

    public static void sortTeachingAssistantListAlphabetically(List<Teaching_Assistant> inputPersonnelList){
      if (inputPersonnelList.size() > 0){
        if (inputPersonnelList.get(0) instanceof Teaching_Assistant){

          //listing the names
          System.out.println("Unsorted names:");
          for (int i = 0; i < inputPersonnelList.size(); i++){
            System.out.println(inputPersonnelList.get(i).getName());
          }

          Collections.sort(inputPersonnelList);

          //prints out a list of the personnel after being sorted. For testing purposes.
          System.out.println("\n" + "Sorted names:");
          for (int i = 0; i < inputPersonnelList.size(); i++){
            System.out.println(inputPersonnelList.get(i).getName());
          }
        }
      }
    }

    public static void sortLabTechnicianListAlphabetically(List<Lab_Technician> inputPersonnelList){
      if (inputPersonnelList.size() > 0){
        if (inputPersonnelList.get(0) instanceof Lab_Technician){

          //listing the names
          System.out.println("Unsorted names:");
          for (int i = 0; i < inputPersonnelList.size(); i++){
            System.out.println(inputPersonnelList.get(i).getName());
          }

          Collections.sort(inputPersonnelList);

          //prints out a list of the personnel after being sorted. For testing purposes.
          System.out.println("\n" + "Sorted names:");
          for (int i = 0; i < inputPersonnelList.size(); i++){
            System.out.println(inputPersonnelList.get(i).getName());
          }
        }
      }
    }

    public static void sortStaffMemberListAlphabetically(List<Staff_Member> inputPersonnelList){
      if (inputPersonnelList.size() > 0){
        if (inputPersonnelList.get(0) instanceof Staff_Member){

          //listing the names
          System.out.println("Unsorted names:");
          for (int i = 0; i < inputPersonnelList.size(); i++){
            System.out.println(inputPersonnelList.get(i).getName());
          }

          Collections.sort(inputPersonnelList);

          //prints out a list of the personnel after being sorted. For testing purposes.
          System.out.println("\n" + "Sorted names:");
          for (int i = 0; i < inputPersonnelList.size(); i++){
            System.out.println(inputPersonnelList.get(i).getName());
          }
        }
      }
    }


    public static boolean displayMainMenu() {
        System.out.println("-------------------------------------------");
        System.out.println(" 1. Add a Course");
        System.out.println(" 2. Add a Person");
        System.out.println(" 3. Register for a Course");
        System.out.println(" 4. Assign Personnel to a Course");
        System.out.println(" 5. Display Information");
        System.out.println(" 6. Search For Course by Personnel Name"); //implement a binary search here.
        //the below two are for testing purposes
        System.out.println(" 7. Sort a list");
        System.out.println(" 8. Get the index of a student object");

        System.out.println(" 0. Exit Application");
        System.out.println("What would you like to do:");
        System.out.println("-------------------------------------------");

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        boolean canContinue = true;

        switch (choice) {
            case "1":
                try {
                    addCourse(sc);
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "2":
                try {
                    addAPerson(sc);
                } catch (InputMismatchException | IOException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "3":
                try {
                    registerForACourse(sc);
                } catch (InputMismatchException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "4":
                assignPersonnel(sc);
                break;
            case "5":
                displayInformation(sc);
                break;
            case "6":
                try {
                    binarySearchForCourseByPersonnelName(courses, instructors, teachingAssistants, labTechnicians, staffMembers);
                } catch (NullPointerException e) {
                    System.err.println(e.getMessage());
                }
                break;

                //test case
            case "7":
                sortListAlphabetically(courses, faceToFaceCourses, syncOnlineCourses, asyncOnlineCourses, lectureOnlyCourses, laboratoryOnlyCourses, hybridCourses, students, instructors, teachingAssistants, labTechnicians, staffMembers);
                break;

            case "8":
                binarySearchForStudent(students); //works
                break;

            case "0":
                canContinue = false;
                System.out.println("Thank you for using the School System.");
            default:
                System.out.print("");
        }
        return canContinue;
    }

    public static boolean addCourse(Scanner sc) throws NumberFormatException {
        System.out.println("-------------------------------------------");
        System.out.println("Choose the type of course you want to add: ");
        System.out.println(" 1. Face to Face");
        System.out.println(" 2. Synchronous Online");
        System.out.println(" 3. Asynchronous Online");
        System.out.println(" 4. Lecture Only");
        System.out.println(" 5. Laboratory Only");
        System.out.println(" 6. Hybrid (Lecture and Laboratory)");
        System.out.println("Enter your choice: ");
        System.out.println("-------------------------------------------");
        String choice = sc.next();
        boolean goon = true;
        switch (choice) {
            case "1":
                addFaceToFaceCourse(sc, "face_to_face_class");
                goon = false;
                break;
            case "2":
                addSyncOnlineCourse(sc, "sync_class_online");
                goon = false;
                break;
            case "3":
                addAsyncOnlineCourse(sc, "async_class_online");
                goon = false;
                break;
            case "4":
                addLectureOnlyCourse(sc, "lecture_only_class");
                goon = false;
                break;
            case "5":
                addLaboratoryOnlyCourse(sc, "laboratory_only_class");
                goon = false;
                break;
            case "6":
                addHybridCourse(sc, "hybrid_class");
                goon = false;
                break;
            default:
                addCourse(sc);
        }
        return goon;
    }

    public static void addFaceToFaceCourse(Scanner sc, String inputFilename) throws NumberFormatException {
        try {
            System.out.println("-------------------------------------------");
            System.out.print("Enter Course Name: ");
            String className = sc.next();
            System.out.print("Enter Credit Hours: ");
            String crdHrs = sc.next();
            int creditHours = Integer.parseInt(crdHrs);
            System.out.print("Enter Classroom Location: ");
            String classRoomName = sc.next();
            System.out.print("Enter Class Time: ");
            String classTime = sc.next();
            Course course = new Face_to_Face_Class(className, creditHours, classRoomName, classTime);
            courses.add(course);
            faceToFaceCourses.add(course);

            FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
            pw = new PrintWriter(fileWriter);
            pw.println(className + "," + creditHours +
                    ",NONE,30,0,NONE,3,0,NONE,3,0,NONE,3,0,NONE,3,0,"
                    + classRoomName + "," + classTime);
            pw.close();

            System.out.println("Face To Face Course successfully added to the School System.");
        } catch (Exception e) {
            String msg = "Please try again using numbers.";
            throw new NumberFormatException(msg);
        }
    }

    public static void addSyncOnlineCourse(Scanner sc, String inputFilename) throws NumberFormatException {
        try {
            System.out.println("-------------------------------------------");
            System.out.print("Enter Course Name: ");
            String className = sc.next();
            System.out.print("Enter Credit Hours: ");
            String crdHrs = sc.next();
            int creditHours = Integer.parseInt(crdHrs);
            System.out.print("Enter Class Time: ");
            String classTime = sc.next();
            Course course = new Sync_Class_Online(className, creditHours, classTime);
            courses.add(course);
            syncOnlineCourses.add(course);

            FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
            pw = new PrintWriter(fileWriter);
            pw.println(className + "," + creditHours +
                    ",NONE,30,0,NONE,3,0,NONE,3,0,NONE,3,0,NONE,3,0," + classTime);
            pw.close();

            System.out.println("Online Synchronous Course successfully added to the School System.");
        } catch (Exception e) {
            String msg = "Please try again using numbers.";
            throw new NumberFormatException(msg);
        }
    }

    public static void addAsyncOnlineCourse(Scanner sc, String inputFilename) throws NumberFormatException {
        try {
            System.out.println("-------------------------------------------");
            System.out.print("Enter Course Name: ");
            String className = sc.next();
            System.out.print("Enter Credit Hours: ");
            String crdHrs = sc.next();
            int creditHours = Integer.parseInt(crdHrs);
            System.out.print("Enter Course Information: ");
            String classInfo = sc.next();
            Course course = new Async_Class_Online(className, creditHours, classInfo);
            courses.add(course);
            asyncOnlineCourses.add(course);

            FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
            pw = new PrintWriter(fileWriter);
            pw.println(className + "," + creditHours +
                    ",NONE,30,0,NONE,3,0,NONE,3,0,NONE,3,0,NONE,3,0,"
                    + classInfo);
            pw.close();

            System.out.println("Online Asynchronous Course successfully added to the School System.");
        } catch (Exception e) {
            String msg = "Please try again using numbers.";
            throw new NumberFormatException(msg);
        }
    }

    public static void addLectureOnlyCourse(Scanner sc, String inputFilename) throws NumberFormatException {
        try {
            System.out.println("-------------------------------------------");
            System.out.print("Enter Course Name: ");
            String className = sc.next();
            System.out.print("Enter Credit Hours: ");
            String crdHrs = sc.next();
            int creditHours = Integer.parseInt(crdHrs);
            System.out.print("Enter Classroom Location: ");
            String classRoomName = sc.next();
            System.out.print("Enter Class Time: ");
            String classTime = sc.next();
            Course course = new Lecture_Only_Class(className, creditHours, classRoomName, classTime);
            courses.add(course);
            lectureOnlyCourses.add(course);

            FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
            pw = new PrintWriter(fileWriter);
            pw.println(className + "," + creditHours +
                    ",NONE,30,0,NONE,3,0,NONE,3,0,NONE,3,0,NONE,3,0,"
                    + classRoomName + "," + classTime);
            pw.close();

            System.out.println("Lecture Only Course successfully added to the School System.");
        } catch (Exception e) {
            String msg = "Please try again using numbers.";
            throw new NumberFormatException(msg);
        }
    }

    public static void addLaboratoryOnlyCourse(Scanner sc, String inputFilename) throws NumberFormatException {
        try {
            System.out.println("-------------------------------------------");
            System.out.print("Enter Course Name: ");
            String className = sc.next();
            System.out.print("Enter Credit Hours: ");
            String crdHrs = sc.next();
            int creditHours = Integer.parseInt(crdHrs);
            System.out.print("Enter Laboratory Room Location: ");
            String labRoomName = sc.next();
            System.out.print("Enter Lab Time: ");
            String labTime = sc.next();
            Course course = new Laboratory_Only_Class(className, creditHours, labRoomName, labTime);
            courses.add(course);
            laboratoryOnlyCourses.add(course);

            FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
            pw = new PrintWriter(fileWriter);
            pw.println(className + "," + creditHours +
                    ",NONE,30,0,NONE,3,0,NONE,3,0,NONE,3,0,NONE,3,0,"
                    + labRoomName + "," + labTime);
            pw.close();

            System.out.println("Laboratory Only Course successfully added to the School System.");
        } catch (Exception e) {
            String msg = "Please try again using numbers.";
            throw new NumberFormatException(msg);
        }
    }

    public static void addHybridCourse(Scanner sc, String inputFilename) throws NumberFormatException {
        try {
            System.out.println("-------------------------------------------");
            System.out.print("Enter Course Name: ");
            String className = sc.next();
            System.out.print("Enter Credit Hours: ");
            String crdHrs = sc.next();
            int creditHours = Integer.parseInt(crdHrs);
            System.out.print("Enter Lecture Room Location: ");
            String classRoomName = sc.next();
            System.out.print("Enter Class Time: ");
            String classTime = sc.next();
            System.out.print("Enter Laboratory Room Location: ");
            String labRoomName = sc.next();
            System.out.print("Enter Lab Time: ");
            String labTime = sc.next();
            Course course = new Hybrid_Class(className, creditHours, classRoomName, labRoomName, classTime, labTime);
            courses.add(course);
            hybridCourses.add(course);

            FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
            pw = new PrintWriter(fileWriter);
            pw.println(className + "," + creditHours +
                    ",NONE,30,0,NONE,3,0,NONE,3,0,NONE,3,0,NONE,3,0,"
                    + classRoomName + "," + classTime + "," + labRoomName + "," + labTime);
            pw.close();

            System.out.println("Hybrid Course successfully added to the School System.");
        } catch (Exception e) {
            String msg = "Please try again using numbers.";
            throw new NumberFormatException(msg);
        }
    }

    public static boolean addAPerson(Scanner sc) throws InputMismatchException, IOException { //provide options to school to add what kind of person to school
        System.out.println("-------------------------------------------");
        System.out.println("Choose the type of person you want to add: ");
        System.out.println(" 1. Student");
        System.out.println(" 2. Instructor");
        System.out.println(" 3. Teaching Assistant");
        System.out.println(" 4. Lab Technician");
        System.out.println(" 5. Staff Member");
        System.out.println("Enter your choice: ");
        System.out.println("-------------------------------------------");
        String choice = sc.next();
        boolean goon = true;
        switch (choice) {
            case "1":
                addStudent(sc, "student");
                goon = false;
                break;
            case "2":
                addInstructor(sc, "instructor");
                goon = false;
                break;
            case "3":
                addTeachingAssistant(sc, "teaching_assistant");
                goon = false;
                break;
            case "4":
                addLabTechnician(sc, "lab_technician");
                goon = false;
                break;
            case "5":
                addStaffMember(sc, "staff_member");
                goon = false;
                break;
            default:
                addAPerson(sc);
        }
        return goon;
    }

    public static void addStudent(Scanner sc, String inputFilename) throws InputMismatchException {
        try {
            System.out.println("-------------------------------------------");
            System.out.print("Enter student's full name: ");
            String name = sc.next() + sc.nextLine();
            System.out.print("Enter student ID: ");
            int id = sc.nextInt();
            Student student = new Student(name, id);
            students.add(student);

            FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
            pw = new PrintWriter(fileWriter);
            pw.println(student.getName() + "," + student.getId() + "," + student.getCurrentCredits());
            pw.close();

            System.out.println("Student successfully added to the School System.");

        } catch (Exception e) {
            String msg = "Please try again using numbers.";
            throw new InputMismatchException(msg);
        }
    }

    public static void addInstructor(Scanner sc, String inputFilename) throws IOException {
        System.out.println("-------------------------------------------");
        System.out.print("Enter instructor's full name: ");
        String name = sc.next() + sc.nextLine();
        Instructor instructor = new Instructor(name);
        instructors.add(instructor);

        FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
        pw = new PrintWriter(fileWriter);
        pw.println(instructor.getName());
        pw.close();

        System.out.println("Instructor successfully added to the School System.");
    }

    public static void addTeachingAssistant(Scanner sc, String inputFilename) throws IOException {
        System.out.println("-------------------------------------------");
        System.out.print("Enter teaching assistants's full name: ");
        String name = sc.next() + sc.nextLine();
        Teaching_Assistant teachingAssistant = new Teaching_Assistant(name);
        teachingAssistants.add(teachingAssistant);

        FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
        pw = new PrintWriter(fileWriter);
        pw.println(teachingAssistant.getName());
        pw.close();

        System.out.println("Teaching Assistant successfully added to the School System.");
    }

    public static void addLabTechnician(Scanner sc, String inputFilename) throws IOException {
        System.out.println("-------------------------------------------");
        System.out.print("Enter lab technician's full name: ");
        String name = sc.next() + sc.nextLine();
        Lab_Technician labTechnician = new Lab_Technician(name);
        labTechnicians.add(labTechnician);

        FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
        pw = new PrintWriter(fileWriter);
        pw.println(labTechnician.getName());
        pw.close();

        System.out.println("Lab Technician successfully added to the School System.");
    }

    public static void addStaffMember(Scanner sc, String inputFilename) throws IOException {
        System.out.println("-------------------------------------------");
        System.out.print("Enter staff member's full name: ");
        String name = sc.next() + sc.nextLine();
        Staff_Member staffMember = new Staff_Member(name);
        staffMembers.add(staffMember);

        FileWriter fileWriter = new FileWriter(PATH + inputFilename + ".txt", true);
        pw = new PrintWriter(fileWriter);
        pw.println(staffMember.getName());
        pw.close();

        System.out.println("Staff Member successfully added to the School System.");
    }

    public static boolean assignPersonnel(Scanner sc) {
        System.out.println("-------------------------------------------");
        System.out.println("Choose the type of personnel you want to assign to a course: ");
        System.out.println(" 1. Instructor");
        System.out.println(" 2. Teaching Assistant");
        System.out.println(" 3. Lab technician");
        System.out.println(" 4. Staff Member");
        System.out.println("Enter your choice: ");
        System.out.println("-------------------------------------------");
        String choice = sc.nextLine();
        boolean goon = true;
        switch (choice) {
            case "1":
                assignInstructorToACourse(sc);
                goon = false;
                break;
            case "2":
                assignTeachingAssistantToACourse(sc);
                goon = false;
                break;
            case "3":
                assignLabTechnicianToACourse(sc);
                goon = false;
                break;
            case "4":
                assignStaffMemberToACourse(sc);
                goon = false;
                break;
            default:
                assignPersonnel(sc);
        }
        return goon;
    }

    public static void assignInstructorToACourse(Scanner sc) {
        System.out.println("-------------------------------------------");
        System.out.print("Enter the instructor's full name: ");
        String tempName = sc.nextLine();
        Instructor foundInstructor = searchForInstructor(tempName);
        if (foundInstructor != null) {
            System.out.print("Enter the name of the course that the instructor will teach: ");
            String tempCourseName = sc.nextLine();
            Course foundCourse = searchForCourse(tempCourseName);
            if (foundCourse != null && foundCourse.hasInstructorSpace()) {
                foundCourse.addInstructorToCourse(foundInstructor);
                System.out.println("Instructor successfully assigned to " + tempCourseName);
            } else {
                System.out.println("The instructor was not successfully assigned to this course because " +
                        "it has reached its maximum instructor capacity or this course does not exist. " +
                        "Create the desired course or assign a different course.");
            }
        } else {
            System.out.println("Please create an instructor.");
        }
    }

    public static void assignTeachingAssistantToACourse(Scanner sc) {
        System.out.println("-------------------------------------------");
        System.out.print("Enter the teaching Assistant's full name: ");
        String tempName = sc.nextLine();
        Teaching_Assistant foundTeachingAssistant = searchForTeachingAssistant(tempName);
        if (foundTeachingAssistant != null) {
            System.out.print("Enter the name of the course that the teaching assistant will assist in: ");
            String tempCourseName = sc.nextLine();
            Course foundCourse = searchForCourse(tempCourseName);
            if (foundCourse != null && foundCourse.hasTeachingAssistantSpace()) {
                foundCourse.addTeachingAssistantToCourse(foundTeachingAssistant);
                System.out.println("Teaching assistant successfully assigned to " + tempCourseName);
            } else {
                System.out.println("The teaching assistant was not successfully assigned to this course because " +
                        "it has reached its maximum teaching assistant capacity or this course does not exist. " +
                        "Create the desired course or assign a different course.");
            }
        } else {
            System.out.println("Please create a teaching assistant.");
        }
    }

    public static void assignLabTechnicianToACourse(Scanner sc) {
        System.out.println("-------------------------------------------");
        System.out.print("Enter the lab technician's full name: ");
        String tempName = sc.nextLine();
        Lab_Technician foundLabTechnician = searchForLabTechnician(tempName);
        if (foundLabTechnician != null) {
            System.out.print("Enter the name of the course that the lab technician will teach: ");
            String tempCourseName = sc.nextLine();
            Course foundCourse = searchForCourse(tempCourseName);
            if (foundCourse != null && foundCourse.hasLabTechnicianSpace()) {
                foundCourse.addLabTechnicianToCourse(foundLabTechnician);
                System.out.println("Lab technician successfully assigned to " + tempCourseName);
            } else {
                System.out.println("The lab technician was not successfully assigned to this course because " +
                        "it has reached its maximum lab technician capacity or this course does not exist, or this class doesn't requre laboratory. " +
                        "Create the desired course or assign a different course.");
            }
        } else {
            System.out.println("Please add a lab technician.");
        }
    }

    public static void assignStaffMemberToACourse(Scanner sc) {
        System.out.println("-------------------------------------------");
        System.out.print("Enter the staff member's full name: ");
        String tempName = sc.nextLine();
        Staff_Member foundStaffMember = searchForStaffMember(tempName);
        if (foundStaffMember != null) {
            System.out.print("Enter the name of the course that the staff member will work in: ");
            String tempCourseName = sc.nextLine();
            Course foundCourse = searchForCourse(tempCourseName);
            if (foundCourse != null && foundCourse.hasStaffMemberSpace()) {
                foundCourse.addStaffMemberToCourse(foundStaffMember);
                System.out.println("Staff Member successfully assigned to " + tempCourseName);
            } else {
                System.out.println("The staff member was not successfully assigned to this course because " +
                        "it has reached its maximum staff member capacity or this course does not exist. " +
                        "Create the desired course or assign a different course.");
            }
        } else {
            System.out.println("Please add a staff member.");
        }
    }

    public static void registerForACourse(Scanner sc) throws InputMismatchException {
        try {
            System.out.println("-------------------------------------------");
            System.out.print("Enter your ID: ");
            int tempID = sc.nextInt();
            Student foundStudent = searchForStudent(tempID);
            if (foundStudent != null) {
                System.out.print("Enter the name of the course you want to enroll in: ");
                String tempCourseName = sc.nextLine() + sc.nextLine();
                Course foundCourse = searchForCourse(tempCourseName);

                //System.out.println(foundCourse.toString());
//            System.out.println("LIMIT: " + foundStudent.getCREDIT_LIMIT());
//            System.out.println("Current: " + foundStudent.getCurrentCredits());
                if (foundCourse != null && foundCourse.hasStudentSpace()) {
//                System.out.println("Course: " + foundCourse.getCreditHours());
                    if (foundStudent.getCurrentCredits() + foundCourse.getCreditHours() <= foundStudent.getCREDIT_LIMIT()) {
                        foundCourse.addStudentToCourse(foundStudent);
                        foundStudent.addCredits(foundCourse.getCreditHours());

                        File old_file = new File(PATH + "student.txt");
                        Scanner sc1 = new Scanner(old_file);
                        File new_file = new File(PATH + "temp_student.txt");
                        pw = new PrintWriter(new_file);
                        String[] student_tokens = null;
                        while (sc1.hasNextLine()) {
                            String curr = sc1.nextLine();

                            student_tokens = curr.split(",");

                            if (Integer.parseInt(student_tokens[1]) == foundStudent.getId()) {
                                student_tokens[2] = Integer.toString(foundStudent.getCurrentCredits());
                                String person = foundStudent.getName() + "," + foundStudent.getId() + "," + student_tokens[2];
                                pw.println(person);
                            } else {
                                pw.println(curr);
                            }
                        }
                        pw.close();
                        sc1.close();

                        if (old_file.delete()) {
                            new_file.renameTo(new File(PATH + "student.txt"));
                            System.out.println("Deleted the file: " + old_file.getName());
                        } else {
                            System.out.println("Failed to delete the file.");
                        }

                        String course_class = foundCourse.getClass().toString();
                        //System.out.println(course_class);

                        switch (course_class) {
                            case "Async_Class_Online":
                                update_course("async_class_online", foundCourse, foundStudent);
                                break;
                            case "Face_to_Face_Class":
                                update_course("face_to_face_class", foundCourse, foundStudent);
                                break;
                            case "Hybrid_Class":
                                update_course("hybrid_class", foundCourse, foundStudent);
                                break;
                            case "Laboratory_Only_Class":
                                update_course("laboratory_only_class", foundCourse, foundStudent);
                                break;
                            case "Lecture_Only_Class":
                                update_course("lecture_only_class", foundCourse, foundStudent);
                                break;
                            case "Sync_Class_Online":
                                update_course("sync_class_online", foundCourse, foundStudent);
                                break;
                        }


//                    System.out.println("UPDATED: " + foundStudent.getCurrentCredits());
                        System.out.println("Student successfully enrolled to " + tempCourseName);
                    } else {
                        System.out.println("The student was not successfully enrolled in this course because" +
                                " they have reached or will exceed their credit limit.");
                    }
                } else {
                    System.out.println("This course does not exist, or it has reached it maximum student capacity." +
                            " Create the desired course or enroll in a different course.");
                }
            } else {
                System.out.println("Please create a student.");
            }
        } catch (Exception e) {
            String msg = "Please try again using numbers.";
            throw new InputMismatchException(msg);
        }

    }

    public static void update_course(String class_type, Course foundCourse, Student foundStudent) throws FileNotFoundException {
        File old_file = new File(PATH + class_type + ".txt");
        Scanner sc2 = new Scanner(old_file);
        File new_file = new File(PATH + "temp_course.txt");
        pw = new PrintWriter(new_file);
        String[] course_tokens = null;
        while (sc2.hasNextLine()) {
            String curr = sc2.nextLine();

            course_tokens = curr.split(",");

            if (course_tokens[0] == foundCourse.getClassName()) {
                course_tokens[2] += ";" + foundStudent.getId();
                String course = foundCourse.getClassName();
                pw.println(course);
            } else {
                pw.println(curr);
            }
        }
        pw.close();
        sc2.close();

        if (old_file.delete()) {
            new_file.renameTo(new File(PATH + class_type + ".txt"));
            System.out.println("Deleted the file: " + old_file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static Student binarySearchForStudent(List<Student> students){ //could have a central search method that would give a parameter of a search string to this method.
        sortStudentListAlphabetically(students);
        String input;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the Student, exactly, that you would like to search for.");
        input = sc.nextLine();

        Student tempSearchStudent = new Student(input, 0);

        int studentIndex = Collections.binarySearch(students, tempSearchStudent);
        System.out.println(studentIndex);

        if (studentIndex < 0){
          System.out.println("Student not found, please try again.");
          return null;
        } else {
          return students.get(studentIndex);
        }
    }

    public static Student searchForStudent(int tempId) {
        Student result = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == tempId) {
                return students.get(i);
            }
        }
        return result;
    }


    public static Course binarySearchForCourse(List<Course> courses){ //could have a central search method that would give a parameter of a search string to this method.
        sortCourseListAlphabetically(courses);
        String input;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the Course, exactly, that you would like to search for.");
        input = sc.nextLine();

        Course tempSearchCourse = new Course(input, 0);

        int courseIndex = Collections.binarySearch(courses, tempSearchCourse);
        System.out.println(courseIndex);

        if (courseIndex < 0){
          System.out.println("Course not found, please try again.");
          return null;
        } else {
          return courses.get(courseIndex);
        }
    }
    //now adapt the above for the other search methods, implement and test them, and then call it a day.
    //oh, and add the credit file

    public static Course searchForCourse(String tempCourseName) {
        Course result = null;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getClassName().equals(tempCourseName)) {
                return courses.get(i);
            }
        }
        return result;
    }


    public static Instructor binarySearchForInstructor(List<Instructor> instructors, String input){ //could have a central search method that would give a parameter of a search string to this method.
        sortInstructorListAlphabetically(instructors);
        //String input;

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter the name of the Instructor, exactly, that you would like to search for.");
        //input = sc.nextLine();

        Instructor tempSearchInstructor = new Instructor(input);

        int instructorIndex = Collections.binarySearch(instructors, tempSearchInstructor);
        System.out.println(instructorIndex);

        if (instructorIndex < 0){
          System.out.println("Instructor not found, please try again.");
          return null;
        } else {
          return instructors.get(instructorIndex);
        }
    }

    public static Instructor searchForInstructor(String tempName) {
        Instructor result = null;
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getName().equals(tempName)) {
                return instructors.get(i);
            }
        }
        return result;
    }


    public static Teaching_Assistant binarySearchForTeachingAssistant(List<Teaching_Assistant> teachingAssistants, String input){ //could have a central search method that would give a parameter of a search string to this method.
        sortTeachingAssistantListAlphabetically(teachingAssistants);
        //String input;

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter the name of the Teaching Assistant, exactly, that you would like to search for.");
        //input = sc.nextLine();

        Teaching_Assistant tempSearchTeachingAssistant = new Teaching_Assistant(input);

        int teachingAssistantIndex = Collections.binarySearch(teachingAssistants, tempSearchTeachingAssistant);
        System.out.println(teachingAssistantIndex);

        if (teachingAssistantIndex < 0){
          System.out.println("Teaching Assistant not found, please try again.");
          return null;
        } else {
          return teachingAssistants.get(teachingAssistantIndex);
        }
    }

    public static Teaching_Assistant searchForTeachingAssistant(String tempName) {
        Teaching_Assistant result = null;
        for (int i = 0; i < teachingAssistants.size(); i++) {
            if (teachingAssistants.get(i).getName().equals(tempName)) {
                return teachingAssistants.get(i);
            }
        }
        return result;
    }


    public static Lab_Technician binarySearchForLabTechnician(List<Lab_Technician> labTechnicians, String input){ //could have a central search method that would give a parameter of a search string to this method.
        sortLabTechnicianListAlphabetically(labTechnicians);
        //String input;

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter the name of the Lab Technician, exactly, that you would like to search for.");
        //input = sc.nextLine();

        Lab_Technician tempSearchLabTechnician = new Lab_Technician(input);

        int labTechnicianIndex = Collections.binarySearch(labTechnicians, tempSearchLabTechnician);
        System.out.println(labTechnicianIndex);

        if (labTechnicianIndex < 0){
          System.out.println("Lab Technician not found, please try again.");
          return null;
        } else {
          return labTechnicians.get(labTechnicianIndex);
        }
    }

    public static Lab_Technician searchForLabTechnician(String tempName) {
        Lab_Technician result = null;
        for (int i = 0; i < labTechnicians.size(); i++) {
            if (labTechnicians.get(i).getName().equals(tempName)) {
                return labTechnicians.get(i);
            }
        }
        return result;
    }


    public static Staff_Member binarySearchForStaffMember(List<Staff_Member> staffMembers, String input){ //could have a central search method that would give a parameter of a search string to this method.
        sortStaffMemberListAlphabetically(staffMembers);
        //String input;

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter the name of the Staff Member, exactly, that you would like to search for.");
        //input = sc.nextLine();

        Staff_Member tempSearchStaffMember = new Staff_Member(input);

        int staffMemberIndex = Collections.binarySearch(staffMembers, tempSearchStaffMember);
        System.out.println(staffMemberIndex);

        if (staffMemberIndex < 0){
          System.out.println("Staff Member not found, please try again.");
          return null;
        } else {
          return staffMembers.get(staffMemberIndex);
        }
    }

    public static Staff_Member searchForStaffMember(String tempName) {
        Staff_Member result = null;
        for (int i = 0; i < staffMembers.size(); i++) {
            if (staffMembers.get(i).getName().equals(tempName)) {
                return staffMembers.get(i);
            }
        }
        return result;
    }

    public static boolean displayInformation(Scanner sc) {
        System.out.println("-------------------------------------------");
        System.out.println(" 1. Students");
        System.out.println(" 2. Instructors");
        System.out.println(" 3. Teacher Assistants");
        System.out.println(" 4. Lab Technicians");
        System.out.println(" 5. Staff Members");
        System.out.println(" 6. Face to Face");
        System.out.println(" 7. Synchronous Online");
        System.out.println(" 8. Asynchronous Online");
        System.out.println(" 9. Lecture Only");
        System.out.println(" 10. Laboratory Only");
        System.out.println(" 11. Hybrid (Lecture and Laboratory)");
        System.out.println(" 12. Display All People");
        System.out.println(" 13. Display All Courses");
        System.out.println(" 14. Display Everything");
        System.out.println("Select the Information you want to display: ");
        System.out.println("-------------------------------------------");
        String user = sc.nextLine();
        boolean goon = true;
        switch (user) {
            case "1":
                displayStudents();
                goon = false;
                break;
            case "2":
                displayInstructors();
                goon = false;
                break;
            case "3":
                displayTeacherAssistants();
                goon = false;
                break;
            case "4":
                displayLabTechnicians();
                goon = false;
                break;
            case "5":
                displayStaffMembers();
                goon = false;
                break;
            case "6":
                displayFaceToFaceCourses();
                goon = false;
                break;
            case "7":
                displaySyncOnlineCourses();
                goon = false;
                break;
            case "8":
                displayAsyncOnlineCourses();
                goon = false;
                break;
            case "9":
                displayLectureOnlyCourses();
                goon = false;
                break;
            case "10":
                displayLaboratoryOnlyCourses();
                goon = false;
                break;
            case "11":
                displayHybridCourses();
                goon = false;
                break;
            case "12":
                displayAllPeople();
                goon = false;
                break;
            case "13":
                displayAllCourses();
                goon = false;
                break;
            case "14":
                displayAllPeople();
                displayAllCourses();
                goon = false;
                break;
            default:
                displayInformation(sc);
        }
        return goon;
    }

    public static void displayStudents() {
        System.out.println("-------------------------------------------");
        String studentList = "Students: [";
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName() != null) {
                studentList += students.get(i).getName() + ", ";
            }
        }
        if (students.size() == 0) {
            studentList += "  ";
        }
        System.out.println(studentList + "\b\b]");
    }

    public static void displayInstructors() {
        System.out.println("-------------------------------------------");
        String InstructorList = "Instructors: [";
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getName() != null) {
                InstructorList += instructors.get(i).getName() + ", ";
            }
        }
        if (instructors.size() == 0) {
            InstructorList += "  ";
        }
        System.out.println(InstructorList + "\b\b]");
    }

    public static void displayTeacherAssistants() {
        System.out.println("-------------------------------------------");
        String TeacherAssistantList = "Teacher Assistants: [";
        for (int i = 0; i < teachingAssistants.size(); i++) {
            if (teachingAssistants.get(i).getName() != null) {
                TeacherAssistantList += teachingAssistants.get(i).getName() + ", ";
            }
        }
        if (teachingAssistants.size() == 0) {
            TeacherAssistantList += "  ";
        }
        System.out.println(TeacherAssistantList + "\b\b]");
    }

    public static void displayLabTechnicians() {
        System.out.println("-------------------------------------------");
        String labTechnicianList = "Lab Technicians: [";
        for (int i = 0; i < labTechnicians.size(); i++) {
            if (labTechnicians.get(i).getName() != null) {
                labTechnicianList += labTechnicians.get(i).getName() + ", ";
            }
        }
        if (labTechnicians.size() == 0) {
            labTechnicianList += "  ";
        }
        System.out.println(labTechnicianList + "\b\b]");
    }

    public static void displayStaffMembers() {
        System.out.println("-------------------------------------------");
        String staffMemberList = "Staff Members: [";
        for (int i = 0; i < staffMembers.size(); i++) {
            if (staffMembers.get(i).getName() != null) {
                staffMemberList += staffMembers.get(i).getName() + ", ";
            }
        }
        if (staffMembers.size() == 0) {
            staffMemberList += "  ";
        }
        System.out.println(staffMemberList + "\b\b]");
    }

    public static void displayFaceToFaceCourses() {
        System.out.println("-------------------------------------------");
        for (int i = 0; i < faceToFaceCourses.size(); i++) {
            System.out.print(faceToFaceCourses.get(i).toString());
        }
    }

    public static void displaySyncOnlineCourses() {
        System.out.println("-------------------------------------------");
        for (int i = 0; i < syncOnlineCourses.size(); i++) {
            System.out.print(syncOnlineCourses.get(i).toString());
        }
    }

    public static void displayAsyncOnlineCourses() {
        System.out.println("-------------------------------------------");
        for (int i = 0; i < asyncOnlineCourses.size(); i++) {
            System.out.print(asyncOnlineCourses.get(i).toString());
        }
    }

    public static void displayLectureOnlyCourses() {
        System.out.println("-------------------------------------------");
        for (int i = 0; i < lectureOnlyCourses.size(); i++) {
            System.out.print(lectureOnlyCourses.get(i).toString());
        }
    }

    public static void displayLaboratoryOnlyCourses() {
        System.out.println("-------------------------------------------");
        for (int i = 0; i < laboratoryOnlyCourses.size(); i++) {
            System.out.print(laboratoryOnlyCourses.get(i).toString());
        }
    }

    public static void displayHybridCourses() {
        System.out.println("-------------------------------------------");
        for (int i = 0; i < hybridCourses.size(); i++) {
            System.out.print(hybridCourses.get(i).toString());
        }
    }

    public static void displayAllCourses() {
        displayFaceToFaceCourses();
        displaySyncOnlineCourses();
        displayAsyncOnlineCourses();
        displayLectureOnlyCourses();
        displayLaboratoryOnlyCourses();
        displayHybridCourses();
    }

    public static void displayAllPeople() {
        displayStudents();
        displayInstructors();
        displayTeacherAssistants();
        displayLabTechnicians();
        displayStaffMembers();
    }

    public static void binarySearchForCourseByPersonnelName(List<Course> courses, List<Instructor> instructors, List<Teaching_Assistant> teachingAssistants, List<Lab_Technician> labTechnicians, List<Staff_Member> staffMembers) throws NullPointerException{
      Scanner sc = new Scanner(System.in);

      System.out.print("Enter the name of the personnel member you want to search for:");
      String tempPersonnelName = sc.next();

      try {
        Instructor searchInstructor = binarySearchForInstructor(instructors, tempPersonnelName);
        Teaching_Assistant searchTeachingAssistant = binarySearchForTeachingAssistant(teachingAssistants, tempPersonnelName);
        Lab_Technician searchLabTechnician = binarySearchForLabTechnician(labTechnicians, tempPersonnelName);
        Staff_Member searchStaffMember = binarySearchForStaffMember(staffMembers, tempPersonnelName);

        int coursesTaughtCounter = 0;


        if (searchInstructor != null){
          for (int i = 0; i < courses.size(); i++){
            if (courses.get(i).binarySearchInstructors(courses.get(i).getInstructorsInCourse(), searchInstructor) != null){
              coursesTaughtCounter++;
              System.out.println(courses.get(i).getClassName());
            }
          }
        } else if (searchTeachingAssistant != null){
          for (int i = 0; i < courses.size(); i++){
            if (courses.get(i).binarySearchTeachingAssistants(courses.get(i).getTeachingAssistantsInCourse(), searchTeachingAssistant) != null){
              coursesTaughtCounter++;
              System.out.println(courses.get(i).getClassName());
            }
          }
        } else if (searchLabTechnician != null){
          for (int i = 0; i < courses.size(); i++){
            if (courses.get(i).binarySearchLabTechnicians(courses.get(i).getLabTechniciansInCourse(), searchLabTechnician) != null){
              coursesTaughtCounter++;
              System.out.println(courses.get(i).getClassName());
            }
          }
        } else if (searchStaffMember != null){
          for (int i = 0; i < courses.size(); i++){
            if (courses.get(i).binarySearchStaffMembers(courses.get(i).getStaffMembersInCourse(), searchStaffMember) != null){
              coursesTaughtCounter++;
              System.out.println(courses.get(i).getClassName());
            }
          }
        } else {
          System.out.println("The given personnel name did not match any personnel in our records. Please try again.");
        }

      } catch (Exception ex){
        System.out.println(ex);
      }
    }

    public static void searchForCourseByPersonnelName(Scanner sc) throws NullPointerException {
        System.out.println("-------------------------------------------");
        System.out.print("Enter the name of the personnel member you want to search courses by: ");
        String tempPersonnelName = sc.next();

        String result = "[";

        //System.out.print(result);
        for (int i = 0; i < courses.size(); i++) {
            Instructor[] instructorList = courses.get(i).getInstructorsInCourse();
            Teaching_Assistant[] teachingAssistantList = courses.get(i).getTeachingAssistantsInCourse();
            Lab_Technician[] labTechnicianList = courses.get(i).getLabTechniciansInCourse();
            Staff_Member[] staffMemberList = courses.get(i).getStaffMembersInCourse();

            for (int j = 0; j < instructorList.length; j++) {
                try {
                    if (instructorList[j].getName() != null) {
                        //System.out.println("STEP1");
                        if (instructorList[j].getName().equals(tempPersonnelName)) {
                            result += courses.get(i).getClassName() + ", ";
                        }
                    }
                } catch (Exception e) {
                    //System.out.println("ERROR1");
                }
            }

            //System.out.println("STEP3");

            for (int j = 0; j < teachingAssistantList.length; j++) {

                try {
                    if (teachingAssistantList[j].getName() != null) {
                        if (teachingAssistantList[j].getName().equals(tempPersonnelName)) {
                            //System.out.println("STEP4");
                            result += courses.get(i).getClassName() + ", ";
                        }
                    }

                } catch (Exception e) {
                    //System.out.println("ERROR2");
                }
            }

            for (int j = 0; j < labTechnicianList.length; j++) {

                try {
                    if (labTechnicianList[j].getName() != null) {
                        if (labTechnicianList[j].getName().equals(tempPersonnelName)) {
                            result += courses.get(i).getClassName() + ", ";
                        }
                    }

                } catch (Exception e) {
                    //System.out.println("ERROR3");
                }
            }

            for (int j = 0; j < staffMemberList.length; j++) {

                try {
                    if (staffMemberList[j].getName() != null) {
                        if (staffMemberList[j].getName().equals(tempPersonnelName)) {
                            result += courses.get(i).getClassName() + ", ";
                        }
                    }

                } catch (Exception e) {
                    //System.out.println("ERROR4");
                    //System.out.println(e);
                    //String msg = "There are no courses that the personnel member is a part of.";
                    //throw new NullPointerException(msg);
                }
            }
        }

        if (result.length() == 1) {
            result += "  ";
        }

        System.out.println(result + "\b\b]");
    }
}
