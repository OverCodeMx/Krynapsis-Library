package mx.overcode.krynapsis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mx.overcode.krynapsislibrary.Krynapsis;

public class MainActivity extends AppCompatActivity {

    EditText etEncrypt, etDecrypt;
    Button btnEncrypt, btnDecrypt;
    TextView tvResultEncrypt, tvResultDencrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEncrypt = findViewById(R.id.etEncrypt);
        etDecrypt = findViewById(R.id.etDecrypt);
        btnEncrypt = findViewById(R.id.btnEncrypt);
        btnDecrypt = findViewById(R.id.btnDecrypt);
        tvResultEncrypt = findViewById(R.id.tvResultEncrypt);
        tvResultDencrypt = findViewById(R.id.tvResultDencrypt);

        Krynapsis.init("[49,66,36][57,7,65][78,13,54]");

        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etEncrypt.getText().toString();
                String encrypted = Krynapsis.encrypt(text);
                tvResultEncrypt.setText(encrypted);
            }
        });

        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = etDecrypt.getText().toString();
                String decrypted = Krynapsis.decrypt(text);
                tvResultDencrypt.setText(decrypted);
            }
        });

    }
}
