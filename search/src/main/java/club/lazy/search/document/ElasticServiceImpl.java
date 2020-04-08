package club.lazy.search.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ElasticServiceImpl implements IElasticService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    private final ElasticRepository elasticRepository;

    private Pageable pageable = PageRequest.of(0, 10);

    @Autowired
    public ElasticServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate, ElasticRepository elasticRepository) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.elasticRepository = elasticRepository;
    }

    @Override
    public void createIndex() {
        elasticsearchRestTemplate.createIndex(DocBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchRestTemplate.deleteIndex(index);
    }

    @Override
    public void save(DocBean docBean) {
        elasticRepository.save(docBean);
    }

    @Override
    public void saveAll(List<DocBean> list) {
        elasticRepository.saveAll(list);
    }

    @Override
    public Iterator<DocBean> findAll() {
        return elasticRepository.findAll().iterator();
    }

    @Override
    public Page<DocBean> findByContent(String content) {
        return elasticRepository.findByContent(content, pageable);
    }

    @Override
    public Page<DocBean> findByFirstCode(String firstCode) {
        return elasticRepository.findByFirstCode(firstCode, pageable);
    }

    @Override
    public Page<DocBean> findBySecondCode(String secondCode) {
        return elasticRepository.findBySecondCode(secondCode, pageable);
    }

    @Override
    public Page<DocBean> query(String key) {
        return elasticRepository.findByContent(key, pageable);
    }
}
