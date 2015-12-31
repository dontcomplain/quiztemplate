package com.summerice.quiztemplate;

public class QAPair {

	private String question = "";
	private String answera = "";
	private String answerb = "";
	private String answerc = "";
	private String answerd = "";
	private boolean correcta = false;
	private boolean correctb = false;
	private boolean correctc = false;
	private boolean correctd = false;

	public QAPair(String q, String aa, String ab, String ac, String ad, boolean ca, boolean cb, boolean cc,
			boolean cd) {
		question = q;
		answera = aa;
		answerb = ab;
		answerc = ac;
		answerd = ad;
		correcta = ca;
		correctb = cb;
		correctc = cc;
		correctd = cd;
	}

	public String getQuestion() {
		return question;
	}

	// Attach labels when getting answers
	public String getAnswerA() {
		return answera;
	}

	public String getAnswerB() {
		return answerb;
	}

	public String getAnswerC() {
		return answerc;
	}

	public String getAnswerD() {
		return answerd;
	}

	public boolean isACorrect() {
		return correcta;
	}

	public boolean isBCorrect() {
		return correctb;
	}

	public boolean isCCorrect() {
		return correctc;
	}

	public boolean isDCorrect() {
		return correctd;
	}

	// Get the correct answer
	public String getCorrectAnswer() {
		String correct = "";
		if (correcta) {
			correct = answera;
		} else if (correctb) {
			correct = answerb;
		} else if (correctc) {
			correct = answerc;
		} else if (correctd) {
			correct = answerd;
		}
		return correct;
	}
}
