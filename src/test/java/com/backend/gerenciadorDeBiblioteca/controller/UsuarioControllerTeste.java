package com.backend.gerenciadorDeBiblioteca.controller;

import com.backend.gerenciadorDeBiblioteca.model.Usuario;
import com.backend.gerenciadorDeBiblioteca.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // DEPRECIADO 3.4.0 POREM FUNCIONA
    private UsuarioService usuarioService;

    @Test
    void deveBuscarUsuarioPorId() throws Exception {
        UUID id = UUID.randomUUID();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);
        usuario.setNome("Maria");

        Mockito.when(usuarioService.buscarPorId(id)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/{id}", id)
                        .with(user("admin").password("admin123").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.nome").value("Maria"));
    }
}