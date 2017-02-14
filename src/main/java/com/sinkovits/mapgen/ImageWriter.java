package com.sinkovits.mapgen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import com.sinkovits.mapgen.model.Map;

public class ImageWriter {

	private static final String PNG = "png";
	private static final String IMAGE_NAME_PREFIX = "map-ds";
	private static final String IMAGE_NAME_MASK = IMAGE_NAME_PREFIX + "_%d." + PNG;

	public void write(Map map, Path directory) throws IOException {
		int[][] values = scale(map.getValues());
		int width = values.length;
		int height = values[0].length;
		BufferedImage b = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = (int) values[x][y] << 16 | (int) values[x][y] << 8 | (int) values[x][y];
				b.setRGB(x, y, rgb);
			}
		}
		Path file = appendFileName(directory);
		ImageIO.write(b, PNG, file.toFile());
	}

	private int[][] scale(double[][] values) {
		int[][] scaled = new int[values.length][values[0].length];
		double max = max(values);
		double scale = (256d / max);
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				scaled[i][j] = (int) Math.round(Math.floor(values[i][j] * scale));
			}
		}
		return scaled;
	}

	private double max(double[][] values) {
		double max = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				max = Math.max(max, values[i][j]);
			}
		}
		return max;
	}

	private static Path appendFileName(Path directory) {
		String[] list = directory.toFile().list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith(IMAGE_NAME_PREFIX) && name.endsWith(PNG);
			}
		});
		return directory.resolve(String.format(IMAGE_NAME_MASK, list.length));
	}
}
