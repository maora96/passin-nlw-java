package com.maora.passin.services;

import com.maora.passin.domain.attendee.Attendee;
import com.maora.passin.domain.checkin.CheckIn;
import com.maora.passin.dto.attendee.AttendeeDetails;
import com.maora.passin.dto.attendee.AttendeesListResponseDTO;
import com.maora.passin.repositories.AttendeeRepository;
import com.maora.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
       return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);
        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
                Optional<CheckIn> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getCreatedAt(), checkedInAt);
        }).toList();
        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

}
