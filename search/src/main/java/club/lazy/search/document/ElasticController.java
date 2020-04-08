package club.lazy.search.document;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/elastic")
public class ElasticController {

    private final IElasticService iElasticService;

    @Autowired
    public ElasticController(IElasticService iElasticService) {
        this.iElasticService = iElasticService;
    }

    @GetMapping("/init")
    public void init(){
        iElasticService.createIndex();
        List<DocBean> list =new ArrayList<>();
        list.add(new DocBean(1L,"XX0193","XX8064","xxxxxx",1));
        list.add(new DocBean(2L,"XX0210","XX7475","xxxxxxxxxx",1));
        list.add(new DocBean(3L,"XX0257","XX8097","xxxxxxxxxxxxxxxxxx",1));
        iElasticService.saveAll(list);
    }

    @GetMapping("/delete")
    public void delete(){
        iElasticService.deleteIndex("ems");
    }

    @GetMapping("/all")
    public Iterator<DocBean> all(){
        return iElasticService.findAll();
    }

    @PostMapping("/findByCode")
    public Page<DocBean> findByCode(){
        String code = "xxxxxx";
        return iElasticService.findByContent(code);
    }
}
