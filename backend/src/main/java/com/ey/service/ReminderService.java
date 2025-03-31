package com.ey.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.RentReminder;
import com.ey.entity.User;
import com.ey.repository.ReminderRepository;
import com.ey.repository.UserRepository;
import com.ey.request.ReminderRequest;
import com.ey.response.ReminderResponse;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private UserRepository userRepository;

    public String addReminder(ReminderRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return "User not found";
        }

        RentReminder reminder = new RentReminder();
        reminder.setUser(user);
        reminder.setSendDate(LocalDate.parse(request.getReminderDate())); // FIXED
        reminder.setMessage(request.getMessage());
        reminder.setStatus("scheduled");

        reminderRepository.save(reminder);
        return "Reminder added successfully";
    }

    public List<ReminderResponse> getRemindersByUser(Long userId) {
        List<RentReminder> reminders = reminderRepository.findByUserUserId(userId);
        List<ReminderResponse> responses = new ArrayList<>();

        for (RentReminder reminder : reminders) {
            ReminderResponse response = new ReminderResponse();
            response.setReminderId(reminder.getReminderId());
            response.setUserName(reminder.getUser().getName());
            response.setReminderDate(reminder.getSendDate().toString()); // FIXED
            response.setMessage(reminder.getMessage());
            responses.add(response);
        }

        return responses;
    }

    public String deleteReminder(Long reminderId) {
        if (!reminderRepository.existsById(reminderId)) {
            return "Reminder not found";
        }
        reminderRepository.deleteById(reminderId);
        return "Reminder deleted successfully";
    }
}
