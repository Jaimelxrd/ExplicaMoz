package mz.dev.lxrd.ExplicaMoz.dto;

public class AdminLoginDTO {

    private String username;
    private String password;

    // Construtor padrão
    public AdminLoginDTO() {}

    // Construtor com parâmetros
    public AdminLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}