package com.ayyappasamaaj.dataservices.repository;

/**
 * Created by ssurya on 8/10/17.
 */

import com.ayyappasamaaj.dataservices.domain.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface EventsRepository extends JpaRepository<Events, String> {

    @Query("FROM Events WHERE start_date > :currentDate ORDER BY start_date")
    public List<Events> findAllEvents(@Param("currentDate") Timestamp currentDate);

}
