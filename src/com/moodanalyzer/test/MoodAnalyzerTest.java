package com.moodanalyzer.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
		});
	}

	@Test
	public void test() {
		Assert.assertEquals(moodAnalyzer.analyseMood(message), mood);
	}

}
