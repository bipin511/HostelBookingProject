package com.ey.controller;

import com.ey.request.ReminderRequest;
import com.ey.response.ReminderResponse;
import com.ey.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@CrossOrigin
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public String addReminder(@RequestBody ReminderRequest request) {
        return reminderService.addReminder(request);
    }

    @GetMapping("/user/{userId}")
    public List<ReminderResponse> getRemindersByUser(@PathVariable Long userId) {
        return reminderService.getRemindersByUser(userId);
    }

    @DeleteMapping("/{reminderId}")
    public String deleteReminder(@PathVariable Long reminderId) {
        return reminderService.deleteReminder(reminderId);
    }
}
