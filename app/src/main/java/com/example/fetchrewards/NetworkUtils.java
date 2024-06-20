public class NetworkUtils {
    private static final String BASE_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

    public static List<Item> fetchData() {
        List<Item> itemList = new ArrayList<>();

        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            Gson gson = new Gson();
            Item[] items = gson.fromJson(response.toString(), Item[].class);
            itemList = Arrays.asList(items);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemList;
    }
}