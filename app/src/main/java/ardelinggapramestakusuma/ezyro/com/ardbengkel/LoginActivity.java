package ardelinggapramestakusuma.ezyro.com.ardbengkel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.sql.Driver;

import ardelinggapramestakusuma.ezyro.com.ardbengkel.Model.User;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends Activity {
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
//        CalligraphyConfig.initDefault(new CalligraphyConfig().Builder()
//                                            .setDefaultFontPath("fonts/Arkhip_font.ttf")
//                                            .setFontAttrId(R.attr.fontPath)
//                                            .build());
        setContentView(R.layout.activity_login);

        //Init Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        //Init View
        btnRegister = (Button) findViewById(R.id.btn_register);
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
        final AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        dialog.setTitle("LOGIN ");
        dialog.setMessage("Please use email to login");

        LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
        View layout_login = inflater.inflate(R.layout.layout_login,null);

        final MaterialEditText edtEmail = layout_login.findViewById(R.id.email);
        final MaterialEditText edtPassword = layout_login.findViewById(R.id.password);

        //Set Button
        dialog.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        //CHECK VALIDATION
                        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                            Snackbar.make(rootLayout, "Please Enter Phone Number", Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        //makan

                        if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                            Snackbar.make(rootLayout, "Please Enter Password", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (edtPassword.getText().toString().length() < 6) {
                            Snackbar.make(rootLayout, "Password to short !!!", Snackbar.LENGTH_SHORT).show();
                            return;
                        }


                        //login
                        auth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(rootLayout, "Failed"+e.getMessage(), Snackbar.LENGTH_SHORT)
                                        .show();
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

    private void showRegisterDialog() {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                dialog.setTitle("REGISTER ");
                dialog.setMessage("Please use email to register");

                LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
                View register_layout = inflater.inflate(R.layout.activity_register, null);

                final MaterialEditText edtNama = register_layout.findViewById(R.id.et_nama);
                final MaterialEditText edtEmail = register_layout.findViewById(R.id.et_email);
                final MaterialEditText edtPassword = register_layout.findViewById(R.id.et_password);
                final MaterialEditText edtNoTelp = register_layout.findViewById(R.id.et_no_telp);
                final MaterialEditText edtAlamat = register_layout.findViewById(R.id.et_alamat);

                //Set Button
                dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        //CHECK VALIDATION
                        if (TextUtils.isEmpty(edtNama.getText().toString()))
                        {
                            Snackbar.make(rootLayout, "Please Enter Email Address",Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(edtEmail.getText().toString()))
                        {
                            Snackbar.make(rootLayout, "Please Enter Phone Number",Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(edtPassword.getText().toString()))
                        {
                            Snackbar.make(rootLayout, "Please Enter Password",Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(edtNoTelp.getText().toString()))
                        {
                            Snackbar.make(rootLayout, "Please Enter Phone Number",Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(edtAlamat.getText().toString()))
                        {
                            Snackbar.make(rootLayout, "Please Enter Alamat",Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (edtPassword.getText().toString().length() < 6)
                        {
                            Snackbar.make(rootLayout, "Password to short !!!",Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        //Register new user
                        auth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        //Save user to db
                                        User user = new User();
                                        user.setNama(edtNama.getText().toString());
                                        user.setEmail(edtEmail.getText().toString());
                                        user.setPassword(edtPassword.getText().toString());
                                        user.setNama(edtNoTelp.getText().toString());
                                        user.setNama(edtAlamat.getText().toString());

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