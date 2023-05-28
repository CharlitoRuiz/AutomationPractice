package DataProviders;

import POJO.OrderData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class OrderProvider {
    @DataProvider(name="getProductCategory")
    private Object[][] getProductCategory() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/data/order.json"));
        JsonElement dataset = jsonData.getAsJsonObject().get("orders");

        List<OrderData> testData = new Gson().fromJson(dataset, new TypeToken<List<OrderData>>() {}.getType());

        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for(Object [] each: returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}
