package org.kedzo.dreamy.commons;

import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.services.impl.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.tartarus.snowball.ext.russianStemmer;

@Component
public class DreamBookParser {

    @Autowired
    private TagRepository tagRepository;

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("spring-config.xml");
        DreamBookParser parser = contex.getBean(DreamBookParser.class);
        String[] iconUrls = new ClassPathResource("icons").getFile().list();
        File dreamBook = new ClassPathResource("dreamBook.txt").getFile();
        parser.parse(dreamBook, iconUrls);
        contex.destroy();
    }

    private void parse(final File dreamBookFile, final String[] iconUrls) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(dreamBookFile))) {
            br.lines().forEach(s -> {
                String[] termInterpretation = s.trim().split("-");
                if (termInterpretation.length == 2) {
                    String term = termInterpretation[0].trim();
                    String interpretation = termInterpretation[1].trim();

                    if (isValidInterpretation(term, interpretation)) {
                        russianStemmer stemmer = new russianStemmer();
                        stemmer.setCurrent(term.toLowerCase());
                        stemmer.stem();

                        Tag tag = new Tag();
                        tag.setTerm(stemmer.getCurrent());
                        tag.setInterpritation(interpretation.substring(0, interpretation.length()));
                        tag.setPicture(iconUrls[new Random().nextInt(iconUrls.length - 1)]);
                        System.out.println(tagRepository.save(tag));
                    }
                }
            });
        }
    }

    private boolean isValidInterpretation(final String term, final String interpretation) {
        return !term.isEmpty() &&
                Character.isUpperCase(term.charAt(0)) &&
                interpretation.charAt(interpretation.length() - 1) == '.';
    }
}
