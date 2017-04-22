package com.insightfullogic.java8.examples.chapter5;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
// 待收集元素的类型，这里是 String
// 累加器的类型 StringCombiner
// 最终结果的类型，这里依然是 String
// 例5-25　定义字符串收集器
// BEGIN class_def
public class StringCollector implements Collector<String, StringCombiner, String> {
// END class_def

    private static final Set<Characteristics> characteristics = Collections.emptySet();

    private final String delim;
    private final String prefix;
    private final String suffix;

    public StringCollector(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    // 例5-26　Supplier 是创建容器的工厂
    // 一个收集器由四部分组成。首先是一个Supplier，这是一个工厂方法，用来创建容器，在
    // 这个例子中，就是StringCombiner
    @Override
    // BEGIN supplier
	public Supplier<StringCombiner> supplier() {
	    return () -> new StringCombiner(delim, prefix, suffix);
	}
    // END supplier

    // 例5-27　accumulator 是一个函数，它将当前元素叠加到收集器
    @Override
    // BEGIN accumulator
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }
    // END accumulator

    // 例5-28　combiner 合并两个容器
    // 在收集阶段，容器被combiner 方法成对合并进一个容器，直到最后只剩一个容器为止
    @Override
    // BEGIN combiner
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }
    // END combiner

    // 例5-29　finisher 方法返回收集操作的最终结果
    @Override
    // BEGIN finisher
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }
    // END finisher

    // characteristics 方法定义了特征
    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }

}
