package com.ayyappasamaaj.dataservices.controller;

import com.ayyappasamaaj.dataservices.domain.Ebook;
import com.ayyappasamaaj.dataservices.domain.EbookResponse;
import com.ayyappasamaaj.dataservices.services.EbookServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ssurya on 8/10/17.
 */
@Controller
@Slf4j
public class EbookController {

    @Autowired
    private EbookServices ebookServices;

    @CrossOrigin
    @RequestMapping(value = "/getEbooks", method = RequestMethod.GET)
    public @ResponseBody EbookResponse getEbookData() {
        return ebookServices.getAllEbookRecords();
    }

    @CrossOrigin
    @RequestMapping(value = "/saveEbook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EbookResponse saveEbookData(@RequestBody Ebook ebook) {
        return ebookServices.saveEbookRecord(ebook);
    }

}
