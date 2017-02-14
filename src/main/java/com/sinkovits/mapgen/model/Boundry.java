package com.sinkovits.mapgen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

public class Boundry {

	private final Point tl, t, tr, l, mp, r, bl, b, br;
	private final int size;
	private final int depth;

	public Boundry(Point ll, Point ur, int depth) {
		super();
		this.bl = ll;
		this.tr = ur;
		this.br = Point.create(ur.getX(), ll.getY());
		this.tl = Point.create(ll.getX(), ur.getY());
		this.mp = Point.create((ll.getX() + ur.getX()) / 2, (ll.getY() + ur.getY()) / 2);
		this.b = Point.create(mp.getX(), ll.getY());
		this.l = Point.create(ll.getX(), mp.getY());
		this.t = Point.create(mp.getX(), ur.getY());
		this.r = Point.create(ur.getX(), mp.getY());
		this.size = (br.getX() - ll.getX()) + 1;
		this.depth = depth;
	}

	public Boundry(Point ll, Point ur) {
		this(ll, ur, 0);
	}

	public Point getBL() {
		return bl;
	}

	public Point getBR() {
		return br;
	}

	public Point getTL() {
		return tl;
	}

	public Point getTR() {
		return tr;
	}

	public Point getMP() {
		return mp;
	}

	public Point getB() {
		return b;
	}

	public Point getL() {
		return l;
	}

	public Point getT() {
		return t;
	}

	public Point getR() {
		return r;
	}

	public int getSize() {
		return size;
	}

	public List<Boundry> split() {
		List<Boundry> result = new ArrayList<>();
		result.add(new Boundry(bl, mp, depth + 1));
		result.add(new Boundry(Point.create(mp.getX(), bl.getY()), Point.create(tr.getX(), mp.getY()), depth + 1));
		result.add(new Boundry(Point.create(bl.getX(), mp.getY()), Point.create(mp.getX(), tr.getY()), depth + 1));
		result.add(new Boundry(mp, tr, depth + 1));
		return result;
	}

	public int getDepth() {
		return depth;
	}
}
