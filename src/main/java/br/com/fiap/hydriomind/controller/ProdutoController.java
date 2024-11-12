package br.com.fiap.hydriomind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.hydriomind.dto.ProdutoDTO;
import br.com.fiap.hydriomind.service.ProdutoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        List<ProdutoDTO> produtos = produtoService.listarTodosProdutos();
        model.addAttribute("produtos", produtos);
        return "produtos/listar";
    }

    @GetMapping("/{id}")
    public String obterProdutoPorId(@PathVariable Long id, Model model) {
        return produtoService.encontrarProdutoPorId(id)
                .map(produto -> {
                    model.addAttribute("produto", produto);
                    return "produtos/detalhes";
                })
                .orElse("error/404");
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        model.addAttribute("titulo", "Cadastrar Produto");
        return "produtos/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarProduto(@ModelAttribute @Valid ProdutoDTO produtoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "produtos/cadastrar";
        }
        produtoService.salvarProduto(produtoDTO);
        return "redirect:/produtos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Optional<ProdutoDTO> produto = produtoService.encontrarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produtoDTO", produto.get());
            model.addAttribute("titulo", "Editar Produto");
            return "produtos/editar"; // Certifique-se de que este template existe
        } else {
            return "error/404"; // Página de erro se o produto não for encontrado
        }
    }

    @PostMapping("/editar/{id}")
    public String atualizarProduto(@PathVariable Long id, @ModelAttribute @Valid ProdutoDTO produtoDTO, BindingResult result) {
        if (result.hasErrors()) {
            produtoDTO.setId(id);
            return "produtos/editar";
        }
        produtoDTO.setId(id);
        produtoService.salvarProduto(produtoDTO);
        return "redirect:/produtos/listar";
    }

    @GetMapping("/deletar/{id}")
    public String confirmarDelecao(@PathVariable Long id, Model model) {
        return produtoService.encontrarProdutoPorId(id)
                .map(produto -> {
                    model.addAttribute("produto", produto);
                    return "produtos/deletar";
                })
                .orElse("error/404");
    }

    @PostMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return "redirect:/produtos/listar";
    }
}
