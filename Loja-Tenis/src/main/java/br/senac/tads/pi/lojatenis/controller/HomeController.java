/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.pi.lojatenis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alexsandro
 */

@Controller
@RequestMapping("/home")
public class HomeController {
    
    @GetMapping({"", "/"})
    public String home(Model model){
        model.addAttribute("message", "Olá !");
        return "home/index";
    }
    
}
