import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

//@ExtendWith(MockitoExtension.class)
class TestDemoTest {
	
	private TestDemo testDemo;
	public TestDemo mockDemo;
	
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
		mockDemo = spy(testDemo);
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, 
			boolean expectException) throws Exception  
	{
		try 
		{
			if(!expectException) 
			{
				assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
				System.out.println(" - Right Parameters");
			} else 
			{
				assertThatThrownBy(()->testDemo.addPositive(a, b)).
				isInstanceOf(IllegalArgumentException.class).hasNoCause();
				//throw new IllegalArgumentException(Exception);
			}

		} catch (IllegalArgumentException ex) {
		System.out.println(ex.getMessage());
		
		}
	}
	
	public static Stream<Arguments> argumentsForAddPositive() 
	{
		return Stream.of(arguments(2, 4, 6, false),
				arguments(2, 4, 6, false),
				arguments(8, 5, 13, false), 
				arguments(30, 0, 30, false), 
				arguments(0, 0, 0, false),
				arguments(-5, 4, -2, false));
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect()
	{
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
}
