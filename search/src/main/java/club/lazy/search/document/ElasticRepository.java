package club.lazy.search.document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ElasticRepository extends ElasticsearchRepository<DocBean, Long> {

    Page<DocBean> findByContent(String content, Pageable pageable);

    Page<DocBean> findByFirstCode(String firstCode, Pageable pageable);

    Page<DocBean> findBySecondCode(String secondCode, Pageable pageable);
}
