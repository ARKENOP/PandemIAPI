package epsi.pandemia.pandemiapi.controller;

import epsi.pandemia.pandemiapi.entity.Region;
import epsi.pandemia.pandemiapi.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regions")
public class RegionController {
    @Autowired
    private RegionRepository repository;

    @GetMapping
    public List<Region> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Region> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Region create(@RequestBody Region region) {
        return repository.save(region);
    }

    @PutMapping("/{id}")
    public Region update(@PathVariable Long id, @RequestBody Region region) {
        region.setId(id);
        return repository.save(region);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
