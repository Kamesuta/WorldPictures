package com.kamesuta.mc.worldpictures.resource;

public class WorldResource {
	private final String domain;
	private final String name;

	public WorldResource(String domain, String name) {
		this.domain = domain;
		this.name = name;
	}

	public String getDomain() {
		return this.domain;
	}

	public String getNames() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.domain + ":" + this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		WorldResource other = (WorldResource) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
