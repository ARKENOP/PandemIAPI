package epsi.pandemia.pandemiapi.controller;

import epsi.pandemia.pandemiapi.entity.Disease;
import epsi.pandemia.pandemiapi.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diseases")
public class DiseaseController {
    @Autowired
    private DiseaseRepository repository;

    @GetMapping
    public List<Disease> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Disease> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Disease create(@RequestBody Disease disease) {
        return repository.save(disease);
    }

    @PutMapping("/{id}")
    public Disease update(@PathVariable Long id, @RequestBody Disease disease) {
        disease.setId(id);
        return repository.save(disease);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
