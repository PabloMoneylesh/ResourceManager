package com.hid.resourceManager.client;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * client example for resource manager application
 */
public class ResourceClient {

    private static final String authenticationURL = "http://localhost:8080/login";
    private static final String downloadURL = "http://localhost:8080/getResource";

    /**
     * Apache HttpClient for request sending
     */
    private CloseableHttpClient httpclient;

    public ResourceClient() {
        this.httpclient = HttpClients.createDefault();
    }

    /**
     * method downloads the requested resource and prints content to console
     *
     * @param resKey
     * @param resClass
     * @throws Exception
     */
    public void downloadResource(String resKey, String resClass) throws Exception {

        Header[] cookies = authenticate();

        URIBuilder builder = new URIBuilder(downloadURL)
                .addParameter("key", resKey)
                .addParameter("class", resClass);
        HttpGet httpGet = new HttpGet(builder.build());
        httpGet.setHeaders(cookies);

        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            InputStream is = response.getEntity().getContent();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            String line;
            System.out.println("resource body:");
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            rd.close();

        } finally {
            response.close();
        }

    }


    /**
     * authenticates in ResourceManagerApplication by providing user credentials
     * @return cookies content with sessionId
     * @throws IOException
     */
    private Header[] authenticate() throws IOException {
        HttpPost httpPost = new HttpPost(authenticationURL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", "user"));
        nvps.add(new BasicNameValuePair("password", "user"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        Header[] cookie = response2.getHeaders("Set-Cookie");

        response2.close();
        return cookie;


    }


    public static void main(String[] args) {
        ResourceClient client = new ResourceClient();
        try {
            client.downloadResource("PN0001", "ARTWORK");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
