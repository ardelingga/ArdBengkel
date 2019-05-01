package ardelinggapramestakusuma.ezyro.com.ardbengkel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ardelinggapramestakusuma.ezyro.com.ardbengkel.LoginAndRegister.LoginAndRegisterActivity;

public class HomeActivity extends AppCompatActivity {
    private Button btnSepeda, btnMotor, btnMobil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        btnSepeda = (Button) findViewById(R.id.btnSepeda);
        btnSepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginAndRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
