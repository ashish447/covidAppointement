package com.demo.engine;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HeadRequestService {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	@Value("${covid.url}")
	String covidURL;
	@Autowired
	RestTemplate restTemplate;

	@Async("asyncExecutor")
	public CompletableFuture<String> doHeadRequest() {

		SimpleDateFormat fo = new SimpleDateFormat("MM-dd-yyyy");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate today = LocalDate.now().plusDays(1);
		String formatDateTime = today.format(formatter);

		String uri = covidURL + formatDateTime;
		System.out.println(formatDateTime);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		JSONObject jps = new JSONObject(result);

		JSONArray centryArarya = jps.getJSONArray("centers");

		for (int i = 0; i < centryArarya.length(); i++) {
			JSONObject cetobj = centryArarya.getJSONObject(i);

			JSONArray sessionsaRRAY = cetobj.getJSONArray("sessions");

			JSONObject sesionObj = (JSONObject) sessionsaRRAY.get(0);

			if (sesionObj.getInt("available_capacity") > 0) {

				System.out.println("---------------" + sesionObj.getInt("available_capacity"));

				System.out.println("---------------" + sesionObj.getInt("min_age_limit"));
				System.out.println(
						"---------------" + cetobj.getString("name") + " ----------pincode " + cetobj.get("pincode"));

			}
		}
		return CompletableFuture.completedFuture("sdsd");
	}

}