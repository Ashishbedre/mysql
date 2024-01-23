package mysqlDemo.example.mysqlDemo.Service;

import mysqlDemo.example.mysqlDemo.Entities.AppData;
import mysqlDemo.example.mysqlDemo.repo.MySQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@EnableScheduling
public class DataService {

    private final String apiUrl = "http://localhost:8081/v1/versionControl/checkForUpdate";


    @Autowired
    private MySQLRepository mySQLRepository;


//    @Scheduled(fixedDelay = 900000)
    public void fetchDataAndSaveToMysql() {
        WebClient webClient = WebClient.create();
        List<AppData> apiDataList = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToFlux(AppData.class)
                .collectList()
                .block();

        if (apiDataList != null) {
            mySQLRepository.saveAll(apiDataList);
        }
    }

    public List<AppData> fetchDataFromMysql(){
        return  mySQLRepository.findAll();
    }

}
