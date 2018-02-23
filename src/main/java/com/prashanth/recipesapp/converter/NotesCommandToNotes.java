package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.NotesCommand;
import com.prashanth.recipesapp.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class NotesCommandToNotes implements Converter<NotesCommand,Notes> {
    @Override
    @Synchronized
    @Nullable
    public Notes convert(NotesCommand notesCommand) {
        if(notesCommand == null)
           return null;
        Notes notes = new Notes();
        notes.setId(notesCommand.getId());
        notes.setNotes(notesCommand.getNotes());
        return notes;
    }
}
