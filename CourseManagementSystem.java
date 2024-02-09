import java.util.ArrayList;
import java.util.List;
class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
    public String getCode() {
        return code;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getSchedule() {
        return schedule;
    }
    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}
class Student {
    private int id;
    private String name;
    private List<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registeredCourses=" + registeredCourses +
                '}';
    }
}

public class CourseManagementSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseManagementSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void displayAvailableSlots() {
        System.out.println("Available Slots:");
        for (Course course : courses) {
            int availableSlots = course.getCapacity() - getRegisteredStudents(course).size();
            System.out.println(course.getTitle() + " - Available Slots: " + availableSlots);
        }
    }

    public void registerStudentForCourse(Student student, Course course) {
        if (getRegisteredStudents(course).size() < course.getCapacity()) {
            student.registerCourse(course);
            System.out.println(student.getName() + " registered for " + course.getTitle());
        } else {
            System.out.println("Course " + course.getTitle() + " is full!");
        }
    }

    public void dropStudentFromCourse(Student student, Course course) {
        student.dropCourse(course);
        System.out.println(student.getName() + " dropped from " + course.getTitle());
    }

    private List<Student> getRegisteredStudents(Course course) {
        List<Student> registeredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getRegisteredCourses().contains(course)) {
                registeredStudents.add(student);
            }
        }
        return registeredStudents;
    }

    public static void main(String[] args) {
        CourseManagementSystem cms = new CourseManagementSystem();

        // Adding courses
        Course course1 = new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 30, "Mon-Wed-Fri 9:00 AM");
        Course course2 = new Course("MAT201", "Calculus I", "Introduction to differential calculus", 25, "Tue-Thu 10:30 AM");
        cms.addCourse(course1);
        cms.addCourse(course2);

        // Adding students
        Student student1 = new Student(1, "Alice");
        Student student2 = new Student(2, "Bob");
        cms.addStudent(student1);
        cms.addStudent(student2);

        // Displaying available courses and slots
        cms.displayCourses();
        cms.displayAvailableSlots();

        // Registering students for courses
        cms.registerStudentForCourse(student1, course1);
        cms.registerStudentForCourse(student2, course2);
        cms.registerStudentForCourse(student1, course2); // Attempt to register student1 for a full course

        // Dropping student from a course
        cms.dropStudentFromCourse(student2, course2);

        // Displaying updated registered courses for each student
        System.out.println(student1);
        System.out.println(student2);
    }
}
