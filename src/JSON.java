import com.eclipsesource.json.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.util.*;

public class JSON {

    public void tableJson(DefaultTableModel tableModel, String filePath) {
        List<String> names = new ArrayList<>(); // Array för att lagra kolumnnamn.
        List<List<String>> rows = new ArrayList<>(); // Array för att lagra data för varje rad.

        try (FileReader reader = new FileReader(filePath)) {
            JsonArray jsonArray = Json.parse(reader).asArray(); // Konverterar innehållet i filen till en JsonArray.

            JsonObject columnObject = jsonArray.get(0).asObject(); // Använder det första objektet för att få kolumnnamn.
            for (JsonObject.Member header : columnObject) {
                // Lägger till headern med de värdena den fått ut
                names.add(header.getValue().asString());
            }

            // Skaffar data från Jsonfilen
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).asObject();
                List<String> row = new ArrayList<>(); // Lista för att lagra data


                for (String key : columnObject.names()) {
                    JsonValue value = jsonObject.get(key); // Hämtar värdet för varje nyckel från objektet.
                    row.add(value != null ? value.asString() : "N/A"); // Lägger till värdet i raden, eller "N/A"
                }
                rows.add(row);
            }

            // Använder SwingUtilities.invokeLater för att den ska köras i rätt ordning
            SwingUtilities.invokeLater(() -> {
                tableModel.setRowCount(0);
                tableModel.setColumnIdentifiers(names.toArray()); // Anger kolumnidentifikatorer (kolumnnamn).
                rows.forEach(row -> tableModel.addRow(row.toArray())); // Lägger till varje rad i modellen.
            });
        } catch (Exception e) {
            e.printStackTrace(); // Fångar om det blir fel och skriver ut felet.
        }
    }
}
