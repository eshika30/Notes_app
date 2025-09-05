package com.eshi.notes.controller;

import com.eshi.notes.model.notes;
import com.eshi.notes.repository.NotesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NotesRepository notesRepository;

    public NotesController(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    // Get all notes
    @GetMapping
    public List<notes> getAllNotes() {
        return notesRepository.findAll();
    }

    // Get note by id
    @GetMapping("/{id}")
    public ResponseEntity<notes> getNoteById(@PathVariable Long id) {
        return notesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create note
    @PostMapping
    public notes createNote(@RequestBody notes note) {
        return notesRepository.save(note);
    }

    // Update note
    @PutMapping("/{id}")
    public ResponseEntity<notes> updateNote(@PathVariable Long id, @RequestBody notes noteDetails) {
        return notesRepository.findById(id)
                .map(note -> {
                    note.setTitle(noteDetails.getTitle());
                    note.setContent(noteDetails.getContent());
                    return ResponseEntity.ok(notesRepository.save(note));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        return notesRepository.findById(id)
                .map(note -> {
                    notesRepository.delete(note);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
