package com.vuelos.reservations.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Hidden //Sirve para ocultar el endpoint de la documentación
@Controller
@RequestMapping("documentation")
public class DocumentationController {

    @GetMapping
    @ResponseBody
    public void redirectToDocumentation(HttpServletResponse response) {

        try {
            response.sendRedirect("swagger-ui.html");
        } catch (Exception e) {
            StringBuffer sb = new StringBuffer("UNEXPECTED ERROR: ");
            if (e.getMessage() != null) {
                sb.append(e.getMessage());
            }
        }
    }
}
