/*
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class RunLocalTest {

    private final PrintStream originalOutput = System.out;
    private final InputStream originalSysin = System.in;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayInputStream testIn;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayOutputStream testOut;

    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreInputAndOutput() {
        System.setIn(originalSysin);
        System.setOut(originalOutput);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @SuppressWarnings("SameParameterValue")
    private void receiveInput(String str) {
        testIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(testIn);
    }

    @Test(timeout = 1000)
    public void testTeacherRegistration() throws IOException {
        Date date = new Date();
        String input = "2" + System.lineSeparator() +
                "notAPedo69" + System.lineSeparator() +
                "password" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "notAPedo69" + System.lineSeparator() +
                "password" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "My Course" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "My Forum" + System.lineSeparator() +
                "My Forum Prompt" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "My Post" + System.lineSeparator() +
                "Post Content" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "Reply" + System.lineSeparator() +
                "-1" + System.lineSeparator() +
                "-1" + System.lineSeparator() +
                "-1" + System.lineSeparator() +
                "-1" + System.lineSeparator() +
                "3" + System.lineSeparator() +
                "3" + System.lineSeparator();
        String expectedOutput = "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Create the username:" + System.lineSeparator() + "Create the password:" + System.lineSeparator() +
                "Select one of the following:" + System.lineSeparator() + "1. Teacher" + System.lineSeparator() + "2." +
                " Student" + System.lineSeparator() +
                "Congratulations! Your account has been created." + System.lineSeparator() +

                "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Select one of the following:\n1. Teacher\n2. Student\n" +
                "Please enter the username:\nPlease enter the password:\n" +
                "Currently logged in as: notAPedo69\n" +
                "1. Manage my account\n" +
                "2. Select the course\n" +
                "3. Log Out\n" +
                "Select a Course:\n" +
                "0: Create a new Course\n" +
                "-1: Go Back\n" +
                "Please write the title of this new course\n" +
                "Select a Course:\n" +
                "0: Create a new Course\n" +
                "1: My Course\n" +
                "Course: My Course\n" +
                "Select a Forum:\n" +
                "0: Create a new Forum\n" +
                "-1: Go Back\n" +
                "Please write the title of this new forum\n" +
                "Forum created\n" +
                "Course: My Course\n" +
                "Select a Forum:\n" +
                "0: Create a new Forum\n" +
                "1: My Forum\n" +
                "-1: Go Back\n" +
                "Discussion Forum: My Forum\n" +
                "\n" +
                "My Forum Prompt\n" +
                "\n" +
                "Choose a post to reply to or create a new post:\n" +
                "0: New Post\n" +
                "-1: Go Back\n" +
                "Discussion Forum: My Forum\n" +
                "\n" +
                "My Forum Prompt\n" +
                "\n" +
                "Choose a post to reply to or create a new post:\n" +
                "0: New Post\n" +
                "1: My Post\n" +
                "-1: Go Back\n" +
                "Post by: notAPedo69\n" +
                "Created on:" + date.toString() +
                "\nPost Content\n" +
                "Replies: \n" +
                "No Replies Yet\n" +
                "0: Write a reply\n" +
                "-1: Go Back\n" +
                "Write your reply:\n" +
                "Post by: notAPedo69\n" +
                "Created on: " + date.toString() +
                "\nPost Content\n" +
                "Replies: \n" +
                "Reply 1:\n" +
                "posted by: notAPedo69\n" +
                "created on: " + date.toString() +
                "\nReply\n" +
                "Discussion Forum: My Forum\n" +
                "\n" +
                "My Forum Prompt\n" +
                "\n" +
                "Choose a post to reply to or create a new post:\n" +
                "0: New Post\n" +
                "1: My Post\n" +
                "-1: Go Back\n" +
                "Course: My Course\n" +
                "Select a Forum:\n" +
                "0: Create a new Forum\n" +
                "1: My Forum\n" +
                "-1: Go Back\n" +
                "Currently logged in as: notAPedo69\n" +
                "1. Manage my account\n" +
                "2. Select the course\n" +
                "3. Log Out\n" +
                "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Thank you for choosing our Discussion Board, goodbye.\n";
        receiveInput(input);
        Discussion.main(new String[0]);

        String actual = getOutput();
        actual = actual.replace("\r\n", "\n");
        Assert.assertEquals("Verify that the output matches!", expectedOutput, actual);
    }

    @Test(timeout = 1000)
    public void testAccountAlreadyExists() throws IOException {
        String input =
                "2" + System.lineSeparator() +
                        "notAPedo69" + System.lineSeparator() +
                        "password" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "notAPedo69" + System.lineSeparator() +
                        "password2" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "3" + System.lineSeparator();
        String expectedOutput = "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Create the username:" + System.lineSeparator() + "Create the password:" + System.lineSeparator() +
                "Select one of the following:" + System.lineSeparator() + "1. Teacher" + System.lineSeparator() + "2." +
                " Student" + System.lineSeparator() +
                "Congratulations! Your account has been created." + System.lineSeparator() +
                "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Create the username:" + System.lineSeparator() + "Create the password:" + System.lineSeparator() +
                "Select one of the following:" + System.lineSeparator() + "1. Teacher" + System.lineSeparator() + "2." +
                " Student" + System.lineSeparator() +
                "javax.security.auth.login.AccountException: An account with this username already exists!\n" +
                "Welcome to the Discussion Board!\n" +
                "Please choose the option:\n" +
                "1. Log in\n" +
                "2. Register\n" +
                "3. Quit\n" +
                "Thank you for choosing our Discussion Board, goodbye.\n";

        receiveInput(input);
        Discussion.main(new String[0]);

        String actual = getOutput();
        actual = actual.replace("\r\n", "\n");
        Assert.assertEquals("Verify that the output matches!", expectedOutput, actual);
    }

    @Test(timeout = 1000)
    public void logInFailure() throws IOException {
        String input =
                "1" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
                        "asdf" + System.lineSeparator() +
                        "asdf" + System.lineSeparator() +
                        "3" + System.lineSeparator();
        String expectedOutput = "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" + System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Select one of the following:\n1. Teacher\n2. Student\n" +
                "Please enter the username:\nPlease enter the password:\n" +
                "Error. Cannot log in. Please ensure your username and password is correct," +
                " and you selected the right role.\n" +
                "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Thank you for choosing our Discussion Board, goodbye.\n";
        receiveInput(input);
        Discussion.main(new String[0]);

        String actual = getOutput();
        actual = actual.replace("\r\n", "\n");
        Assert.assertEquals("Verify that the output matches!", expectedOutput, actual);
    }

    @Test(timeout = 1000)
    public void testEditandDelete() throws IOException {
        String input = "2" + System.lineSeparator() +
                "notAPedo69" + System.lineSeparator() +
                "password" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "notAPedo69" + System.lineSeparator() +
                "password" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "defAPedo69" + System.lineSeparator() +
                "wordpass" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "defAPedo69" + System.lineSeparator() +
                "wordpass" + System.lineSeparator() +
                "3" + System.lineSeparator();
        String expectedOutput = "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Create the username:" + System.lineSeparator() + "Create the password:" + System.lineSeparator() +
                "Select one of the following:" + System.lineSeparator() + "1. Teacher" + System.lineSeparator() + "2." +
                " Student" + System.lineSeparator() +
                "Congratulations! Your account has been created." + System.lineSeparator() +

                "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Select one of the following:\n1. Teacher\n2. Student\n" +
                "Please enter the username:\nPlease enter the password:\n" +
                "Currently logged in as: notAPedo69\n" +
                "1. Manage my account\n" +
                "2. Select the course\n" +
                "3. Log Out\n" +
                "1. Edit account\n" +
                "2. Delete account\n" +
                "Please type your new username\n" +
                "Please type your new password\n" +
                "Currently logged in as: defAPedo69\n" +
                "1. Manage my account\n" +
                "2. Select the course\n" +
                "3. Log Out\n" +
                "1. Edit account\n" +
                "2. Delete account\n" +
                "Welcome to the Discussion Board!\n" +
                "Please choose the option:\n" +
                "1. Log in\n" +
                "2. Register\n" +
                "3. Quit" +
                "Select one of the following:\n1. Teacher\n2. Student\n" +
                "Please enter the username:\nPlease enter the password:\n" +
                "Error. Cannot log in. Please ensure your username and password is correct," +
                " and you selected the right role.\n" +
                "Welcome to the Discussion Board!" + System.lineSeparator() +
                "Please choose the option:" + System.lineSeparator() +
                "1. Log in" +  System.lineSeparator() +
                "2. Register" +  System.lineSeparator() +
                "3. Quit" + System.lineSeparator() +
                "Thank you for choosing our Discussion Board, goodbye.\n";
        receiveInput(input);
        Discussion.main(new String[0]);

        String actual = getOutput();
        actual = actual.replace("\r\n", "\n");
        Assert.assertEquals("Verify that the output matches!", expectedOutput, actual);
    }
}

 */