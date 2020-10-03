package com.ufcg.psoft.mercadofacil.controller;


import com.ufcg.psoft.mercadofacil.DTO.CompraDTO;
import com.ufcg.psoft.mercadofacil.DTO.ItemCompraDTO;
import com.ufcg.psoft.mercadofacil.DTO.VendaDTO;
import com.ufcg.psoft.mercadofacil.enums.PagamentoEnum;
import com.ufcg.psoft.mercadofacil.model.*;
import com.ufcg.psoft.mercadofacil.repositories.CarrinhoRepository;
import com.ufcg.psoft.mercadofacil.repositories.CompraRepository;
import com.ufcg.psoft.mercadofacil.repositories.ItemCarrinhoRepository;
import com.ufcg.psoft.mercadofacil.repositories.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.services.PagamentoService;
import com.ufcg.psoft.mercadofacil.util.CustomErrorType;
import exceptions.EstoqueInsuficienteException;
import exceptions.TipoDePagamentoInvalido;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @RequestMapping(value = "/carrinho/produtos", method = RequestMethod.POST)
    public ResponseEntity<?> finalizarCompra(@RequestBody(required = false) PagamentoEnum tipoPagamento) throws TipoDePagamentoInvalido {
        long idCarrinho = 1;

        Optional<Carrinho> carrinhoOpt = this.carrinhoRepository.findById(idCarrinho);
        Carrinho carrinho;
        if(!carrinhoOpt.isPresent()) carrinho = new Carrinho();
        else carrinho = carrinhoOpt.get();

        if(carrinho.isEmpty())
            return new ResponseEntity<>(new CustomErrorType("Carrinho vazio"), HttpStatus.BAD_REQUEST);
        try {
            carrinho.finalizarCompra();
        } catch (EstoqueInsuficienteException e) {
            return new ResponseEntity<>(new CustomErrorType(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        List<ItemCompra> itensCompra = List.copyOf(carrinho.getProdutos())
                .stream()
                .map(itemCarrinho -> new ItemCompra(itemCarrinho.getProduto(), itemCarrinho.getQuantidade()))
                .collect(Collectors.toList());

        double valorPagamento = this.pagamentoService.getPagamento(tipoPagamento, carrinho.getPrecoTotal());
        Compra compra = new Compra(itensCompra, this.pagamentoService.getNomePagamento(tipoPagamento), valorPagamento, carrinho.getId());

        this.compraRepository.save(compra);
        carrinho.limpaCarrinho();
        this.carrinhoRepository.save(carrinho);
        return new ResponseEntity<>(compra, HttpStatus.OK);

    }

    @RequestMapping(value ="/carrinho/produto/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> adicionaProduto(@PathVariable("id") long idProduto, @RequestBody VendaDTO venda) {
        long idCarrinho = 1;
        Optional<Carrinho> carrinhoOpt = this.carrinhoRepository.findById(idCarrinho);
        Carrinho carrinho;
        if (!carrinhoOpt.isPresent()) carrinho = new Carrinho();
        else carrinho = carrinhoOpt.get();

        Optional<Produto> produtoOpt = this.produtoRepository.findById(idProduto);
        Produto produto;
        if (!produtoOpt.isPresent())
            return new ResponseEntity<>(new CustomErrorType("Produto inexistente"), HttpStatus.NOT_FOUND);

        produto = produtoOpt.get();
        if (produto.situacao == produto.INDISPONIVEL)
            return new ResponseEntity<>(new CustomErrorType("Produto indisponivel"), HttpStatus.BAD_REQUEST);

        ItemCarrinho newItemCarrinho;
        try {
            newItemCarrinho = carrinho.addProduto(produto, venda.getQuantidade());
        } catch (EstoqueInsuficienteException e) {
            return new ResponseEntity<>(new CustomErrorType("Mais produtos que o estoque possui"), HttpStatus.BAD_REQUEST);
        }

        this.carrinhoRepository.save(carrinho);
        return new ResponseEntity<>(newItemCarrinho, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/carrinho/produtos", method = RequestMethod.DELETE)
    public ResponseEntity<?> cancelarCompras(){
        long idCarrinho = 1;
        Optional<Carrinho> carrinhoOpt = this.carrinhoRepository.findById(idCarrinho);
        if(!carrinhoOpt.isPresent())
            return new ResponseEntity<>(new CustomErrorType("Carrinho inexistente"), HttpStatus.NOT_FOUND);

        Carrinho carrinho = carrinhoOpt.get();
        carrinho.limpaCarrinho();

        this.carrinhoRepository.save(carrinho);
        Map<String, String> returnMessage = new HashMap<>();
        returnMessage.put("message", "Compras descartadas com sucesso");
        return new ResponseEntity<>(returnMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/carrinho/{id}/compras/", method = RequestMethod.GET)
    public ResponseEntity<?> ultimasComprasRealizadas(@PathVariable("id") long idCarrinho){

        List<Compra> compras = this.compraRepository.findComprasByCarrinhoId(idCarrinho);

        if(compras.isEmpty()) return new ResponseEntity<>(new CustomErrorType("Registro de carrinho inexistente"), HttpStatus.NOT_FOUND);

        List<CompraDTO> comprasDTO = compras.stream()
                .map(compra -> new CompraDTO(compra.getItens(), compra.getData())
                )
                .collect(Collectors.toList());
        return new ResponseEntity<>(comprasDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/carrinho/compras/{id}")
    public ResponseEntity<?> compraEspecificaRealizada(@PathVariable("id") long idCompra){
        Optional<Compra> compraOpt = this.compraRepository.findById(idCompra);

        if(!compraOpt.isPresent())
            return new ResponseEntity<>(new CustomErrorType("Compra não encontrada"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(compraOpt.get(), HttpStatus.OK);
    }
}
