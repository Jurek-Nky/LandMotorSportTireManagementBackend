package com.dev.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotes() {
        List<Note> notes = noteRepository.findByOrderByIdAsc();
        if (notes.isEmpty()) {
            throw new IllegalStateException("Notes empty");
        }
        return notes;
    }

    @Transactional
    public Note changeStatusById(Long id, boolean done) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty()) {
            throw new IllegalStateException("Note not found");
        }
        note.get().done = done;
        return note.get();
    }

    public void deleteNoteByID(Long id) {
        if (noteRepository.findById(id).isEmpty()) {
            throw new IllegalStateException("Note not found.");
        }
        noteRepository.deleteById(id);
    }

    public Note insertNewNote(Note noteDTO) {
        Note note = new Note(noteDTO.getMessage());
        note.setDone(false);
        return noteRepository.save(note);
    }
}
