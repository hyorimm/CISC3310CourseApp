public class Hybrid_Class extends Course {

    private String classRoomName;
    private String labRoomName;
    private String classTime;
    private String labTime;

    public Hybrid_Class() {
        super();
        this.classRoomName = "default room";
        this.labRoomName = "default lab room";
        this.classTime = "default time";
        this.labTime = "default lab time";
    }

    public Hybrid_Class(String className, int creditHours, String classRoomName, String labRoomName, String classTime, String labTime) {
        super(className, creditHours);
        this.classRoomName = classRoomName;
        this.labRoomName = labRoomName;
        this.classTime = classTime;
        this.labTime = labTime;
    }

    public Hybrid_Class(String className,
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
                        String classRoomName,
                        String labRoomName,
                        String classTime,
                        String labTime) {
        super (className, creditHours, studentsInCourse, studentCapacity, studentEnrollmentCounter,
                instructorsInCourse, instructorCapacity, instructorEnrollmentCounter, teachingAssistantsInCourse,
                teachingAssistantCapacity, teachingAssistantEnrollmentCounter, labTechniciansInCourse,
                labTechnicianCapacity, labTechnicianEnrollmentCounter, staffMembersInCourse, staffMemberCapacity ,
                staffMemberEnrollmentCounter);
        this.classRoomName = classRoomName;
        this.labRoomName = labRoomName;
        this.classTime = classTime;
        this.labTime = labTime;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public String getLabRoomName() {
        return labRoomName;
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

        String labTechnicianList = "[";
        for (int i = 0; i < getLabTechniciansInCourse().length; i++) {
            if (getLabTechniciansInCourse()[i] != null) {
                labTechnicianList += getLabTechniciansInCourse()[i].getName() + ", ";
            }
        }
        if (getLabTechniciansInCourse()[0] == null) {
            labTechnicianList += "  ";
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

        return "Course Information: \nCourse Type: Hybrid (Lecture and Laboratory)" +
                " \nCourse Name: " + getClassName() + "\nCredit Hours: " + getCreditHours() +
                "\nClassroom Location: " + classRoomName + "\nClass Time: " + classTime +
                "\nLaboratory Location: " + labRoomName + "\nLab Time: " + labTime +
                "\nInstructor(s): " + instructorList + "\b\b]" + "\nLab Technician(s): " + labTechnicianList +
                "\b\b]" + "\nTeaching Assistant(s): " + teachingAssistantsList +
                "\b\b]" + "\nStaff Member(s): " + staffMembersList + "\b\b]" +
                "\nEnrollment: " + getStudentEnrollmentCounter() + "/" + getStudentCapacity() +
                "\n-------------------------------------------\n";
    }
}
