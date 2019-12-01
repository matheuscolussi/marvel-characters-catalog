package com.marvel.catalog.model;

import java.util.Set;

public class GenericDataContainer<T> {

    private int offset;
    private int limit;
    private int total;
    private int count;
    private Set<T> results;

    public int getOffset() {
	return offset;
    }

    public void setOffset(int offset) {
	this.offset = offset;
    }

    public int getLimit() {
	return limit;
    }

    public void setLimit(int limit) {
	this.limit = limit;
    }

    public int getTotal() {
	return total;
    }

    public void setTotal(int total) {
	this.total = total;
    }

    public int getCount() {
	return count;
    }

    public void setCount(int count) {
	this.count = count;
    }

    public Set<T> getResults() {
	return results;
    }

    public void setResults(Set<T> results) {
	this.results = results;
    }
}
