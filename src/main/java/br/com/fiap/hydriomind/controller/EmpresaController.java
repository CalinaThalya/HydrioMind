package br.com.fiap.hydriomind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.hydriomind.dto.EmpresaDTO;
import br.com.fiap.hydriomind.service.EmpresaService;

import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/listar")
    public String listarEmpresas(Model model) {
        List<EmpresaDTO> empresas = empresaService.listarTodasEmpresas();
        model.addAttribute("empresas", empresas);
        return "empresas/listar";
    }

    @GetMapping("/{id}")
    public String obterEmpresaPorId(@PathVariable Long id, Model model) {
        return empresaService.encontrarEmpresaPorId(id)
                .map(empresa -> {
                    model.addAttribute("empresa", empresa);
                    return "empresas/detalhes";
                })
                .orElse("error/404");
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("empresaDTO", new EmpresaDTO());
        return "empresas/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarEmpresa(@ModelAttribute EmpresaDTO empresaDTO) {
        empresaService.salvarEmpresa(empresaDTO);
        return "redirect:/empresas/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        return empresaService.encontrarEmpresaPorId(id)
                .map(empresa -> {
                    model.addAttribute("empresaDTO", empresa);
                    return "empresas/editar";
                })
                .orElse("error/404");
    }

    @PostMapping("/editar/{id}")
    public String atualizarEmpresa(@PathVariable Long id, @ModelAttribute EmpresaDTO empresaDTO) {
        empresaDTO.setId(id);
        empresaService.salvarEmpresa(empresaDTO);
        return "redirect:/empresas/listar";
    }

    // Novo método GET para confirmar a deleção
    @GetMapping("/deletar/{id}")
    public String confirmarDelecao(@PathVariable Long id, Model model) {
        return empresaService.encontrarEmpresaPorId(id)
                .map(empresa -> {
                    model.addAttribute("empresa", empresa);
                    return "empresas/deletar"; // Nome da página de confirmação de deleção
                })
                .orElse("error/404");
    }

    // Método POST para deletar a empresa
    @PostMapping("/deletar/{id}")
    public String deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresa(id);
        return "redirect:/empresas/listar";
    }
}
