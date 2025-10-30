package me.touchie771.notes;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Notes {

    private List<String> notes = new ArrayList<>();

    @Tool(name = "add-note", description = "Add a note to the list of notes.")
    public void addNote(String note) {
        notes.add(note);
    }

    @Tool(name = "add-note-bulk", description = "Add multiple notes to the list of notes.")
    public void addNoteBulk(String[] notes) {
        this.notes.addAll(List.of(notes));
    }

    @Tool(name = "remove-note", description = "Remove a note from the list of notes.")
    public void removeNote(String note) {
        notes.remove(note);
    }

    @Tool(name = "remove-note-bulk", description = "Remove multiple notes from the list of notes.")
    public void removeNoteBulk(String[] notes) {
        this.notes.removeAll(List.of(notes));
    }

    @Tool(name = "clear-notes", description = "Clear the list of notes.")
    public void clearNotes() {
        notes.clear();
    }

    @Tool(name = "get-notes", description = "Get the list of notes.")
    public List<String> getNotes() {
        return notes;
    }
}