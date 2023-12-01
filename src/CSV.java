import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    public void tableCsv(DefaultTableModel tableModel, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) { // Skapar BufferedReader för att läsa CSV-filen.
            String line;
            List<String[]> data = new ArrayList<>(); // Lista för att lagra rader från CSV-filen.

            while ((line = reader.readLine()) != null) { // Läser varje rad i filen tills ingen rad finns kvar.
                data.add(line.split(",")); // delar upp raden vid komman
            }

            // Använder SwingUtilities.invokeLater för att den ska köras i rätt ordning
            SwingUtilities.invokeLater(() -> {
                tableModel.setRowCount(0); // Nollställer antalet rader i modellen för att förbereda för ny data.
                tableModel.setColumnIdentifiers(data.get(0)); // Använder den första raden i 'data' som kolumnrubriker.

                // Skriver ut datan i raderna under första coloumn
                for (int i = 1; i < data.size(); i++) {
                    tableModel.addRow(data.get(i));
                }
            });
        } catch (Exception e) {
            e.printStackTrace(); // Fångar om det blir fel och skriver ut felet.
        }
    }
}
