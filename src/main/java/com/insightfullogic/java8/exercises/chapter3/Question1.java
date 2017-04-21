package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Question1 {
    public static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (x,y)->x+y);
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
    	return artists.stream()
    		.flatMap(artist->Stream.of(artist.getName(), artist.getNationality()))
    		.collect(Collectors.toList());
    }

    public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> input) {
        // 接受专辑列表作为参数，返回一个由最多包含3 首歌曲的专辑组成的列表
    	return input.stream()
    		.filter(album->album.getTrackList().size()<=3)
    		.collect(Collectors.toList());
    }
}
