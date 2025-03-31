package com.ey.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rent_reminders")
public class RentReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reminderId;

    // Reminder is for a specific user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String message;
    private LocalDate sendDate;
    private String status; // e.g., scheduled, sent

    public RentReminder() {}

    public RentReminder(Long reminderId, User user, String message, LocalDate sendDate, String status) {
        this.reminderId = reminderId;
        this.user = user;
        this.message = message;
        this.sendDate = sendDate;
        this.status = status;
    }

    // Getters and setters
    public Long getReminderId() {
        return reminderId;
    }

    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RentReminder{" +
                "reminderId=" + reminderId +
                ", user=" + user +
                ", message='" + message + '\'' +
                ", sendDate=" + sendDate +
                ", status='" + status + '\'' +
                '}';
    }
}
