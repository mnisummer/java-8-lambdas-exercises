package com.insightfullogic.java8.examples.chapter5;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class EncounterOrderTest {

	// 例5-1　顺序测试永远通过
    @Test
    public void listToStream() {
        // BEGIN LIST_TO_STREAM
	    List<Integer> numbers = asList(1, 2, 3, 4);
	
	    List<Integer> sameOrder = numbers.stream()
	                                     .collect(toList());
	    assertEquals(numbers, sameOrder);
        // END LIST_TO_STREAM
    }

    // 例5-2　顺序测试不能保证每次通过
    // NB: to actually get this to fail you need to reverse the order of the numbers.
    @Ignore
    @Test
    public void hashSetToStream() {
        // BEGIN HASHSET_TO_STREAM
	    Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
	
	    List<Integer> sameOrder = numbers.stream()
                                     .collect(toList());

	    // This may not pass
	    assertEquals(asList(4, 3, 2, 1), sameOrder);
        // END HASHSET_TO_STREAM
    }

    // 例5-3　生成出现顺序
    @Test
    public void hashSetToStreamSorted() {
        // BEGIN HASHSET_TO_STREAM_SORTED
	    Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
	
	    List<Integer> sameOrder = numbers.stream()
                                     .sorted()// 进行排序
                                     .collect(toList());

	    assertEquals(asList(1, 2, 3, 4), sameOrder);
        // END HASHSET_TO_STREAM_SORTED
    }

    // 例5-4　本例中关于顺序的假设永远是正确的
    @Test
    public void toStreamMapped() {
        // BEGIN TO_STREAM_MAPPED
	    List<Integer> numbers = asList(1, 2, 3, 4);
	
	    List<Integer> stillOrdered = numbers.stream()
                                        .map(x -> x + 1)// 元素转换
                                        .collect(toList());

	    // Reliable encounter ordering
	    assertEquals(asList(2, 3, 4, 5), stillOrdered);
	
	    Set<Integer> unordered = new HashSet<>(numbers);

	    List<Integer> stillUnordered = unordered.stream()
                                            .map(x -> x + 1)
                                            .collect(toList());

	    // Can't assume encounter ordering
	    assertThat(stillUnordered, hasItem(2));
	    assertThat(stillUnordered, hasItem(3));
	    assertThat(stillUnordered, hasItem(4));
	    assertThat(stillUnordered, hasItem(5));
        // END TO_STREAM_MAPPED
    }

}
