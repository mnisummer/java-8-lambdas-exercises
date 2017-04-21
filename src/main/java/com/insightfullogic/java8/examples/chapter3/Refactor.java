package com.insightfullogic.java8.examples.chapter3;

import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Track;

/**
 * 遗留代码使用lambda重构
 * 
 * @author huangjc
 */
public class Refactor {

	public static interface LongTrackFinder {
		public Set<String> findLongTracks(List<Album> albums);
	}

	// 例3-19　遗留代码：找出长度大于1 分钟的曲目
	public static class Step0 implements LongTrackFinder {
		// BEGIN findLongTracks_0
		public Set<String> findLongTracks(List<Album> albums) {
			Set<String> trackNames = new HashSet<>();
			// 循环专辑
			for (Album album : albums) {
				// 循环每个专辑的曲目
				for (Track track : album.getTrackList()) {
					// 判断长度
					if (track.getLength() > 60) {
						String name = track.getName();
						trackNames.add(name);
					}
				}
			}
			return trackNames;
		}
		// END findLongTracks_0
	}

	// 例3-20　重构的第一步：找出长度大于1 分钟的曲目
	public static class Step1 implements LongTrackFinder {
		// BEGIN findLongTracks_1
		public Set<String> findLongTracks(List<Album> albums) {
			Set<String> trackNames = new HashSet<>();
			// 循环使用forEach替换
			albums.stream().forEach(album -> {
				album.getTracks().forEach(track -> {
					if (track.getLength() > 60) {
						String name = track.getName();
						trackNames.add(name);
					}
				});
			});
			return trackNames;
		}
		// END findLongTracks_1
	}

	// 例3-21　重构的第二步：找出长度大于1 分钟的曲目
	public static class Step2 implements LongTrackFinder {
		// BEGIN findLongTracks_2
		public Set<String> findLongTracks(List<Album> albums) {
			Set<String> trackNames = new HashSet<>();
			albums.stream().forEach(album -> {
				album.getTracks()// 内部循环和过滤用filter替换
						.filter(track -> track.getLength() > 60)
						.map(track -> track.getName())
						.forEach(name -> trackNames.add(name));
			});
			return trackNames;
		}
		// END findLongTracks_2
	}

	// 例3-22　重构的第三步：找出长度大于1 分钟的曲目
	public static class Step3 implements LongTrackFinder {
		// BEGIN findLongTracks_3
		public Set<String> findLongTracks(List<Album> albums) {
			Set<String> trackNames = new HashSet<>();

			albums.stream()
					.flatMap(album -> album.getTracks())// 把专辑变成曲目，再做其他处理
					.filter(track -> track.getLength() > 60)
					.map(track -> track.getName())
					.forEach(name -> trackNames.add(name));

			return trackNames;
		}
		// END findLongTracks_3
	}

	// 例3-23　重构的第四步：找出长度大于1 分钟的曲目
	public static class Step4 implements LongTrackFinder {
		// BEGIN findLongTracks_4
		public Set<String> findLongTracks(List<Album> albums) {
			// 这个是我写的
			albums.stream()
					.flatMap(album -> album.getTracks())
					.filter(track -> track.getLength() > 60)
					.map(track -> track.getName())
					.collect(toSet());
			
			// 这个是提供的源码
			return albums.stream()
					.flatMap(album -> album.getTracks())
					.filter(track -> track.getLength() > 60)
					.map(track -> track.getName())
					.collect(toSet());
		}
		// END findLongTracks_4
	}

}
