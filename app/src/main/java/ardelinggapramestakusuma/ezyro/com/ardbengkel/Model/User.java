package ardelinggapramestakusuma.ezyro.com.ardbengkel.Model;

public class User {
    private String email, password, nama, noTelp;

    public User() {
    }

    public User(String email, String password, String nama, String noTelp) {
        this.email = email;
        this.password = password;
        this.nama = nama;
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
}
