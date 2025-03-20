package epsi.pandemia.pandemiapi.controller;

import epsi.pandemia.pandemiapi.entity.Country;
import epsi.pandemia.pandemiapi.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryRepository repository;

    @GetMapping
    public List<Country> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Country> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Country create(@RequestBody Country country) {
        return repository.save(country);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable Long id, @RequestBody Country country) {
        country.setId(id);
        return repository.save(country);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
