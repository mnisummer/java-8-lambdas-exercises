package com.insightfullogic.java8.examples.chapter3;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.Iterator;
import java.util.List;

public class Iteration {

	// 例3-1　使用for 循环计算来自伦敦的艺术家人数
	public int externalCountArtistsFromLondon(List<Artist> allArtists) {
		// BEGIN external_count_londoners
		int count = 0;
		for (Artist artist : allArtists) {
			if (artist.isFrom("London")) {
				count++;
			}
		}
		// END external_count_londoners
		return count;
	}

	// 例3-2　使用迭代器计算来自伦敦的艺术家人数
	public int externalCountArtistsFromLondonExpanded(List<Artist> allArtists) {
		// BEGIN external_count_londoners_expanded
		int count = 0;
		Iterator<Artist> iterator = allArtists.iterator();
		while (iterator.hasNext()) {
			Artist artist = iterator.next();
			if (artist.isFrom("London")) {
				count++;
			}
		}
		// END external_count_londoners_expanded
		return count;
	}

	// 例3-3　使用内部迭代计算来自伦敦的艺术家人数
	public long internalCountArtistsFromLondon(List<Artist> allArtists) {
		// Stream 是用函数式编程方式在集合类上进行复杂操作的工具。
		// BEGIN internal_count_londoners
		// 整个过程被分解为两种更简单的操作：过滤和计数
		long count = allArtists.stream()
				.filter(artist -> artist.isFrom("London"))
				.count();
		// END internal_count_londoners
		return count;
	}

	// 例3-4　只过滤，不计数
	public void filterArtistsFromLondon(List<Artist> allArtists) {
		// BEGIN filter_londoners
		allArtists.stream()
			.filter(artist -> artist.isFrom("London"));
		// END filter_londoners
	}

	// 例3-5　由于使用了惰性求值，没有输出艺术家的名字
	public void filterArtistsFromLondonPrinted(List<Artist> allArtists) {
		// BEGIN filter_londoners_printed
		allArtists.stream()
			.filter(artist -> {
				System.out.println(artist.getName());
				return artist.isFrom("London");
			});
		// END filter_londoners_printed
	}

	// 例3-6　输出艺术家的名字
	public long internalCountArtistsFromLondonPrinted(List<Artist> allArtists) {
		// BEGIN internal_count_londoners_printed
		long count = allArtists.stream().filter(artist -> {
			System.out.println(artist.getName());
			return artist.isFrom("London");
		}).count();
		// END internal_count_londoners_printed
		return count;
	}

}
