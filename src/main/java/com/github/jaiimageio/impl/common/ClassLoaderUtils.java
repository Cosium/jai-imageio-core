package com.github.jaiimageio.impl.common;

/**
 * Created on 06/01/17.
 *
 * @author Reda.Housni-Alaoui
 */
public class ClassLoaderUtils {

	private final static ClassLoaderUtils INSTANCE = new ClassLoaderUtils();

	private ClassLoaderUtils(){

	}

	public static ClassLoaderUtils getInstance(){
		return INSTANCE;
	}

	/**
	 * @param object
	 * @return True if the provided object was loaded by the current class loader or one of its parents
	 */
	public boolean wasLoadedByCurrentClassLoader(Object object) {
		ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();

		ClassLoader classLoaderToCheck = object.getClass().getClassLoader();
		try {
			while (classLoaderToCheck != null) {
				if (threadClassLoader == classLoaderToCheck) {
					return true;
				}
				classLoaderToCheck = classLoaderToCheck.getParent();
			}
		} catch (SecurityException e) {
			return false; // Since we needed permission to call getParent(), it is unlikely it is a descendant
		}
		return false;
	}
}
