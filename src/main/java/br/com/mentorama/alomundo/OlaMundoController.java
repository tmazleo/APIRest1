package br.com.mentorama.alomundo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/olamundo")
public class OlaMundoController {
    private final List<Message> messages;

    public OlaMundoController() {
        this.messages = new ArrayList<>();

    }

    @GetMapping
    public List<Message> findAll(@RequestParam(required = false) String message) {
        if (message != null){
            return messages.stream().filter(e -> e.getMessage().contains(message))
                    .collect(Collectors.toList());
        }
        return messages;

    }

    @GetMapping("/{id}")
    public Message findById(@PathVariable("id") Integer id) {
        return this.messages.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);   //fazendo filtro opcional por id
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody final Message message) {
        if(message.getId() == null) {
            message.setId(messages.size() + 1);
        }
        messages.add(message);
        return new ResponseEntity<>(message.getId(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody final Message message) {
        messages.stream()
                .filter(msg -> msg.getId().equals(message.getId()))
                .forEach(msg -> msg.setMessage(message.getMessage()));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        messages.removeIf(msg -> msg.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
