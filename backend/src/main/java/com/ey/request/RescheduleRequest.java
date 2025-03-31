package com.ey.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class RescheduleRequest {

    @NotNull(message = "New check-in date is required")
    @Future(message = "New check-in must be in the future")
    private LocalDate newCheckIn;

    @NotNull(message = "New check-out date is required")
    @Future(message = "New check-out must be in the future")
    private LocalDate newCheckOut;

    public LocalDate getNewCheckIn() {
        return newCheckIn;
    }

    public void setNewCheckIn(LocalDate newCheckIn) {
        this.newCheckIn = newCheckIn;
    }

    public LocalDate getNewCheckOut() {
        return newCheckOut;
    }

    public void setNewCheckOut(LocalDate newCheckOut) {
        this.newCheckOut = newCheckOut;
    }
}
