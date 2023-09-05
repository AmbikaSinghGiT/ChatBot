package com.example.chatbot.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.activation.DataSource;
import javax.activation.FileDataSource;



public class DailyEmailScheduler {

	public static void main(String[] args) {
	    // Create a ScheduledExecutorService with a single thread
	    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	    // Schedule the email sending task at a specific time daily (e.g., 1:30 PM)
	    int hourToSend = 13; // 5PM
	    int minuteToSend = 00; // 48 minutes past the hour
	    long initialDelay = calculateInitialDelay(hourToSend, minuteToSend);
	    long period = 24 * 60 * 60 * 1000; // 24 hours

	    scheduler.scheduleAtFixedRate(DailyEmailScheduler::sendDailyEmail, initialDelay, period, TimeUnit.MILLISECONDS);
	}

	public static long calculateInitialDelay(int hourToSend, int minuteToSend) {
	    // Calculate the delay until the next scheduled time (hourToSend and minuteToSend)
	    LocalDateTime now = LocalDateTime.now();
	    LocalDateTime sendTime = LocalDateTime.of(now.toLocalDate(), LocalTime.of(hourToSend, minuteToSend));

	    if (sendTime.isBefore(now)) {
	        sendTime = sendTime.plusDays(1); // Move to the next day if send time is before now
	    }

	    Duration duration = Duration.between(now, sendTime);
	    return duration.toMillis();
	}

//    public static long calculateInitialDelay(int hourToSend) {
//        // Calculate the delay until the next scheduled time (hourToSend)
//        long now = System.currentTimeMillis();
//        int currentHour = (int) ((now / (60 * 60 * 1000)) % 24);
//        long hoursUntilSend = (24 + hourToSend - currentHour) % 24;
//
//        return hoursUntilSend * 60 * 60 * 1000; // Convert hours to milliseconds
//    }
	

    public static void sendDailyEmail() {
        // Fetch the collected data
        String collectedData = fetchData();

        // Configure email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a Session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sakshipatanker0@gmail.com","wsdwapjbrlfufsvg");
            }
        });
        
        List<String> recipientAddresses = fetchRecipientEmails();
        for (String address : recipientAddresses) {
            System.out.println("Recipient Address: " + address);
        }
	    if (recipientAddresses.isEmpty()) {
	        System.out.println("No recipient email addresses found.");
	        return;
	    }
        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
         // Set the recipients, subject, and content
            message.setFrom(new InternetAddress("sakshipatanker0@gmail.com"));

            // Modify this line to include multiple recipient addresses
            Address[] recipientInternetAddresses = new Address[recipientAddresses.size()];
	        for (int i = 0; i < recipientAddresses.size(); i++) {
	            recipientInternetAddresses[i] = new InternetAddress(recipientAddresses.get(i));
	        }
	        message.setRecipients(Message.RecipientType.TO, recipientInternetAddresses);
            message.setSubject("Daily Data Summary");
            message.setText("Here is the collected data for the day:" + collectedData);

            // Set the recipients, subject, and content
            MimeMultipart multipart = new MimeMultipart();

            // Part 1: Text content
            BodyPart textPart = new MimeBodyPart();
            textPart.setText("Here is the collected data for the day:");
            multipart.addBodyPart(textPart);

            // Part 2: Excel attachment
            BodyPart excelPart = new MimeBodyPart();
            DataSource source = new FileDataSource("collected_data.xlsx");
            excelPart.setDataHandler(new DataHandler(source));
            excelPart.setFileName("collected_data.xlsx");
            multipart.addBodyPart(excelPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static List<String> fetchRecipientEmails() {
        List<String> recipientEmails = new ArrayList<>();

        try {
            InputStream excelFile = DailyEmailScheduler.class.getResourceAsStream("/recipient_emails.xlsx");
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0); // Assuming email addresses are in the first column
                if (cell != null) {
                    recipientEmails.add(cell.getStringCellValue());
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipientEmails;
    }



    public static String fetchData() {
        StringBuilder collectedData = new StringBuilder();

        String jdbcUrl = "jdbc:mysql://localhost:3306/kfbynew_schema";
        String username = "root";
        String password = "Mysql2207";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Query to fetch data from a specific table
            String query = "SELECT * FROM chatbot_model";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Collected Data");
            // Create header row
            Row headerRow = sheet.createRow(0);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(metaData.getColumnName(i));
            }
            // Populate Excel rows with data
            int rowNum = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(i));
                }
            }
            // Write Excel data to a file
            try (FileOutputStream fileOut = new FileOutputStream("collected_data.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Excel file generated successfully.");
            }
            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collectedData.toString();
    }
       
}
