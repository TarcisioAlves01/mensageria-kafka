package br.com.tarcisio.api_boleto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarcisio.api_boleto.dto.BoletoDTO;
import br.com.tarcisio.api_boleto.dto.BoletoRequestDTO;
import br.com.tarcisio.api_boleto.service.BoletoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Controle de boleto")
@RestController
@RequestMapping("/boleto")
public class BoletoController {

    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping("/{codigoBarras}")
    public ResponseEntity<Object> buscarBoletoCodigoBarras(@PathVariable("codigoBarras") String codigoBarras) {
        var boletoDTO = boletoService.buscarBoletoPorCodigoBarras(codigoBarras);
        return ResponseEntity.ok(boletoDTO);
    }

    @PostMapping
    public ResponseEntity<BoletoDTO> salvar(@Valid @RequestBody BoletoRequestDTO boletoRequestDTO) {
        var boleto = boletoService.salvar(boletoRequestDTO.getCodigoBarras());
        return new ResponseEntity<>(boleto, HttpStatus.CREATED);
    }

}
