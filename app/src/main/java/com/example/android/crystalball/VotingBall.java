package com.example.android.crystalball;


import java.util.Random;

public class VotingBall {
    // Member variables (properties about the object)
    public String[] mAnswers = {
            "1",
            "2",
            "3",
             "4"};

    // Methods (abilities: things it can do)
    public String getAnAnswer() {
        String answer = "";

        // Randomly select one of three answers: Yes, No, or Maybe
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mAnswers.length);

        answer = mAnswers[randomNumber];

        return answer;
    }

}

