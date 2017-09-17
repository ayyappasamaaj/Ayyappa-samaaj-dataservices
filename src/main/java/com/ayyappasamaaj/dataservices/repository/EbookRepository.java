package com.ayyappasamaaj.dataservices.repository;

/**
 * Created by ssurya on 8/10/17.
 */

import com.ayyappasamaaj.dataservices.domain.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EbookRepository extends JpaRepository<Ebook, String>   {

}
