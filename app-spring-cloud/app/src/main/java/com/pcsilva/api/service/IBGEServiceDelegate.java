package com.pcsilva.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pcsilva.api.config.IBGEScope;
import com.pcsilva.api.exception.ServicoIndisponivelException;
import com.pcsilva.api.gateway.IBGEClient;
import com.pcsilva.api.representation.EstadoRepresentation;
import com.pcsilva.api.representation.LocalidadesRepresentation;
import com.pcsilva.api.representation.MunicipioRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class IBGEServiceDelegate {

    @Resource(name = "applicationScopedBean")
    private IBGEScope ibgeScope;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IBGEClient ibgeClient;

    @PostConstruct
    public void iniciaEscopo() {

        ibgeScope.setCidades( listarLocalidades() );

    }




    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod="listarIdCidadePorNomeCidadePadrao", defaultFallback = "listarIdCidadePorNomeCidade")
    public Long listarIdCidadePorNomeCidade(String nomeCidade) {

        return ibgeScope.getIdCidade(nomeCidade);

    }
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public Long listarIdCidadePorNomeCidadePadrao(String nomeCidade)  {
            return null;

        }





    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod="listarIdUfPorNomeCidadePadrao", defaultFallback = "listarIdUfPorNomeCidade")
    public Long listarIdUfPorNomeCidade(String nomeCidade) {

        return ibgeScope.getIdUf(nomeCidade);

    }
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public Long listarIdUfPorNomeCidadePadrao(String nomeCidade)  {

            return null;

        }




    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod="listarCidadesIBGEPadrao", defaultFallback = "listarCidadesIBGE")
    public List<LocalidadesRepresentation> listarCidadesIBGE() {

        return ibgeScope.getCidades();

    }
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public List<LocalidadesRepresentation> listarCidadesIBGEPadrao()  {

            return null;

        }




    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod="listarLocalidadesPadrao", defaultFallback = "listarLocalidades")
    private List<LocalidadesRepresentation> listarLocalidades()  throws ServicoIndisponivelException {

            List<EstadoRepresentation> estados = ibgeClient.listarEstados();

            List<LocalidadesRepresentation> localidades = new ArrayList<LocalidadesRepresentation>();

            for( EstadoRepresentation estado : estados) {

                List<MunicipioRepresentation> municipios = ibgeClient.listarMunicipios(estado.getId());

                for(MunicipioRepresentation municipio : municipios) {

                    LocalidadesRepresentation localidade = new LocalidadesRepresentation(
                            estado.getId(),
                            estado.getSigla(),
                            estado.getRegiao().getNome(),
                            municipio.getNome(),
                            municipio.getMicrorRegiao().getMesoRegiao().getNome(),
                            municipio.getId());

                    localidades.add( localidade );

                }

            }

        return localidades;

    }
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        private List<LocalidadesRepresentation> listarLocalidadesPadrao() {

            return null;

        }




    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();

    }




}
