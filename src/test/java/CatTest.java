import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline mockFeline;

    private Cat cat;

    @Before
    public void setUp() {
        //Мок в коструктор
        cat = new Cat(mockFeline);
    }

    @Test
    public void testGetSound() {
        String sound = cat.getSound();

        // Проверяем, что звук равен "Мяу"
        assertEquals("Мяу", sound);
    }

    @Test
    public void testGetFood() throws Exception {
        //список еды
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        when(mockFeline.eatMeat()).thenReturn(expectedFood);

        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood);

        //метод вызван 1 раз
        verify(mockFeline, times(1)).eatMeat();
    }

    @Test(expected = Exception.class)
    // Тест на исключение
    public void testGetFoodThrowsException() throws Exception {

        when(mockFeline.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));

        cat.getFood();
    }

    @Test
    public void testGetFoodCallsEatMeat() throws Exception {

        when(mockFeline.eatMeat()).thenReturn(List.of());

        cat.getFood();

        verify(mockFeline, times(1)).eatMeat();

        verifyNoMoreInteractions(mockFeline);
    }
}