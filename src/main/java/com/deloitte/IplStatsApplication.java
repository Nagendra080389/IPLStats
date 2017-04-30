package com.deloitte;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.mongodb.util.JSON;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class IplStatsApplication {

	private final IplDoMapper iplDoMapper;

	private final DatabaseDao databaseDao;

	@Autowired
	public IplStatsApplication(IplDoMapper iplDoMapper, DatabaseDao databaseDao) {
		this.iplDoMapper = iplDoMapper;
		this.databaseDao = databaseDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(IplStatsApplication.class, args);
	}

	CommandLineRunner commandLineRunner(){
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

				String matchDetails = "C:\\Users\\nagesingh\\Documents\\IPLDATASET\\deliveries.csv";
				String iplStatsDetails = "C:\\Users\\nagesingh\\Documents\\IPLDATASET\\matches.csv";

				CSVReader iplStatsReader = null;
				CSVReader matchDetailsReader = new CSVReader(new FileReader(matchDetails), ',', '"', 1);
				List<String[]> allMatchdetails = matchDetailsReader.readAll();
				try {
					iplStatsReader = new CSVReader(new FileReader(iplStatsDetails), ',', '"', 1);
					String[] line;
					while ((line = iplStatsReader.readNext()) != null) {

						IplStat iplStat = iplDoMapper.setAttributes(line, allMatchdetails);
						databaseDao.save(iplStat);


					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};


	}
}
