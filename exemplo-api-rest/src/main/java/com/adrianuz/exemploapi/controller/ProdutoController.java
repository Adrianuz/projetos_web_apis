package com.adrianuz.exemploapi.controller;

import com.adrianuz.exemploapi.model.Produto;
import com.adrianuz.exemploapi.service.ProdutoService;
import com.adrianuz.common.dto.ApiResponse;
import com.adrianuz.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações com produtos.
 * Demonstra o uso do ApiResponse do módulo common.
 */
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Produto>>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(ApiResponse.success("Produtos listados com sucesso", produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Produto>> buscarPorId(@PathVariable Long id) {
        try {
            Produto produto = produtoService.buscarPorId(id);
            return ResponseEntity.ok(ApiResponse.success(produto));
        } catch (BusinessException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Produto>> criar(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criar(produto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ApiResponse.success("Produto criado com sucesso", novoProduto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Produto>> atualizar(
            @PathVariable Long id, 
            @RequestBody Produto produto) {
        try {
            Produto produtoAtualizado = produtoService.atualizar(id, produto);
            return ResponseEntity.ok(
                ApiResponse.success("Produto atualizado com sucesso", produtoAtualizado));
        } catch (BusinessException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletar(@PathVariable Long id) {
        try {
            produtoService.deletar(id);
            return ResponseEntity.ok(
                ApiResponse.success("Produto deletado com sucesso", null));
        } catch (BusinessException e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(e.getMessage()));
        }
    }
}
