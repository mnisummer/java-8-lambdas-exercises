package com.insightfullogic.java8.exercises.chapter3;

import java.util.List;
import java.util.stream.Stream;

import com.insightfullogic.java8.examples.chapter1.Artist;

public class Question2 {
    // Q3
    public static int countBandMembersInternal(List<Artist> artists) {
    	int totalMembers = 0;
//    	for (Artist artist : artists) {
//    		Stream<Artist> members = artist.getMembers();
//    		totalMembers += members.count();
//    	}
    	totalMembers += artists.stream().flatMap(artist->artist.getMembers()).count();
        return totalMembers;
    }
}
