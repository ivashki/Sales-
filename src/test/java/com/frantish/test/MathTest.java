package com.frantish.test;

import org.junit.Assert;
import org.junit.Test;

import com.frantishex.simpleMethods.Math;

public class MathTest {

	@Test
	public void add_TwoPlusTwo_ReturnsFour() {
		// Arrange
		final int expected = 4;

		// Act
		final int actual = Math.add(2, 2);

		// Assert
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void multiply_TwoWithTwo_ReturnsFour() {

		final int expected = 4;

		final int actual = Math.multiply(2, 2);

		Assert.assertEquals(actual, expected);

	}

}
