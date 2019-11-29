package com.marvel.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.marvel.catalog.repository.ImageRepository;

public class ImageService {

	@Autowired
	ImageRepository imageRepository;

	public List<com.marvel.catalog.model.Image> findAllByPath(String path) {
		return imageRepository.findAllByPath(path);
	}

	public List<com.marvel.catalog.model.Image> findAllByExtension(String extension) {
		return imageRepository.findAllByExtension(extension);
	}

	public com.marvel.catalog.model.Image findByPath(String path) {
		return imageRepository.findByPath(path);
	}

	public com.marvel.catalog.model.Image findByExtension(String extension) {
		return imageRepository.findByExtension(extension);
	}
}
