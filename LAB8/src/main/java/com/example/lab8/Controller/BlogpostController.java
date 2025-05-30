package com.example.lab8.Controller;

import com.example.lab8.Entity.Blogpost;
import com.example.lab8.Repository.BlogpostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class BlogpostController {

    @Autowired
    private BlogpostRepository repo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("posts", repo.findAll());
        return "listar";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("post", new Blogpost());
        return "formulario";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute Blogpost post, BindingResult result) {
        if (result.hasErrors()) return "formulario";
        repo.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("post", repo.findById(id).orElseThrow());
        return "ver";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", repo.findById(id).orElseThrow());
        return "formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @Valid @ModelAttribute Blogpost post, BindingResult result) {
        if (result.hasErrors()) return "formulario";
        post.setId(id);
        repo.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/posts";
    }
}

