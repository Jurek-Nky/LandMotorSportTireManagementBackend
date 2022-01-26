package com.dev.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    private NoteService noteService;

    @Autowired
    NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/get/all")
    public List<Note> getAllNotes() {
        return noteService.getNotes();
    }

    @PostMapping("/new")
    public Note addNewNote(@RequestBody() Note note) {
        return noteService.insertNewNote(note);
    }

    @PutMapping("/update/{id}/status")
    public Note changeNoteStatus(@PathVariable(name = "id") Long id,
                                 @RequestParam(name = "s") boolean done) {
        return noteService.changeStatusById(id, done);
    }

    @DeleteMapping("delete/{id}")
    public void deleteNoteById(@PathVariable(name = "id") Long id) {
        noteService.deleteNoteByID(id);
    }
}
