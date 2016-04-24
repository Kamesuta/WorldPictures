package com.kamesuta.mc.worldpictures.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.lang3.Validate;

import net.minecraft.nbt.NBTTagList;

@Immutable
public class Scene {
	public final List<Keyframe> keyframes;

	public Scene(final List<Keyframe> kfs) {
		Validate.notNull(kfs);
		Validate.notEmpty(kfs);
		this.keyframes = Collections.unmodifiableList(kfs);
	}

	public Scene(final Scene c) {
		this(c.keyframes);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.keyframes == null) ? 0 : this.keyframes.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Scene))
			return false;
		final Scene other = (Scene) obj;
		if (this.keyframes == null) {
			if (other.keyframes != null)
				return false;
		} else if (!this.keyframes.equals(other.keyframes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Scene[keyframes:%s]", this.keyframes);
	}

	/**
	 * 現在の推移を返します
	 *
	 * @return vectorpool
	 */
	public static Square takeashot(final List<Keyframe> c, final long now) {
		if (c != null)
			if (!c.isEmpty()) {
				final Keyframe last = c.get(c.size() - 1);
				Keyframe prevlast = last, nextlast = last;

				long alltime = 0;
				for (final Keyframe vertex : c) {
					if (vertex != null)
						alltime += vertex.length;
				}

				if (alltime > 0) {
					final long nowtime = now % alltime;

					long nowFrameTime = 0;
					for (final Keyframe vertex : c) {
						nowFrameTime += vertex.length;
						if (nowtime > nowFrameTime) {
							prevlast = vertex;
						} else {
							nextlast = vertex;
							break;
						}
					}

					final long betweentime = (prevlast.length);
					final long betweennowtime = nowtime - (nowFrameTime - prevlast.length);
					final float progress = (float) betweennowtime / betweentime;

					final Square pl = prevlast.square;
					final Square nl = nextlast.square;

					final Position lt = new Position((pl.lt.toVec().scale(1-progress)).add(nl.lt.toVec().scale(progress)));
					final Position lb = new Position((pl.lb.toVec().scale(1-progress)).add(nl.lb.toVec().scale(progress)));
					final Position rb = new Position((pl.rb.toVec().scale(1-progress)).add(nl.rb.toVec().scale(progress)));
					final Position rt = new Position((pl.rt.toVec().scale(1-progress)).add(nl.rt.toVec().scale(progress)));

					return new Square(lt, lb, rb, rt);
				}
			}
		return null;
	}

	public static Square takeashot(final Scene c, final long now) {
		if (c != null) return takeashot(c.keyframes, now);
		return null;
	}

	/**
	 * NBTから作成
	 */
	public static Scene fromNBT(final NBTTagList nbtlist) {
		if (nbtlist != null) {
			final ArrayList<Keyframe> kfs = new ArrayList<Keyframe>();
			final int count = nbtlist.tagCount();
			for (int i = 0; i < count; i++) {
				final Keyframe kf = Keyframe.fromNBT(nbtlist.getCompoundTagAt(i));
				if (kf != null)
					kfs.add(kf);
			}
			if (!kfs.isEmpty())
				return new Scene(kfs);
		}
		return null;
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagList toNBT(final Scene scene) {
		final NBTTagList nbtlist = new NBTTagList();
		for (final Keyframe v : scene.keyframes) {
			nbtlist.appendTag(Keyframe.toNBT(v));
		}
		return nbtlist;
	}
}
