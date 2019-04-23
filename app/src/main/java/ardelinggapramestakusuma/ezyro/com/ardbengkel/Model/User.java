package ardelinggapramestakusuma.ezyro.com.ardbengkel.Model;

public class User {
    private String nama,email,password,no_telp,alamat;

    public User() {
    }

    public User(String nama, String email, String password, String no_telp, String alamat) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.no_telp = no_telp;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
