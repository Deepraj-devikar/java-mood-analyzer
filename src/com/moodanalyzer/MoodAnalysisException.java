package com.moodanalyzer;

public class MoodAnalysisException extends Exception{
	public MoodAnalysisException(MoodAnalysisErrors moodAnalysisError) {
		super(moodAnalysisError.toString()+" message occur during mood analysis");
	}
}
