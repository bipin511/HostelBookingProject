package com.ey.service;

import com.ey.entity.RentReminder;
import com.ey.repository.ReminderRepository;
import com.ey.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Scheduled method to check for due rent reminders.
     * Currently set to run every minute for testing.
     * In production, change the cron expression (e.g., "0 0 9 * * ?" for 9 AM daily).
     */
    @Scheduled(cron = "0 * * * * ?") // Every minute
    public void sendDueRentReminders() {
        LocalDate today = LocalDate.now();
        // Retrieve reminders scheduled for today with status "Scheduled"
        List<RentReminder> reminders = reminderRepository.findBySendDateAndStatus(today, "Scheduled");
        
        for (RentReminder reminder : reminders) {
            String email = reminder.getUser().getEmail(); // Assumes User entity has an email field
            String subject = "Rent Due Reminder";
            String body = "Dear " + reminder.getUser().getName() + ",\n\n" +
                    "This is a friendly reminder that your rent is due today.\n" +
                    "Please ensure that you make the payment on time.\n\n" +
                    "Thank you!";
            
            // Send the reminder email
            emailService.sendSimpleEmail(email, subject, body);
            
            // Update the reminder status to "Sent" after successful email dispatch
            reminder.setStatus("Sent");
            reminderRepository.save(reminder);
        }
    }
}
