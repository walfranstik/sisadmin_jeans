package com.admin_pedidos.jean.controller;

import com.admin_pedidos.jean.entity.Usuario;
import com.admin_pedidos.jean.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String mostrarLogin(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
        }
        return "login";
    }

    @PostMapping("")
    public String procesarLogin(@RequestParam String usuario, 
                               @RequestParam String clave) {
        Usuario user = usuarioService.autenticarUsuario(usuario, clave);
        if (user != null) {
            return "redirect:/productos";
        }
        return "redirect:/?error";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
