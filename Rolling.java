/*
 * Author: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 1
 * Due Date: 18 September 2020
 * Description: Animates a square rolling across the screen.
 * OS: MacOS
 * Notes: Formatting was based on the line and character spacing of the Mac Terminal app (not the IntelliJ terminal)
 */

import java.util.Scanner; // for user input
import java.io.IOException; // to handle exceptions

public class Rolling {

    /**
     * Displays a flat square.
     * @param size The side length of the square.
     * @param iteration The current rotation number, used to calculate space before square.
     * @param character The character used to draw the square.
     */
    public static void flatSquare(int size, int iteration, char character)
    {
        String row = "", // A single row of the square
                indent = "", // The spaces before each row to move the square to the right
                spacing = "   ";

        // Create a single row of the square
        for (int i = 0; i < size; i++)
            row += character + spacing; // Add proper spacing between characters

        row += '\n'; // add an extra line of space between each row

        // Calculate the space before the square
        for (int i = 0; i < (size / 2 * (iteration * (spacing.length() + 1))); i++)
            indent += " ";

        // Draw the square
        for (int i = 0; i < size; i++)
            System.out.println(indent + row);
    }

    /**
     * Displays a square on one of its points.
     * @param size The side length of the square.
     * @param iteration The current rotation number, used to calculate space before square.
     * @param character The character used to draw the square.
     */
    public static void squareOnPoint(int size, int iteration, char character)
    {
        String row = "", // Will hold the current row size
                bottom = "", // bottom half of the square
                top = "", // top half of square
                indent = "", // space before square
                maxSpace = "                                        ", // Maximum space before each row to align characters. Will use substring to get correct amount
                spacing = "   "; // space between characters

        // Calculate the indent
        for (int i = 0; i < (size / 2 * (iteration * (spacing.length() + 1))); i++)
            indent += " ";

        // Create the square
        for (int i = 1; i < size; i++)
        {
            row += character + spacing; // Add a character to the row template
            // The space.substring() calls in the below two lines eliminates the need for a for loop to calculate spacing. Could be replaced with some sort of character repetition function.
            top += indent + maxSpace.substring(0, (spacing.length() + 1)/2 * (size - i)) + row + '\n'; // Add a row to the top half of the square
            bottom = indent + maxSpace.substring(0, (spacing.length() + 1)/2 * (size - i)) + row + '\n' + bottom; // Add a row to the bottom half of the square
        }

        System.out.print(top + indent + row + character + '\n' + bottom); // Print the square
    }

    /**
     * Gets user input for square size and number of rotations, then animates the square.
     * @param args Commandline args
     * @throws IOException if a file I/O exception occurs
     * @throws InterruptedException if function is interrupted
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
        Scanner keyboard = new Scanner(System.in); // for user input
        int size, // side length of square
                rotations, // total number of rotations
                option; // user's choice from the character menu
        char character; // character used in the square

        // This loop repeatedly prompts the user to enter a side length until the user enters an acceptable size
        do {
            System.out.println("What do you want the side length of the square to be? ");
            size = keyboard.nextInt();
        } while(size < 2 || size > 10);

        // ask user for number of 45 degree rotations
        System.out.println("How many 45 degree rotations would you like the square to undergo? ");
        rotations = keyboard.nextInt();

        // ask user which character they would like to use for the square
        System.out.println("Which character would you like the square to be drawn with?");
        System.out.println("1. #\n2. @\n3. $\n4. •\n5. *");
        // repeat the prompt if they gave an invalid option
        do {
            System.out.println("Enter 1-5: ");
            option = keyboard.nextInt();
        } while (option < 1 || option > 5);

        // set the character variable based on the menu option chosen by the user
        switch(option)
        {
            case 1:
                character = '#';
                break;
            case 2:
                character = '@';
                break;
            case 3:
                character = '$';
                break;
            case 4:
                character = '•';
                break;
            case 5:
            default:
                character = '*';
                break;
        }

        // animate the square
        for(int i = 0; i < rotations; i++) {
            // wait 250 ms
            Thread.sleep(250);

            // clear the window
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // display the next rotation
            if (i % 2 == 0)
                flatSquare(size, i, character);
            else
                squareOnPoint(size, i, character);
        }
    }
}
