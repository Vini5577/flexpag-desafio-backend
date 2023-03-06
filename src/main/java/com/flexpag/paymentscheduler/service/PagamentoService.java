package com.flexpag.paymentscheduler.service;

import java.time.LocalTime;
import java.util.List;

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
        pagamento.setStatus(PagamentoStatus.PENDING);

        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listaPagamento() {
        return pagamentoRepository.findAll();
    }

    public Pagamento atualizar(Pagamento pagamento) {
        if (pagamento.getValor() == null) {
            pagamento.setStatus(PagamentoStatus.PENDING);
        }
        
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizarPagamento(Pagamento pagamento) {
        pagamento.setStatus(PagamentoStatus.PAID);
        pagamento.setHoraPagamento(LocalTime.now());

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento agendarPagamento(Long id, LocalTime horaPagamento) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado!"));
        pagamento.setHoraPagamento(horaPagamento);

        return pagamentoRepository.save(pagamento);
    }

    public void deletar(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public void removerPorId(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
