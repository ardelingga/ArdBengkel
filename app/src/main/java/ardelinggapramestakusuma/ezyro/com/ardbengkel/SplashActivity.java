package ardelinggapramestakusuma.ezyro.com.ardbengkel;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ardelinggapramestakusuma.ezyro.com.ardbengkel.LoginAndRegister.LoginAndRegisterActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashActivity.this, Home.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
