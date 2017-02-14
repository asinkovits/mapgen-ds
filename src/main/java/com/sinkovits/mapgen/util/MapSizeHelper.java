package com.sinkovits.mapgen.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapSizeHelper {

	public static long calculateSize(int n) {
		if( n < 1){
			throw new IllegalArgumentException();
		}
		return (1 << n) + 1;
	}

	public static boolean isSizeValid(long size) {
		Set<Long> valids = Arrays.stream(calculateValidSizeArray(size+1)).boxed().collect(Collectors.toSet());
		return valids.contains(size);
	}
	
	public static long[] calculateValidSizeArray(long max) {
		List<Long> result = new ArrayList<>();
		int i = 1;
		long size = calculateSize(i++);
		while (size < max) {
			result.add(size);
			size = calculateSize(i++);
		}
		return result.stream().mapToLong(l -> l).toArray();
	}

	public static long[] calculateInvalidSizeArray(long max) {
		List<Long> result = new ArrayList<>();
		Set<Long> valids = Arrays.stream(calculateValidSizeArray(max)).boxed().collect(Collectors.toSet());
		for (long i = 0; i < max; i++) {
			if( !valids.contains(i)){
				result.add(i);
			}
		}
		return result.stream().mapToLong(l -> l).toArray();
	}

}
