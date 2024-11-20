package br.com.fiap.hydriomind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.hydriomind.dto.RecomendacaoDTO;
import br.com.fiap.hydriomind.service.RecomendacaoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recomendacoes")
public class RecomendacaoController {

    @Autowired
    private RecomendacaoService recomendacaoService;

    @GetMapping("/listar")
    public String listarRecomendacoes(Model model) {
        System.out.println("Iniciando listagem de recomendações");
        List<RecomendacaoDTO> recomendacoes = recomendacaoService.listarTodasRecomendacoes();
        System.out.println("Recomendações recuperadas: " + recomendacoes.size());
        model.addAttribute("recomendacoes", recomendacoes);
        return "recomendacoes/listar";
    }
    

    @GetMapping("/{id}")
    public String obterRecomendacaoPorId(@PathVariable Long id, Model model) {
        return recomendacaoService.encontrarRecomendacaoPorId(id)
                .map(recomendacao -> {
                    model.addAttribute("recomendacao", recomendacao);
                    return "recomendacoes/detalhes";
                })
                .orElse("error/404");
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("recomendacaoDTO", new RecomendacaoDTO());
        model.addAttribute("titulo", "Cadastrar Recomendação");
        return "recomendacoes/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarRecomendacao(@ModelAttribute @Valid RecomendacaoDTO recomendacaoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "recomendacoes/cadastrar";
        }
        recomendacaoService.salvarRecomendacao(recomendacaoDTO);
        return "redirect:/recomendacoes/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarRecomendacao(@PathVariable Long id, Model model) {
        Optional<RecomendacaoDTO> recomendacao = recomendacaoService.encontrarRecomendacaoPorId(id);
        if (recomendacao.isPresent()) {
            model.addAttribute("recomendacaoDTO", recomendacao.get());
            model.addAttribute("titulo", "Editar Recomendação");
            return "recomendacoes/editar";
        } else {
            return "error/404";
        }
    }

    @PostMapping("/editar/{id}")
    public String atualizarRecomendacao(@PathVariable Long id, @ModelAttribute @Valid RecomendacaoDTO recomendacaoDTO, BindingResult result) {
        if (result.hasErrors()) {
            recomendacaoDTO.setId(id);
            return "recomendacoes/editar";
        }
        recomendacaoDTO.setId(id);
        recomendacaoService.salvarRecomendacao(recomendacaoDTO);
        return "redirect:/recomendacoes/listar";
    }

    @GetMapping("/deletar/{id}")
    public String confirmarDelecao(@PathVariable Long id, Model model) {
        return recomendacaoService.encontrarRecomendacaoPorId(id)
                .map(recomendacao -> {
                    model.addAttribute("recomendacao", recomendacao);
                    return "recomendacoes/deletar";
                })
                .orElse("error/404");
    }

    @PostMapping("/deletar/{id}")
    public String deletarRecomendacao(@PathVariable Long id) {
        recomendacaoService.deletarRecomendacao(id);
        return "redirect:/recomendacoes/listar";
    }
}
