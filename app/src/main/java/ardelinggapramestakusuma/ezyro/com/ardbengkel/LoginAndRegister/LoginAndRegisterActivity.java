package ardelinggapramestakusuma.ezyro.com.ardbengkel.LoginAndRegister;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import ardelinggapramestakusuma.ezyro.com.ardbengkel.Home;
import ardelinggapramestakusuma.ezyro.com.ardbengkel.Model.User;
import ardelinggapramestakusuma.ezyro.com.ardbengkel.R;
import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginAndRegisterActivity extends Activity {
    Button btnLogin, btnRegister;
    RelativeLayout rootLayout;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    //Tekan CTRL+O
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sebelum setContentView
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                            .setDefaultFontPath("fonts/Arkhip_font.ttf")
                                            .setFontAttrId(R.attr.fontPath)
                                            .build());

        setContentView(R.layout.activity_login);

        //Init Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        //Init View
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        //Event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });
    }

    private void showLoginDialog() {

        final EditText edtEmail = (EditText) findViewById(R.id.text_email);
        final EditText edtPassword= (EditText) findViewById(R.id.text_password);

            //Set disable button Login in if processing
            btnLogin.setEnabled(false);

                //CHECK VALIDATION
                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Snackbar.make(rootLayout, "Mohon Masukan Email", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                //makan

                if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                    Snackbar.make(rootLayout, "Mohon Masukan Password", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (edtPassword.getText().toString().length() < 6) {
                    Snackbar.make(rootLayout, "Password Terlalu Pendek!!!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                final AlertDialog waitingDialog = new SpotsDialog(LoginAndRegisterActivity.this);
                waitingDialog.show();

                //login
                auth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                waitingDialog.dismiss();
                                startActivity(new Intent(LoginAndRegisterActivity.this, Home.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        waitingDialog.dismiss();
                        Snackbar.make(rootLayout, "Failed"+e.getMessage(), Snackbar.LENGTH_SHORT)
                                .show();

                        //Active Button
                        btnLogin.setEnabled(true);
                    }
                });

    }

    private void showRegisterDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(LoginAndRegisterActivity.this);
        dialog.setTitle("REGISTER ");
        dialog.setMessage("Please use email to register");

        LayoutInflater inflater = LayoutInflater.from(LoginAndRegisterActivity.this);
        View register_layout = inflater.inflate(R.layout.layout_register, null);

        final MaterialEditText edtEmail = register_layout.findViewById(R.id.edtEmailRegister);
        final MaterialEditText edtPassword = register_layout.findViewById(R.id.edtPasswordRegister);
        final MaterialEditText edtNama = register_layout.findViewById(R.id.edtNamaRegister);
        final MaterialEditText edtNoTelp = register_layout.findViewById(R.id.edtNoTelpRegister);

        dialog.setView(register_layout);

        //Set Button
        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                //CHECK VALIDATION

                if (TextUtils.isEmpty(edtEmail.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Mohon Masukan Email",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtPassword.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Mohon Masukan Password",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edtNama.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Mohon Masukan Nama",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edtNoTelp.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Mohon Masukan No Telepon",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (edtPassword.getText().toString().length() < 6)
                {
                    Snackbar.make(rootLayout, "Password Terlalu Pendek !!!",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                final AlertDialog waitingDialog = new SpotsDialog(LoginAndRegisterActivity.this);
                waitingDialog.show();
                //Register new user
                auth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                waitingDialog.dismiss();
                                //Save user to db
                                User user = new User();
                                user.setEmail(edtEmail.getText().toString());
                                user.setPassword(edtPassword.getText().toString());
                                user.setNama(edtNama.getText().toString());
                                user.setNoTelp(edtNoTelp.getText().toString());

                                //Use Email to key
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(rootLayout, "Register Success Fully !!!",Snackbar.LENGTH_SHORT).show();
                                                return;
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(rootLayout, "Failed "+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                                                return;
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                waitingDialog.dismiss();
                                Snackbar.make(rootLayout, "Failed "+e.getMessage(),Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        dialog.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}