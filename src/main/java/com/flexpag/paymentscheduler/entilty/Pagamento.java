package com.flexpag.paymentscheduler.entilty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name="valor")
    private BigDecimal valor;

    @Column(name = "data-do-pagamento")
    private LocalDateTime dataPagamento;
    
    @Column(name="status")
    private PagamentoStatus status; // pendig, paid


}
