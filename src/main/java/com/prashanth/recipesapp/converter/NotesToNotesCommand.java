package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.NotesCommand;
import com.prashanth.recipesapp.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Override
    @Synchronized
    @Nullable
    public NotesCommand convert(Notes notes) {
        if(notes == null)
            return null;
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setNotes(notes.getNotes());
        return notesCommand;
    }
}
