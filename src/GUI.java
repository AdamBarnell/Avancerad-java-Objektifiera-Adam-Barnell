import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame {
    private JButton openCsvButton;
    private JButton openJsonButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private FileExplorer fileExplorer;
    private JPanel topPanel;

    public GUI() {
        setTitle("CSV/JSON");
        setSize(1080, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        topPanel = new JPanel(new FlowLayout());
        openCsvButton = new JButton("Open CSV File");
        openJsonButton = new JButton("Open JSON File");
        topPanel.add(openCsvButton);
        topPanel.add(openJsonButton);

        fileExplorer = new FileExplorer();
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true); // Aktiverar automatisk sortering för tabellen.

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        openCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selectedFile = fileExplorer.chooseFile();
                if (selectedFile != null) {
                    new CSV().tableCsv(tableModel, selectedFile.getPath());
                }
            }
        });
        openJsonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selectedFile = fileExplorer.chooseFile();
                if (selectedFile != null) {
                    new JSON().tableJson(tableModel, selectedFile.getPath());
                }
            }
        });

        setVisible(true);
    }
}
