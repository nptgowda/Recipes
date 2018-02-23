package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.NotesCommand;
import com.prashanth.recipesapp.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final String NOTES = "notes";
    public static final Long LONG_VALUE = new Long(1L);
    private NotesCommandToNotes converter;
    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        NotesCommand command = new NotesCommand();
        command.setId(LONG_VALUE);
        command.setNotes(NOTES);

        Notes notes= converter.convert(command);
        assertEquals(LONG_VALUE,notes.getId());
        assertEquals(NOTES, notes.getNotes());
    }
}