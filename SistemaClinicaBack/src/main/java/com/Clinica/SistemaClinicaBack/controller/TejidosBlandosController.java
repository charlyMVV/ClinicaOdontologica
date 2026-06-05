package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.entity.TejidosBlandos;
import com.Clinica.SistemaClinicaBack.repository.TejidosBlandosRepository;
import com.Clinica.SistemaClinicaBack.service.HistoriaClinicaService;
import com.Clinica.SistemaClinicaBack.service.TejidosBlandosService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tejidosblandos")
@CrossOrigin(origins = "http://localhost:4200")
public class TejidosBlandosController {

    private final TejidosBlandosService tejidosBlandosService;
    private final TejidosBlandosRepository tejidosBlandosRepository;
    private final HistoriaClinicaService historiaClinicaService;

    public TejidosBlandosController(
            TejidosBlandosService tejidosBlandosService,
            TejidosBlandosRepository tejidosBlandosRepository,
            HistoriaClinicaService historiaClinicaService) {

        this.tejidosBlandosService = tejidosBlandosService;
        this.tejidosBlandosRepository = tejidosBlandosRepository;
        this.historiaClinicaService = historiaClinicaService;
    }

    @GetMapping
    public List<TejidosBlandos> findAll() {
        return tejidosBlandosService.findAll();
    }

    @GetMapping("/{idTejidosBlandos}")
    public TejidosBlandos findById(@PathVariable("idTejidosBlandos") Integer id) {
        return tejidosBlandosService.findById(id);
    }

    @DeleteMapping("/{idTejidosBlandos}")
    public void deleteById(@PathVariable("idTejidosBlandos") Integer id) {
        tejidosBlandosService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<TejidosBlandos> save(@RequestBody TejidosBlandos tejidosBlandos) {

        TejidosBlandos guardado = tejidosBlandosService.save(tejidosBlandos);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(guardado.getCurp());

        hc.setTejidosBlandos(guardado);

        historiaClinicaService.update(hc);

        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenTejidosBlandosPorCurp(@PathVariable String curp) {
        boolean existen = tejidosBlandosRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }

    @PutMapping("/curp/{curp}")
    public TejidosBlandos updateTejidosBlandos(
            @PathVariable String curp,
            @RequestBody TejidosBlandos tejidosBlandos) {

        TejidosBlandos tejidosBlandosdb = tejidosBlandosService.findByCurp(curp);

        if (!tejidosBlandosdb.getCurp().equals(curp)) {
            throw new IllegalArgumentException("La CURP de tejidos blandos no se puede modificar.");
        }

        tejidosBlandosdb.setGanglios(tejidosBlandos.getGanglios());
        tejidosBlandosdb.setGlandulasSalivales(tejidosBlandos.getGlandulasSalivales());
        tejidosBlandosdb.setLabioExterno(tejidosBlandos.getLabioExterno());
        tejidosBlandosdb.setBordeBermellon(tejidosBlandos.getBordeBermellon());
        tejidosBlandosdb.setLabioInterno(tejidosBlandos.getLabioInterno());
        tejidosBlandosdb.setComisuras(tejidosBlandos.getComisuras());
        tejidosBlandosdb.setCarrillos(tejidosBlandos.getCarrillos());
        tejidosBlandosdb.setFondoDeSaco(tejidosBlandos.getFondoDeSaco());
        tejidosBlandosdb.setFrenillos(tejidosBlandos.getFrenillos());
        tejidosBlandosdb.setLenguaTercioMedio(tejidosBlandos.getLenguaTercioMedio());
        tejidosBlandosdb.setPaladarDuro(tejidosBlandos.getPaladarDuro());
        tejidosBlandosdb.setPaladarBlando(tejidosBlandos.getPaladarBlando());
        tejidosBlandosdb.setIstmoBucofaringe(tejidosBlandos.getIstmoBucofaringe());
        tejidosBlandosdb.setLenguaDorso(tejidosBlandos.getLenguaDorso());
        tejidosBlandosdb.setLenguaBordes(tejidosBlandos.getLenguaBordes());
        tejidosBlandosdb.setLenguaVentral(tejidosBlandos.getLenguaVentral());
        tejidosBlandosdb.setPisoBoca(tejidosBlandos.getPisoBoca());
        tejidosBlandosdb.setDientes(tejidosBlandos.getDientes());
        tejidosBlandosdb.setMucosaAlveolar(tejidosBlandos.getMucosaAlveolar());
        tejidosBlandosdb.setEncia(tejidosBlandos.getEncia());

        TejidosBlandos actualizado = tejidosBlandosService.update(tejidosBlandosdb);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(actualizado.getCurp());

        hc.setTejidosBlandos(actualizado);

        historiaClinicaService.update(hc);

        return actualizado;
    }
}