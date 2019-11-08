package com.pcsilva.api.gateway;

import com.pcsilva.api.representation.EstadoRepresentation;
import com.pcsilva.api.representation.MunicipioRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name= "ibge", url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados" )
public interface IBGEClient {

    @RequestMapping(method = RequestMethod.GET, value = "/", consumes = "application/json")
    List<EstadoRepresentation> listarEstados();

    @RequestMapping(method = RequestMethod.GET, value = "/{uf}/municipios", consumes = "application/json")
    List<MunicipioRepresentation> listarMunicipios(@PathVariable("uf") Long id);

}


