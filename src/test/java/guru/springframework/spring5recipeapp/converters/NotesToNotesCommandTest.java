package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    public static final String ID_VALUE = new String("1");
    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }

}