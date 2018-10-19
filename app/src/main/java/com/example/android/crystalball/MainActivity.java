package com.example.android.crystalball;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {
    TextView mAnswerLabel;
    private VotingBall mVotingBall = new VotingBall();
    private SensorManager mSensorManager;
    private ShakeEventListener mShakeEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare our View variables and assign them the Views from the layout file
        mAnswerLabel = findViewById(R.id.textView1);
        mSensorManager = (SensorManager) getSystemService(MainActivity.SENSOR_SERVICE);
        mShakeEventListener = new ShakeEventListener();
        mShakeEventListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                handleNewAnswer();
            }
        });
        /*mSensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mshakedetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                handleNewAnswer();
            }
        });*/

    }

    private void handleNewAnswer() {
        String answer = mVotingBall.getAnAnswer();

        // Update the label with our dynamic answer

        mAnswerLabel.setText(answer);
        animateCrystalBall();
        animateAnswer();
        playSound();
    }

    private void animateCrystalBall() {
        ImageView crystalBallImage = findViewById(R.id.imageView1);
        crystalBallImage.setImageResource(R.drawable.ball_animation);
        AnimationDrawable ballAnimation = (AnimationDrawable) crystalBallImage.getDrawable();
        if (ballAnimation.isRunning()) {
            ballAnimation.stop();
        }
        ballAnimation.start();
    }

    private void animateAnswer() {
        AlphaAnimation fadeAnswer = new AlphaAnimation(0 , 1);
        fadeAnswer.setDuration(1500);
        fadeAnswer.setFillAfter(true);
        mAnswerLabel.setAnimation(fadeAnswer);
    }

    private void playSound() {
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.crystal_ball);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    */
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeEventListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mShakeEventListener);
        super.onPause();
    }
}

