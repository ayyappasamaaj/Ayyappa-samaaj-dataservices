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
public class EbookCategory {
    private String title;
    private boolean hasSubCategory;
    private List<Ebook> items;
    private List<EbookSubCategory> subCategoryList;
}
