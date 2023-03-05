package com.flexpag.paymentscheduler.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flexpag.paymentscheduler.entilty.Pagamento;
import com.flexpag.paymentscheduler.entilty.PagamentoStatus;
import com.flexpag.paymentscheduler.service.PagamentoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento criarPagamento(@RequestBody Pagamento pagamento) {
        return pagamentoService.inserir(pagamento);
    }

    @DeleteMapping("/{id}")
    public void pagamentoDelete(@PathVariable Long id) {
        pagamentoService.deletar(id);
    }

    @PutMapping("/{id}/date")
    public Pagamento agendarPagamento(@PathVariable Long id, @RequestBody LocalDateTime dataPagamento) {
        return pagamentoService.agendarDataDPagamento(id, dataPagamento);
    }

    @GetMapping("/{id}")
    public Pagamento verificarPagamentoPorId(@PathVariable("id") Long id) {
        return pagamentoService.buscarPorId(id);
    }
}
