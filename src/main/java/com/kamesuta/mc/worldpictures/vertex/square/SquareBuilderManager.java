package com.kamesuta.mc.worldpictures.vertex.square;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * SquareBuilderを管理、切り替えをします
 * @author Kamesuta
 */
public class SquareBuilderManager {
	public static final SquareBuilderManager INSTANCE = new SquareBuilderManager();

	private final List<ISquareBuilder> builders = Lists.newArrayList();
	private int position;
	private ISquareBuilder currentbuilder;

	private SquareBuilderManager() {
		registerSquareBuilder(new MinecraftSquareBuilder());
		registerSquareBuilder(new RectangleSquareBuilder());
		registerSquareBuilder(new ParallelogramSquareBuilder());
		registerSquareBuilder(new VertexSquareBuilder());
	}

	public void registerSquareBuilder(ISquareBuilder builder) {
		builders.add(builder);
	}

	public int size() {
		return builders.size();
	}

	public ISquareBuilder get() {
		if (currentbuilder == null) setPosition(0);
		return currentbuilder;
	}

	public int getPosition() {
		return position;
	}

	public int setPosition(int pos) {
		return setPositionImpl(limitRange(size(), pos));
	}

	public int next() {
		return setPositionImpl(limitLoop(size(), getPosition() + 1));
	}

	public int prev() {
		return setPositionImpl(limitLoop(size(), getPosition() - 1));
	}

	protected ISquareBuilder getImpl(int pos) {
		return builders.get(pos);
	}

	protected int setPositionImpl(int pos) {
		ISquareBuilder before = getImpl(position);
		currentbuilder = getImpl(pos);
		if (before != null) {
			currentbuilder.load(before);
			before.clear();
		}
		return position = pos;
	}

	private final int limitLoop(int limit, int i) {
		if (limit <= 0) return 0;
		return (((i % limit) + limit) % limit);
	}

	private final int limitRange(int limit, int i) {
		if (limit <= 0) return 0;
		return Math.max(0, Math.min(limit - 1, i));
	}
}
