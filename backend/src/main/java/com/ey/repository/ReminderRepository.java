package com.ey.repository;

import com.ey.entity.RentReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReminderRepository extends JpaRepository<RentReminder, Long> {
    // Existing method for fetching reminders by user ID
    List<RentReminder> findByUserUserId(Long userId);

    // New method: fetch reminders scheduled for a specific date with a specific status (e.g., "Scheduled")
    List<RentReminder> findBySendDateAndStatus(LocalDate sendDate, String status);
}
