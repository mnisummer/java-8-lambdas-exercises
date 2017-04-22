package com.insightfullogic.java8.examples.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.List;
import java.util.stream.Collectors;

public class StringExamples {

    public static String formatArtists(List<Artist> artists) {
        // BEGIN collectors_joining
		String result = artists.stream()
		              .map(Artist::getName)
		              // 分隔符（用以分隔元素）、前缀和后缀
		              .collect(Collectors.joining(", ", "[", "]"));
        // END collectors_joining
        return result;
    }

    // 例5-11　使用for 循环格式化艺术家姓名
    // 例5-17　使用for 循环和StringBuilder 格式化艺术家姓名
    public static String formatArtistsForLoop(List<Artist> artists) {
        // BEGIN for_loop
		StringBuilder builder = new StringBuilder("[");
		for (Artist  artist : artists) {
		    if (builder.length() > 1)
		        builder.append(", ");
		
		    String name = artist.getName();
		    builder.append(name);
		}
		builder.append("]");
		String result = builder.toString();
        // END for_loop
        return result;
    }

    // 例5-18　使用forEach 和StringBuilder 格式化艺术家姓名
    // 重构1
    public static String formatArtistsRefactor1(List<Artist> artists) {
        // BEGIN refactor_1
		StringBuilder builder = new StringBuilder("[");
		artists.stream()
		       .map(Artist::getName)
		       .forEach(name -> {
		           if (builder.length() > 1)
		               builder.append(", ");
		
		           builder.append(name);
		       });
		builder.append("]");
		String result = builder.toString();
        // END refactor_1
        return result;
    }

    // 例5-19　使用reduce 和StringBuilder 格式化艺术家姓名
    // 重构2
    public static String formatArtistsRefactor2(List<Artist> artists) {
        // BEGIN refactor_2
		StringBuilder reduced =
		    artists.stream()
		           .map(Artist::getName)
		           .reduce(new StringBuilder(), (builder, name) -> {
		                   if (builder.length() > 0)
		                       builder.append(", ");
		
		                   builder.append(name);
		                   return builder;
		               }, (left, right) -> left.append(right));
		
		reduced.insert(0, "[");
		reduced.append("]");
		String result = reduced.toString();
       // END refactor_2
       return result;
    }

    // 例5-20　使用reduce 和StringCombiner 类格式化艺术家姓名
    // 重构3
    public static String formatArtistsRefactor3(List<Artist> artists) {
        // BEGIN refactor_3
		StringCombiner combined =
		        artists.stream()
		               .map(Artist::getName)
		               .reduce(new StringCombiner(", ", "[", "]"),
		                       StringCombiner::add,
		                       StringCombiner::merge);
		
		String result = combined.toString();
        // END refactor_3
        return result;
    }

    // 例5-23　使用reduce 操作，将工作代理给StringCombiner 对象
    // 重构4
    public static String formatArtistsRefactor4(List<Artist> artists) {
        // BEGIN refactor_4
    	String result = artists.stream()
		            .map(Artist::getName)
		            .reduce(new StringCombiner(", ", "[", "]"),
		                    StringCombiner::add,
		                    StringCombiner::merge)
		            .toString();
        // END refactor_4
        return result;
    }

    // 例5-24　使用定制的收集器StringCollector 收集字符串
    // 重构5
    public static String formatArtistsRefactor5(List<Artist> artists) {
        // BEGIN refactor_5
		String result = artists.stream()
		           .map(Artist::getName)
		           .collect(new StringCollector(", ", "[", "]"));
        // END refactor_5
        return result;
    }

    // 例5-30　reducing 是一种定制收集器的简便方式
    public static String formatArtistsReducing(List<Artist> artists) {
        // BEGIN reducing
		String result = artists.stream()
		                .map(Artist::getName)
		                .collect(Collectors.reducing(
		                    new StringCombiner(", ", "[", "]"),// 初始化
		                    name -> new StringCombiner(", ", "[", "]").add(name),// 添加
		                    StringCombiner::merge))// 合并
		                .toString();
        // END reducing
        return result;
    }

    /*.reduce(,
    ,
    StringCombiner::merge)
            .toString()*/

}
