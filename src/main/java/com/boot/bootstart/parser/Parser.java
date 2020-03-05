package com.boot.bootstart.parser;

import com.boot.bootstart.entity.Review;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Parser {

    public List<Review> parseCvsToReviews(File file) {
        List<String> list = getListFromFile(file);
        List<Review> reviewEntities = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            String[] fields = list.get(i).split(",");
            Review entity = new Review();
            entity.setProductId(fields[1]);
            entity.setUserId(fields[2]);
            entity.setProfileName(fields[3]);
            entity.setText(fields[9]);
            reviewEntities.add(entity);
        }
        return reviewEntities;
    }

    private List<String> getListFromFile(File file) {
        try {
            return Files.lines(Paths.get(file.getAbsolutePath()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
