package com.insightfullogic.java8.examples.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Decisions {

    public static class Imperative {
        // BEGIN origins_of_bands_meth_imp
        public Set<String> originsOfBands(Album album) {
            Set<String> nationalities = new HashSet<>();
            // 拿到表演者列表，循环获取拿到国籍
            for (Artist artist : album.getMusicianList()) {
                if (artist.getName().startsWith("The")) {
                    String nationality = artist.getNationality();
                    nationalities.add(nationality);
                }
            }
            return nationalities;
        }
        // END origins_of_bands_meth_imp
    }

    // 例3-25　符合Stream 使用习惯的链式调用
    public Set<String> originsOfBands(Album album) {
        // BEGIN origins_of_bands
    	// 拿到表演者->过滤表演者->拿到国籍->收集结果
    	Set<String> origins = album.getMusicians()
                           .filter(artist -> artist.getName().startsWith("The"))
                           .map(artist -> artist.getNationality())
                           .collect(toSet());
        // END origins_of_bands
        return origins;
    }

    // 例3-24　误用Stream 的例子
    public Set<String> originsOfBandsMisuse(Album album) {
        // BEGIN misuse
    	// 表演者
    	List<Artist> musicians = album.getMusicians()
                              .collect(toList());
    	// 指定表演者
    	List<Artist> bands = musicians.stream()
                              .filter(artist -> artist.getName().startsWith("The"))
                              .collect(toList());
    	// 国籍
    	Set<String> origins = bands.stream()
                           .map(artist -> artist.getNationality())
                           .collect(toSet());
        // END misuse
        return origins;
    }

}
