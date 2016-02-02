package scapi.common.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component("httpsUtil")
public class HttpsUtil {

	/************************************************* get ********************************************************/
	@SuppressWarnings("rawtypes")
	public Object get(String fullApi, Class c) {
		Object cbResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
				Integer responseCode = this.getResponseCode(fullApi);
				if(responseCode == 200){
					String content = this.printContent(new URL(fullApi).openConnection().getInputStream());
					cbResult = objectMapper.readValue(content, c.getClass());
				}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cbResult;
	}

	public Integer getResponseCode(String fullApi) throws IOException{
		URL url = new URL(fullApi);
		if(fullApi.contains("https://")){
			HttpsURLConnection con = (HttpsURLConnection) url
					.openConnection();
			return con.getResponseCode();
		}else{
			HttpURLConnection con = (HttpURLConnection) url
					.openConnection();
			return con.getResponseCode();
		}
		
	}
	public String printContent(InputStream inputStream) throws IOException{
		String cbString = "";

		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream, "UTF-8"));

		String input;
		while ((input = br.readLine()) != null) {
			log.info(input);
			cbString += input;
		}
		br.close();

		return cbString;
	}

	/************************************************* post ********************************************************/
	@SuppressWarnings("rawtypes")
	public Object post(String fullApi, String param, Class c) {
		Object cbResult = null;
		try {
			String url = fullApi;
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add reuqest header

			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			log.info("\nSending 'POST' request to URL : {}", url);
			log.info("Post parameters : {}", param);
			log.info("Response Code : {}", responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			log.info("response.toString() : {}", response.toString());
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			cbResult = objectMapper
					.readValue(response.toString(), c.getClass());
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cbResult;
	}
}
