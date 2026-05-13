package mx.unpa.android.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mx.unpa.android.dtos.requests.UsuarioRequest;
import mx.unpa.android.dtos.responses.UsuarioResponse;
import mx.unpa.android.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.crearUsuario(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponse> obtenerUsuarioByEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerTodos(HttpServletRequest request) {
        String baseUrl=request.getRequestURL().toString()
                .replace(request.getRequestURI(),"");
        return ResponseEntity.ok(usuarioService.obtenerTodos().stream().map(
                u->{
                    u.setFoto(baseUrl+u.getFoto());
                    return u;
                }
        ).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
