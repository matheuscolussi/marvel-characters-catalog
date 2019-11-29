package com.marvel.catalog.utils;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.marvel.catalog.model.GenericDataContainer;
import com.marvel.catalog.model.GenericWrapper;

public class Utils {

	public static final int LIMIT_SIZE_DEFAULT = 20;
	public static final int OFFSET_SIZE_DEFAULT = 0;
	public static final String COPYRIGHT = "© 2019 MARVEL";
	public static final String ATTRIBUTION_TEXT = "Data provided by Marvel. © 2019 MARVEL";
	public static final String ATTRIBUTION_HTML = "<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2019 MARVEL</a>";

	public static <T> GenericDataContainer<T> createGenericDataContainer(List<T> listOfObjects) {
		GenericDataContainer<T> dataContainer = new GenericDataContainer<T>();
		dataContainer.setCount(listOfObjects.size());
		dataContainer.setLimit(LIMIT_SIZE_DEFAULT);
		dataContainer.setOffset(OFFSET_SIZE_DEFAULT);
		dataContainer.setTotal(listOfObjects.size());
		dataContainer.setResults(listOfObjects);
		return dataContainer;
	}

	public static <T> GenericWrapper<T> createGenericWrapper(HttpStatus httpStatus) {
		GenericWrapper<T> wrapper = new GenericWrapper<T>();
		wrapper.setCode(httpStatus.value());
		wrapper.setStatus(httpStatus.name());
		wrapper.setCopyright(COPYRIGHT);
		wrapper.setAttributionHTML(ATTRIBUTION_HTML);
		wrapper.setAttributionText(ATTRIBUTION_TEXT);
		wrapper.setEtag(UUID.randomUUID().toString());
		wrapper.setData(null);
		return wrapper;
	}
}