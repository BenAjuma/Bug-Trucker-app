
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Bugs {
    private int id;
    private String title;
    private String description;
    private String status;

    public Bugs(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Status: " + status;
    }
}

public class BugTrackerIn {
    private static ArrayList<Bug> bugList = new ArrayList<>();
    private static int bugIdCounter = 1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bug Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        DefaultListModel<String> bugListModel = new DefaultListModel<>();
        JList<String> bugJList = new JList<>(bugListModel);
        JScrollPane scrollPane = new JScrollPane(bugJList);

        JButton addButton = new JButton("Add Bug");
        JButton updateButton = new JButton("Update Bug");
        JButton deleteButton = new JButton("Delete Bug");
        JButton viewDetailsButton = new JButton("View Details");

        // Layout
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewDetailsButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "Enter Bug Title:");
            String description = JOptionPane.showInputDialog(frame, "Enter Bug Description:");
            if (title != null && description != null) {
                Bug bug = new Bug(bugIdCounter++, title, description, "Open");
                bugList.add(bug);
                bugListModel.addElement(bug.toString());
            }
        });

        updateButton.addActionListener(e -> {
            int selectedIndex = bugJList.getSelectedIndex();
            if (selectedIndex != -1) {
                Bug selectedBug = bugList.get(selectedIndex);
                String status = JOptionPane.showInputDialog(frame, "Enter new status (Open, In Progress, Resolved):", selectedBug.getStatus());
                if (status != null) {
                    selectedBug.setStatus(status);
                    bugListModel.set(selectedIndex, selectedBug.toString());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No bug selected!");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = bugJList.getSelectedIndex();
            if (selectedIndex != -1) {
                bugList.remove(selectedIndex);
                bugListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "No bug selected!");
            }
        });

        viewDetailsButton.addActionListener(e -> {
            int selectedIndex = bugJList.getSelectedIndex();
            if (selectedIndex != -1) {
                Bug selectedBug = bugList.get(selectedIndex);
                JOptionPane.showMessageDialog(frame, "Details:\n" + selectedBug.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "No bug selected!");
            }
        });

        frame.setVisible(true);
    }
}
