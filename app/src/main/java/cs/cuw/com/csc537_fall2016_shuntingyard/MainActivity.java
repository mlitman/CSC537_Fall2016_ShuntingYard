package cs.cuw.com.csc537_fall2016_shuntingyard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText inputET;
    private TextView outputTV;
    private ShuntingYard sy;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Core.appContext = this;
        Core.mainActivity = this;
        this.inputET = (EditText)this.findViewById(R.id.inputET);
        this.outputTV = (TextView)this.findViewById(R.id.outputTV);
    }

    public void nextStepButtonPressed(View v)
    {
        this.sy.nextStep();
    }

    public void processButtonPressed(View v)
    {
        this.sy = new ShuntingYard(this.inputET.getText().toString());
    }

    public void clearButtonPressed(View v)
    {
        this.inputET.setText("");
    }
}
