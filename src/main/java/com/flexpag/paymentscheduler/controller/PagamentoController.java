package com.flexpag.paymentscheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flexpag.paymentscheduler.entilty.Pagamento;
import com.flexpag.paymentscheduler.entilty.PagamentoStatus;
import com.flexpag.paymentscheduler.service.PagamentoService;

import java.util.List;

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> pagamentoDelete(@PathVariable("id") Long id) {
        Pagamento pagamento = pagamentoService.buscarPorId(id);

        if(pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        if(pagamento.getStatus() == PagamentoStatus.PAID) {
            return ResponseEntity.badRequest().build();
        }

        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/paid")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pagamento atualizarPagamento(@PathVariable("id") Long id) {
        Pagamento pagou = pagamentoService.buscarPorId(id);
        
        pagamentoService.atualizarPagamento(pagou);

        return pagou;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id,@RequestBody Pagamento pagamento) {
        Pagamento atualiarHora = pagamentoService.buscarPorId(id);

        if(pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        if(pagamento.getStatus() == PagamentoStatus.PAID) {
            return ResponseEntity.badRequest().build();
        }
        atualiarHora.setDataHoraPagamento(pagamento.getDataHoraPagamento());

        pagamentoService.atualizar(atualiarHora);
        return ResponseEntity.noContent().build();

    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pagamento verificarPagamentoPorId(@PathVariable("id") Long id) {
        return pagamentoService.buscarPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pagamento> listaPagamento() {
        return pagamentoService.listaPagamento();
    }
}
