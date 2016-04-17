package org.kedzo.dreamy.commons;

import org.kedzo.dreamy.models.DreamType;
import org.kedzo.dreamy.services.impl.DreamTypeRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UtilDreamType {

    @Resource(name = "dreamTypeRepository")
    private DreamTypeRepository dreamTypeRepository;

    public static void main(String[] args) {
        UtilDreamType utilDreamType = new ClassPathXmlApplicationContext("spring-config.xml").getBean(UtilDreamType.class);
        utilDreamType.generate();
    }

    private void generate() {
        addDreamTypeDb("мечта", 5);
        addDreamTypeDb("кошмар", -5);
        addDreamTypeDb("осознаный", 0);
        addDreamTypeDb("вещий", 0);
        addDreamTypeDb("эротический", 3);
        addDreamTypeDb("повторяющийся", -1);
        addDreamTypeDb("творчкский", 1);
        addDreamTypeDb("летаргичкский", -1);
    }

    private void addDreamTypeDb(String name, int weight) {
        DreamType dreamType = new DreamType();
        dreamType.setName(name);
        dreamType.setWeight(weight);
        dreamTypeRepository.save(dreamType);
    }
}
