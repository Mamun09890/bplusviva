package dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dictionary {
    public static void main(String[] args) {
        BPlusTree bpt = new BPlusTree(4); // Order can be adjusted

        // Determine the absolute path of Dictionary.txt
        Path path = Paths.get("src", "dictionary", "Dictionary.txt").toAbsolutePath();
        System.out.println("Looking for file at: " + path);

        // Read dictionary file
        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String word = parts[0].trim().toLowerCase(); // Ensure consistency
                    String meaning = parts[1].trim();
                    bpt.insert(word, meaning);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // User input for searching the dictionary
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a word to find its meaning (or type 'exit' to quit): ");
            String word = scanner.nextLine().trim().toLowerCase(); // Ensure consistency
            if (word.equalsIgnoreCase("exit")) {
                break;
            }
            String meaning = bpt.search(word);
            if (meaning != null) {
                System.out.println("Meaning: " + meaning);
            } else {
                System.out.println("Word not found in the dictionary.");
            }
        }
        scanner.close();
    }
}
