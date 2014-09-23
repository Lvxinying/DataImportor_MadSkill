package com.taozuicezhe;

import java.util.Properties;

public abstract class ResourceManagerAbs {
	public abstract Properties getProperties(String configFilePath) throws CustomerizedException;
	public abstract String getResourceInfo(String methodName);
}
