package com.ayyappasamaaj.dataservices.domain;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ssurya on 8/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EbookResponse {

    public enum ResponseCode {
        UNKNOWN,
        SUCCESS,
        ERROR
    }

    private ResponseCode status = ResponseCode.UNKNOWN;
    private String message;
    private List<EbookCategory> ebooks;
}

