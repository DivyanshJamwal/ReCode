import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class rcmain extends JFrame implements ActionListener {

    private JTextArea chatHistory;
    private JTextField userInput;
    private JButton sendButton;
    private JLabel background; // Add a JLabel for the background

    public rcmain() {
        super("Simple Chatbot");
        setLayout(new BorderLayout());

        // Set up the background image
        try {
            URL imageURL = new URL("file:/path/to/your/image.jpg"); // Replace with your image path
            ImageIcon icon = new ImageIcon(imageURL);
            background = new JLabel(icon);
            background.setLayout(new BorderLayout());
            add(background);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Chat history display
        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatHistory);
        background.add(scrollPane, BorderLayout.CENTER);

        // Add a default opening message from the bot with a delay
        SwingUtilities.invokeLater(() -> {
            String botOpeningMessage = "Hello! I'm a simple chatbot. Feel free to send a message.";
            chatHistory.append("Bot: " + botOpeningMessage + "\n");

            // Small delay between the bot's opening message and the user's input field
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Set the user input field focused
            userInput.requestFocusInWindow();
        });

        // User input field
        userInput = new JTextField();
        background.add(userInput, BorderLayout.SOUTH);

        // Send button
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        background.add(sendButton, BorderLayout.EAST);

        // Set window size and visibility
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            String userMessage = userInput.getText().trim();
            chatHistory.append("You: " + userMessage + "\n");
            userInput.setText("");

            String botResponse = "This is a default response from the bot.";
            chatHistory.append("Bot: " + botResponse + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new rcmain();
        });
    }
}
