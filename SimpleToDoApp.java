import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleToDoApp {

    public SimpleToDoApp() {
    
        JFrame frame = new JFrame("Simple To-Do List");
        frame.setSize(400, 500);
        frame.setLayout(null); // Use absolute layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField taskInput = new JTextField();
        taskInput.setBounds(50, 30, 200, 30);
        frame.add(taskInput);
        JButton addButton = new JButton("Add Task");
        addButton.setBounds(260, 30, 100, 30);
        frame.add(addButton);
        JButton deleteButton = new JButton("Delete Task");
        deleteButton.setBounds(50, 70, 310, 30);
        frame.add(deleteButton);
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(50, 110, 310, 330);
        frame.add(scrollPane);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskText = taskInput.getText().trim();
                if (!taskText.isEmpty()) {
                    JCheckBox taskCheckBox = new JCheckBox(taskText);
                    taskPanel.add(taskCheckBox);
                    taskPanel.revalidate(); // Refresh panel
                    taskInput.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter a task!");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component[] components = taskPanel.getComponents();
                for (int i = components.length - 1; i >= 0; i--) {
                    if (components[i] instanceof JCheckBox) {
                        JCheckBox cb = (JCheckBox) components[i];
                        if (cb.isSelected()) {
                            taskPanel.remove(cb);
                        }
                    }
                }
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleToDoApp());
    }
}
