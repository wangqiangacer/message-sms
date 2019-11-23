package com.jacken.messagesms.result;

import java.util.ArrayList;

public class PagerList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1934615959350815259L;

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
