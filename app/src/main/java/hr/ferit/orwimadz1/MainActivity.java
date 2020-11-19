package hr.ferit.orwimadz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button inspiration;
    Button editDescription;
    EditText newDescription;
    TextView[] description = new TextView[3];
    RadioGroup radioGroup;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    ImageView[] image = new ImageView[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
    }

    private void setListeners() {
        //Preloading quotes from strings.xml
        String[] quotes = new String[3];
        loadQuotes(quotes);


        //Inspiration button functionality
        inspiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, generateRandomQuote(quotes), Toast.LENGTH_LONG).show();
            }

            //Returns 1 random of 3 preloaded strings in String[] quotes
            private String generateRandomQuote(String[] quotes) {
                Random rand = new Random();
                int generatedRandomIndex = rand.nextInt(3);     //(max-min)+min [0,3>
                return quotes[generatedRandomIndex];
            }
        });

        //Edit Description button + radio button functionality
        editDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPerson = getCheckedRadioID();
                String input = newDescription.getText().toString();

                //Replace person's description with a string from EditText if it's not empty
                if (selectedPerson != -1 && !input.isEmpty()) {
                    description[selectedPerson].setText(input);

                    //clear everything
                    radioGroup.clearCheck();
                    newDescription.setText("");
                }
            }

            //Returns checked RadioButotn as index [0-2] or -1 if none is checked
            private int getCheckedRadioID() {
                if (radio1.isChecked())
                    return 0;
                if (radio2.isChecked())
                    return 1;
                if (radio3.isChecked())
                    return 2;
                return -1;
            }

        });

        //ImageView disappears on click -> no toggle
        for(int i=0;i<3;i++){
            int current = i;
            image[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(image[current].getVisibility() == View.VISIBLE){
                        image[current].setVisibility(View.GONE);
                    }
                }
            });
        }
    }


    //Preloading quotes from string.xml
    private void loadQuotes(String[] quotes) {
        quotes[0] = getString(R.string.first_person_inspiration);
        quotes[1] = getString(R.string.second_person_inspiration);
        quotes[2] = getString(R.string.third_person_inspiration);
    }

    private void initializeUI() {
        inspiration = findViewById(R.id.btnInspiration);
        editDescription = findViewById(R.id.btnDescription);
        newDescription = findViewById(R.id.etDescription);
        description[0] = findViewById(R.id.tvFirstPersonDescription);
        description[1] = findViewById(R.id.tvSecondPersonDescription);
        description[2] = findViewById(R.id.tvThirdPersonDescription);
        radioGroup = findViewById(R.id.radioGroup);
        radio1 = findViewById(R.id.radioBtn1);
        radio2 = findViewById(R.id.radioBtn2);
        radio3 = findViewById(R.id.radioBtn3);
        image[0] = findViewById(R.id.img1);
        image[1] = findViewById(R.id.img2);
        image[2] = findViewById(R.id.img3);

        setListeners();
    }
}