package com.ayyappasamaaj.dataservices.services;

import com.ayyappasamaaj.dataservices.domain.Ebook;
import com.ayyappasamaaj.dataservices.domain.EbookCategory;
import com.ayyappasamaaj.dataservices.domain.EbookResponse;
import com.ayyappasamaaj.dataservices.domain.EbookSubCategory;
import com.ayyappasamaaj.dataservices.repository.EbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by ssurya on 8/10/17.
 */
@Service
public class EbookServices {

    @Autowired
    private EbookRepository ebookRepository;

    public EbookResponse getAllEbookRecords () {

        EbookResponse ebookResponse = new EbookResponse();
        List<Ebook> ebookList = ebookRepository.findAllByOrderBySequence();

        if ( ebookList.size() > 0) {
            ebookResponse.setStatus(EbookResponse.ResponseCode.SUCCESS);
            ebookResponse.setMessage("");
            ebookResponse.setEbooks(categorizeEbooks(ebookList));
        } else {
            ebookResponse.setStatus(EbookResponse.ResponseCode.ERROR);
            ebookResponse.setMessage("No data found!!!");
        }
        return ebookResponse;
    }

    public EbookResponse saveEbookRecord (Ebook ebook) {

        EbookResponse ebookResponse = new EbookResponse();
        Map<String, List<Ebook>> map = new HashMap<>();

        try {
            ebook = ebookRepository.save(ebook);

            if (ebook != null ) {
                ebookResponse.setStatus(EbookResponse.ResponseCode.SUCCESS);
                ebookResponse.setMessage("");
            } else {
                ebookResponse.setStatus(EbookResponse.ResponseCode.ERROR);
                ebookResponse.setMessage("Something bad happened");
            }
        } catch (DataAccessException e) {
            ebookResponse.setStatus(EbookResponse.ResponseCode.ERROR);
            ebookResponse.setMessage("Something bad happened");
        }

        return ebookResponse;
    }

    public void saveEbookFromCsv () {
        String csvFileToRead = "/Users/ssurya/Desktop/ebook.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFileToRead));
            while ((line = br.readLine()) != null) {

                // split on comma(',')
                String[] ebooks = line.split(splitBy);

                Ebook ebook = new Ebook();
                ebook.setItemTitle(ebooks[0]);
                ebook.setCategory(ebooks[1]);
                ebook.setSubCategory(ebooks[2]);
                ebook.setFileUrl(ebooks[3]);
                ebook.setLanguage(ebooks[4]);
                ebook.setSequence(Integer.valueOf(ebooks[5]));
                saveEbookRecord(ebook);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<EbookCategory> categorizeEbooks (List<Ebook> ebookList) {

        List<EbookCategory> resultList = new ArrayList<>();

        for (Ebook ebook : ebookList) {
            boolean hasCategory = false;
            if (ebook.getSubCategory() == null || ebook.getSubCategory().isEmpty()){
                if (!resultList.isEmpty()) {
                    for (EbookCategory ebookCategory : resultList) {
                        if (ebookCategory.getTitle().equals(ebook.getCategory())) {
                            ebookCategory.getItems().add(ebook);
                            hasCategory = true;
                            break;
                        }
                    }
                }
                if (!hasCategory) {
                    EbookCategory ebookCategory = new EbookCategory(ebook.getCategory(), false, new ArrayList<>(), null);
                    ebookCategory.getItems().add(ebook);
                    resultList.add(ebookCategory);
                }
            } else {
                if (!resultList.isEmpty()) {
                    for (EbookCategory ebookCategory : resultList) {
                        if (ebookCategory.getTitle().equals(ebook.getCategory())) {
                            hasCategory = true;
                            boolean hasSubCategory = false;
                            for (EbookSubCategory ebookSubCategory: ebookCategory.getSubCategoryList()) {
                                if (ebookSubCategory.getTitle().equals(ebook.getSubCategory())) {
                                    ebookSubCategory.getItems().add(ebook);
                                    hasSubCategory = true;
                                    break;
                                }
                            }

                            if (!hasSubCategory) {
                                EbookSubCategory ebookSubCategory = new EbookSubCategory(ebook.getSubCategory(), new ArrayList<>());
                                ebookSubCategory.getItems().add(ebook);
                                ebookCategory.getSubCategoryList().add(ebookSubCategory);
                            }
                        }
                    }
                }
                if (!hasCategory) {
                    EbookCategory ebookCategory = new EbookCategory(ebook.getCategory(), true, null, new ArrayList<>());
                    EbookSubCategory ebookSubCategory = new EbookSubCategory(ebook.getSubCategory(), new ArrayList<>());
                    ebookSubCategory.getItems().add(ebook);
                    ebookCategory.getSubCategoryList().add(ebookSubCategory);
                    resultList.add(ebookCategory);
                }
            }
        }

        return resultList;
    }

}
