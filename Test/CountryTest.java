import com.company.Country;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Troy on 9/25/16.
 */
public class CountryTest {

    @Test
    public void testChooseCountry () throws Exception {
        Country country = new Country();
        country.name = "test country";
        country.abbrev = "TC";
        country.chooseCountry();
        assertTrue(country.name.equalsIgnoreCase("test country"));
    }
}