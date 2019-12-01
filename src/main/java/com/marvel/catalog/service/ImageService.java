package com.marvel.catalog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marvel.catalog.model.Image;
import com.marvel.catalog.repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> findAll() {
	return imageRepository.findAll();
    }

    public Optional<Image> findById(int id) {
	return imageRepository.findById(id);
    }

    public Set<Image> findAllByPath(String path) {
	return imageRepository.findAllByPath(path);
    }

    public Set<Image> findAllByExtension(String extension) {
	return imageRepository.findAllByExtension(extension);
    }

    public Image findByPath(String path) {
	return imageRepository.findByPath(path);
    }

    public Image findByExtension(String extension) {
	return imageRepository.findByExtension(extension);
    }
}
