import java.util.ArrayList;

public class Course {
    String title; // Title of the course
    ArrayList<DiscussionForums> forums; // List of the Forums within the course
    ArrayList<Courses> courses = new ArrayList<Courses>(); // List of Courses

    public Course(String title) {
        this.title = title;
        ArrayList<DiscussionForums> forums = new ArrayList<DiscussionForums>();
        this.forums = forums;
        this.courses = new ArrayList<>();
    }

    // Adds a course to the list
    public void createCourse(Courses course) {
        courses.add(course);
    }

    // Gets the forums list
    public ArrayList<DiscussionForums> getForums() {
        return forums;
    }

    // Sets the forums list, used for File IO
    public void setForums(ArrayList<DiscussionForums> forums) {
        this.forums = forums;
    }

    // Creates a forum, unless one already exists
    public void createForum(String title, String prompt) {
        for (DiscussionForums forum : forums) {
            if (forum.getTitle().equals(title)) {
                System.out.println("Forum already exists.");
                return;
            }
        }
        forums.add(0, new DiscussionForums(title, prompt));
        System.out.println("Forum created");
    }

    // Deletes a forum, unless it doesn't exist
    public void deleteForum(String title) {
        for (DiscussionForums forum : forums) {
            if(forum.getTitle().equals(title)) {
                forum.setTitle(null);
                forum.setPrompt(null);
                System.out.println("Forum deleted!");
                return;
            }
        }
        System.out.println("Forum was not found");
    }

    // Edits a forum, unless it doesn't exist
    public void editForum(String title, String content) {
        for (DiscussionForums forum : forums) {
            if(forum.getTitle().equals(title)) {
                forum.setPrompt(content);
                System.out.println("Forum edited!");
                return;
            }
        }
        System.out.println("Forum was not found");
    }
}