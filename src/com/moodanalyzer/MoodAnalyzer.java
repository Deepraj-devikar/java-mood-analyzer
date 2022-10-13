package com.moodanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MoodAnalyzer {
	public final String HAPPY_MOOD = "Happy Mood";
	public final String SAD_MOOD = "Sad Mood";
	
	public String message;
	
	public MoodAnalyzer() {
	}
	
	public MoodAnalyzer(String message) {
		this.message = message;
	}
	
	/**
	 * read message and check which kind of words it contains happy words or sad words
	 * and analyze the mood according words it contain 
	 * 
	 * @return Happy Mood or Sad Mood according to words used in message
	 */
	public String analyseMood() {
		// if message is null then have to return happy mood
		if(message == null) {
			return HAPPY_MOOD;
		}
		String word;
		String resourceFolder = "resource/";
		final String NO = "NO";
		final String YES = "YES";
		// files names contain words 
		// 1st file contain negation kind of words like no, not, can't, don't
		// 2nd file contain sad kind of words like sad, unhappy, sorrow
		// 3rd file contain happy kind of words like happy, joy
		// NOTE:- data collected from https://www.thesaurus.com/browse/ web site for sad synonyms and happy synonyms 
		// and NO is showing currently not any kind of words message have 
		// because we didn't check words occur in message yet
		String[][] wordsFilesInfo = {
				{resourceFolder+"negation_words.txt", NO},
				{resourceFolder+"sad_words.txt", NO},
				{resourceFolder+"happy_words.txt", NO},
		};
		// check occur of words in message
		for (int i = 0 ; i < wordsFilesInfo.length; i++) {
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(wordsFilesInfo[i][0].toString()))) {
				while((word = bufferedReader.readLine()) != null) {
					if(message.toLowerCase().contains(word.toLowerCase())) {
						wordsFilesInfo[i][1] = YES;
						break;
					}
				}
			}
			// catch file not found exception if any data file not present
			catch (FileNotFoundException e) {
				System.out.println("can't read "+wordsFilesInfo[i][0]+" file");
			}
			// catch null pointer exception if got null value
			catch (NullPointerException e) {
				System.out.println("got null value");
			}
			// catch exception other than File not found exception and null pointer exception
			catch (Exception e) {
				System.out.println(e);
			}	
		}
		switch(wordsFilesInfo[0][1]+" "+wordsFilesInfo[1][1]+" "+wordsFilesInfo[2][1]) {
		case NO+" "+NO+" "+NO:
			return HAPPY_MOOD;
		case NO+" "+NO+" "+YES:
			return HAPPY_MOOD;
		case NO+" "+YES+" "+NO:
			return SAD_MOOD;
		case NO+" "+YES+" "+YES:
			return HAPPY_MOOD;
		case YES+" "+NO+" "+NO:
			return SAD_MOOD;
		case YES+" "+NO+" "+YES:
			return SAD_MOOD;
		case YES+" "+YES+" "+NO:
			return HAPPY_MOOD;
		case YES+" "+YES+" "+YES:
			return HAPPY_MOOD;
		}
		return HAPPY_MOOD;
	}
	
	/**
	 * read message and check which kind of words it contains happy words or sad words
	 * and analyze the mood according words it contain 
	 * 
	 * @param message
	 * @return Happy Mood or Sad Mood according to words used in message
	 */
	public String analyseMood(String message) {
		this.message = message;
		return analyseMood();
	}

	public static void main(String[] args) {
		System.out.println("Welcome to mood analyzer program");
		
		MoodAnalyzer moodAnalyzer = new MoodAnalyzer();
		System.out.println(moodAnalyzer.analyseMood());
	}

}
