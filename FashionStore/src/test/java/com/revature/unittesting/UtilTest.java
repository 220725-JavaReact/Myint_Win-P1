package com.revature.unittesting;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.fashion.util.ConnectionUtil;

public class UtilTest {
	@Test
	public void testConnection() {
		Assert.assertNotNull(ConnectionUtil.getConnection());
	}
}
