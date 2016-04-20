package com.kamesuta.mc.worldpictures.component;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.nbt.NBTTagList;

public class Scene extends ArrayList<Keyframe> {

	public static final Scene NULL = new Scene();

	public Scene() {
		super();
	}

	public Scene(Collection<? extends Keyframe> c) {
		super(c);
	}

	/**
	 * 現在の推移を返します
	 *
	 * @return vectorpool
	 */
	public Square takeashot(long now) {
		if (!this.isEmpty()) {
			Keyframe last = this.get(this.size() - 1);
			Keyframe prevlast = last, nextlast = last;

			long alltime = 0;
			for (Keyframe vertex : this) {
				if (vertex != null)
					alltime += vertex.getLength();
			}

			if (alltime > 0) {
				long nowtime = now % alltime;

				long nowFrameTime = 0;
				for (Keyframe vertex : this) {
					nowFrameTime += vertex.getLength();
					if (nowtime > nowFrameTime) {
						prevlast = vertex;
					} else {
						nextlast = vertex;
						break;
					}
				}

				long betweentime = (long) (prevlast.getLength());
				long betweennowtime = (long) (nowtime - (nowFrameTime - prevlast.getLength()));
				float progress = (float) betweennowtime / betweentime;

//				prevlast.lt.toVec(nextlast.lt).scale(progress).add(prevlast.lt.toVec());
//				prevlast.lb.toVec(nextlast.lb).scale(progress).add(prevlast.lb.toVec());
//				prevlast.rb.toVec(nextlast.rb).scale(progress).add(prevlast.rb.toVec());
//				prevlast.rt.toVec(nextlast.rt).scale(progress).add(prevlast.rt.toVec());
				Square pl = prevlast.getSquare();
				Square nl = nextlast.getSquare();
				if (pl != null && nl != null) {
					final Position lt = new Position((pl.lt.toVec().scale(1-progress)).add(nl.lt.toVec().scale(progress)));
					final Position lb = new Position((pl.lb.toVec().scale(1-progress)).add(nl.lb.toVec().scale(progress)));
					final Position rb = new Position((pl.rb.toVec().scale(1-progress)).add(nl.rb.toVec().scale(progress)));
					final Position rt = new Position((pl.rt.toVec().scale(1-progress)).add(nl.rt.toVec().scale(progress)));

					return new Square(lt, lb, rb, rt);
				}
			}
		}
		return null;
	}

	public NBTTagList toNBT() {
		NBTTagList nbtlist = new NBTTagList();
		for (Keyframe v : this) {
			nbtlist.appendTag(v.toNBT());
		}
		return nbtlist;
	}

	public void fromNBT(NBTTagList nbtlist) {
		int count = nbtlist.tagCount();
		for (int i=0; i<count; i++) {
			Keyframe square = new Keyframe();
			square.fromNBT(nbtlist.getCompoundTagAt(i));
			this.add(square);
		}
	}
}
