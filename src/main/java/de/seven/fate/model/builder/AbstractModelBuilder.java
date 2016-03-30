package de.seven.fate.model.builder;


import de.seven.fate.model.util.ClassUtil;

import java.util.*;

import static de.seven.fate.model.adapter.TypeRandomAdapterFactory.initPropertiesWithRandomValues;


public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {


	private static final int MIN_COLLECTION_SIZE = 1;
	private static final int MAX_COLLECTION_SIZE = 10;

	private static int randomCollectionSize() {

		return Math.max(MIN_COLLECTION_SIZE, new Random().nextInt(MAX_COLLECTION_SIZE));
	}

	@Override
	public Class<T> getGenericType() {

		return ClassUtil.getGenericType(getClass());
	}

	@Override
	public T min() {

		Class<T> modelType = getGenericType();

		T model = ClassUtil.createInstance(modelType);

		initPropertiesWithRandomValues(model);

		return model;
	}

	@Override
	public T max() {

		return min();
	}

	@Override
	public T random() {

		return random(new Random().nextBoolean());
	}

	@Override
	public List<T> list() {

		return list(randomCollectionSize());
	}

	@Override
	public List<T> list(int size) {

		List<T> list = new ArrayList<>();

		fillCollection(size, list);

		return list;
	}

	@Override
	public Set<T> set(int size) {

		Set<T> set = new HashSet<>();

		fillCollection(size, set);

		return set;
	}

	@Override
	public Set<T> set() {

		return set(randomCollectionSize());
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