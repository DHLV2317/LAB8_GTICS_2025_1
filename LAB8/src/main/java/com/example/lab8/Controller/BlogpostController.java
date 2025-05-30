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
        return "/List";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("post", new Blogpost());
        return "/Form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute Blogpost post, BindingResult result) {
        if (result.hasErrors()) return "/Form";
        repo.save(post);
        return "redirect:/List";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("post", repo.findById(id).orElseThrow());
        return "/Ver";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", repo.findById(id).orElseThrow());
        return "/Form";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @Valid @ModelAttribute Blogpost post, BindingResult result) {
        if (result.hasErrors()) return "/Form";
        post.setId(id);
        repo.save(post);
        return "redirect:/List";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/List";
    }
}

