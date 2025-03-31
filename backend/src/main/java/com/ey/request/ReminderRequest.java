package com.ey.request;

public class ReminderRequest {
    private Long userId;
    // Reminder date provided in ISO-8601 format (e.g., "2025-04-01T10:15:30")
    private String reminderDate;
    private String message;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
