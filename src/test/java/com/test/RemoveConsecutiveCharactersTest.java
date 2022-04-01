package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import com.test.StringUtils.ConvertConsecutiveCharactersParam;

public class RemoveConsecutiveCharactersTest {

	public static final int maxIdenticalCharCont = 3;
	public static final Function<ConvertConsecutiveCharactersParam, String> strategy = StringUtils.removeConsecutiveCharactersStrategy;

	@Test
	public void testNormalStr() {
		String str = "aabcccbbad";
		List<String> expectedResultList = new ArrayList<>(Arrays.asList("aabbbad", "aaad", "d"));
		AtomicInteger resultIndex = new AtomicInteger(0);

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			System.out.println(result);
			Assert.assertEquals(result, expectedResultList.get(resultIndex.getAndIncrement()));
		});
	}

	@Test
	public void testNormalStr2() {
		String str = "aaabbbccc";
		List<String> expectedResultList = new ArrayList<>(Arrays.asList("bbbccc", "ccc", ""));
		AtomicInteger resultIndex = new AtomicInteger(0);

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			System.out.println(result);
			Assert.assertEquals(result, expectedResultList.get(resultIndex.getAndIncrement()));
		});
	}

	@Test
	public void testNormalStr3() {
		String str = "daaabbbccc";
		List<String> expectedResultList = new ArrayList<>(Arrays.asList("dbbbccc", "dccc", "d"));
		AtomicInteger resultIndex = new AtomicInteger(0);

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			System.out.println(result);
			Assert.assertEquals(result, expectedResultList.get(resultIndex.getAndIncrement()));
		});
	}

	@Test
	public void testStrAllBeRemoved() {
		String str = "ddaabcccbbad";
		List<String> expectedResultList = new ArrayList<>(Arrays.asList("ddaabbbad", "ddaaad", "ddd", ""));
		AtomicInteger resultIndex = new AtomicInteger(0);

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			System.out.println(result);
			Assert.assertEquals(result, expectedResultList.get(resultIndex.getAndIncrement()));
		});
	}

	@Test
	public void testStrNotBeRemoved() {
		String str = "ddaabccbbad";

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			// no result and no exception, if there has some result that will be wrong
			Assert.assertTrue(false);
		});

		Assert.assertTrue(true);
	}

	@Test
	public void testNullOrEmptyStr() {
		StringUtils.convertConsecutiveCharacters(null, maxIdenticalCharCont, strategy, (result) -> {
			// no result and no exception, if there has some result that will be wrong
			Assert.assertTrue(false);
		});

		StringUtils.convertConsecutiveCharacters("", maxIdenticalCharCont, strategy, (result) -> {
			// no result and no exception, if there has some result that will be wrong
			Assert.assertTrue(false);
		});

		Assert.assertTrue(true);
	}

	@Test
	public void testStrLessThan3Char() {
		StringUtils.convertConsecutiveCharacters("gg", maxIdenticalCharCont, strategy, (result) -> {
			// no result and no exception, if there has some result that will be wrong
			Assert.assertTrue(false);
		});

		Assert.assertTrue(true);
	}

}
