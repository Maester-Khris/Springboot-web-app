package nk.projects.webapp.seeders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import nk.projects.webapp.models.Task;
import nk.projects.webapp.repository.TaskRepository;

@Configuration
@RequiredArgsConstructor
public class Taskseeder implements ApplicationRunner {
    private final TaskRepository taskrepo;
    private static final Logger logger = LoggerFactory.getLogger(Taskseeder.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(args.getOptionValues("doSeed")!=null){
            task_seeder();
        }else{
            logger.info("Not asked to seed data");
        }
    }

    private void task_seeder(){
        Map<String,Boolean> taskdefinitions = new HashMap<String,Boolean>(){{
            put("Learn Jpa and Postgre",Boolean.TRUE);
            put("Learn Spring cloudstream",Boolean.FALSE);
            put("APM with Datadog",Boolean.FALSE);
        }};
        List<Map.Entry<String, Boolean>> taskEntries = new ArrayList<>(taskdefinitions.entrySet());
        List<Task> initialtask = new ArrayList<Task>();

        for(int i=0; i<taskEntries.size(); i++){ 
            Map.Entry<String,Boolean> taskEntry = taskEntries.get(i);
            initialtask.add(
                Task.builder()
                .tasktitle(taskEntry.getKey())
                .taskfinish(taskEntry.getValue())
                .build()
            );
        }
        taskrepo.saveAll(initialtask);
        logger.info("Successful dataseeding");
    }
    
}
