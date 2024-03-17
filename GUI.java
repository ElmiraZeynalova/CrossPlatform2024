import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JTextField classNameField;
    private JTextArea resultTextArea;

    public GUI() {
        setTitle("Class Analyzer GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel classNameLabel = new JLabel("Enter class name:");
        classNameField = new JTextField(20);
        JButton analyzeButton = new JButton("Analyze");
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyzeClass();
            }
        });
        inputPanel.add(classNameLabel);
        inputPanel.add(classNameField);
        inputPanel.add(analyzeButton);
        add(inputPanel, BorderLayout.NORTH);

        // Text area for result
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void analyzeClass() {
        String className = classNameField.getText();
        if (!className.isEmpty()) {
            try {
                String result = ClassAnalyzer.analyzeClass(className);
                resultTextArea.setText(result);
            } catch (ClassNotFoundException e) {
                resultTextArea.setText("Class not found: " + className);
            }
        } else {
            resultTextArea.setText("Please enter a class name.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}
