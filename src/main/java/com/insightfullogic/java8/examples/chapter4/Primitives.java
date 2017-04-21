package com.insightfullogic.java8.examples.chapter4;

import com.insightfullogic.java8.examples.chapter1.Album;

import java.util.IntSummaryStatistics;

public class Primitives {

	// 例4-4　使用summaryStatistics 方法统计曲目长度
	// BEGIN printTrackLengthStatistics
	public static void printTrackLengthStatistics(Album album) {
	    IntSummaryStatistics trackLengthStats
	            = album.getTracks()
	                   .mapToInt(track -> track.getLength())
	                   .summaryStatistics();
	
	    System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
	                      trackLengthStats.getMax(),
	                      trackLengthStats.getMin(),
	                      trackLengthStats.getAverage(),
	                      trackLengthStats.getSum());
	}
	// END printTrackLengthStatistics

}
