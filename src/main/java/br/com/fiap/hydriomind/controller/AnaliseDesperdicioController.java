package br.com.fiap.hydriomind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.hydriomind.dto.AnaliseDesperdicioDTO;
import br.com.fiap.hydriomind.service.AnaliseDesperdicioService;

import java.util.List;

@Controller
@RequestMapping("/analises-desperdicio")
public class AnaliseDesperdicioController {

    @Autowired
    private AnaliseDesperdicioService analiseDesperdicioService;

    // Listar todas as análises de desperdício
    @GetMapping("/listar")
    public String listarAnalisesDesperdicio(Model model) {
        List<AnaliseDesperdicioDTO> analises = analiseDesperdicioService.listarTodasAnalises();
        model.addAttribute("analises", analises);
        return "analises-desperdicio/listar";
    }

    // Mostrar detalhes de uma análise de desperdício por ID
    @GetMapping("/{id}")
    public String obterAnaliseDesperdicioPorId(@PathVariable Long id, Model model) {
        return analiseDesperdicioService.encontrarAnalisePorId(id)
                .map(analise -> {
                    model.addAttribute("analise", analise);
                    return "analises-desperdicio/detalhes";
                })
                .orElse("error/404");
    }

    // Mostrar o formulário de cadastro de nova análise de desperdício
    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("analiseDTO", new AnaliseDesperdicioDTO());
        return "analises-desperdicio/cadastrar";
    }

    // Cadastrar uma nova análise de desperdício
    @PostMapping("/cadastrar")
    public String cadastrarAnaliseDesperdicio(@ModelAttribute AnaliseDesperdicioDTO analiseDTO, RedirectAttributes redirectAttributes) {
        try {
            analiseDesperdicioService.salvarAnalise(analiseDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Análise cadastrada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar a análise. Tente novamente.");
        }
        return "redirect:/analises-desperdicio/listar";
    }

    // Mostrar o formulário de edição para análise de desperdício existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        return analiseDesperdicioService.encontrarAnalisePorId(id)
                .map(analise -> {
                    model.addAttribute("analiseDTO", analise);
                    return "analises-desperdicio/editar";
                })
                .orElse("error/404");
    }

    // Atualizar uma análise de desperdício existente
    @PostMapping("/editar/{id}")
    public String atualizarAnaliseDesperdicio(@PathVariable Long id, @ModelAttribute AnaliseDesperdicioDTO analiseDTO, RedirectAttributes redirectAttributes) {
        try {
            analiseDTO.setId(id);
            analiseDesperdicioService.salvarAnalise(analiseDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Análise atualizada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar a análise. Tente novamente.");
        }
        return "redirect:/analises-desperdicio/listar";
    }

    // Confirmar a deleção de uma análise de desperdício
    @GetMapping("/deletar/{id}")
    public String confirmarDelecao(@PathVariable Long id, Model model) {
        return analiseDesperdicioService.encontrarAnalisePorId(id)
                .map(analise -> {
                    model.addAttribute("analise", analise);
                    return "analises-desperdicio/deletar";
                })
                .orElse("error/404");
    }

    // Deletar a análise de desperdício
    @PostMapping("/deletar/{id}")
    public String deletarAnaliseDesperdicio(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            analiseDesperdicioService.deletarAnalise(id);
            redirectAttributes.addFlashAttribute("successMessage", "Análise deletada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar a análise. Tente novamente.");
        }
        return "redirect:/analises-desperdicio/listar";
    }
}
