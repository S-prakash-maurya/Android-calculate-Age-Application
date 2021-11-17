package com.example.countage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;


public class MainActivity extends AppCompatActivity {

    Button submitButton;
    EditText inputTextField;
    TextView outputTextView;
    String inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.SubmitButton);

        inputTextField = findViewById(R.id.inputTextField);
        outputTextView = findViewById(R.id.outputTextView);

        dialogBox();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputDate = inputTextField.getText().toString();
                getDobdata(inputDate);

            }
        });
    }

    public void getDobdata(String ageDob) {
        outputTextView.setText("");
        try {
            if (ageDob.isEmpty()) {
                Toast.makeText(this, " Please Enter Birth Day.........", Toast.LENGTH_SHORT).show();
            } else if (ageDob.length() == 10) {

                try {
                    int day = Integer.parseInt("" + ageDob.charAt(0) + ageDob.charAt(1));
                    int month = Integer.parseInt("" + ageDob.charAt(3) + ageDob.charAt(4));
                    int year = Integer.parseInt("" + ageDob.charAt(6) + ageDob.charAt(7) + ageDob.charAt(8) + ageDob.charAt(9));

                    LocalDate birthday = LocalDate.of(year, month, day);
                    LocalDate currentDate = LocalDate.now();

                    //  Period period = Period.between(birthday, currentDate);

                    Period period = Period.between(birthday, currentDate);

                    outputTextView.setText("Mr./Ms " + inputName + "\nYear = " + period.getYears() + "\n Month = " + period.getMonths() + "\n Days = " + period.getDays());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, " Please Enter Here Valid Birth Day........." + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, " Please Enter Here Valid Birth Day.........", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dialogBox() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View mView = layoutInflater.inflate(R.layout.dialog_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(mView);


        alertDialogBuilder.setCancelable(false).setPositiveButton("send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                EditText editText = (EditText) mView.findViewById(R.id.meditText);
                inputName = editText.getText().toString();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Error message ", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog mAlertDialog = alertDialogBuilder.create();
        mAlertDialog.show();
    }
}