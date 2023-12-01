import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileExplorer {
    private JFileChooser fileChooser;

    public FileExplorer() {

        fileChooser = new JFileChooser(new File("src"));

        // Tar bara emot CSV och JSON, kan lägga till fler filtyper i framtiden
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV & JSON Files", "csv", "json");

        fileChooser.setFileFilter(filter);
    }

    public File chooseFile() {
        // Dialogruta
        int returnValue = fileChooser.showOpenDialog(null);

        // Returnerar filen som användaren valt
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        // Returnera null om användaren avbryter valet
        return null;
    }
}
