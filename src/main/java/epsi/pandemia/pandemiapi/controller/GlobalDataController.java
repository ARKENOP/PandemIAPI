package epsi.pandemia.pandemiapi.controller;

import epsi.pandemia.pandemiapi.entity.GlobalData;
import epsi.pandemia.pandemiapi.repository.GlobalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/globaldata")
public class GlobalDataController {
    @Autowired
    private GlobalDataRepository repository;

    @GetMapping
    public List<GlobalData> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<GlobalData> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public GlobalData create(@RequestBody GlobalData globalData) {
        return repository.save(globalData);
    }

    @PutMapping("/{id}")
    public GlobalData update(@PathVariable Long id, @RequestBody GlobalData globalData) {
        globalData.setId(id);
        return repository.save(globalData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
