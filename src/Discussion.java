import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

public class Discussion extends Thread {
    private static final String welcomeMessage = "Welcome to the Discussion Board!";
    private static final String actionsMenu = "Please choose the option:\n1. Log in\n2. Register\n3. Quit";
    private static final String createUsernamePrompt = "Create the username:";
    private static final String createPasswordPrompt = "Create the password:";
    private static final String accountCreatedConfirmation = "Congratulations! Your account has been created.";
    private static final String studentOrTeacher = "Select one of the following:\n1. Teacher\n2. Student";
    private static final String usernamePrompt = "Please enter the username:";
    private static final String passwordPrompt = "Please enter the password:";
    private static final String logInError = "Error. Cannot log in. Please ensure your username and password is correct, " +
            "and you selected the right role.";
    private static final String currentLogIn = "Currently logged in as: ";
    private static final String loggedInMenu = "1. Manage my account\n2. Select the course\n3. Log Out";
    private static final String manageAccountMenu = "1. Edit account\n2. Delete account";
    private static final String newUsernamePrompt = "Please type your new username";
    private static final String newPasswordPrompt = "Please type your new password";
    private static final String accountEditedSuccessfullyPrompt = "Congratulations! Your account information has been changed successfully!";
    private static final String accountDeletedConfirmation = "Your account has been successfully deleted.";
    private static final String goBack = "-1: Go Back";
    private static final String selectCourse = "Select a Course:\n0: Create a new Course";
    private static final String selectForum = "Course: %s\nSelect a Forum:\n0: Create a new Forum\n-2: Edit a forum\n-3: Delete a forum";
    private static final String selectPosts = "\nDiscussion Forum: %s\n\n%s\n\nChoose a post to view or create a new " +
            "post:\n0: New Post";
    private static final String enterNewCourse = "Please write the title of this new course";
    private static final String enterNewForumTitle = "Please write the title of this new forum";
    private static final String editForum = "Enter title of forum to be edited:";
    private static final String deleteForum = "Enter title of forum to be deleted:";
    private static final String enterNewForumPrompt = "Please enter the prompt for this new forum";
    private static final String enterPostTitle = "Enter the title of the post:";
    private static final String enterPostContent = "Write the contents of your post here:";
    private static final String replyDecision = "0: Write a reply\n1: Edit this post\n2: Delete this post\n3: Upvote this post";
    private static final String enterReply = "Write your reply:";
    private static final String chooseFileImport = "Enter 1 to enter manually, 2 to use file import";
    private static final String errorStudent = "Only teachers can do that!";
    private static final String editFailure = "You can only edit your own posts";
    private static final String generalError = "An error as occurred, logging out and returning to the main menu...";
    private static final String farewell = "Thank you for choosing our Discussion Board, goodbye.";
    private static final String title = "Discussion Board";
    private static boolean started = false;
    public Discussion() {

    }

