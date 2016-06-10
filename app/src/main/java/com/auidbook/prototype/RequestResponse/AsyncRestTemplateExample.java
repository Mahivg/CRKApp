package com.auidbook.prototype.RequestResponse;

/**
 * Created by mgundappan on 07-06-2016.
 */
/*
import java.util.concurrent.ExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
*/

public class AsyncRestTemplateExample {

/*
    public static void main(String[] args) {

        String url = "http://pretechsol.com";
        AsyncRestTemplate asyncRestTemplate = null;
        HttpMethod method = null;
        try {

            // Create AsyncRestTemplate object
            asyncRestTemplate = new AsyncRestTemplate();

            // Define http method
            method = HttpMethod.GET;

            // Define response type
            Class<String> responseType = String.class;

            // Define headers

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);

            HttpEntity<String> requestEntity = new HttpEntity<String>("params",
                    headers);
            ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate
                    .exchange(url, method, requestEntity, responseType);

            ResponseEntity<String> entity = future.get();
            System.out.println(entity.getBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
*/

}
