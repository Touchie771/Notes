package me.touchie771.notes;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class Notes {

    @Tool(name = "add-note", description = "Add a note to the list of notes.")
    public void addNote(String note) {}
}