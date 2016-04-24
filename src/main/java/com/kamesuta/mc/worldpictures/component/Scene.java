package com.kamesuta.mc.worldpictures.component;

import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.lang3.Validate;

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
}
