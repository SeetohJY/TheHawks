package com.example.the_hawks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {
    private static JSONArray mergeList = new JSONArray();
    private static JSONArray mergeList2 = new JSONArray();

    public static JSONArray getArray1() {
        return mergeList;
    }

    public static JSONArray getArray2() {
        return mergeList2;
    }

    private static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
    //Calculates the similarity (a number within 0 and 1) between two strings
    private static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
		    /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
		    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
		    return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    private static JSONArray getJSONArray(String url) throws IOException, JSONException{
        JSONArray response_body;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String reply = response.body().string();
        JSONObject jsonObj = new JSONObject(reply);
        JSONObject result = jsonObj.getJSONObject("result");
        //System.out.println(result.toString());
        Iterator x = result.keys();
        JSONArray jsonArray = new JSONArray();

        while (x.hasNext()) {
            String key = (String) x.next();
            jsonArray.put(result.get(key));

        }
        String newStr = jsonArray.get(2).toString();
        response_body = new JSONArray(newStr);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();


                }
            }
        });
        //Log.e("Printing-1:", response_body.toString());

        return response_body;
    }


    public static void initData() throws IOException, JSONException{
        JSONArray jsonArray_list1 = new JSONArray();
        JSONArray jsonArray_list2 = new JSONArray();
        //read in datasets
        jsonArray_list1 = getJSONArray("https://data.gov.sg/api/action/datastore_search?resource_id=8f6bba57-19fc-4f36-8dcf-c0bda382364d&limit=200");
        jsonArray_list2 = getJSONArray("https://data.gov.sg/api/action/datastore_search?resource_id=34f86c3e-a90c-4de9-a69f-afc86b7f31b6&limit=40000");
        JSONArray jsonArray_list2_reduced = new JSONArray();
//        Log.e("List1:", jsonArray_list1.toString());
//        Log.e("List2:", jsonArray_list2.toString());
//        jsonArray_list2_reduced = reduceList(jsonArray_list2);
        filter(jsonArray_list1,jsonArray_list2);
//        filter(jsonArray_list1, jsonArray_list2_reduced);
    }


    private static JSONArray reduceList(JSONArray jsonArray_list2) throws JSONException{
        JSONArray jsonObj_red = new JSONArray();
        for (int y=1; y<jsonArray_list2.length()-1; y++) {
            String s1 = jsonArray_list2.getJSONObject(y).getString("licence_number");
            String s2 = jsonArray_list2.getJSONObject(y+1).getString("licence_number");
            String s3 = jsonArray_list2.getJSONObject(y-1).getString("licence_number");
            if (similarity(s1,s3) >= 0.65 || similarity(s1,s3) >= 0.65) {
                if(y==1 && similarity(s1,s3) >= 0.7) {
                    jsonObj_red.put(jsonArray_list2.getJSONObject(y-1));
                }

                jsonObj_red.put(jsonArray_list2.getJSONObject(y));
                //System.out.println(jsonArray_list2.getJSONObject(y).getString("premises_address"));

                if(y==jsonArray_list2.length()-2 && similarity(s1,s3) >= 0.7) {
                    jsonObj_red.put(jsonArray_list2.getJSONObject(y+1));
                    //System.out.println(jsonArray_list2.getJSONObject(y+1).getString("premises_address"));
                }
            }
        } return jsonObj_red;
    }

    private static String cleanString_2(String addr) {
        String temp;
        String stall_pattern = "stall no .+";
        Pattern blk_pattern = Pattern.compile("blk .+ ");
        String s2 = addr.toLowerCase().replaceAll("[()]", "").replaceAll(stall_pattern, "");
        Matcher match = blk_pattern.matcher(s2);
        if (match.find()) {
            temp = match.group(0);
            //System.out.println(temp);
            s2 = s2.replaceAll(temp, "");
            //System.out.println(s2);
            s2 = temp + s2;
        }
        return s2;
    }

    private static String cleanString_1(String addr) {
        String [] addr_list = addr.toLowerCase().split(",");
        String s1 = addr_list[0] + "" + addr_list[1];

        return s1;
    }

    private static void filter(JSONArray jsonArray_list1, JSONArray jsonArray_list2) throws JSONException{
        boolean ismatch = false;
        //Log.e("Printing1:", jsonArray_list1.toString());
        //Log.e("Printing2:", jsonArray_list2.toString());
        for (int i=0; i<jsonArray_list1.length(); i++) {
            double similarity = 0;
            for (int j=0; j<jsonArray_list2.length(); j++) {
                String s1 = cleanString_1(jsonArray_list1.getJSONObject(i).getString("location_of_centre"));
                String s2 = cleanString_2(jsonArray_list2.getJSONObject(j).getString("premises_address"));
                String s3 = jsonArray_list1.getJSONObject(i).getString("name_of_centre");
                //Log.e("s1:", s1);
                //Log.e("s2:", s2);
//                Log.e("s3:", s3);
                if (similarity(s1, s2) >= 0.7 || similarity(s2, s3) >= 0.7) {

                    ismatch = true;
                    if(similarity(s1, s2)>similarity) {
                        similarity = similarity(s1, s2);
                    }
                    if(similarity(s2, s3)>similarity) {
                        similarity = similarity(s2, s3);
                    }
                }
            }
            if (ismatch) {
                for (int k=0; k<jsonArray_list2.length(); k++) {
                    String s1 = cleanString_1(jsonArray_list1.getJSONObject(i).getString("location_of_centre"));
                    String s2 = cleanString_2(jsonArray_list2.getJSONObject(k).getString("premises_address"));
                    String s3 = jsonArray_list1.getJSONObject(i).getString("name_of_centre");
                    if(similarity(s1, s2) == similarity) {
                        //System.out.println("new " + "Similarity" + similarity(s1, s2));
                        //System.out.println("new " + jsonArray_list1.getJSONObject(i).getString("location_of_centre") + jsonArray_list2.getJSONObject(k).getString("premises_address"));
                        mergeList.put(jsonArray_list1.getJSONObject(i));
                        mergeList2.put(jsonArray_list2.getJSONObject(k));
                    }
                    else if(similarity(s2, s3) == similarity){
                        //System.out.println("new " + "Similarity" + similarity(s2, s3));
                        //System.out.println("new " + jsonArray_list1.getJSONObject(i).getString("name_of_centre") + jsonArray_list2.getJSONObject(k).getString("premises_address"));
                        mergeList.put(jsonArray_list1.getJSONObject(i));
                        mergeList2.put(jsonArray_list2.getJSONObject(k));
                    }
                }
            }
            else {

            }
        }

    }
	  /*private static JSONArray test(String url) throws IOException, JSONException{
	  int offset=0;
	  boolean test = false;


	  JSONArray finalArray = new JSONArray();
	  do {
		  int count = 0;
		  JSONObject json_list = readJsonFromUrl(url+"&offset="+offset);
		  System.out.println(json_list.toString());
		  JSONObject result= json_list.getJSONObject("result");
		  Iterator x = result.keys();
		  JSONArray jsonArray = new JSONArray();

		  while (x.hasNext()){
		      String key = (String) x.next();
              jsonArray.put(result.get(key));
              if (count==0) {
            	  if(!key.equals("total")) {
            		  test = true;
            		  break;
            	  }
              }
              count++;
		  }
		  //test = jsonArray.get(0).toString();

		  if (!test) {

			  String newStr = jsonArray.get(2).toString();
			  JSONArray jsonObj = new JSONArray(newStr); //string to jsonobj

			  for (int i = 0; i < jsonObj.length(); i++) {
				    finalArray.put(jsonObj.getJSONObject(i));
				}
		  }
		  offset += 100;


	  }while(!test);

	  // System.out.println(finalArray.toString());
	  return finalArray;
  }

  private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
	   */


}
