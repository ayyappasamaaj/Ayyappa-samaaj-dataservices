package com.ayyappasamaaj.dataservices.domain;

import lombok.*;

import java.util.List;

/**
 * Created by ssurya on 9/12/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EbookSubCategory {
    private String title;
    private List<Ebook> items;
}
