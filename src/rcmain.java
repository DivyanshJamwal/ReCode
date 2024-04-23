import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.*;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class rcmain {

    private JTextField userMessage;
    private JTextArea chatHistory;
    private JButton sendMessage;
    private Timer timer;
    private JFrame loginFrame;
    private JFrame f;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private boolean loginSuccessful;
    private JCheckBox showPasswordCheckBox;

    public rcmain() {
        loginFrame = new JFrame("ReCode");
        loginFrame.setBounds(100, 100, 450, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 153, 102));
        headerPanel.setPreferredSize(new Dimension(450, 50));
        headerPanel.setLayout(new GridBagLayout());
        loginFrame.getContentPane().add(headerPanel, BorderLayout.NORTH);

        JLabel headerLabel = new JLabel("Login Page", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerPanel.add(headerLabel);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.white);
        formPanel.setLayout(new GridBagLayout());
        loginFrame.getContentPane().add(formPanel, BorderLayout.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 153, 102)),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)));
        usernameField.setBackground(Color.white);
        usernameField.setForeground(new Color(102, 102, 102));
        usernameField.setText("Username");
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Username");
                    usernameField.setForeground(new Color(102, 102, 102));
                }
            }
        });
        formPanel.add(usernameField, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 10, 10), 0, 0));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(passwordLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 153, 102)),
                BorderFactory.createEmptyBorder(7, 7, 7, 7)));
        passwordField.setBackground(Color.white);
        passwordField.setForeground(new Color(102, 102, 102));
        passwordField.setText("Password");
        passwordField.addFocusListener(new FocusListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField.getText().equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);
                }
            }

            @SuppressWarnings("deprecation")
            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().isEmpty()) {
                    passwordField.setText("Password");
                    passwordField.setForeground(new Color(102, 102, 102));
                }
            }
        });
        formPanel.add(passwordField, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 10, 10), 0, 0));

        showPasswordCheckBox = new JCheckBox("Show password");
        showPasswordCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        showPasswordCheckBox.setBackground(Color.white);
        showPasswordCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });
        formPanel.add(showPasswordCheckBox, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 10, 10), 0, 0));

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 153, 102));
        loginButton.setForeground(Color.white);
        loginButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 153, 102)));
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("admin") && password.equals("root")) {
                loginSuccessful = true;
                JOptionPane.showMessageDialog(loginFrame, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(usernameField, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
            if (loginSuccessful) {
                loginFrame.dispose();
                createChatFrame();
            }
        });
        formPanel.add(loginButton, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 10, 10), 0, 0));
        loginFrame.setSize(600, 450);
        loginFrame.setVisible(true);
    }

    

        public void createChatFrame() {
        JFrame f = new JFrame("ReCode");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        
        // Add a header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#009688")); // Set the header background color to WhatsApp green
        headerPanel.setPreferredSize(new Dimension(800, 50)); // Set the header height
        f.add(headerPanel, BorderLayout.NORTH);
        
        JLabel headerLabel = new JLabel("ReCode ChatBot", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerPanel.add(headerLabel);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#009688"));
        f.add(panel, BorderLayout.SOUTH);

        String[] options = {"new chat", "darkmode", "logout", "lightmode"};
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(options);
        JComboBox<String> dropdownMenu = new JComboBox<>(comboBoxModel);

        dropdownMenu.setBounds(5, 5, 150, 25);

            dropdownMenu.addActionListener(e -> {
            String selectedOption = (String) dropdownMenu.getSelectedItem();

            if (selectedOption.equals("new chat")) {
                // Clear chat history and reset user input field
                chatHistory.setText("");
                userMessage.setText("");
                
            } else if (selectedOption.equals("darkmode")) {
                    chatHistory.setBackground(Color.BLACK);
                    chatHistory.setForeground(Color.WHITE);
            } else if (selectedOption.equals("lightmode")) {
                    chatHistory.setBackground(Color.white);
                    chatHistory.setForeground(Color.black);
                    
            } else if (selectedOption.equals("logout")) {
                // Log out and display the login frame
                loginFrame.setVisible(true);
                f.dispose();
            }
        });

        f.getContentPane().add(dropdownMenu, BorderLayout.NORTH);

        userMessage = new JTextField(" Type your message here...", 37);
        userMessage.setFont(userMessage.getFont().deriveFont(Font.BOLD, 14f));
        userMessage.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        userMessage.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userMessage.getText().equals(" Type your message here...")) {
                    userMessage.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userMessage.getText().isEmpty()) {
                    userMessage.setText(" Type your message here...");
                }
            }
        });
        sendMessage = new JButton("Send");
        sendMessage.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));

        panel.add(userMessage);
        panel.add(sendMessage);

        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        chatHistory.setBackground(Color.white); // Set the background color of the text area to cyan
        JScrollPane scrollPane = new JScrollPane(chatHistory);
        f.add(scrollPane, BorderLayout.CENTER);

        sendMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = userMessage.getText();
                userMessage.setText("");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                chatHistory.setFont(chatHistory.getFont().deriveFont(Font.BOLD, 14f));
                // chatHistory.setForeground(Color.black);
                chatHistory.append("  You: " + message + "\n");

                // Add chatbot's response here
                if (timer == null) {
                    timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String response = generateBotResponse(message);
                            chatHistory.setFont(chatHistory.getFont().deriveFont(Font.BOLD, 14f));
                            chatHistory.append("  Leo: " + response +"\n\n"); // Add a newline character after each bot message and append the timestamp
                            timer.stop();
                            timer = null;
                        }
                    });
                }
                timer.start();
            }
        });

        userMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do nothing
            }
        });
        f.setBounds(100, 100, 450, 300);
        f.setSize(600, 450);
        f.setVisible(true);
    }

    private String generateBotResponse(String userMessage){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String response = "";
        String trimmedUserMessage = userMessage.trim().toLowerCase();
    
        Map<String[], String[]> promptsToResponses = new HashMap<>();
        String[] callPrompts = {"hello", "hi", "hey"};
        String[] callResponses = {"Hi there!", "Hello!", "Hey!"};
        promptsToResponses.put(callPrompts, callResponses);

        String[] greetingsPrompts = {"morning", "Greetings", "noon", "evening","night"};
        String[] greetingsResponses = {"Greetings", "Nice to meet you", "Hey!"};
        promptsToResponses.put(greetingsPrompts, greetingsResponses);

        String[] howPrompts = {"how are", "whats up","wassup", "all good?"};
        String[] howResponses = {"I am fine, What about you?", "Up and Running", "Perfect! As always."};
        promptsToResponses.put(howPrompts, howResponses);

        String[] goodPrompts = {"feeling good", "fine", "healthy"};
        String[] goodResponses = {"Glad to hear", "That's amazing", "Cool"};
        promptsToResponses.put(goodPrompts, goodResponses);
    
        String[] goodbyePrompts = {"bye", "goodbye", "see you later", "meet you later", "c ya"};
        String[] goodbyeResponses = {"Goodbye!", "See you later!", "Take care!", "Have a nice day"};
        promptsToResponses.put(goodbyePrompts, goodbyeResponses);
    
        String[] helpPrompts = {"help", "support", "assistance"};
        String[] helpResponses = {"I'm here to help you with any questions you have.", "Feel free to ask me anything about our product or service.", "Sure, I'll do my best to assist you with any questions you have."};
        promptsToResponses.put(helpPrompts, helpResponses);
    
        Random random = new Random();
        for (Map.Entry<String[], String[]> entry : promptsToResponses.entrySet()) {
            String[] prompts = entry.getKey();
            for (String prompt : prompts) {
                if (trimmedUserMessage.contains(prompt)) {
                    int randomIndex = random.nextInt(entry.getValue().length);
                    response = entry.getValue()[randomIndex];
                    break;
                }
            }
        }
        
        if (trimmedUserMessage.contains("logout")) {
            // Log out and display the login frame
            loginFrame.setVisible(true);
            f.dispose();
        } else if (trimmedUserMessage.contains("new chat")) {
            response = "Select new chat from dropdown";
        } else if(trimmedUserMessage.contains("who")){
            if(trimmedUserMessage.contains("you")){
                response = "Myself Leo! A Chatbot made by ReCode Corporation";
            }
            else if(trimmedUserMessage.contains("I")){
                response = "You are Divyansh! My Creator";
            }
            else{
                response = "I don't know who you are talking about!";
            }
        } else if (trimmedUserMessage.contains("what")) {
            if(trimmedUserMessage.contains("date")){
                if(trimmedUserMessage.contains("tomorrow")){
                    response = "Tomorrow is "+date.plusDays(1);
                }
                else if(trimmedUserMessage.contains("yesterday")){
                    response = "Yesterday was "+date.minusDays(1);
                }else{
                response = "Today's date is "+date;
                }
            }
            else if(trimmedUserMessage.contains("month")){
                response = "It's "+date.getMonth()+"goimg on";
            }
            else if(trimmedUserMessage.contains("year")){
                response = "It's "+date.getYear();
            }
            else if(trimmedUserMessage.contains("day")){
                response = "Today is "+date.getDayOfWeek();
            }
            else if(trimmedUserMessage.contains("time")){
                if(trimmedUserMessage.contains("time in hour")){
                    response = "Currently it's "+time.getHour()+" hrs";
                } else if(trimmedUserMessage.contains("format")){
                    if(time.getHour()<12){
                        response = "It's "+time.getHour()+" am.";
                    }
                    else{
                        response = "It's "+(time.getHour()-12)+" pm.";
                    }
                }else if(trimmedUserMessage.contains("time in minutes")){
                    response = "Currently it's "+time.getMinute()+" minutes";
                }else{
                    response = "Currently it's "+time;
                }
                
            }
            else if(trimmedUserMessage.contains("leap year")){
                boolean a = date.isLeapYear();
                if(a==true){
                    response = "Yes it's a leap year";
                }
                else{
                    response = "No, it's not a leap year";
                }
            }
            else if(trimmedUserMessage.contains("name")){
                if(trimmedUserMessage.contains("your")){
                    response = "My name is Leo. Nice to meet you ";
                }
                else if(trimmedUserMessage.contains("my")){
                    response = "Your name is Divyansh.";
                }
                else{
                    response = "I don't know who you are talking about!";
                }
            }
            else if(trimmedUserMessage.contains("doing")){
                if(trimmedUserMessage.contains("you")){
                    response = "Chatting with you!";
                }
                else if(trimmedUserMessage.contains("I")){
                    response = "Chatting with me, I guess.";
                }
                else{
                    response = "I don't know who you are talking about!";
                }
            }
            else{
                response = "Sorry! I am unable to answer that yet.";
            }
            
        } else if (trimmedUserMessage.contains("new chat")) {
            response = "Select new chat from dropdown";
        } else if (trimmedUserMessage.contains("new chat")) {
            response = "Select new chat from dropdown";
        } else if (trimmedUserMessage.contains("logout")) {
            response = "Select logout from dropdown";
        } else if (trimmedUserMessage.contains("mode")) {
            response = "Select mode from dropdown";
        } else if (response.isEmpty()) {
            // Generate a random response for the bot
            String[] responses = {"I'm not sure how to respond to that.", "Can you please clarify your question?", "That's an interesting topic, but I don't have a specific response."};
            int randomIndex = new Random().nextInt(responses.length);
            response = responses[randomIndex];
        }
    
        return response;
    }

        


    public static void main(String[] args) {
        new rcmain();
    }
}
