package com.proyecto.proyecto.controllers;

import com.proyecto.proyecto.dao.UsuarioDao;
import com.proyecto.proyecto.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.proyecto.utils.JWTUtil;

@RestController
public class AuthController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        Usuario  usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLogueado != null){
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()),usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";


    }
}
