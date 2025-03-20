package epsi.pandemia.pandemiapi.controller;

import epsi.pandemia.pandemiapi.entity.Continent;
import epsi.pandemia.pandemiapi.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/continents")
public class ContinentController {
    @Autowired
    private ContinentRepository repository;

    @GetMapping
    public List<Continent> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Continent> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Continent create(@RequestBody Continent continent) {
        return repository.save(continent);
    }

    @PutMapping("/{id}")
    public Continent update(@PathVariable Long id, @RequestBody Continent continent) {
        continent.setId(id);
        return repository.save(continent);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
