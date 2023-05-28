package DataProviders;

import POJO.AddressData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class AddressProvider {
    @DataProvider(name="getAddressInfo")
    private Object[][] getAddressInfo() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/data/address.json"));
        JsonElement dataset = jsonData.getAsJsonObject().get("billingAddress");

        List<AddressData> testData = new Gson().fromJson(dataset, new TypeToken<List<AddressData>>() {}.getType());

        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for(Object [] each: returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}
