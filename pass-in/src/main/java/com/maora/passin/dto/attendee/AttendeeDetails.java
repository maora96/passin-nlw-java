package com.maora.passin.dto.attendee;

import java.time.LocalDateTime;

public record AttendeeDetails(String id, String name, LocalDateTime createdAt, LocalDateTime checkedInAt) {
}
