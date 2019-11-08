package com.pcsilva.api.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pcsilva.api.gateway.IBGEClient;
import com.pcsilva.api.representation.LocalidadesRepresentation;
import com.pcsilva.api.service.IBGEServiceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("unused")
public class IBGEController {

    @Autowired
    @Lazy
    private EurekaClient discoveryClient;

    @Autowired
    IBGEClient ibgeClient;

    @Autowired
    IBGEServiceDelegate ibgeServiceDelegate;



    @HystrixCommand(defaultFallback = "listarLocalidadesJson", fallbackMethod = "listarLocalidadesJsonPadrao")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8", value = "/json")
    public List<LocalidadesRepresentation> listarLocalidadesJson() {
        return ibgeServiceDelegate.listarCidadesIBGE();
    }
        public List<LocalidadesRepresentation> listarLocalidadesJsonPadrao() {
            return null;
        }




    @HystrixCommand(defaultFallback = "listarIdCidadePorNomeCidade", fallbackMethod = "listarIdCidadePorNomeCidadePadrao")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8", value = "/cidade/{nome}")
    public Long listarIdCidadePorNomeCidade(@PathVariable("nome") String  nomeCidade ) {
        return ibgeServiceDelegate.listarIdCidadePorNomeCidade(nomeCidade);
    }
        public Long listarIdCidadePorNomeCidadePadrao(String  nomeCidade ) {
            return null;
        }




    @HystrixCommand(defaultFallback = "listarIdUfPorNomeCidade" , fallbackMethod = "listarIdUfPorNomeCidadePadrao")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8", value = "/uf/cidade/{nome}")
    public Long listarIdUfPorNomeCidade(@PathVariable("nome") String  nomeCidade ) {
        return ibgeServiceDelegate.listarIdUfPorNomeCidade(nomeCidade);
    }
        public Long listarIdUfPorNomeCidadePadrao( String  nomeCidade ) {
            return null;
        }

    @HystrixCommand(defaultFallback = "listarLocalidadesCSV" , fallbackMethod = "listarLocalidadesCSVPadrao")
    @RequestMapping(method = RequestMethod.GET, produces = "application/octet-stream; charset=UTF-8", value = "/csv")
    public OutputStream listarLocalidadesCSV(HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {

        response.setHeader("Content-Disposition", "attachment; filename=\"cidades.csv\"");

        List<LocalidadesRepresentation> cidaddes = ibgeServiceDelegate.listarCidadesIBGE();

        String[] header = {"idEstado","siglaEstado","regiaoNome","nomeCidade","nomeMesorregiao","nomeFormatado"};

        response.setCharacterEncoding("UTF-8");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter( ), CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

        for (LocalidadesRepresentation localidade : ibgeServiceDelegate.listarCidadesIBGE() ) {

            csvWriter.write(localidade, header);
        }
        csvWriter.close();

        return response.getOutputStream();

    }
        public OutputStream listarLocalidadesCSVPadrao(HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
            return null;
        }


}