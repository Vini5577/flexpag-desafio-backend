package com.flexpag.paymentscheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpag.paymentscheduler.entilty.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    
}
