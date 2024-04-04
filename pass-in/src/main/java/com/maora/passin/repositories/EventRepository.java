package com.maora.passin.repositories;

import com.maora.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,String> {

}
