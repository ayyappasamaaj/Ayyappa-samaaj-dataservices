package com.ayyappasamaaj.dataservices.domain;

/**
 * Created by ssurya on 8/10/17.
 */

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ebook {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String ebookId;
    private String itemTitle;
    private String category;
    private String subCategory;
    private String fileUrl;
    private String language;
    private int sequence;

}
