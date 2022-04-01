package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import com.test.StringUtils.ConvertConsecutiveCharactersParam;

public class ReplaceConsecutiveCharactersTest {

	public static final int maxIdenticalCharCont = 3;
	public static final Function<ConvertConsecutiveCharactersParam, String> strategy = StringUtils.replaceConsecutiveCharactersStrategy;

	@Test
	public void testNormalStr() {
		String str = "abcccbad";
		List<String> expectedResultList = new ArrayList<>(Arrays.asList("abbbad", "aaad", "d"));
		AtomicInteger resultIndex = new AtomicInteger(0);

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			System.out.println(result);
			Assert.assertEquals(expectedResultList.get(resultIndex.getAndIncrement()), result);
		});
	}

	@Test
	public void testNormalStr2() {
		String str = "cccbbaa";
		List<String> expectedResultList = new ArrayList<>(Arrays.asList("bbbaa", "aaa", ""));
		AtomicInteger resultIndex = new AtomicInteger(0);

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			System.out.println(result);
			Assert.assertEquals(expectedResultList.get(resultIndex.getAndIncrement()), result);
		});
	}

	@Test
	public void testNormalStr3() {
		String str = "daabbccc";
		List<String> expectedResultList = new ArrayList<>(Arrays.asList("daabbb", "daaa", "d"));
		AtomicInteger resultIndex = new AtomicInteger(0);

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			System.out.println(result);
			Assert.assertEquals(expectedResultList.get(resultIndex.getAndIncrement()), result);
		});
	}

	@Test
	public void testStrAllBeRemoved() {
		String str = "aaa";
		List<String> expectedResultList = new ArrayList<>(Arrays.asList(""));
		AtomicInteger resultIndex = new AtomicInteger(0);

		StringUtils.convertConsecutiveCharacters(str, maxIdenticalCharCont, strategy, (result) -> {
			System.out.println(result);
			Assert.assertEquals(expectedResultList.get(resultIndex.getAndIncrement()), result);
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
		StringUtils.convertConsecutiveCharacters("gz", maxIdenticalCharCont, strategy, (result) -> {
			// no result and no exception, if there has some result that will be wrong
			Assert.assertTrue(false);
		});

		Assert.assertTrue(true);
	}

}
