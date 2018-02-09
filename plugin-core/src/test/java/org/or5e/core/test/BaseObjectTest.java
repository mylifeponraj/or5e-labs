package org.or5e.core.test;

import org.or5e.core.BaseObject;

public class BaseObjectTest {
	public static void main(String[] args) {
		new BaseObject() {
			@Override public String getName() {
				return "org.or5e.core.BaseObject";
			}
		};
	}
}
