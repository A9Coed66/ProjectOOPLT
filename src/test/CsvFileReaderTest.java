package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvFileReaderTest {
    public static void main(String[] args) {
        String filePath = "data/csv/blog_new.csv"; // Replace with the actual file path
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skipping the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] data = line.split(cvsSplitBy);

                // Assigning each field to a variable
                String author = data[0].replaceAll("^\"|\"$", "");
                String title = data[1].replaceAll("^\"|\"$", "");
                String link = data[2].replaceAll("^\"|\"$", "");
                String image = data[3].replaceAll("^\"|\"$", "");
                String date = data[4].replaceAll("^\"|\"$", "");
                String tags = data[5].replaceAll("^\"|\"$", "");
                String contents = data[6].replaceAll("^\"|\"$", "");
                String price = data[7].replaceAll("^\"|\"$", "");
                String vote = data[8].replaceAll("^\"|\"$", "");
                String comment = data[9].replaceAll("^\"|\"$", "");

                // Processing or printing out the variables
                System.out.println("Author: " + author);
                System.out.println("Title: " + title);
                // ... and so on for other variables
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}