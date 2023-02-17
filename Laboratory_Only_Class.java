public class Laboratory_Only_Class extends Course {

    private String labRoomName;
    private String labTime;

    public Laboratory_Only_Class() {
        super();
        this.labRoomName = "default lab room";
        this.labTime = "default lab time";
    }

    public Laboratory_Only_Class(String className, int creditHours, String labRoomName, String labTime) {
        super(className, creditHours);
        this.labRoomName = labRoomName;
        this.labTime = labTime;
    }

    public Laboratory_Only_Class(String className,
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
                                 String labRoomName,
                                 String labTime) {
        super(className, creditHours, studentsInCourse, studentCapacity, studentEnrollmentCounter,
                instructorsInCourse, instructorCapacity, instructorEnrollmentCounter, teachingAssistantsInCourse,
                teachingAssistantCapacity, teachingAssistantEnrollmentCounter, labTechniciansInCourse,
                labTechnicianCapacity, labTechnicianEnrollmentCounter, staffMembersInCourse, staffMemberCapacity ,
                staffMemberEnrollmentCounter);
        this.labRoomName = labRoomName;
        this.labTime = labTime;
    }

    public String getLabRoomName() {
        return labRoomName;
    }

    public String getClassTime() {
        return labTime;
    }
    

    public String toString() {
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

        return "Course Information: \nCourse Type: Laboratory Only" +
                " \nCourse Name: " + getClassName() + "\nCredit Hours: " + getCreditHours() +
                "\nLaboratory Location: " + labRoomName + "\nLab Time: " + labTime +
                "\nLab Technician(s): " + labTechnicianList + "\b\b]" + "\nTeaching Assistant(s): " + teachingAssistantsList
                + "\b\b]" + "\nStaff Member(s): " + staffMembersList + "\b\b]" +
                "\nEnrollment: " + getStudentEnrollmentCounter() + "/" + getStudentCapacity() +
                "\n-------------------------------------------\n";
    }

}
