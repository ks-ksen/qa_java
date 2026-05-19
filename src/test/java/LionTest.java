import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.lang.reflect.Field;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline mockFeline;

    private Lion lion;

    @Before
    public void setUp() throws Exception {
        lion = new Lion("Самец", mockFeline);

        Field felineField = Lion.class.getDeclaredField("feline");

        felineField.setAccessible(true);

        felineField.set(lion, mockFeline);
    }

    @Test
    public void testGetKittensWithMock() throws Exception {
        when(mockFeline.getKittens()).thenReturn(3);

        int kittens = lion.getKittens();

        assertEquals("Мок должен вернуть 3 котенка", 3, kittens);

        verify(mockFeline, times(1)).getKittens();
    }

    @Test
    public void testGetFoodWithMock() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        when(mockFeline.getFood("Хищник")).thenReturn(expectedFood);

        List<String> actualFood = lion.getFood();

        assertEquals("Лев должен вернуть еду из мока", expectedFood, actualFood);

        verify(mockFeline, times(1)).getFood("Хищник");
    }


    @Test
    public void testMaleLionHasMane() throws Exception {
        Lion maleLion = new Lion("Самец", mockFeline);
        assertTrue("Самец должен иметь гриву", maleLion.doesHaveMane());
    }

    @Test
    public void testFemaleLionDoesNotHaveMane() throws Exception {
        Lion femaleLion = new Lion("Самка", mockFeline);
        assertFalse("Самка не должна иметь гриву", femaleLion.doesHaveMane());
    }
}