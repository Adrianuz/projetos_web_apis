package com.adrianuz.exemploapi.service;

import com.adrianuz.exemploapi.model.Produto;
import com.adrianuz.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Serviço de exemplo para gerenciar produtos.
 * Usa um Map em memória como armazenamento (não persistente).
 */
@Service
public class ProdutoService {

    private final Map<Long, Produto> produtos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public ProdutoService() {
        // Inicializa com alguns produtos de exemplo
        criar(new Produto(null, "Notebook", "Notebook Dell Inspiron", 3500.00));
        criar(new Produto(null, "Mouse", "Mouse sem fio Logitech", 150.00));
        criar(new Produto(null, "Teclado", "Teclado mecânico RGB", 450.00));
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());
    }

    public Produto buscarPorId(Long id) {
        Produto produto = produtos.get(id);
        if (produto == null) {
            throw new BusinessException("PRODUTO_NAO_ENCONTRADO", 
                "Produto com ID " + id + " não encontrado");
        }
        return produto;
    }

    public Produto criar(Produto produto) {
        Long id = counter.getAndIncrement();
        produto.setId(id);
        produtos.put(id, produto);
        return produto;
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        if (!produtos.containsKey(id)) {
            throw new BusinessException("PRODUTO_NAO_ENCONTRADO", 
                "Produto com ID " + id + " não encontrado");
        }
        produtoAtualizado.setId(id);
        produtos.put(id, produtoAtualizado);
        return produtoAtualizado;
    }

    public void deletar(Long id) {
        if (!produtos.containsKey(id)) {
            throw new BusinessException("PRODUTO_NAO_ENCONTRADO", 
                "Produto com ID " + id + " não encontrado");
        }
        produtos.remove(id);
    }
}
