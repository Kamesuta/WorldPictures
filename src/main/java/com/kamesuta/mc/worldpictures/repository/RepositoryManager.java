package com.kamesuta.mc.worldpictures.repository;

public class RepositoryManager {
//	public static final IRepository TopRepository = new HttpRepository("http://worldpictures.mc.kamesuta.com/");

	private IRepository currentRepository;

	public RepositoryManager(IRepository repo) {
		this.currentRepository = repo;
	}

	public RepositoryManager() {
//		this(TopRepository);
	}

	public IRepository getRepository() {
		return currentRepository;
	}

	public void setRepository(IRepository repo) {
		this.currentRepository = repo;
	}
}
