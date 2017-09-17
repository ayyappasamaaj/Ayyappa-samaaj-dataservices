package com.ayyappasamaaj.dataservices.domain;

/**
 * Created by ssurya on 8/10/17.
 */

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Events {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String eventId;
    private String eventName;
    private String eventDesc;
    private Timestamp startDate;
    private Timestamp endDate;
    private String venue;
    private String registrationLink;
    private boolean isRecurring;

}
