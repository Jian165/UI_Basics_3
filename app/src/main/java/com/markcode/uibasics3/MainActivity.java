package com.markcode.uibasics3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxHarry, checkBoxMatrix, checkBoxJoker;
    private RadioGroup rgMaritalStatus;

    private RadioGroup progVisibility;

    private ProgressBar progressBar;

    private TextView percentage;

    private Button DownloadBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxHarry = findViewById(R.id.checkboxHarry);
        checkBoxMatrix = findViewById(R.id.checkboxMatrix);
        checkBoxJoker = findViewById(R.id.checkboxJoker);



       if(checkBoxHarry.isChecked()){
           Toast.makeText(MainActivity.this, "You have watched Harry Potter, Yey", Toast.LENGTH_SHORT).show();
       }

       else{
           Toast.makeText(MainActivity.this, "You Need to watch Harry Potter", Toast.LENGTH_SHORT).show();
       }


        checkBoxHarry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    Toast.makeText(MainActivity.this, "You have watched Harry Potter, Yey", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You Need to watch Harry Potter", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rgMaritalStatus = findViewById(R.id.MaritalStatus);
        rgMaritalStatus.getCheckedRadioButtonId();

        int checkedId = rgMaritalStatus.getCheckedRadioButtonId();

        if (checkedId == R.id.rbMarried) {
            Toast.makeText(MainActivity.this, "Married", Toast.LENGTH_SHORT).show();
        } else if (checkedId == R.id.rbSingle) {
            Toast.makeText(MainActivity.this, "Single", Toast.LENGTH_SHORT).show();
        } else if (checkedId == R.id.rbInRel) {
            Toast.makeText(MainActivity.this, "In A Relationship", Toast.LENGTH_SHORT).show();
        }

        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int ToCheckID) {
                String CheckID = getResources().getResourceEntryName(ToCheckID);
                switch (CheckID) {
                    case "rbMarried":
                        Toast.makeText(MainActivity.this, "Married", Toast.LENGTH_SHORT).show();
                        break;
                    case "rbSingle":
                        Toast.makeText(MainActivity.this, "Single", Toast.LENGTH_SHORT).show();
                        break;

                    case "rbInRel":
                        Toast.makeText(MainActivity.this, "In A Relationship", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });

        progVisibility = findViewById(R.id.progVisibility);

        progVisibility.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkVis) {
                String visible = getResources().getResourceEntryName(checkVis);
                switch (visible) {

                    case "onProgress":
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case "offProgress":
                        progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });


        progressBar = findViewById(R.id.progressBar);
        percentage = findViewById(R.id.percentage);


        DownloadBtn =findViewById(R.id.DownloadBtn);

        DownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Download();
                DownloadBtn.setVisibility(View.INVISIBLE);

            }
        });

    }


    private void Download() {
       Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    int progress = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                            percentage.setText("Progress " + progress + "%");
                            if(progress==100){
                                percentage.setText("Complete");
                                DownloadBtn.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                    SystemClock.sleep(500);
                }
            }
        });
        thread.start();
    }





}
