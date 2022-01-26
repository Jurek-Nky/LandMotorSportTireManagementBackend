package com.dev.note;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private NoteRepository noteRepository;
    private RaceRepository raceRepository;

    @Autowired
    NoteService(NoteRepository noteRepository, RaceRepository raceRepository) {
        this.noteRepository = noteRepository;
        this.raceRepository = raceRepository;
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
        Optional<Race> race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
        if (race.isEmpty()) {
            throw new IllegalStateException("No race found.");
        }
        Note note = new Note(noteDTO.getMessage());
        note.setRace(race.get());
        note.setDone(false);
        return noteRepository.save(note);
    }
}
