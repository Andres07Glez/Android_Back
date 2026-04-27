package mx.unpa.android.dtos.requests;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String password;
}
