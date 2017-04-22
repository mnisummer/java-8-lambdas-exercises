package com.insightfullogic.java8.examples.chapter6;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class ArraySum {

    public static void main(String[] ignore) throws IOException, RunnerException {
        final String[] args = {
            ".*ArraySum.*",
            "-wi",
            "5",
            "-i",
            "5"
        };
        Main.main(args);
    }

    public List<Album> albums;

    @Setup
    public void initAlbums() {
        int n = Integer.getInteger("arraysum.size", 1000);
        albums = IntStream.range(0, n)
                          .mapToObj(i -> SampleData.aLoveSupreme.copy())
                          .collect(toList());
    }

    // 例6-1　串行化计算专辑曲目长度
    @GenerateMicroBenchmark
    // BEGIN serial
	public int serialArraySum() {
	    return albums.stream()
	                 .flatMap(Album::getTracks)
	                 .mapToInt(Track::getLength)
	                 .sum();
	}
    // END serial

    // 例6-2　并行化计算专辑曲目长度
    @GenerateMicroBenchmark
    // BEGIN parallel
	public int parallelArraySum() {
    	// 调用parallelStream 方法即能并行处理
	    return albums.parallelStream()
	                 .flatMap(Album::getTracks)
	                 .mapToInt(Track::getLength)
	                 .sum();
	}
    // END parallel
    
}
