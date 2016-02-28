package com.kamesuta.mc.worldpictures.resource;

import java.util.List;

public class PicturesResource {
	private final String id;
	private final List<String> names;

	public PicturesResource(String id, List<String> names) {
		this.id = id;
		this.names = names;
	}

	public String getId() {
		return this.id;
	}

	public List<String> getNames() {
		return this.names;
	}

	public boolean exists(String name) {
		return this.names.contains(name);
	}

	@Override
	public String toString() {
		return this.id + ":" + this.names;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((names == null) ? 0 : names.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PicturesResource other = (PicturesResource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (names == null) {
			if (other.names != null)
				return false;
		} else if (!names.equals(other.names))
			return false;
		return true;
	}
}
