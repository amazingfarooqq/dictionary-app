import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class DictionaryGUI {
    private Dictionary dictionary;
    private JFrame frame;
    private JTextField wordField;
    private JTextArea meaningArea;
    private JTextArea displayArea;

    public DictionaryGUI() {
        dictionary = new Dictionary();
        frame = new JFrame("Dictionary Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLUE);
        JLabel headerLabel = new JLabel("Dictionary Application", JLabel.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Panel for adding and updating words
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel wordLabel = new JLabel("Word:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(wordLabel, gbc);

        wordField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(wordField, gbc);

        JLabel meaningLabel = new JLabel("Meaning:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(meaningLabel, gbc);

        meaningArea = new JTextArea(4, 20);
        meaningArea.setLineWrap(true);
        meaningArea.setWrapStyleWord(true);
        JScrollPane meaningScroll = new JScrollPane(meaningArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(meaningScroll, gbc);

        JButton addButton = new JButton("Add Word");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(addButton, gbc);

        JButton updateButton = new JButton("Update Word");
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(updateButton, gbc);

        // Panel for displaying results
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(Color.LIGHT_GRAY);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton lookupButton = new JButton("Lookup Word");
        JButton displayButton = new JButton("Display All Words");
        buttonPanel.add(lookupButton);
        buttonPanel.add(displayButton);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.WEST);
        frame.add(resultPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordField.getText();
                String meaning = meaningArea.getText();
                dictionary.addWord(word, meaning);
                JOptionPane.showMessageDialog(frame, "Word added successfully!");
                clearFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordField.getText();
                String newMeaning = meaningArea.getText();
                dictionary.updateWord(word, newMeaning);
                JOptionPane.showMessageDialog(frame, "Word updated successfully!");
                clearFields();
            }
        });

        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordField.getText();
                String meaning = dictionary.lookupWord(word);
                displayArea.setText("Meaning: " + meaning);
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                Collection<WordEntry> entries = dictionary.getEntries();
                if (entries.isEmpty()) {
                    displayArea.setText("Dictionary is empty.");
                } else {
                    for (WordEntry entry : entries) {
                        displayArea.append(entry.toString() + "\n");
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    private void clearFields() {
        wordField.setText("");
        meaningArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DictionaryGUI();
            }
        });
    }
}
