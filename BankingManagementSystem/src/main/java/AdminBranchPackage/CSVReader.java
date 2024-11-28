/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminBranchPackage;

/**
 *
 * @author Jayjay
 */
import java.io.*;
import java.util.*;

public class CSVReader {
    // List to store the CSV data (each row will be a list of strings)
    private List<List<String>> csvData = new ArrayList<>();

    // Method to read CSV file and store it into csvData
    public void readCSV(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            
            // Read each line from CSV and split by commas
            while ((line = br.readLine()) != null) {
                List<String> row = Arrays.asList(line.split(","));
                csvData.add(row);
            }
            
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    // Method to display a specific row based on the user's choice
    public void displayRow(int rowNumber) {
        if (rowNumber >= 0 && rowNumber < csvData.size()) {
            List<String> row = csvData.get(rowNumber + 1);
            
            String name = row.get(3);
            String birthdate = row.get(4);
            String phoneNumber = row.get(5);
            String address = row.get(6);
//            gui.updateLabel(name, birthdate, phoneNumber, address);
            
            System.out.println(String.join(", ", row));
        } else {
            System.out.println("Invalid row number.");
        }
    }
}
