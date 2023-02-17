public class Sync_Class_Online extends Course {

    private String classTime;

    public Sync_Class_Online() {
        super();
        this.classTime = "default time";
    }

    public Sync_Class_Online(String className, int creditHours, String classTime) {
        super(className, creditHours);
        this.classTime = classTime;
    }

    public Sync_Class_Online(String className,
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
                             int staffMemberEnrollmentCounter,
                             String classTime) {
        super(className, creditHours, studentsInCourse, studentCapacity, studentEnrollmentCounter,
                instructorsInCourse, instructorCapacity, instructorEnrollmentCounter, teachingAssistantsInCourse,
                teachingAssistantCapacity, teachingAssistantEnrollmentCounter, labTechniciansInCourse,
                labTechnicianCapacity, labTechnicianEnrollmentCounter, staffMembersInCourse, staffMemberCapacity ,
                staffMemberEnrollmentCounter);
        this.classTime = classTime;
    }

    public String getClassTime() {
        return classTime;
    }

    public String toString() {
        String instructorList = "[";
        for (int i = 0; i < getInstructorsInCourse().length; i++) {
            if (getInstructorsInCourse()[i] != null) {
                instructorList += getInstructorsInCourse()[i].getName() + ", ";
            }
        }
        if (getInstructorsInCourse()[0] == null) {
            instructorList += "  ";
        }

        String teachingAssistantsList = "[";
        for (int i = 0; i < getTeachingAssistantsInCourse().length; i++) {
            if (getTeachingAssistantsInCourse()[i] != null) {
                teachingAssistantsList += getTeachingAssistantsInCourse()[i].getName() + ", ";
            }
        }
        if (getTeachingAssistantsInCourse()[0] == null) {
            teachingAssistantsList += "  ";
        }

        String staffMembersList = "[";
        for (int i = 0; i < getStaffMembersInCourse().length; i++) {
            if (getStaffMembersInCourse()[i] != null) {
                staffMembersList += getStaffMembersInCourse()[i].getName() + ", ";
            }
        }
        if (getStaffMembersInCourse()[0] == null) {
            staffMembersList += "  ";
        }

        return "Course Information: \nCourse Type: Online Synchronous" +
                " \nCourse Name: " + getClassName() + "\nCredit Hours: " + getCreditHours() +
                "\nClass Time: " + classTime + "\nInstructor(s): " + instructorList + "\b\b]" +
                "\nTeaching Assistant(s): " + teachingAssistantsList + "\b\b]" + "\nStaff Member(s): " +
                staffMembersList + "\b\b]" + "\nEnrollment: " + getStudentEnrollmentCounter() + "/" +
                getStudentCapacity() + "\n-------------------------------------------\n";
    }
}
