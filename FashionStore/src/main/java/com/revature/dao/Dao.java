package com.revature.dao;

import java.util.List;

public interface Dao<T> {
	public T addInstance(T instance);

    public List<T> getAllInstance();

    public T updateInstance(T instance);

    public T deleteInstance(T instance);
}
