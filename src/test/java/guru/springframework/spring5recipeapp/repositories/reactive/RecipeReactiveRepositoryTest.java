package guru.springframework.spring5recipeapp.repositories.reactive;

import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    public static final String FOO = "Foo";

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() throws Exception {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testRecipeSave() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDescription(FOO);

        recipeReactiveRepository.save(recipe).block();

        Long count = recipeReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDescription(FOO);

        recipeReactiveRepository.save(recipe).then().block();

        Recipe fetchedRecipe = recipeReactiveRepository.findByDescription(FOO).block();

        assertNotNull(fetchedRecipe.getId());
        assertEquals(FOO, fetchedRecipe.getDescription());
    }
}