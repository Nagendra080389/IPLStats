package com.deloitte;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by nagesingh on 4/29/2017.
 */
@RepositoryRestResource
public interface DatabaseDao extends MongoRepository<IplStat, String>{

    List<IplStat> findIplStatsBySeason(String season);

    List<IplStat> findIplStatsBycity(String city);

    List<IplStat> findFirstBySeason(String season);

    List<IplStat> findIplStatsBy_id(String _id);

    List<IplStat> findIplStatsBy_idIsBefore(String _id);

}
