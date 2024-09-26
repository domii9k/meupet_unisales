package com.meupet.api.Repository;

import com.meupet.api.Model.HistoricoPesoAltura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoPesoAlturaRepository extends JpaRepository<HistoricoPesoAltura, Long> {
}
