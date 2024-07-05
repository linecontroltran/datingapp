package datingapp.datingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import datingapp.datingapp.entity.DataEntity;
import datingapp.datingapp.repository.DataRepository;

import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {

    private final DataRepository dataRepository;

    @Autowired
    public DataServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public String fetchData() {
        Optional<DataEntity> dataEntity = dataRepository.findById(1L); // Example ID
        return dataEntity.map(DataEntity::getMessage).orElse("No data found");
    }

    @Override
    public String saveData(String message) {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setMessage(message);
        dataRepository.save(dataEntity);
        return "Data saved successfully";
    }
}
