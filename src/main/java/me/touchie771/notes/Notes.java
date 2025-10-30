package me.touchie771.notes;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    @Tool(name = "save-to-file", description = "Save the list of notes to a file.")
    public String saveToFile(String path, boolean overwrite) {
        Path p = Paths.get(path);
        try {
            if (p.getParent() != null) {
                Files.createDirectories(p.getParent());
            }
            if (Files.exists(p) && !overwrite) {
                return "File already exists and overwrite=false: " + path;
            }
            if (overwrite) {
                Files.write(p, notes, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                Files.write(p, notes, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            }
            return "Successfully saved notes to file: " + path;
        } catch (IOException e) {
            return "Failed to save notes to file: " + path;
        }
    }

    @Tool(name = "load-from-file", description = "Load the list of notes from a file.")
    public String loadFromFile(String path) {
        Path p = Paths.get(path);
        try {
            List<String> lines = Files.exists(p)
                    ? Files.readAllLines(p, StandardCharsets.UTF_8)
                    : List.of();
            notes.clear();
            notes.addAll(lines);
            return "Successfully loaded notes from file: " + path;
        } catch (IOException e) {
            return "Failed to load notes from file: " + path;
        }
    }
}