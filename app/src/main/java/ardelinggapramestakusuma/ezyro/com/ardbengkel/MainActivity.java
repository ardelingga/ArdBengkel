package ardelinggapramestakusuma.ezyro.com.ardbengkel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnMontir, btnCostumer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMontir = (Button) findViewById(R.id.montir);
        btnCostumer = (Button) findViewById(R.id.costumer);

        btnMontir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MontirLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
