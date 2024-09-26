package com.meupet.api.Repository;

import com.meupet.api.Model.Vacinas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacinasRepository extends JpaRepository<Vacinas, Long> {
}