    public static void main(String[] args) {
        ArrayList<Discussion> discussions = new ArrayList<>();
        // network io accept here or something
        discussions.add(new Discussion());
        discussions.add(new Discussion());
        try {
            for (int i = 0; i < discussions.size(); i++) {
                discussions.get(i).start();
            }

            for (int i = 0; i < discussions.size(); i++) {
                discussions.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final Object OBJECT = new Object();

    @Override
    public void run() {
        super.run();
        Teacher loggedInTeacher = null;
        Student loggedInStudent = null;

        String username = "";
        String password = "";
        boolean isTeacher = false;
        boolean isStudent = false;
        boolean quit = false;
        boolean loggedIn = false;
        Scanner scanner = new Scanner(System.in);
        while (!quit) {
            try { // giant try block spans the entire program, this is to prevent the program from crashing
                FileStorage f1 = new FileStorage();
                AccountsFile f2 = new AccountsFile();
                synchronized (OBJECT) {
                    Account.setCreatedAccounts(f2.retrieveCreatedAccounts());
                    BufferedReader br = new BufferedReader(new FileReader("Datafile.txt"));
                    if (br.readLine() == null) {
                        f1.encode(Courses.getCourses());
                    }
                    Courses.setCourses(f1.retrieve());
                    System.out.println(welcomeMessage);
                    System.out.println(actionsMenu);
                }
                String[] options = {"Log in", "Register", "Quit"};
                String menuSelect = (String) JOptionPane.showInputDialog(null, welcomeMessage,
                        title,
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                int option = 0;
                for (int i = 0; i < options.length; i++) {
                    if (menuSelect.equals(options[i])) {
                        option = i + 1;
                    }
                }
                if (option == 2) {
                    username = JOptionPane.showInputDialog(null, createUsernamePrompt, title,
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(createPasswordPrompt);
                    password = scanner.nextLine();
                    System.out.println(studentOrTeacher);
                    int role = scanner.nextInt();
                    if (role == 1) {
                        boolean created;
                        synchronized (OBJECT) {
                            Teacher tempTeacher = new Teacher(username, password);
                            created = Account.createAccount(tempTeacher);
                        }
                        if (created) {
                            System.out.println(accountCreatedConfirmation);
                        }

                    }
                    if (role == 2) {
                        boolean created;
                        synchronized (OBJECT) {
                            Student tempStudent = new Student(username, password);
                            created = Account.createAccount(tempStudent);
                        }
                        if (created) {
                            System.out.println(accountCreatedConfirmation);
                        }
                    }
                    synchronized (OBJECT) {
                        f2.updateFile(Account.getCreatedAccounts());
                    }
                }
                if (option == 1) {
                    System.out.println(studentOrTeacher);
                    int role = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(usernamePrompt);
                    username = scanner.nextLine();
                    System.out.println(passwordPrompt);
                    password = scanner.nextLine();
                    synchronized (OBJECT) {
                        loggedIn = Account.logIn(username, password);
                    }
                    if (loggedIn) {
                        try {
                            if (role == 1) {
                                loggedIn = true;
                                loggedInTeacher = new Teacher(username, password);
                            }
                            if (role == 2) {
                                loggedIn = true;
                                loggedInStudent = new Student(username, password);
                            }
                        } catch (ClassCastException | NullPointerException e) {
                            loggedIn = false;
                            System.out.println(logInError);
                        }
                    } else {
                        System.out.println(logInError);
                    }
                }
                if (option == 3) {
                    quit = true;
                    System.out.println(farewell);
                } else if (option == 1 || option == 2) {
                    while (loggedIn) {
                        Account account;
                        if (loggedInStudent != null) {
                            account = loggedInStudent;
                        } else {
                            account = loggedInTeacher;
                        }
                        synchronized (OBJECT) {
                            System.out.print(currentLogIn);
                            System.out.printf("%s\n", account.getUsername());
                            System.out.println(loggedInMenu);
                        }
                        int loggedInOption = scanner.nextInt();
                        if (loggedInOption == 1) {
                            System.out.println(manageAccountMenu);
                            int manageAccountOption = scanner.nextInt();
                            scanner.nextLine();
                            if (manageAccountOption == 1) {
                                System.out.println(newUsernamePrompt);
                                String newUsername = scanner.nextLine();
                                System.out.println(newPasswordPrompt);
                                String newPassword = scanner.nextLine();
                                boolean changeFunction = false;
                                if (account.editAccount(account, newUsername, newPassword)) {
                                    System.out.println(accountEditedSuccessfullyPrompt);
                                    synchronized (OBJECT) {
                                        f2.updateFile(Account.getCreatedAccounts());
                                    }
                                }
                            }
                            if (manageAccountOption == 2) {
                                boolean deleted = account.deleteAccount(account);
                                if (deleted) {
                                    System.out.println(accountDeletedConfirmation);
                                    synchronized (OBJECT) {
                                        f2.updateFile(Account.getCreatedAccounts());
                                    }
                                }
                                loggedIn = false;
                                loggedInStudent = null;
                                loggedInTeacher = null;
                            }
                        } else if (loggedInOption == 2) {
                            int courseDecision = 0;
                            while (courseDecision != -1) {
                                synchronized (OBJECT) {
                                    System.out.println(selectCourse);
                                    Courses.printCourses();
                                    System.out.println(goBack);
                                }
                                courseDecision = scanner.nextInt();
                                scanner.nextLine();
                                if (courseDecision == 0) {
                                    if (loggedInStudent != null) {
                                        System.out.println(errorStudent);
                                    } else {
                                        System.out.println(enterNewCourse);
                                        String title = scanner.nextLine();
                                        synchronized(OBJECT) {
                                            loggedInTeacher.createCourse(new Courses(title));
                                            f1.encode(Courses.getCourses());
                                        }
                                    }
                                } else if (courseDecision > 0 && courseDecision <= Courses.getCourses().size()) {
                                    for (int i = 0; i < Courses.getCourses().size(); i++) {
                                        if (i == courseDecision - 1) {
                                            Courses currentCourse = Courses.getCourses().get(i);
                                            int forumDecision = 0;
                                            while (forumDecision != -1) {
                                                synchronized (OBJECT) {
                                                    String forumSelect = String.format(selectForum, currentCourse.getTitle());
                                                    System.out.println(forumSelect);
                                                    Courses.printForums(currentCourse);
                                                    System.out.println(goBack);
                                                }
                                                forumDecision = scanner.nextInt();
                                                scanner.nextLine();
                                                if (forumDecision == 0) {
                                                    if (loggedInStudent != null) {
                                                        System.out.println(errorStudent);
                                                    } else {
                                                        System.out.println(chooseFileImport);
                                                        int d = scanner.nextInt();
                                                        scanner.nextLine();
                                                        if (d == 1) {
                                                            System.out.println(enterNewForumTitle);
                                                            String forumTitle = scanner.nextLine();
                                                            System.out.println(enterNewForumPrompt);
                                                            String forumPrompt = scanner.nextLine();
                                                            loggedInTeacher.createForum(forumTitle, forumPrompt, currentCourse);
                                                            f1.encode(Courses.getCourses());
                                                        } else if (d == 2) {
                                                            System.out.println("Enter file path");
                                                            String path = scanner.nextLine();
                                                            currentCourse.createForumByFile(path);
                                                        }
                                                    }
                                                } else if (forumDecision > 0 && forumDecision <= currentCourse.getForums().size()) {
                                                    for (int j = 0; j < currentCourse.getForums().size(); j++) {
                                                        if (j == forumDecision - 1) {
                                                            DiscussionForums currentForum = currentCourse.getForums().get(j);
                                                            int postSelection = 0;
                                                            while (postSelection != -1) {
                                                                synchronized (OBJECT) {
                                                                    String postSelect = String.format(selectPosts, currentForum.getTitle(),
                                                                            currentForum.getPrompt());
                                                                    System.out.println(postSelect);
                                                                    currentForum.printPosts(currentForum);
                                                                    System.out.println(goBack);
                                                                }
                                                                postSelection = scanner.nextInt();
                                                                scanner.nextLine();
                                                                if (postSelection == 0) {
                                                                    System.out.println(chooseFileImport);
                                                                    int importD = scanner.nextInt();
                                                                    scanner.nextLine();
                                                                    if (importD == 1) {
                                                                        System.out.println(enterPostTitle);
                                                                        String postTitle = scanner.nextLine();
                                                                        System.out.println(enterPostContent);
                                                                        String content = scanner.nextLine();
                                                                        if (loggedInStudent != null) {
                                                                            loggedInStudent.createPost(currentForum, postTitle, content);
                                                                        } else {
                                                                            loggedInTeacher.createPost(currentForum, postTitle, content);
                                                                        }
                                                                    } else if (importD == 2) {
                                                                        System.out.println("Enter file path");
                                                                        String path = scanner.nextLine();
                                                                        synchronized (OBJECT) {
                                                                            currentForum.fileCreatePost(path, account.getUsername());
                                                                        }
                                                                    }
                                                                    synchronized (OBJECT) {
                                                                        f1.encode(Courses.getCourses());
                                                                    }
                                                                } else if (postSelection > 0 && postSelection <= currentForum.getPosts().size()) {
                                                                    for (int k = 0; k < currentForum.getPosts().size(); k++) {
                                                                        if (k == postSelection - 1) {
                                                                            DiscussionPosts currentPost = currentForum.getPosts().get(k);
                                                                            int reply = 0;
                                                                            while (reply != -1) {
                                                                                synchronized (OBJECT) {
                                                                                    System.out.println(currentPost.displayPost());
                                                                                    System.out.println(replyDecision);
                                                                                    System.out.println(goBack);
                                                                                }
                                                                                reply = scanner.nextInt();
                                                                                scanner.nextLine();
                                                                                if (reply == 0) {
                                                                                    System.out.println(enterReply);
                                                                                    String replyContent = scanner.nextLine();
                                                                                    synchronized (OBJECT) {
                                                                                        if (loggedInStudent != null) {
                                                                                            loggedInStudent.reply(currentPost, new Replies(replyContent,
                                                                                                    loggedInStudent.getUsername()));
                                                                                        } else {
                                                                                            loggedInTeacher.reply(currentPost, new Replies(replyContent,
                                                                                                    loggedInTeacher.getUsername()));
                                                                                        }
                                                                                        f1.encode(Courses.getCourses());
                                                                                    }
                                                                                } else if (reply == 1) {
                                                                                    if (loggedInStudent == null || account.getUsername().equals(currentPost.getPoster())) {
                                                                                        System.out.println(enterPostTitle);
                                                                                        String newT = scanner.nextLine();
                                                                                        System.out.println(enterPostContent);
                                                                                        String newC = scanner.nextLine();
                                                                                        currentPost.editsPost(newT, newC);
                                                                                        System.out.println("Post edited!");
                                                                                        synchronized (OBJECT) {
                                                                                            f1.encode(Courses.getCourses());
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println(editFailure);
                                                                                    }
                                                                                } else if (reply == 2) {
                                                                                    if (loggedInStudent == null || account.getUsername().equals(currentPost.getPoster())) {
                                                                                        synchronized (OBJECT) {
                                                                                            currentForum.deletePost(currentPost.getPostID());
                                                                                            f1.encode(Courses.getCourses());
                                                                                            reply = -1;
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println(editFailure);
                                                                                    }
                                                                                } else if (reply == 3) {
                                                                                    synchronized (OBJECT) {
                                                                                        account.upVote(currentPost);
                                                                                        for (Account acc : Account.getCreatedAccounts()) {
                                                                                            if (acc.getUsername().equals(account.getUsername())) {
                                                                                                Account.getCreatedAccounts().set(Account.getCreatedAccounts().indexOf(acc), account);
                                                                                            }
                                                                                        }
                                                                                        f1.encode(Courses.getCourses());
                                                                                        f2.updateFile(Account.getCreatedAccounts());
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else if (forumDecision == -2) {
                                                    if (loggedInStudent != null) {
                                                        System.out.println(errorStudent);
                                                    } else {
                                                        System.out.println(editForum);
                                                        String editT = scanner.nextLine();
                                                        System.out.println("Enter new content");
                                                        String newC = scanner.nextLine();
                                                        synchronized (OBJECT) {
                                                            currentCourse.editForum(editT, newC);
                                                            f1.encode(Courses.getCourses());
                                                        }
                                                    }
                                                } else if (forumDecision == -3) {
                                                    if (loggedInStudent != null) {
                                                        System.out.println(errorStudent);
                                                    } else {
                                                        synchronized (OBJECT) {
                                                            System.out.println(deleteForum);
                                                            String editT = scanner.nextLine();
                                                            currentCourse.deleteForum(editT);
                                                            f1.encode(Courses.getCourses());
                                                            forumDecision = -1;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (loggedInOption == 3) {
                            synchronized (OBJECT) {
                                loggedInStudent = null;
                                loggedInTeacher = null;
                                loggedIn = false;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                synchronized (OBJECT) {
                    System.out.printf("%s\n", e);
                    System.out.println(generalError);
                    loggedIn = false;
                    loggedInStudent = null;
                    loggedInTeacher = null;
                    scanner.nextLine();
                }
            }
        }
    }
}