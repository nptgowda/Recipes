package com.prashanth.recipesapp.converter;

import com.prashanth.recipesapp.command.NotesCommand;
import com.prashanth.recipesapp.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    public static final String NOTES = "notes";
    public static final Long LONG_VALUE = new Long(1L);
    private NotesToNotesCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNull(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        Notes notes = new Notes();
        notes.setId(LONG_VALUE);
        notes.setNotes(NOTES);

        NotesCommand command= converter.convert(notes);
        assertEquals(LONG_VALUE,command.getId());
        assertEquals(NOTES, command.getNotes());
    }
}