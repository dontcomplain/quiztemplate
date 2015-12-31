package com.summerice.quiztemplate;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizActivity extends MainActivity {

	RelativeLayout qLayout, aLayout;
	private ArrayList<QAPair> newqlist, oldqlist;
	private String[] data;
	private int score, qamount, qlimit;
	private String slabel = "";
	private TextView qTxt, sTxt, reactionTxt, responseTxt, pgTxt;
	private Button btnA, btnB, btnC, btnD, nextbtn, resetBtn;
	private ProgressBar pgbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qa);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// Save all view objects
		qLayout = (RelativeLayout) findViewById(R.id.qlayout);
		aLayout = (RelativeLayout) findViewById(R.id.alayout);
		qTxt = (TextView) findViewById(R.id.questionTxt);
		sTxt = (TextView) findViewById(R.id.scoreTxt);
		btnA = (Button) findViewById(R.id.btna);
		btnB = (Button) findViewById(R.id.btnb);
		btnC = (Button) findViewById(R.id.btnc);
		btnD = (Button) findViewById(R.id.btnd);
		nextbtn = ((Button) findViewById(R.id.nextBtn));
		resetBtn = (Button) findViewById(R.id.resetBtn);
		responseTxt = (TextView) findViewById(R.id.responseTxt);
		pgTxt = (TextView) findViewById(R.id.progressTxt);
		pgbar = (ProgressBar) findViewById(R.id.progressBar);
		reactionTxt = (TextView) findViewById(R.id.reactionTxt);

		// Loads the data, and, creates qa and to store it in a list
		data = this.getResources().getStringArray(R.array.question);
		loadAllQs();
	}

	// Setup the question and answers
	public void setupQA(final QAPair currqa) {
		// Initialize
		slabel = "Score: " + score + " of " + qamount;

		// Set text
		qTxt.setText(currqa.getQuestion());
		btnA.setText(currqa.getAnswerA());
		btnB.setText(currqa.getAnswerB());
		btnC.setText(currqa.getAnswerC());
		btnD.setText(currqa.getAnswerD());

		OnClickListener btnclick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				String reactTxt = "";
				int reactColor = Color.BLACK;
				// If the selected answer is the correct, give a point
				if ((v.getId() == R.id.btna && currqa.isACorrect()) || (v.getId() == R.id.btnb && currqa.isBCorrect())
						|| (v.getId() == R.id.btnc && currqa.isCCorrect())
						|| (v.getId() == R.id.btnd && currqa.isDCorrect())) {
					score++;
					reactTxt = "Correct!";
					reactColor = Color.GREEN;
				} else {
					reactTxt = "Sorry!";
					reactColor = Color.RED;
				}
				slabel = "Score: " + score + " of " + ++qamount;

				qLayout.setVisibility(View.GONE);
				aLayout.setVisibility(View.VISIBLE);
				// Update the score
				sTxt.setText(slabel);
				// Aesthetically manipulate the response text here
				reactionTxt.setText(reactTxt);
				reactionTxt.setTextColor(reactColor);
				responseTxt.setText("The answer was:\n" + currqa.getCorrectAnswer());
				// Get percentage of how much before it hits the limit
				// Instead of the full stack
				int pgnum = (int) (((double) qamount / (double) qlimit) * 100);
				pgbar.setProgress(pgnum);
				pgTxt.setText("Completed: " + pgnum + "%");

				// Used to show continue button if there is unused questions
				// Now a limit can be added instead of using the whole stack
				if (qamount < qlimit) {
					nextbtn.setText("Continue");
					nextbtn.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							// Keep track of which questions are used or not
							qLayout.setVisibility(View.VISIBLE);
							aLayout.setVisibility(View.GONE);
							oldqlist.add(currqa);
							newqlist.remove(currqa);
							setupQA(newqlist.get(0));
							randomQuestion();
						}
					});
				} else {
					// The user can take the test again
					nextbtn.setText("Finish");
					nextbtn.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							finish();
						}
					});
					resetBtn.setVisibility(View.VISIBLE);
					resetBtn.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							// Reset the test questions
							qLayout.setVisibility(View.VISIBLE);
							aLayout.setVisibility(View.GONE);
							loadAllQs();
						}
					});
				}
			}
		};

		// Button reaction
		btnA.setOnClickListener(btnclick);
		btnB.setOnClickListener(btnclick);
		btnC.setOnClickListener(btnclick);
		btnD.setOnClickListener(btnclick);
	}

	// Load the list of questions
	private void loadAllQs() {
		score = 0;
		qamount = 0;
		qlimit = 10;
		// Get a clean list
		oldqlist = new ArrayList<QAPair>();
		newqlist = new ArrayList<QAPair>();
		for (String dataitem : data) {
			String[] tokens = dataitem.split("[|]");
			// Make sure it has the right amount of arguments
			if (tokens.length == 9) {
				newqlist.add(new QAPair(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(),
						tokens[4].trim(), Boolean.valueOf(tokens[5].trim()), Boolean.valueOf(tokens[6].trim()),
						Boolean.valueOf(tokens[7].trim()), Boolean.valueOf(tokens[8].trim())));
			}
		}
		randomQuestion();
	}

	private void randomQuestion() {
		// Pick a random question out of the unused list
		Random rand = new Random();
		int n = rand.nextInt(newqlist.size());
		setupQA(newqlist.get(n));
	}
}
