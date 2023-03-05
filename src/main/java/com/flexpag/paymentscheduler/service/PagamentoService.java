package com.flexpag.paymentscheduler.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexpag.paymentscheduler.entilty.Pagamento;
import com.flexpag.paymentscheduler.entilty.PagamentoStatus;
import com.flexpag.paymentscheduler.repository.PagamentoRepository;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento inserir(Pagamento pagamento) {
        if(pagamento.getValor() == null || pagamento.getValor().equals(0)|| pagamento.getStatus() == null) {
            pagamento.setStatus(PagamentoStatus.PENDING);
            pagamento.setDataPagamento(null);
        } else {
            pagamento.setStatus(PagamentoStatus.PAID);
            pagamento.setDataPagamento(LocalDateTime.now());
        }

        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listaPagamento() {
        return pagamentoRepository.findAll();
    }

    public Pagamento atualizar(Pagamento pagamento) {
        if(pagamento.getValor() == null || pagamento.getValor().equals(0)|| pagamento.getStatus() == null) {
            pagamento.setStatus(PagamentoStatus.PENDING);
            pagamento.setDataPagamento(null);
        } else {
            pagamento.setStatus(PagamentoStatus.PAID);
            pagamento.setDataPagamento(LocalDateTime.now());
        }

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento agendarDataDPagamento(Long id, LocalDateTime Datapagamento) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado!"));
        pagamento.setDataPagamento(Datapagamento);

        return pagamentoRepository.save(pagamento);
    }

    public void deletar(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public void removerPorId(Long id, Pagamento pagamento) {
        if(pagamento.getStatus().equals(PagamentoStatus.PENDING)){
            pagamentoRepository.deleteById(id);
        }
    }
}
