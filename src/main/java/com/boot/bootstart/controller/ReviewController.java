package com.boot.bootstart.controller;

import com.boot.bootstart.entity.Review;
import com.boot.bootstart.parser.Parser;
import com.boot.bootstart.repository.ReviewRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private Parser parser;

    @PostConstruct
    public void init() throws IOException {
        File file = new ClassPathResource("Reviews.csv").getFile();
        List<Review> review = parser.parseCvsToReviews(file);
        reviewRepository.saveAll(review);
    }
}
