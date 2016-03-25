package de.seven.fate.model.builder;


import de.seven.fate.model.util.ClassUtil;

import java.util.*;

import static de.seven.fate.model.adapter.TypeRandomAdapterFactory.initPropertiesWithRandomValues;


public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {


	private static final int MIN_COLLECTION_SIZE = 1;
	private static final int MAX_COLLECTION_SIZE = 10;

	public Class<T> getGenericType() {

		return ClassUtil.getGenericType(getClass());
	}

	public T min() {

		Class<T> modelType = getGenericType();

		T model = ClassUtil.createInstance(modelType);

		initWithRandomValues(model);

		return model;
	}

	public T max() {

		return min();
	}

	public T random() {

		return random(new Random().nextBoolean());
	}

	public List<T> list() {

		return list(randomCollectionSize());
	}

	public List<T> list(int size) {

		List<T> list = new ArrayList<>();

		fillCollection(size, list);

		return list;
	}

	public Set<T> set(int size) {

		Set<T> set = new HashSet<>();

		fillCollection(size, set);

		return set;
	}

	public Set<T> set() {

		return set(randomCollectionSize());
	}

	private void initWithRandomValues(T model) {

		initPropertiesWithRandomValues(model);
	}

	private int randomCollectionSize() {

		return Math.max(MIN_COLLECTION_SIZE, new Random().nextInt(MAX_COLLECTION_SIZE));
	}

	/*
	 * ATTENTION! Size of Collection of type Set can be less than size, when adding multiple the same Object
	 */
	private void fillCollection(int size, Collection<T> collection) {

		int count = 0;
		while (count++ < size) {
			collection.add(random());
		}
	}

	private T random(boolean minOrMax) {
		return minOrMax ? min() : max();
	}

}