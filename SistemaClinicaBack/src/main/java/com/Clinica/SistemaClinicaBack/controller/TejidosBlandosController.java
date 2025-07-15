package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.TejidosBlandos;
import com.Clinica.SistemaClinicaBack.repository.TejidosBlandosRepository;
import com.Clinica.SistemaClinicaBack.service.TejidosBlandosService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author charly michel
 */
@RestController
@RequestMapping("/api/tejidosblandos")
@CrossOrigin(origins = "http://localhost:4200")
public class TejidosBlandosController {

    private final TejidosBlandosService tejidosBlandosService;
    private final TejidosBlandosRepository tejidosBlandosRepository;

    public TejidosBlandosController(TejidosBlandosService tejidosBlandosService, TejidosBlandosRepository tejidosBlandosRepository) {
        this.tejidosBlandosService = tejidosBlandosService;
        this.tejidosBlandosRepository = tejidosBlandosRepository;
    }

    //localhost:8080/api/tejidosblandos
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
        return ResponseEntity.ok(tejidosBlandosService.save(tejidosBlandos));
    }

    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenTejidosBlandosPorCurp(@PathVariable String curp) {
        boolean existen = tejidosBlandosRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }

    @PutMapping
    public TejidosBlandos updateTejidosBlandos(@RequestBody TejidosBlandos tejidosBlandos) {

        TejidosBlandos tejidosBlandosdb = tejidosBlandosService.findById(tejidosBlandos.getIdTejidosBlandos());

        if (!tejidosBlandosdb.getCurp().equals(tejidosBlandos.getCurp())) {
            throw new IllegalArgumentException("La CURP  del los tejidos blandos no se puede modificar.");
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
        
        return tejidosBlandosService.update(tejidosBlandosdb);
    }

}
