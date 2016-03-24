package de.seven.fate.model.builder;


import de.seven.fate.model.util.ClassUtil;
import de.seven.fate.model.util.NumberUtil;

import java.util.*;


public abstract class AbstractModelBuilder<T> implements ModelBuilder<T> {


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

		return list(randomSize());
	}

	public List<T> list(int size) {

		List<T> list = new ArrayList<T>();

		fillCollection(size, list);

		return list;
	}

	public Set<T> set(int size) {

		Set<T> set = new HashSet<T>();

		fillCollection(size, set);

		return set;
	}

	public Set<T> set() {

		return set(randomSize());
	}

	private void initWithRandomValues(T model) {

		//initPropertiesWithRandomValues(model);
	}

	protected int randomSize() {

		return Math.max(1, new Random().nextInt(10));
	}

	/*
	 * ATTENTION! Size of Collection of type Set can be less than size, when adding multiple the same Object
	 */
	private void fillCollection(int size, Collection<T> collection) {

		int randomModulo = Math.max(1, NumberUtil.random(0, size));

		int count = 0;
		while (count++ < size) {
			boolean minOrMax = count % randomModulo == 0;
			collection.add(random(minOrMax));
		}
	}

	private T random(boolean minOrMax) {
		return minOrMax ? min() : max();
	}

}