package com.insightfullogic.java8.examples.chapter3;

import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HigherOrderFunctionExamplesTest {

    @Test
    public void collectToList() {
        // BEGIN collect_to_list_1
		List<String> collected = Stream.of("a", "b", "c") // <1>
				.collect(Collectors.toList()); // <2>

		assertEquals(Arrays.asList("a", "b", "c"), collected); // <3>
		// END collect_to_list_1
    }

    // 例3-9　使用map 操作将字符串转换为大写形式
    @Test
    public void mapToUpperCase() {
        // BEGIN map_to_uppercase
    	List<String> collected = Stream.of("a", "b", "hello")
                               .map(string -> string.toUpperCase()) // <1>
                               .collect(toList());

    	assertEquals(asList("A", "B", "HELLO"), collected);
        // END map_to_uppercase
    }

    // 例3-8　使用for 循环将字符串转换为大写
    @Test
    public void forToUpperCase() {
        // BEGIN for_to_uppercase
		List<String> collected = new ArrayList<>();
		for (String string : asList("a", "b", "hello")) {
		    String uppercaseString = string.toUpperCase();
		    collected.add(uppercaseString);
		}

		assertEquals(asList("A", "B", "HELLO"), collected);
        // END for_to_uppercase
    }

    // 例3-14　使用for 循环查找最短曲目
    @Test
    public void imperativeMaxLength() {
		// BEGIN imperativeMaxLength
		List<Track> tracks = asList(new Track("Bakai", 524),
		                            new Track("Violets for Your Furs", 378),
		                            new Track("Time Was", 451));
		
		Track shortestTrack = tracks.get(0);
		for (Track track : tracks) {
		    if (track.getLength() < shortestTrack.getLength()) {
		        shortestTrack = track;
		    }
		}

		assertEquals(tracks.get(1), shortestTrack);
		// END imperativeMaxLength
    }

    // 例3-13　使用Stream 查找最短曲目
    @Test
    public void streamsMaxLength() {
		// BEGIN streamsMaxLength
		List<Track> tracks = asList(new Track("Bakai", 524),
		                            new Track("Violets for Your Furs", 378),
		                            new Track("Time Was", 451));
		
		Track shortestTrack = tracks.stream()
		                            .min(Comparator.comparing(track -> track.getLength()))
		                            .get();
		
		assertEquals(tracks.get(1), shortestTrack);
		// END streamsMaxLength
    }

    @Test
    public void streamsAnyMatch() {
		// BEGIN streamsAnyMatch
		List<Track> tracksOnColtrane = asList(new Track("Bakai", 524),
		                                      new Track("Violets for Your Furs", 378),
		                                      new Track("Time Was", 451));
		
		boolean matchLength = tracksOnColtrane.stream()
		                                      .anyMatch(track -> track.getLength() > 500);
		assertTrue(matchLength);
		// END streamsAnyMatch
    }

    @Test
    public void imperativeAnyMatch() {
    	// BEGIN imperativeAnyMatch
    	List<Track> tracksOnColtrane = asList(new Track("Bakai", 524),
                                      new Track("Violets for Your Furs", 378),
                                      new Track("Time Was", 451));

		boolean matchLength = false;
		for(Track track : tracksOnColtrane) {
		    if (track.getLength() > 500) {
		        matchLength = true;
		    }
		}
		
		assertTrue(matchLength);
		// END imperativeAnyMatch
    }
    
    // 例3-16　使用reduce 求和
    @Test
    public void sumUsingReduce() {
        // BEGIN count_using_reduce
    	int count = Stream.of(1, 2, 3)
                  .reduce(0, (acc, element) -> acc + element);

    	assertEquals(6, count);
        // END count_using_reduce
    }

    // 例3-17　展开reduce 操作
    @Test
    public void expandedReduce() {
        // BEGIN expanded_reduce
	    BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
	    int count = accumulator.apply(
                    	accumulator.apply(accumulator.apply(0, 1), 2),
                    	3
                    );
        // END expanded_reduce

        assertEquals(6, count);
    }

    // 例3-18　使用命令式编程方式求和
    @Test
    public void countUsingReduceFor() {
        // BEGIN count_using_reduce_for
		int acc = 0;
		for (Integer element : asList(1, 2, 3)) {
		    acc = acc + element;
		}
		assertEquals(6, acc);
        // END count_using_reduce_for
    }

    // 例3-11　函数式风格
    @Test
    public void functionalStringsWithNumbers() {
        // BEGIN strings_numbers_filter
		List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
		          .filter(value -> isDigit(value.charAt(0)))
		          .collect(toList());
		
		assertEquals(asList("1abc"), beginningWithNumbers);
        // END strings_numbers_filter
    }

    // 例3-10　使用循环遍历列表，使用条件语句做判断
    @Test
    public void imperativeStringsWithNumbers() {
        // BEGIN strings_numbers_for
		List<String> beginningWithNumbers = new ArrayList<>();
		for(String value : asList("a", "1abc", "abc1")) {
		    if (isDigit(value.charAt(0))) {
		        beginningWithNumbers.add(value);
		    }
		}

		assertEquals(asList("1abc"), beginningWithNumbers);
        // END strings_numbers_for
    }

    @Test
    public void flatMapCharacters() {
        // BEGIN flatmap_characters
    	// Stream里的每个元素都是一个列表
    	// [[1,2],[3,4],...]
    	// flatMap参数：是一个函数，函数返回值是一个Stream
    	List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                               .flatMap(numbers -> numbers.stream())
                               .collect(toList());

		assertEquals(asList(1, 2, 3, 4), together);
        // END flatmap_characters
    }

}
