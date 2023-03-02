package hospital.model;

import java.time.LocalDate;

public class Doctor {
    private Long id;
    private String name;
    private String login;
    private String password;
    private LocalDate birthDate;

    public Doctor() {
    }

    public Doctor(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long doctorId) {
        this.id = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Doctor{"
                + "doctorId=" + id
                + ", name='" + name + '\''
                + ", birthDate=" + birthDate
                + '}';
    }
}
