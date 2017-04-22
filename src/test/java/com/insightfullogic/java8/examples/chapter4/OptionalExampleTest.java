package com.insightfullogic.java8.examples.chapter4;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionalExampleTest {

    @Test
    public void examples() {
    	// 例4-22　创建某个值的Optional 对象
		// BEGIN value_creation
		Optional<String> a = Optional.of("a");
		assertEquals("a", a.get());
		// END value_creation
		
		// 例4-23　创建一个空的Optional 对象，并检查其是否有值
		// BEGIN is_present
		Optional emptyOptional = Optional.empty();
		Optional alsoEmpty = Optional.ofNullable(null);
		
		assertFalse(emptyOptional.isPresent());
		assertFalse(alsoEmpty.isPresent());
		
		// a is defined above
		assertTrue(a.isPresent());
		// END is_present
		
		// 例4-24　使用orElse 和orElseGet 方法
		// BEGIN orElse
		assertEquals("b", emptyOptional.orElse("b"));
		assertEquals("c", emptyOptional.orElseGet(() -> "c"));
		// END orElse
    }

}
