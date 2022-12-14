package com.moodanalyzer.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.moodanalyzer.MoodAnalysisException;
import com.moodanalyzer.MoodAnalyzer;

@RunWith(Parameterized.class)
public class MoodAnalyzerTest {
	private String message;
	private String mood;
	
	private MoodAnalyzer moodAnalyzer;
	
	@Before
	public void setUp() throws Exception {
		moodAnalyzer = new MoodAnalyzer();
	}
	
	public MoodAnalyzerTest(String message, String mood) {
		this.message = message;
		this.mood = mood;
	}
	
	@Parameterized.Parameters
	public static Collection<String[]> testMessages() {
		MoodAnalyzer tempMoodAnalyzer = new MoodAnalyzer();
		return Arrays.asList(new String[][] {
			{"I am in Sad Mood", tempMoodAnalyzer.SAD_MOOD},
			{"I am Not in Sad Mood", tempMoodAnalyzer.HAPPY_MOOD},
			{"I am in Happy Mood", tempMoodAnalyzer.HAPPY_MOOD},
			{"I am Not in Happy Mood", tempMoodAnalyzer.SAD_MOOD},
			{"I am in Any Mood", tempMoodAnalyzer.HAPPY_MOOD},
			{null, tempMoodAnalyzer.HAPPY_MOOD},
			{"     ", tempMoodAnalyzer.HAPPY_MOOD},
			{"", tempMoodAnalyzer.HAPPY_MOOD}
		});
	}

	@Test
	public void test() {
		try {
			Assert.assertEquals(moodAnalyzer.analyseMood(message), mood);
		} catch (MoodAnalysisException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUsingConstructor() {
		MoodAnalyzer tempMoodAnalyzer = new MoodAnalyzer(message);
		try {
			Assert.assertEquals(tempMoodAnalyzer.analyseMood(), mood);
		} catch (MoodAnalysisException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUsingNullMessage() {
		MoodAnalyzer tempMoodAnalyzer = new MoodAnalyzer();
		try {
			Assert.assertEquals(tempMoodAnalyzer.analyseMood(), tempMoodAnalyzer.HAPPY_MOOD);
		} catch (MoodAnalysisException e) {
			e.printStackTrace();
		}
	}

}
