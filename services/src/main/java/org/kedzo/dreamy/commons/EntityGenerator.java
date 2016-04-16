package org.kedzo.dreamy.commons;

import org.kedzo.dreamy.models.*;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Component
public class EntityGenerator {

    static private final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private SecureRandom rnd = new SecureRandom();
    private Random rand = new Random();

    public User generateUser() {
        User user = new User();
        user.setUsername(generateString(8) + " " + generateString(8));
        user.setEmail(generateString(5) + "@test.com");
        user.setLogin(generateString(8));
        user.setPassword(generateString(10));
        user.setBirthday(new Date());
        user.setPicture(generateString(50));
        user.setLng(generateFloat(180, -180));
        user.setLat(generateFloat(180, -180));
        user.setIndividual(rand.nextBoolean());
        return user;
    }

    public Dream generateDream() {
        Dream dream = new Dream();
        dream.setDate(new Date());
        dream.setDescription(generateString(100));
        return dream;
    }

    public Tag generateTag() {
        Tag tag = new Tag();
        tag.setInterpritation(generateString(100));
        tag.setPicture(generateString(50));
        return tag;
    }

    public Episode generateEpisode() {
        Episode episode = new Episode();
        episode.setNote(generateString(100));
        episode.setPicture(generateString(50));
        episode.setOrder(1);
        return episode;
    }

    public Blog generateBlog() {
        Blog blog = new Blog();
        return blog;
    }

    public BlogComment generateBlogComment() {
        BlogComment comment = new BlogComment();
        comment.setComment(generateString(100));
        return comment;
    }

    public BlogEntry generateBlogEntity() {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setText(generateString(100));
        blogEntry.setTitle(generateString(10));
        blogEntry.setDate(new Date());
        return blogEntry;
    }

    private String generateString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    private byte[] generateByte(int len) {
        return generateString(len).getBytes();
    }

    private float generateFloat(float max, float min) {
        return rand.nextFloat() * (max - min) + min;
    }


}
