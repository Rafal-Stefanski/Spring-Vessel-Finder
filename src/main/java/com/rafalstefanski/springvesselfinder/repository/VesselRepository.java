package com.rafalstefanski.springvesselfinder.repository;

import com.rafalstefanski.springvesselfinder.dto.VesselDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VesselRepository extends CrudRepository<VesselDto, Long> {
}
